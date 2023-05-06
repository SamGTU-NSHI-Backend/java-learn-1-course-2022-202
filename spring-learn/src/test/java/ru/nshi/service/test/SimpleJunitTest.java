package ru.nshi.service.test;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class SimpleJunitTest {

    @BeforeEach
    public void beforeEachMethod(){
        log.info("Before each method");
    }

    @AfterEach
    public void afterEachMethod(){
        log.info("After each method");
    }

    @Test
    @DisplayName("Проверка суммы")
    public void sumOperation() {
        Assertions.assertThat(2 + 2)
            .isEqualTo(4);
    }

    @Test
    public void listContainsAnyOf() {
        Assertions
            .assertThat(testStringList())
            .hasSize(3)
            .containsAnyOf("first",
                "second3");
    }

    @Test
    public void listContainsExactlyInAnyOrder() {
        Assertions
            .assertThat(testStringList())
            .hasSize(3)
            .containsExactlyInAnyOrder("first", "second", "third");
    }

    @Test
    public void listContainsExactly() {
        Assertions
            .assertThat(testStringList())
            .hasSize(3)
            .containsExactly("second", "first", "third");
    }

    @Test
    public void listDoesNotContain() {
        Assertions
            .assertThat(testStringList())
            .hasSize(3)
            .doesNotContain("zero");
    }

    private List<String> testStringList() {
        return List.of(
            "second",
            "first",
            "third"
        );
    }

}
