package com.daila.yousty.controller;

import com.daila.yousty.dto.outfit.OutfitAccountDTO;
import com.daila.yousty.dto.outfit.OutfitCreateDTO;
import com.daila.yousty.dto.user.UserAccountDTO;
import com.daila.yousty.dto.user.UserCreateDTO;
import com.daila.yousty.entity.Outfit;
import com.daila.yousty.entity.Style;
import com.daila.yousty.entity.User;
import com.daila.yousty.service.OutfitService;
import com.daila.yousty.service.StyleService;
import com.daila.yousty.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AccountController {
    private final UserService userService;
    private final StyleService styleService;
    private final OutfitService outfitService;


    public AccountController(UserService userService, StyleService styleService, OutfitService outfitService) {
        this.userService = userService;
        this.styleService = styleService;
        this.outfitService = outfitService;
    }

    @GetMapping("/sign-in")
    public String getSignInPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            org.springframework.security.core.AuthenticationException ex =
                    (org.springframework.security.core.AuthenticationException)
                            session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                String errorMessage = ex.getMessage();
                model.addAttribute("errorMessage", errorMessage);
            }
        }
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registration(@Valid @ModelAttribute UserCreateDTO userCreateDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            User user = new User(userCreateDTO);
            userService.createUser(user);
            return "redirect:/account";
        }
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String getAccountPage(Model model) {
        Pageable outfitPage = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "dateOfCreated"));
        PageRequest imagePage = PageRequest.of(0, 1);
        UserAccountDTO user = userService.getAuthenticatedUserDTO(UserAccountDTO.class);
        List<OutfitAccountDTO> outfits = outfitService.getOutfitsByUserId(user.getId(), outfitPage, imagePage, OutfitAccountDTO.class);
        model.addAttribute("user", user);
        model.addAttribute("outfits", outfits);
        return "account";
    }

    @GetMapping("/account/add-outfit")
    public String addOutfitPage(Model model) {
        List<Style> styles = styleService.getAll();
        model.addAttribute("styles", styles);
        return "add-outfit";
    }

    @PostMapping("/account/add-outfit")
    public String addOutfit(@Valid @ModelAttribute OutfitCreateDTO outfitCreateDTO, BindingResult binding) {

        if (binding.hasErrors()) {
            return "redirect:/account/add-outfit";
        }

        // Получение текущего SecurityContext
        SecurityContext securityContext = SecurityContextHolder.getContext();
        new Thread(() -> {
            try {
                // Установка SecurityContext в новом потоке
                SecurityContextHolder.setContext(securityContext);
                Outfit outfit = outfitService.createEntity(outfitCreateDTO);
                outfitService.saveEntity(outfit);
            } finally {
                // Очистка контекста после выполнения
                SecurityContextHolder.clearContext();
            }
        }).start();

        return "redirect:/account";
    }
}
