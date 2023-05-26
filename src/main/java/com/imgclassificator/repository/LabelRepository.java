package com.imgclassificator.repository;

import com.imgclassificator.entity.LabelEntity;

import java.util.List;

public interface LabelRepository {
    List<LabelEntity> findByName(String labelName);
}
