
package com.imgclassificator.service;

import com.imgclassificator.entity.ImageEntity;
import com.imgclassificator.entity.LabelEntity;
import com.imgclassificator.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.imgclassificator.repository.ImageRepository;
import com.imgclassificator.service.ImageService;
import java.nio.charset.StandardCharsets;
import java.util.*;



@SpringBootApplication
@RestController

public class ImageSubmit {

    private static final String IMAGGA_API_KEY = "acc_36c475c133aa761";
    private static final String IMAGGA_API_SECRET = "e774e4e796859b130206d49b238a2b34";
    private static final String IMAGGA_TAGS_ENDPOINT = "https://api.imagga.com/v2/tags";

   private static final String DB_URL="jdbc:postgresql://localhost:5432/ics ";
    private static final String DB_USERNAME ="postgres";
    private static final String DB_PASSWORD="Anreb";
    private static final String url="https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";

    public static void main(String[] args) {
        SpringApplication.run(ImageSubmit .class, args);
    }

    //@Autowired
    public JdbcTemplate jdbcTemplate;



    //@GetMapping("/images")
    public static String categorizeImage(@RequestParam(required = false) String imageUrl) {
        //Map<String, Object> response = new HashMap<>();

        try {
            // Prepare the request URL
           // @Value("${server.endpoint}")
            String url = IMAGGA_TAGS_ENDPOINT  + "?image_url=" + imageUrl ;

            // Set the Imagga authentication credentials
            String credentialsToEncode = IMAGGA_API_KEY + ":" + IMAGGA_API_SECRET;
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

            // Send the request to Imagga
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + basicAuth);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();

        }

    }

//    private Long saveImageToDatabase(String imageUrl) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO image (url, analysedAt) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, imageUrl);
//            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
//            return ps;
//        }, keyHolder);
//
//        return keyHolder.getKey().longValue();
//    }

}

