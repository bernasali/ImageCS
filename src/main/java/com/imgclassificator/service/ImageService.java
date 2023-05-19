package com.imgclassificator.service;

import com.imgclassificator.entity.ImageEntity;
//import com.imgclassificator.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Image;
import java.util.Optional;
import java.util.List;

@Component
public class ImageService {
   // private final ImageRepository imageRepository;

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
}


