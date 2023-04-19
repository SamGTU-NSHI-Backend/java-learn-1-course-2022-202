package ru.nshi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.nshi.controller.AuthorController;
import ru.nshi.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final JdbcTemplate template;

    @Override
    public List<Author> getAll(int size, int page) {
        int offset = page * size;
        List<Author> authors = template.query("SELECT * FROM author " +
            "LIMIT ? OFFSET ?", new AuthorMapper(), size, offset);
        return authors;
    }

    @Override
    public Optional<Author> getById(int id) {
        Author author = template.queryForObject("SELECT * FROM author " +
            "WHERE id = ?", new AuthorMapper(), id);
        return Optional.ofNullable(author);
    }

    @Override
    public Author create(AuthorController.CreateAuthor create) {
        String name = create.getName();
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template)
            .withTableName("author")
            .usingGeneratedKeyColumns("id");
        Number id = jdbcInsert.executeAndReturnKey(Map.of("name", name));
//        template.update(con -> {
//            PreparedStatement st = con.
//                prepareStatement("INSERT INTO author (name) VALUES (?)");
//            st.setString(1, name);
//            return st;
//        }, key);
//        return new Author(key.getKeyAs(Integer.class), name);
        return new Author(id.intValue(), name);
    }

    @Override
    public boolean deleteById(int id) {
        int update = template.update("DELETE FROM author " +
            "WHERE id = ?", id);
        return update > 0;
    }
}
