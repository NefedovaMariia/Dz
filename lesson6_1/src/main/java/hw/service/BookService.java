package hw.service;

import hw.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    int getCount();
    void insert(Book book);
    Book getById(int id);
    Book getNewBook();
}
