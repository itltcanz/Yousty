package com.daila.yousty.dto.style;

import com.daila.yousty.dto.image.ImageDTO;
import com.daila.yousty.dto.outfit.OutfitStylesDTO;

import java.util.List;

@SuppressWarnings("unused")
public class StyleStylePageDTO {
    private Long id;
    private String name;
    private String description;
    private long popularity;
    private ImageDTO image;
    private String backColor;
    private String textColor;
    private List<OutfitStylesDTO> outfits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public List<OutfitStylesDTO> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<OutfitStylesDTO> outfits) {
        this.outfits = outfits;
    }
}
