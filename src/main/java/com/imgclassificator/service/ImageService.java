package com.imgclassificator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imgclassificator.entity.ImageEntity;
import com.imgclassificator.entity.LabelEntity;
import com.imgclassificator.repository.ImageRepository;

import com.imgclassificator.repository.LabelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ImageService{
        private ImageRepository imageRepository;
        private LabelRepository labelRepository;
        private ImageSubmit imageSubmit;
        private final ModelMapper modelMapper;

        public ImageService(ModelMapper modelMapper){
                this.modelMapper = modelMapper;
        }

        @Autowired
        public ImageService(ImageRepository imageRepository, LabelRepository labelRepository, ImageSubmit imageSubmit,ModelMapper modelMapper) {
                this.imageRepository = imageRepository;
                this.labelRepository = labelRepository;
                this.imageSubmit = imageSubmit;
                this.modelMapper=modelMapper;

        }
       // ObjectMapper-преобразуване от един формат в друг
      //ObjectNode представя JSON обект като дървовидна структура от възли
        private String generateJsonResponse(List<LabelEntity> labels) {
                ObjectMapper objectMapper = new ObjectMapper();
                ImageEntity image = new ImageEntity();
                try {

                        ObjectNode responseJson = objectMapper.createObjectNode();
                        responseJson.put("url",image.getUrl());
                        responseJson.put("analysedAt", String.valueOf(image.getAnalysed_at()));




                        ArrayNode tagsJson = objectMapper.createArrayNode();
                        ObjectNode tagJson = objectMapper.createObjectNode();
                        for (LabelEntity label : labels) {

                                tagJson.put("label", label.getName());
                                /*{
                                        "label": "sunset"
                                },
*/
                        }

                        responseJson.set("tags", tagsJson);
//problem?
                       // responseJson.put("width", ImageEntity.getWidth());
                       // responseJson.put("height", ImageEntity.getHeight());

                        return objectMapper.writeValueAsString(responseJson);
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                }

                return "Error generating JSON response";
        }




        public String saveInDb(ImageEntity imageEntity) {
                String imageUrl = imageEntity.getUrl();
                ImageEntity existingImage = imageRepository.findByUrl(imageUrl);
//When a user submits an image that has been submitted before
                if (existingImage != null) {
                        List<LabelEntity> labels = existingImage.getLabels();
                        String jsonResponse = generateJsonResponse(labels);
                        return jsonResponse;

                } else {
                        // never-seen-before image is submitted
                        String jsonResponse = ImageSubmit.categorizeImage(imageUrl);
                        String response = "Error";
                        List<LabelEntity> labels = takeLabelFromJson(jsonResponse);
                        if (!labels.isEmpty()) {
                                imageEntity.setLabels(labels);
                                imageRepository.save(imageEntity);
                                response = jsonResponse;
                        } else {
                                response = "Error";
                        }

                        return response;
                }
        }
//взима етикети от JSON формата и записва в обекти от тип LabelEntity
        public List<LabelEntity> takeLabelFromJson(String jsonResponse){
                List<LabelEntity> labels = new ArrayList<>();
                try {
                        JsonNode responseJson = JsonUtils.readTree(jsonResponse);

                        if (responseJson.has("result")) {
                                JsonNode resultJson = responseJson.get("result");
                                JsonNode tagsJson = resultJson.get("tags");

                                if (tagsJson != null) {
                                        for (JsonNode tagNode : tagsJson) {
                                                JsonNode tagJson = tagNode.get("tag");

                                                if (tagJson != null) {
                                                        String labelName = tagJson.get("en").asText();

                                                        LabelEntity label = new LabelEntity();
                                                        label.setName(labelName);
                                                        labels.add(label);
                                                }
                                        }
                                }
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return labels;
        }
      /*  {
                "result": {
                "tags": [
                {
                        "tag": {
                        "en": "cat"
                }*/

        public List<ImageEntity> getAllImages(){
                return imageRepository.findAll();
        }


        public class ImageNotFoundException extends RuntimeException {
                public ImageNotFoundException(String message) {
                        super(message);
                }
        }

        public ImageEntity findImageById(long id) {
                ImageEntity image = imageRepository.findById(id);
                if (image == null) {
                        throw new ImageNotFoundException("Image not found with ID: " + id);
                }
                return modelMapper.map(image, ImageEntity.class);
        }
        /*I1
        Labels: ["sunset", "sea", "landscape"]
        I2
        Labels: ["mountain", "sky", "nature"]
*/
        public List<ImageEntity> getImagesByLabels(List<String> labels) {
                return labels.stream()
                        .map(labelRepository::findByName)
                        .flatMap(List::stream)
                        .collect(Collectors.toList())
                        .stream()
                        .map(LabelEntity::getImages)
                        .flatMap(List::stream)
                        .distinct()
                        .collect(Collectors.toList());
        }
        //return all images with same label
//        public List<ImageEntity> getImagesByLabels(List<String> labels) {
//                return labels.stream()
//                        .map(labelRepository::findByName)
//                        .flatMap(Collection::stream)
//                        .collect(Collectors.toList());
//        }

       /* public String uploadImage(ImageEntity image) {
                String imageUrl = image.getUrl();
                String jsonResponse = imageSubmit.categorizeImage(imageUrl);

                List<LabelEntity> labels = extractLabelsFromJson(jsonResponse);
                if (!labels.isEmpty()) {
                        image.setLabels(labels);
                        imageRepository.save(image);
                        return jsonResponse;
                } else {
                        //String errorMessage = extractErrorMessageFromJson(jsonResponse);
                        return "Error ";
                }
        }*/


}


//@Component
/*
@Service
public class ImageService {
     private final ImageRepository imageRepository;

    private static final String IMAGGA_API_KEY = "acc_36c475c133aa761";
    private static final String IMAGGA_API_SECRET = "e774e4e796859b130206d49b238a2b34";
    private static final String IMAGGA_TAGS_ENDPOINT = "https://api.imagga.com/v2/tags";

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    public static Map<String, Object> categorizeImage(@RequestParam(required = false) String imageUrl) {


        Map<String, Object> response = new HashMap<>();

        try {
            // Prepare the request URL
            // @Value("${server.endpoint}")
            String url = IMAGGA_TAGS_ENDPOINT  + "?image_url=" +"https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg" ;

            // Set the Imagga authentication credentials
            String credentialsToEncode = IMAGGA_API_KEY + ":" + IMAGGA_API_SECRET;
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

            // Send the request to Imagga
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + basicAuth);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> imaggaResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Process the response from Imagga
            if (imaggaResponse.getStatusCode() == HttpStatus.OK) {
                response.put("success", true);
                response.put("tags", imaggaResponse.getBody());

//                String json = imaggaResponse.getBody();
//                String insertQuery = "INSERT INTO image (url,analysed_at,tags,width,height) VALUES (?,new Timestamp(System.currentTimeMillis()),?,640,480)";
//                jdbcTemplate.update(insertQuery, json);
            } else {
                response.put("success", false);
                response.put("error", "Image categorization failed");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
*/





//    @Autowired
//    public ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }

//    public ImageEntity saveImage(ImageEntity image) {
//        return imageRepository.save(image);
//    }

//    public Optional<Image> getImageById(long id) {
//        return imageRepository.findById(id);
//    }
//
//    public List<Image> getAllImages() {
//        return imageRepository.findAll();
//    }
////List<Tags> changed with
//    public List<Image> getImagesByTags(List<String> tags) {
//        return imageRepository.findByTags(tags);
//    }
//
//    public void deleteImage(long id) {
//        imageRepository.deleteById( id);
//    }
//}


