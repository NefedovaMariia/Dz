package hw.service;

import hw.domain.Author;

public interface AuthorService {
    Author getById(int id);
    Author getAuthor(String genreName);
}
