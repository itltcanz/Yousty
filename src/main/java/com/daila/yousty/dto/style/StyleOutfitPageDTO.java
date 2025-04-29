package com.daila.yousty.dto.style;

import com.daila.yousty.dto.outfit.OutfitPageDTO;
import com.daila.yousty.entity.Image;

import java.util.List;

@SuppressWarnings("unused")
public class StyleOutfitPageDTO {
    private Image image;
    private String backColor;
    private String textColor;
    private long popularity;
    List<OutfitPageDTO> outfits;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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

    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    public List<OutfitPageDTO> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<OutfitPageDTO> outfits) {
        this.outfits = outfits;
    }
}
