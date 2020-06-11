package hw.service;

import hw.dao.AuthorDao;
import hw.domain.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    final private AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    public Author getById(int id) {
        return authorDao.getById(id);
    }

    public Author getAuthor(String authorName) {

        if (!authorDao.checkByName(authorName)) authorDao.insert(new Author(authorName));
        return authorDao.getByName(authorName);
    }

}
