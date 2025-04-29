package com.daila.yousty.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@SuppressWarnings("unused")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String description;
    private long popularity;
    private String backColor;
    private String textColor;
    @OneToOne
    private Image image;
    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Outfit> outfits;

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

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backgroundColor) {
        this.backColor = backgroundColor;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image backgroundImage) {
        this.image = backgroundImage;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String descriptionColor) {
        this.textColor = descriptionColor;
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<Outfit> outfits) {
        this.outfits = outfits;
    }
}
