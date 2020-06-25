package com.service;

import com.dao.BookDao;
import com.domain.Author;
import com.domain.Book;
import com.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final private BookDao bookDao;
    final private GenreService genreService;
    final private AuthorService authorService;


   /* @Autowired
    public BookServiceImpl(BookDao bookDao, GenreService genreService, AuthorService authorService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }*/

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public  Book findById(long id) {
        return bookDao.findById(id).get();
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBooksByTitle(String name) {
        return bookDao.findBooksByTitle(name);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Transactional
    @Override
    public void addNewBook(Book book) {
        Author author = authorService.findByName(book.getAuthor().getName());
        if (author == null) author = new Author(book.getAuthor().getName());
        Genre genre = genreService.findByName(book.getGenre().getName());
        if (genre == null) genre = new Genre(book.getGenre().getName());
        book.setAuthor(author);
        book.setGenre(genre);
        bookDao.save(book);
    }
    @Override
    public long getCount() {
        return bookDao.count();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookDao.findBooksByAuthorId(id);
    }
}