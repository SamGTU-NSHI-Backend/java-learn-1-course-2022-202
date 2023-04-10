package ru.nshi.repository;

import ru.nshi.controller.AuthorController;
import ru.nshi.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> getAll(int size, int page);

    Optional<Author> getById(int id);

    Author create(AuthorController.CreateAuthor create);

    boolean deleteById(int id);
}
