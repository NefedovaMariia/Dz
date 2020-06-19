package com.service;

import com.dao.CommentDao;
import com.domain.Book;
import com.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return commentDao.findAllByBook_Id(id);
    }


    @Override
    public void deleteById(long id) {
        commentDao.deleteById(id);
    }
    @Transactional
    @Override
    public void addNewComment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите id книги, которой хотите добавить комментарий: "+ "\n");
        int bookId = sc.nextInt();
        Book book = bookService.findById(bookId);
        if (book != null) {
            System.out.print(book.getTitle()+"\n");
            System.out.print("Введите комментарий для книги: ");
            String commentText = sc.nextLine();
            Comment comment = new Comment(commentText, book);
            commentDao.save(comment);
        } else {
            System.out.print("Книги по такому ID не существует.");
        }
    }
}
