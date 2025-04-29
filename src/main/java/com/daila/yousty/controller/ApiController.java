package com.daila.yousty.controller;

import com.daila.yousty.dto.outfit.OutfitAccountDTO;
import com.daila.yousty.dto.outfit.OutfitStylesDTO;
import com.daila.yousty.dto.style.StyleStylePageDTO;
import com.daila.yousty.service.OutfitService;
import com.daila.yousty.service.StyleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    private final StyleService styleService;
    private final OutfitService outfitService;

    public ApiController(StyleService styleService, OutfitService outfitService) {
        this.styleService = styleService;
        this.outfitService = outfitService;
    }

    @GetMapping("/api/styles")
    public ResponseEntity<List<StyleStylePageDTO>> getStyles(@RequestParam int page) {
        Pageable stylePage = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable outfitPage = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable imagePage = PageRequest.of(0, 1);
        List<StyleStylePageDTO> styles = styleService.getTopStylesDTO(stylePage, outfitPage, imagePage);
        return ResponseEntity.ok(styles);
    }

    @GetMapping("/api/styles/{styleName}/outfits")
    public ResponseEntity<List<OutfitStylesDTO>> getOutfits(@PathVariable String styleName, @RequestParam int page) {
        Pageable outfitPage = PageRequest.of(page, 8, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable imagePage = PageRequest.of(0, 1);
        StyleStylePageDTO style = styleService.getStyleDTOByName(styleName, outfitPage, imagePage);
        List<OutfitStylesDTO> outfits = outfitService.getOutfitsDTOWithImage(style.getId(), outfitPage, imagePage);
        return ResponseEntity.ok(outfits);
    }

    @GetMapping("/api/account/{userId}")
    public ResponseEntity<List<OutfitAccountDTO>> getOutfitsForAccount(@PathVariable long userId, @RequestParam int page) {
        Pageable outfitPage = PageRequest.of(page, 8, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable imagePage = PageRequest.of(0, 1);
        List<OutfitAccountDTO> outfits = outfitService.getOutfitsByUserId(userId, outfitPage, imagePage, OutfitAccountDTO.class);
        return ResponseEntity.ok(outfits);
    }
}
