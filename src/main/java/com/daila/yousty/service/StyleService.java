package com.daila.yousty.service;

import com.daila.yousty.dto.style.StyleOutfitPageDTO;
import com.daila.yousty.dto.style.StyleStylePageDTO;
import com.daila.yousty.entity.Outfit;
import com.daila.yousty.entity.Style;
import com.daila.yousty.repository.StyleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SameParameterValue")
@Service
public class StyleService {
    private final StyleRepository styleRepository;
    private final OutfitService outfitService;
    private final ModelMapper converter;

    public StyleService(StyleRepository styleRepository, OutfitService outfitService, ModelMapper converter) {
        this.styleRepository = styleRepository;
        this.outfitService = outfitService;
        this.converter = converter;
    }
    public List<Style> getAll() {
        return styleRepository.findAll();
    }
    public StyleStylePageDTO getStyleDTOByName(String name, Pageable outfitPageRequest, Pageable imagePageRequest) {
        Style style = styleRepository.getStyleByName(name);
        style.setOutfits(outfitService.getOutfitsWithImage(style.getId(), outfitPageRequest, imagePageRequest));
        return convertStyleToDTO(style, StyleStylePageDTO.class);
    }

    public List<StyleStylePageDTO> getTopStylesDTO(Pageable stylePageRequest, Pageable outfitPageRequest, Pageable imagePageRequest) {
        Page<Style> styles = styleRepository.findAll(stylePageRequest);
        for (Style style : styles) {
            style.setOutfits(outfitService.getOutfitsWithImage(style.getId(), outfitPageRequest, imagePageRequest));
        }
        return convertStylesToDTOs(styles.toList(), StyleStylePageDTO.class);
    }

    private <T> T convertStyleToDTO(Style style, Class<T> dtoClass) {
        return converter.map(style, dtoClass);
    }

    private <T> List<T> convertStylesToDTOs(List<Style> styles, Class<T> dtoClass) {
        return styles.stream()
                .map(style -> converter.map(style, dtoClass))
                .collect(Collectors.toList());
    }

    public Style getStyleById(Long id) {
        return styleRepository.getStyleById(id);
    }

    public StyleOutfitPageDTO getStyleOutfitPageDTO(String styleName, Long outfitId) {
        Style style = styleRepository.getStyleByName(styleName);
        // Increment style popular
        style.setPopularity(style.getPopularity() + 1);
        saveStyle(style);
        Outfit outfit = outfitService.getOutfitsById(outfitId);
        // Increment outfit popular
        outfit.setPopularity(outfit.getPopularity() + 1);
        outfitService.saveEntity(outfit);
        // Continue
        style.setOutfits(List.of(outfit));
        return convertStyleToDTO(style, StyleOutfitPageDTO.class);
    }

    private void saveStyle(Style style) {
        styleRepository.save(style);
    }


}