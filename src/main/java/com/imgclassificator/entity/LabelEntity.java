package com.imgclassificator.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="labels")
public class LabelEntity {
    @Id
    //id generator
    @SequenceGenerator(
            name = "label_seq",
            sequenceName = "label_seq",
            allocationSize = 1

    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "labels")
    private List<ImageEntity> images;

    public LabelEntity() {
    }

    public LabelEntity(long id, String name, List<ImageEntity> images) {
        this.id = id;
        this.name = name;
        this.images = new ArrayList<>(images);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageEntity> getImages() {
        return new ArrayList<>(images);
    }

    public void setImages(List<ImageEntity> images) {
        this.images = new ArrayList<>(images);
    }
}
