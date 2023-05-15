package com.imgclassificator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@RestController

public class ImageSubmit {

    private static final String IMAGGA_API_KEY = "acc_36c475c133aa761";
    private static final String IMAGGA_API_SECRET = "e774e4e796859b130206d49b238a2b34";
    private static final String IMAGGA_TAGS_ENDPOINT = "https://api.imagga.com/v2/tags";

    public static void main(String[] args) {
        SpringApplication.run(ImageSubmit .class, args);
    }

    @GetMapping("/images")
    public Map<String, Object> categorizeImage(@RequestParam String imageUrl) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Prepare the request URL
           // @Value("${server.endpoint}")
            String url = IMAGGA_TAGS_ENDPOINT + "https://en.wikipedia.org/wiki/Western_honey_bee#/media/File:Apis_mellifera_Western_honey_bee.jpg" + imageUrl;

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
}
