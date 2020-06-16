package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.BookDao;
import com.domain.Author;
import com.domain.Book;
import com.domain.Genre;

import java.util.List;
import java.util.Scanner;

@Service
public class BookServiceImpl implements BookService {
    final private BookDao bookDao;
    final private GenreService genreService;
    final private AuthorService authorService;


    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreService genreService, AuthorService authorService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book findById(long id) {
        return bookDao.findById(id).get();
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByName(String name) {
        return bookDao.findByName(name);
    }

   /* @Override
    public void updateNameById(long id, String name) {
        bookDao.updateNameById(id, name);
    }*/

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void addNewBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите наименование книги");
        String title = sc.nextLine();
        System.out.print("Введите жанр: ");
        String genreName = sc.nextLine();
        System.out.print("Введите автора: ");
        String authorName = sc.nextLine();
        Author author = authorService.findByName(authorName);
        if (author == null) author = new Author(authorName);
        Genre genre = genreService.findByName(genreName);
        if (genre == null) genre = new Genre(genreName);
        Book book = new Book(title, author, genre);
        System.out.print("Книга добавлена\n");
        bookDao.save(book);
    }

    @Override
    public long getCount() {
        return bookDao.getCount();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookDao.findAllBooksByAuthorId(id);
    }

    @Override
    public List<Book> findAllWithComments() { return bookDao.findAllWithComments(); }
}