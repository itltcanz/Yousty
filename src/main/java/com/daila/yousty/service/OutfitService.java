package com.daila.yousty.service;

import com.daila.yousty.dto.outfit.OutfitCreateDTO;
import com.daila.yousty.dto.outfit.OutfitStylesDTO;
import com.daila.yousty.entity.*;
import com.daila.yousty.repository.OutfitRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@SuppressWarnings({"unused", "SameParameterValue"})
public class OutfitService {
    private final OutfitRepository outfitRepository;
    private final ClothingService clothingService;
    private final ImageService imageService;
    private final CommentService commentService;
    private final UserService userService;
    private final ModelMapper converter;

    public OutfitService(OutfitRepository outfitRepository, ClothingService clothingService, ImageService imageService, CommentService commentService, UserService userService, ModelMapper converter) {
        this.outfitRepository = outfitRepository;
        this.clothingService = clothingService;
        this.imageService = imageService;
        this.commentService = commentService;
        this.userService = userService;
        this.converter = converter;
    }

    public List<OutfitStylesDTO> getOutfitsDTOWithImage(Long id, Pageable outfitPageRequest, Pageable imagePageRequest) {
        Page<Outfit> outfits = outfitRepository.getOutfitsByStyleId(id, outfitPageRequest);
        for (Outfit outfit : outfits) {
            outfit.setImages(imageService.getImagesByOutfitId(outfit.getId(), imagePageRequest));
        }
        return convertToOutfitDTOs(outfits.toList(), OutfitStylesDTO.class);
    }

    public List<Outfit> getOutfitsWithImage(Long id, Pageable outfitPageRequest, Pageable imagePageRequest) {
        Page<Outfit> outfits = outfitRepository.getOutfitsByStyleId(id, outfitPageRequest);
        for (Outfit outfit : outfits) {
            outfit.setImages(imageService.getImagesByOutfitId(outfit.getId(), imagePageRequest));
        }
        return outfits.toList();
    }

    public Outfit getOutfitsById(Long outfitId) {
        Outfit outfit = outfitRepository.getOutfitById(outfitId);
        List<Image> images = imageService.getImagesByOutfitId(outfitId);
        List<Clothing> clothes = clothingService.getClothesByOutfitId(outfitId);
        List<Comment> comments = commentService.getCommentsByOutfitId(outfitId);
        outfit.setImages(images);
        outfit.setClothing(clothes);
        outfit.setComments(comments);
        return outfit;
    }

    private <T> T convertToOutfitDTO(Outfit outfit, Class<T> dtoClass) {
        return converter.map(outfit, dtoClass);
    }

    private <T> List<T> convertToOutfitDTOs(List<Outfit> outfits, Class<T> dtoClass) {
        return outfits.stream()
                .map(style -> converter.map(style, dtoClass))
                .collect(Collectors.toList());
    }

    @Transactional
    public <T> List<T> getOutfitsByUserId(Long id, Pageable outfitPage, Pageable imagePage, Class<T> dtoClass) {
        List<Outfit> outfits = outfitRepository.getOutfitsByCreatorId(id, outfitPage).toList();
        for (Outfit outfit : outfits) {
            outfit.setImages(imageService.getImagesByOutfitId(outfit.getId(), imagePage));
            Hibernate.initialize(outfit.getStyle());
        }
        return convertToOutfitDTOs(outfits, dtoClass);
    }

    public Outfit createEntity(OutfitCreateDTO outfitFormDTO) {
        Outfit outfit = new Outfit();
        outfit.setStyle(outfitFormDTO.getStyle());
        outfit.setCreator(userService.getAuthenticatedUser());
        outfit.setDateOfCreated(LocalDateTime.now());
        List<Image> images = imageService.saveImages(outfitFormDTO.getImages(), outfitFormDTO.getStyle());
        List<Clothing> clothing = clothingService.findClothing(images.get(0));
        outfit.setImages(images);
        outfit.setClothing(clothing);
        return outfit;
    }

    public void saveEntity(Outfit outfit) {
        outfitRepository.save(outfit);
    }
}
