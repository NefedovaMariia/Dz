package com.dao;

import com.domain.Genre;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreDao {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    Genre findByName(String name);

}
