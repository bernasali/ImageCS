package com.imgclassificator.entity;
import javax.persistence.*;

@Table(name="image_tag")
public class ImageTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_tag_id")

    private int image_tag_id;
    @Column(name="image_id_t")
    private int image_id_t;
    @Column(name="tag_id_t")
    private int tag_id_t;

    public ImageTagEntity() {
    }

    public ImageTagEntity(int image_tag_id, int image_id_t, int tag_id_t) {
        this.image_tag_id = image_tag_id;
        this.image_id_t = image_id_t;
        this.tag_id_t = tag_id_t;
    }


    public int getImage_tag_id() {
        return image_tag_id;
    }

    public void setImage_tag_id(int image_tag_id) {
        this.image_tag_id = image_tag_id;
    }

    public int getImage_id_t() {
        return image_id_t;
    }

    public void setImage_id_t(int image_id_t) {
        this.image_id_t = image_id_t;
    }

    public int getTag_id_t() {
        return tag_id_t;
    }

    public void setTag_id_t(int tag_id_t) {
        this.tag_id_t = tag_id_t;
    }
}
