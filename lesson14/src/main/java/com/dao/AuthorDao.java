package com.dao;

import com.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author,Long> {
    List<Author> findAll();

    Author findByName(String name);
}
