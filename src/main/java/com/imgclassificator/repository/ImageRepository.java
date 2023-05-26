
package com.imgclassificator.repository;

import com.imgclassificator.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

   ImageEntity findById(long id);
   ImageEntity findByUrl(String url);

   //List<ImageEntity> findByTagsIn(List<TagEntity> tags);


   //Page<ImageEntity> findAll(SpringDataWebProperties.Pageable pageable);
}
