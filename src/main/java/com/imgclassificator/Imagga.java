package com.imgclassificator;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.net.URLEncoder;

//@RestController
public class Imagga {
    //@GetMapping("/images")

    public static String main(String[] args) {
        try {
            String credentialsToEncode = "acc_36c475c133aa761" + ":" + "e774e4e796859b130206d49b238a2b34";
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));
            String endpoint_url = "https://api.imagga.com/v2/tags";
            String image_url = "https://en.wikipedia.org/wiki/Western_honey_bee#/media/File:Apis_mellifera_Western_honey_bee.jpg";
            String encodedImageUrl = URLEncoder.encode(image_url, StandardCharsets.UTF_8);
            String url = endpoint_url + "?image_url=" + encodedImageUrl;
            //String url = endpoint_url + "?image_url=" + image_url;
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            connection.setRequestProperty("Authorization", "Basic " + basicAuth);

            int responseCode = connection.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = connectionInput.readLine()) != null) {
                jsonResponse.append(line);
            }

            connectionInput.close();

            //System.out.println(jsonResponse.toString());
            return new String(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



        }



