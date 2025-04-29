package com.daila.yousty.repository;

import com.daila.yousty.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unused")
public interface StyleRepository extends JpaRepository<Style, Long> {
    Style getStyleByName(String name);
    Style getStyleById(Long id);

    Style getStyleByOutfitsId(Long outfitId);
}
