package com.service;

import com.dao.GenreDao;
import com.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    final private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre save(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    public Optional<Genre> findById(long id) {
        return genreDao.findById(id);
    }


    @Override
    public Genre findByName(String name) {
        return genreDao.findByName(name);
    }
}


