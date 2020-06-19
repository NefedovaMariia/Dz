package com.service;

import com.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);
    Optional<Genre> findById(long id);
    Genre findByName(String name);
}
