package ru.nshi.repository;

import org.springframework.data.domain.Pageable;
import ru.nshi.controller.AuthorController;
import ru.nshi.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> getAll(Pageable pageable);

    Optional<Author> getById(int id);

    Author create(AuthorController.CreateAuthor create);
    Optional<Author> patch(int id, AuthorController.CreateAuthor create);

    Optional<Author> put(int id, AuthorController.CreateAuthor update);

    boolean deleteById(int id);
}
