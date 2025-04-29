package com.daila.yousty.repository;

import com.daila.yousty.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unused")
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
    List<Clothing> getClothesByOutfitsId(Long id);
}
