package ru.nshi.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nshi.model.Author;

import java.util.List;

@RequestMapping(AuthorController.MAPPING)
public interface AuthorController {
    String MAPPING = "/author";

    @GetMapping
    ResponseEntity<List<Author>> getAll(@RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}")
    ResponseEntity<Author> getById(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateAuthor create);

//    @PatchMapping("/{id}")
//    ResponseEntity<?> patchById(@PathVariable long id, @RequestBody Object update);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable int id);

    @AllArgsConstructor
    @RequiredArgsConstructor
    @Data
    class CreateAuthor {
        private String name;
    }
}
