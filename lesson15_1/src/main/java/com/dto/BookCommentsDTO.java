package com.dto;

import com.domain.Book;
import com.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class BookCommentsDTO {
    Book book;
    List<Comment> comments;
}
