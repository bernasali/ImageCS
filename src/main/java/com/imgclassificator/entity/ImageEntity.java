package com.imgclassificator.entity;
//import jakarta.persistance.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="image")
public class ImageEntity {

    @Id
   @SequenceGenerator(
           name = "image_seq",
           sequenceName = "image_seq",
           allocationSize = 1
   )
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="url")
    private String url;


    @Column(name="analysed_at")
    private LocalDate analysed_at;
    //@OneToMany
//    @Column(name="tags")
//    private String tags;
    @Column(name="width")
    private double width;
    @Column(name="height")
    private double height;


    @JoinTable(name = "image_label",
            joinColumns = {
                    @JoinColumn(name = "id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "label_id", referencedColumnName = "label_id")

            }
    )
    private List<LabelEntity> labels;

    public ImageEntity() {
    }

    public ImageEntity(int id, String url, LocalDate analysed_at, String tags, double width, double height) {
        this.id = id;
        this.url = url;
        this.analysed_at = analysed_at;
        //this.tags = tags;
        this.width = width;
        this.height = height;
        this.labels=new ArrayList<>(labels);
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

    public LocalDate getAnalysed_at() {
        return analysed_at;
    }

    public void setAnalysed_at(LocalDate analysed_at) {
        this.analysed_at = analysed_at;
    }

    /*public String getTags() {
        return tags;
    }*/

    /*public void setTags(String tags) {
        this.tags = tags;
    }
*/
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

    public List<LabelEntity> getLabels() {
        return new ArrayList<>(labels);
    }

    public void setLabels(List<LabelEntity> labels) {
        this.labels = new ArrayList<>(labels);
    }
}


