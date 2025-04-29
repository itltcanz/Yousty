package com.daila.yousty.repository;

import com.daila.yousty.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByOutfitId(Long outfitId);
}
