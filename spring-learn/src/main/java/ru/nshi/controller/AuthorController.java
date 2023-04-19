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

    /**
     * curl 'localhost:8080/author?size=0&page=3'
     * @param size
     * @param page
     * @return
     */
    @GetMapping
    ResponseEntity<List<Author>> getAll(@RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "0") int page);

    /**
     * curl 'localhost:8080/author/1'
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ResponseEntity<Author> getById(@PathVariable int id);

    /**
     * curl localhost:8080/author -X POST -H "Content-Type: application/json" -d '{"name": "Sergey 1"}' -v
     * @param create
     * @return
     */
    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateAuthor create);

    /**
     * curl localhost:8080/author/2 -X PATCH -v -H 'Content-Type: application/json' -d '{"avatar": "http://imgur.com/er12"}'
     * @param id
     * @param update
     * @return
     */
    @PatchMapping("/{id}")
    ResponseEntity<?> patchById(@PathVariable int id, @RequestBody CreateAuthor update);

    /**
     * curl localhost:8080/author/2 -X PUT -v -H 'Content-Type: application/json' -d '{"name": "test"}'
     * @param id
     * @param update
     * @return
     */
    @PutMapping("/{id}")
    ResponseEntity<?> putById(@PathVariable int id, @RequestBody CreateAuthor update);

    /**
     * curl 'localhost:8080/author/1' -X DELETE
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable int id);

    @AllArgsConstructor
    @RequiredArgsConstructor
    @Data
    class CreateAuthor {
        private String name;
        private String avatar;
    }
}
