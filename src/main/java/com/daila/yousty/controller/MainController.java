package com.daila.yousty.controller;

import com.daila.yousty.dto.style.StyleStylePageDTO;
import com.daila.yousty.dto.style.StyleOutfitPageDTO;
import com.daila.yousty.dto.user.UserNickDTO;
import com.daila.yousty.service.OutfitService;
import com.daila.yousty.service.StyleService;
import com.daila.yousty.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"DuplicatedCode"})
@Controller
public class MainController {
    private final StyleService styleService;
    private final OutfitService outfitService;
    private final UserService userService;

    public MainController(StyleService styleService, OutfitService outfitService, UserService userService) {
        this.styleService = styleService;
        this.outfitService = outfitService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        Pageable stylePage = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable outfitPage = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable imagePage = PageRequest.of(0, 1);
        List<StyleStylePageDTO> styles = styleService.getTopStylesDTO(stylePage, outfitPage, imagePage);
        model.addAttribute("styles", styles);
        // User detection
        UserNickDTO user = userService.getAuthenticatedUserDTO(UserNickDTO.class);
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/styles")
    public String getStylesPage(Model model) {
        Pageable stylePage = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable outfitPage = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable imagePage = PageRequest.of(0, 1);
        List<StyleStylePageDTO> styles = styleService.getTopStylesDTO(stylePage, outfitPage, imagePage);
        model.addAttribute("styles", styles);
        // User detection
        UserNickDTO user = userService.getAuthenticatedUserDTO(UserNickDTO.class);
        model.addAttribute("user", user);
        return "styles";
    }

    @GetMapping("/styles/{name}")
    public String getStylePage(@PathVariable String name, Model model) {
        Pageable outfitPage = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "popularity"));
        Pageable imagePage = PageRequest.of(0, 1);
        StyleStylePageDTO style = styleService.getStyleDTOByName(name, outfitPage, imagePage);
        style.setOutfits(outfitService.getOutfitsDTOWithImage(style.getId(), outfitPage, imagePage));
        model.addAttribute("style", style);
        // User detection
        UserNickDTO user = userService.getAuthenticatedUserDTO(UserNickDTO.class);
        model.addAttribute("user", user);
        return "style";
    }

    @GetMapping("/styles/{styleName}/{outfitId}")
    public String getOutfitPage(@PathVariable String styleName, @PathVariable Long outfitId, Model model) {
        StyleOutfitPageDTO style = styleService.getStyleOutfitPageDTO(styleName, outfitId);
        model.addAttribute("style", style);
        // User detection
        UserNickDTO user = userService.getAuthenticatedUserDTO(UserNickDTO.class);
        model.addAttribute("user", user);
        return "outfit";
    }
}