package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CommentDao;
import com.domain.Book;
import com.domain.Comment;

import java.util.List;
import java.util.Scanner;

@Service
public class CommentServiceImpl implements CommentService {
    final private CommentDao commentDao;
    final private BookService bookService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, BookService bookService) {
        this.commentDao = commentDao;
        this.bookService = bookService;
    }

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> findByBookId(long id) {
        return commentDao.findByBookId(id);
    }

    @Override
    public void updateTextById(long id, String text) {
        commentDao.updateTextById(id, text);
    }

    @Override
    public void deleteById(long id) {
        commentDao.deleteById(id);
    }

    @Override
    public void addNewComment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите id книги, которой хотите добавить комментарий");
        int bookId = sc.nextInt();
        Book book = bookService.findById(bookId);
        if (book != null) {
            System.out.print("Введите комментарий для книги - " + book.getTitle());
            String commentText = sc.nextLine();
            Comment comment = new Comment(commentText, book);
            commentDao.save(comment);
        } else {
            System.out.print("Книги по такому ID не существует.");
        }
    }

    @Override
    public List<Comment> findAllCommentsByAuthorId(long id) {
        return commentDao.findAllCommentsByAuthorId(id);
    }

    @Override
    public void deleteByBookId(long id) {
        commentDao.deleteByBookId(id);
    }
}
