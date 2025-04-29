package com.daila.yousty.dto.outfit;

import com.daila.yousty.entity.Clothing;
import com.daila.yousty.entity.Style;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@SuppressWarnings("unused")
public class OutfitCreateDTO {
    private Style style;
    private List<MultipartFile> images;
    private List<Clothing> clothes;

    public OutfitCreateDTO() {

    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<Clothing> getClothes() {
        return clothes;
    }

    public void setClothes(List<Clothing> clothes) {
        this.clothes = clothes;
    }
}
