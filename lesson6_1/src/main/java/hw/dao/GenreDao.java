package hw.dao;


import hw.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);
    Genre getById(long id);
    boolean checkByName(String genreName);
    Genre getByName(String genreName);
}
