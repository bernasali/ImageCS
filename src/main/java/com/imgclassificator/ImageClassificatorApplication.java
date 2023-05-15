package com.imgclassificator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan

public class ImageClassificatorApplication {

	public static void main(String[] args) {




//	Imagga imagga = new Imagga();
//        try {
//        	String imageUrl="https://www.google.com/imgres?imgurl=https%3A%2F%2Fhips.hearstapps.com%2Fhmg-prod%2Fimages%2Fclose-up-of-tulips-blooming-in-field-royalty-free-image-1584131603.jpg%3Fcrop%3D1.00xw%3A0.798xh%3B0%2C0.202xh%26resize%3D1200%3A*&tbnid=PmZdTwFUkaTy2M&vet=12ahUKEwjprYv4qPL-AhVZwgIHHXjACzEQMygCegUIARC9AQ..i&imgrefurl=https%3A%2F%2Fwww.housebeautiful.com%2Flifestyle%2Fgardening%2Fg13074130%2Fbeautiful-flower-images%2F&docid=pxn6KoNeXNKDvM&w=1200&h=601&q=flowers&ved=2ahUKEwjprYv4qPL-AhVZwgIHHXjACzEQMygCegUIARC9AQ";
//		String tags = imagga.getTagsForImage(imageUrl);
//		System.out.println(tags);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        SpringApplication.run(ImageClassificatorApplication.class, args);
}
}
