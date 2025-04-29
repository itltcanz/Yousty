package com.daila.yousty.service;

import com.daila.yousty.entity.Comment;
import com.daila.yousty.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByOutfitId(Long outfitId) {
        return commentRepository.getCommentsByOutfitId(outfitId);
    }
}
