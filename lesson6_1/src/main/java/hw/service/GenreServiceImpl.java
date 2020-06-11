package hw.service;

import hw.dao.GenreDao;
import hw.domain.Genre;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    final private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }


    public Genre getById(int id) {
        return genreDao.getById(id);
    }


    public Genre getGenre(String genreName) {
        if (!genreDao.checkByName(genreName)) genreDao.insert(new Genre(genreName));
        return genreDao.getByName(genreName);
    }
}
