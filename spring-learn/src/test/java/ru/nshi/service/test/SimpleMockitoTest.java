package ru.nshi.service.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.nshi.model.Author;
import ru.nshi.repository.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimpleMockitoTest {

    @Mock
    AuthorRepository repo;

    @Test
    public void testSimpleMock() {
        when(repo.getById(anyInt()))
            .thenReturn(Optional.of(new Author(123, "Test author", "Test avatar")));

        Optional<Author> byId = repo.getById(1234);

        assertThat(byId)
            .isPresent()
            .get()
            .as("Проверка автора")
            .hasFieldOrPropertyWithValue("id", 123)
            .hasFieldOrPropertyWithValue("name", "Test author")
            .hasFieldOrPropertyWithValue("avatar", "Test avatar");
    }

}
