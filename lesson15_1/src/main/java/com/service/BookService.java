package com.service;

import com.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    Book findById(long id);
    List<Book> findAll();
    List<Book> findBooksByTitle(String name);
    void deleteById(long id);
    void addNewBook(Book book);
    long getCount();
    List<Book> findAllBooksByAuthorId(long id);
}
