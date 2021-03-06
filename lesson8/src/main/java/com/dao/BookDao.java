package com.dao;

import com.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);
    Optional<Book> findById(long id);
    List<Book> findAll();
    List<Book> findByName(String name);
    void deleteById(long id);
    long getCount();
    List<Book> findAllBooksByAuthorId(long id);
    List<Book> findAllWithComments();
}
