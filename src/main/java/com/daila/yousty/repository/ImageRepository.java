package com.daila.yousty.repository;

import com.daila.yousty.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unused")
public interface ImageRepository extends JpaRepository<Image, Long> {
    Page<Image> getImagesByOutfitsId(Long id, Pageable imagePageRequest);
    List<Image> getImagesByOutfitsId(Long id);
    Image getImageByOutfitsId(Long id);
    Image getImageByPath(String path);

    Image getImageById(Long i);
}