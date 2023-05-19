package com.imgclassificator.controller;

//import com.imgclassificator.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.List;
import java.util.Optional;


//ImageEntity vs Image Tag Entity
@RestController
public class ImageController {
  //  @Autowired
//    private ImageRepository imageRepository;
//    @GetMapping("/images")
//    public List getAllImages(@RequestParam(required = false) List tags) {
//        if (tags != null && !tags.isEmpty()) {
//            // Fetch images with the specified tags
//            return imageRepository.findByTags(tags);
//        } else {
//            // Fetch all images
//            return imageRepository.findAll();
//        }
//    }


//    @GetMapping("/images/{id}")
//    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
//        Optional<Image> optionalImage = imageRepository.findById(id);
//        if (optionalImage.isPresent()) {
//            return ResponseEntity.ok(optionalImage.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//    @PostMapping("/images")
//    public ResponseEntity<Image> submitImage(@RequestParam String url,
//                                             @RequestParam(required = false, defaultValue = "false") boolean noCache) {
//        // Check if the image already exists in the database based on URL or checksum (bonus task)
//        Optional<Image> optionalImage = imageRepository.findByUrl(url);
//        if (optionalImage.isPresent()) {
//            // Image already exists, return it directly from the database
//            return ResponseEntity.ok(optionalImage.get());
//        }
//
//        // Perform categorization and get the tags for the image
//        List<Tag> tags = categorizeImage(url);
//
//        // Create a new Image object
//        Image image = new Image();
//        image.setUrl(url);
//        image.setAnalysedAt(LocalDateTime.now());
//        image.setService("categorization_service");
//        image.setWidth(getImageWidth(url));
//        image.setHeight(getImageHeight(url));
//
//        // Associate tags with the image
//        for (Tag tag : tags) {
//            image.addTag(tag);
//        }
//
//        // Save the image in the database
//        Image savedImage = imageRepository.save(image);
//        return ResponseEntity.ok(savedImage);
//    }
//

}
