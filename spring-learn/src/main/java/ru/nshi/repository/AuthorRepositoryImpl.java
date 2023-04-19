package ru.nshi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.nshi.controller.AuthorController;
import ru.nshi.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final JdbcTemplate template;

    @Override
    public List<Author> getAll(Pageable pageable) {
        Sort sorting = pageable.getSort();
        String query = "SELECT * " +
            "FROM " + Author.TABLE;
        if (sorting.isSorted()) {
            query += sorting.stream()
                .map(s -> s.getProperty() + " " + s.getDirection().name())
                .collect(Collectors.joining(", ", " ORDER BY ", ""));
        }
        if (pageable.isPaged()) {
            query += " LIMIT ? OFFSET ?";
            return template.query(query, new AuthorMapper(),
                pageable.getPageSize(), pageable.getOffset());
        }
        return template.query(query, new AuthorMapper());
    }

    @Override
    public Optional<Author> getById(int id) {
        String query = "SELECT * " +
            "FROM " + Author.TABLE + " " +
            "WHERE " + Author.ID_COLUMN + " = ?";
        Author author = template.queryForObject(query, new AuthorMapper(), id);
        return Optional.ofNullable(author);
    }

    @Override
    public Author create(AuthorController.CreateAuthor create) {
        String name = create.getName();
        String avatar = create.getAvatar();
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template)
            .withTableName(Author.TABLE)
            .usingGeneratedKeyColumns(Author.ID_COLUMN);
        Number id = jdbcInsert.executeAndReturnKey(
            Map.of(Author.NAME_COLUMN, name,
                Author.AVATAR_COLUMN, avatar));
        return new Author(id.intValue(), name, avatar);
    }

    @Override
    public Optional<Author> patch(int id, AuthorController.CreateAuthor create) {
        String query = "UPDATE " + Author.TABLE + " SET ";
        ArrayList<Object> args = new ArrayList<>();
        if (create.getAvatar() != null) {
            query += " " + Author.AVATAR_COLUMN + " = ?";
            args.add(create.getAvatar());
        }
        if (create.getName() != null) {
            if (!args.isEmpty()) {
                query += ",";
            }
            query += " " + Author.NAME_COLUMN + " = ?";
            args.add(create.getName());
        }
        if (!args.isEmpty()) {
            args.add(id);
            query += " WHERE " + Author.ID_COLUMN + " = ?";
            int updates = template.update(query, args.toArray());
            if (updates == 0) {
                return Optional.empty();
            }
        }
        return getById(id);
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM " + Author.TABLE + " " +
            "WHERE " + Author.ID_COLUMN + " = ?";
        int update = template.update(query, id);
        return update > 0;
    }

    @Override
    public Optional<Author> put(int id, AuthorController.CreateAuthor update) {
        String query = "UPDATE " + Author.TABLE + " SET " +
            Author.NAME_COLUMN + " = ?, " +
            Author.AVATAR_COLUMN + " = ? WHERE " + Author.ID_COLUMN + " = ?";
        int updates = template.update(query, update.getName(), update.getAvatar(), id);
        if (updates == 0) {
            return Optional.empty();
        }
        return getById(id);
    }
}
