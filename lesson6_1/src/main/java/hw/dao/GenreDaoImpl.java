package hw.dao;

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
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into genre (name) values(:name)", params, keyHolder);

    }


    public Genre getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        return jdbcOperations.queryForObject("select * from genre where id = :id", params, new GenreMapper());
    }


    public boolean checkByName(String genreName) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("name", genreName);
        List queryResult = jdbcOperations.query("select * from genre where name = :name",
                params, new GenreMapper());
        return queryResult.size() != 0;
    }


    public Genre getByName(String genreName) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("name", genreName);
        return jdbcOperations.queryForObject("select * from genre where name = :name",
                params, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
