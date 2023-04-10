package ru.nshi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.nshi.model.Author;
import ru.nshi.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthorControllerImpl implements AuthorController {
    private final AuthorRepository repository;

    @Override
    public ResponseEntity<List<Author>> getAll(int size, int page) {
        return ResponseEntity.ok(repository.getAll(size, page));
    }

    @Override
    public ResponseEntity<Author> getById(int id) {
        Optional<Author> optionalAuthor = repository.getById(id);
//        if (optionalAuthor.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(optionalAuthor.get());
//        }
        return optionalAuthor
            .map(a -> ResponseEntity.ok(a))
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> create(CreateAuthor create) {
        try {
            Author author = repository.create(create);
            return ResponseEntity.created(
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(author.getId())
                    .toUri()).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity<?> deleteById(int id) {
        boolean deleted = repository.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
