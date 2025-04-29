package com.daila.yousty.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuppressWarnings("unused")
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private long popularity;
    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;
    private LocalDateTime dateOfCreated;
    private long likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="style_id")
    private Style style;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "outfits_images",
            joinColumns = @JoinColumn(name = "outfit_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "outfits_clothes",
            joinColumns = @JoinColumn(name = "outfit_id"),
            inverseJoinColumns = @JoinColumn(name = "clothing_id")
    )
    private List<Clothing> clothing;
    @OneToMany(mappedBy = "outfit", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public List<Image> getImages() {
        return images;
    }

    public Image getImage() {
        return images.get(0);
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Clothing> getClothing() {
        return clothing;
    }

    public void setClothing(List<Clothing> clothes) {
        this.clothing = clothes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}