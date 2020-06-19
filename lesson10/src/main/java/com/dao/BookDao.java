package com.dao;

import com.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    List<Book> findAll();
    List<Book> findBooksByTitle(String title);

    @Modifying
    @Query("update Book b set b.title=:name where b.id=:id")
    void updateNameById(@Param("id") Long id, @Param("name") String name);

    void deleteById(long Id);
    List<Book> findBooksByAuthorId(long id);
}
