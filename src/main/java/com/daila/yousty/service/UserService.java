package com.daila.yousty.service;

import com.daila.yousty.entity.Image;
import com.daila.yousty.entity.Role;
import com.daila.yousty.entity.User;
import com.daila.yousty.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings("SameParameterValue")
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper converter;

    public UserService(UserRepository userRepository, RoleService roleService, ImageService imageService, PasswordEncoder passwordEncoder, ModelMapper converter) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void createUser(User user) {
        if (userRepository.getByEmail(user.getEmail()) == null ||
                userRepository.getByUsername(user.getUsername()) == null) {
            user.setActive(true);
            Role role = roleService.getUserRole();
            Image image = imageService.getUserDefaultImage();
            user.setRole(role);
            user.setImage(image);
            user.setDateOfCreated(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Transactional
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() == "anonymousUser")) {
            User preUser = (User) authentication.getPrincipal();
            User user = userRepository.getUserById(preUser.getId());
            Hibernate.initialize(user.getImage());
            return user;
        }
        return null;
    }
    @Transactional
    public <T> T getAuthenticatedUserDTO(Class<T> dtoClass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() == "anonymousUser")) {
            User preUser = (User) authentication.getPrincipal();
            User user = userRepository.getUserById(preUser.getId());
            Hibernate.initialize(user.getImage());
            return convertUserToDTO(user, dtoClass);
        }
        return null;
    }

    private <T> T convertUserToDTO(User user, Class<T> dtoClass) {
        return converter.map(user, dtoClass);
    }

}