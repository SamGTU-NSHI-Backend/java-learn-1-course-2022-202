package ru.nshi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.nshi.model.Author;
import ru.nshi.repository.AuthorRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthorControllerImpl implements AuthorController {
    private final AuthorRepository repository;

    @Override
    public ResponseEntity<List<Author>> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(Author.ID_COLUMN)));
        return ResponseEntity.ok(repository.getAll(pageable));
    }

    @Override
    public ResponseEntity<Author> getById(int id) {
        Optional<Author> optionalAuthor = repository.getById(id);
        return optionalAuthor
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> create(CreateAuthor create) {
        try {
            Author author = repository.create(create);
            return ResponseEntity.created(
                getAuthorLocation(author.getId())).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity<?> patchById(int id, CreateAuthor update) {
        try {
            Optional<Author> author = repository.patch(id, update);
            author.ifPresentOrElse(a -> System.out.println("Patch author: " + a), () -> System.out.println("Patch author not found: " + id));
            return author
                .map(a -> ResponseEntity.accepted().location(getAuthorLocation(id)).build())
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity<?> putById(int id, CreateAuthor update) {
        try {
            Optional<Author> author = repository.put(id, update);
            author.ifPresentOrElse(a -> System.out.println("Put author: " + a), () -> System.out.println("Put author not found: " + id));
            return author
                .map(a -> ResponseEntity.accepted().location(getAuthorLocation(id)).build())
                .orElse(ResponseEntity.notFound().build());
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

    URI getAuthorLocation(long id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }
}
