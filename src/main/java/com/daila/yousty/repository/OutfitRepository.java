package com.daila.yousty.repository;

import com.daila.yousty.dto.outfit.OutfitPageDTO;
import com.daila.yousty.entity.Outfit;
import com.daila.yousty.entity.Style;
import com.daila.yousty.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {
    Outfit getOutfitById(Long id);
    Page<Outfit> getOutfitsByStyleId(Long id, Pageable OutfitPageRequest);
    Page<Outfit> getOutfitsByCreatorId(Long id, Pageable pageable);
}
