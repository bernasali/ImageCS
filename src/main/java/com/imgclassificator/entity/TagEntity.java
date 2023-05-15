package com.imgclassificator.entity;

import javax.persistence.*;

@Table(name="tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private int tag_id;
    @Column(name="tag_name")
    private String tag_name;
    @Column(name="tag_count")
    private int tag_count;


    public TagEntity() {
    }

    public TagEntity(int tag_id, String tag_name, int tag_count) {
        this.tag_id = tag_id;
        this.tag_name = tag_name;
        this.tag_count = tag_count;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getTag_count() {
        return tag_count;
    }

    public void setTag_count(int tag_count) {
        this.tag_count = tag_count;
    }
}
