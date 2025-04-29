package com.daila.yousty.dto;

import com.daila.yousty.dto.user.UserNickDTO;

public class CommentDTO {
    private Long id;
    private UserNickDTO author;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserNickDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserNickDTO author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
