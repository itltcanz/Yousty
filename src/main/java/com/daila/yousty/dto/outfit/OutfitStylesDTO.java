package com.daila.yousty.dto.outfit;

import com.daila.yousty.dto.image.ImageDTO;
import com.daila.yousty.dto.user.UserNickDTO;

import java.util.List;

@SuppressWarnings("unused")
public class OutfitStylesDTO {
    private Long id;
    private UserNickDTO creator;
    private List<ImageDTO> images;

    public OutfitStylesDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserNickDTO getCreator() {
        return creator;
    }

    public void setCreator(UserNickDTO creator) {
        this.creator = creator;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public ImageDTO getImage() {
        return images.get(0);
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }
}
