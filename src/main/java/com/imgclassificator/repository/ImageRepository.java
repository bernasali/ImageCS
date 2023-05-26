
package com.imgclassificator.repository;

import com.imgclassificator.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
   // Card card = cardRepository.save(new Card(createCardResource.getCardType(), cardOwner.get()));

   // Custom query method to find an image by ID

   ImageEntity findById(long id);
   ImageEntity findByUrl(String url);
   boolean urlCheck(String url);
   // Custom query method to find all images with specific tags
   //List<ImageEntity> findByTagsIn(List<TagEntity> tags);

    // Custom query method to implement pagination
   //Page<ImageEntity> findAll(SpringDataWebProperties.Pageable pageable);
}
