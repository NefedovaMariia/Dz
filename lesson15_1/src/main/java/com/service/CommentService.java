package com.service;

import com.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findByBookId(long id);
    void deleteById(long id);
    void addNewComment();
}
