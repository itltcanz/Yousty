package com.daila.yousty.dto.outfit;

import com.daila.yousty.dto.CommentDTO;
import com.daila.yousty.dto.clothing.ClothingDTO;
import com.daila.yousty.dto.image.ImageDTO;
import com.daila.yousty.entity.*;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class OutfitPageDTO {
    private long popularity;
    private User creator;
    private LocalDateTime dateOfCreated;
    private long likes;
    private List<ImageDTO> images;
    private List<ClothingDTO> clothing;
    private List<CommentDTO> comments;

    public OutfitPageDTO() {

    }
    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public ImageDTO getImage() {
        return images.get(0);
    }

    public List<ClothingDTO> getClothing() {
        return clothing;
    }

    public void setClothing(List<ClothingDTO> clothing) {
        this.clothing = clothing;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
