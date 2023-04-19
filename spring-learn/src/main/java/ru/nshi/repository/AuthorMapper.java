package ru.nshi.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.nshi.error.ApiException;
import ru.nshi.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        if (rs.wasNull()) {
            throw new ApiException("Author name cannot be null");
        }
        String avatar = rs.getString(3);
        return new Author(id, name, avatar);
    }
}
