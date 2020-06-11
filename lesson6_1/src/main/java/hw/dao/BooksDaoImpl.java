package hw.dao;

import hw.domain.Author;
import hw.domain.Book;
import hw.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Repository
public class BooksDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    public BooksDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int getCount() {
        return jdbcOperations.queryForObject("select count(*) from books", new HashMap<>(1), Integer.class);
    }

    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genre_Id", book.getGenre().getId());
        params.addValue("author_Id", book.getAuthor().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into books (title,genre_id,author_id) values(:title,:genre_Id,:author_Id)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }


    public Book getById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbcOperations.queryForObject("select b.id, b.title, b.genre_id, b.author_id, a.name authorName, g.name genreName " +
                        "from (books b left join author a on b.author_id = a.id) " +
                        "left join genre g on b.genre_id = g.id " +
                        "where b.id = :id",
                params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select b.id, b.title, b.genre_id, b.author_id, a.name authorName, g.name genreName " +
                        "from (books b left join author a on b.author_id = a.id) " +
                        "left join genre g on b.genre_id = g.id",
                new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            Book book = new Book(id, title);
            book.setAuthor(new Author(resultSet.getInt("author_id"), resultSet.getString("authorName")));
            book.setGenre(new Genre(resultSet.getInt("genre_id"), resultSet.getString("genreName")));
            return book;
        }
    }
}
