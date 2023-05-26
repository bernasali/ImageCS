package com.imgclassificator;

import com.imgclassificator.entity.ImageEntity;
//import com.imgclassificator.entity.ImageTagEntity;
//import com.imgclassificator.repository.ImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.imgclassificator.entity.*")
@EnableJpaRepositories(
        basePackages = { "com.imgclassificator.repository" })
public class ImageClassificatorApplication {

	public static void main(String[] args) {


        SpringApplication.run(ImageClassificatorApplication.class, args);

}


}
