package com.imgclassificator.entity;
//import jakarta.persistance.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="image")
public class ImageEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="image_id")
    private int id;
    @Column(name="url")
    private String url;
    @Column(name="analysed_at")
    private String analysed_at;
    @Column(name="tags")
    private List<Tags> tags;
    @Column(name="width")
    private double width;
    @Column(name="height")
    private double height;

    public ImageEntity() {
    }

    public ImageEntity(int id, String url, String analysed_at, List<Tags> tags, double width, double height) {
        this.id = id;
        this.url = url;
        this.analysed_at = analysed_at;
        this.tags = tags;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnalysed_at() {
        return analysed_at;
    }

    public void setAnalysed_at(String analysed_at) {
        this.analysed_at = analysed_at;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}


