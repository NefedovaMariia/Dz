package hw.service;

import hw.domain.Genre;

public interface GenreService {
    Genre getById(int id);
    Genre getGenre(String genreName);
}
