package ru.nshi.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.nshi.model.Author;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @ParameterizedTest
    @MethodSource
//    @ValueSource(ints = {1, 2, 3, 4})
    public void testEmptyGetById(int id) {
        Optional<Author> byId = authorRepository.getById(id);
        Assertions.assertThat(byId)
            .as("Проверка пустого автора")
            .isPresent();
    }

    @Test
    public void testUndefinedAuthor() {
        assertThatThrownBy(() -> authorRepository.getById(12345))
            .isInstanceOf(RuntimeException.class);
    }

    public static IntStream testEmptyGetById() {
        return IntStream.of(1, 2, 3, 4);
    }
}
