package hw.dao;


import hw.domain.Book;

import java.util.List;


public interface BookDao {
    int getCount();
    long insert(Book book);
    Book getById(int id);
    List<Book> getAll();
}
