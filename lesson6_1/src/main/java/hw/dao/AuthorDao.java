package hw.dao;


import hw.domain.Author;

public interface AuthorDao {
    void insert(Author genre);
    Author getById(int id);
    boolean checkByName(String genreName);
    Author getByName(String genreName);
}
