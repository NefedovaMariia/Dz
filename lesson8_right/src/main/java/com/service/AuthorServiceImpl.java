package com.service;

import org.springframework.stereotype.Service;
import com.dao.AuthorDao;
import com.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    final private AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author findById(long id) {
        return authorDao.findById(id).get();
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author findByName(String name) {
        return authorDao.findByName(name);
    }


}
