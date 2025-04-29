package com.daila.yousty.dto.user;

import com.daila.yousty.dto.image.ImageDTO;

@SuppressWarnings("unused")
public class UserAccountDTO {
    private Long id;
    private String username;
    private String email;
    private ImageDTO image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }
}
