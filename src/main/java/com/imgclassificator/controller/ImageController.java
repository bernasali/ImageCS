package com.imgclassificator.controller;

//import com.imgclassificator.repository.ImageRepository;
//import com.imgclassificator.service.ImageService;
import com.imgclassificator.entity.ImageEntity;
//import com.imgclassificator.entity.ImageTagEntity;
import com.imgclassificator.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {

        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestBody ImageEntity image) {
        if (image.getUrl() == null || image.getUrl().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String response = imageService.saveInDb(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping
    public ResponseEntity<List<ImageEntity>> getAllImages(@RequestParam(required = false) List<String> labels) {
        List<ImageEntity> images;

        if (labels != null && !labels.isEmpty()) {
            images = imageService.getImagesByLabels(labels);
        } else {
            images = imageService.getAllImages();
        }

        if (images.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(images);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Optional<ImageEntity>> getImageById(@PathVariable(value = "id") Long imageId) {
        try {
            Optional<ImageEntity> image = Optional.ofNullable(imageService.findImageById(imageId));

            if (image.isPresent() && image.get() != null) {
                return ResponseEntity.ok(image);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

