package ru.nshi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.nshi.model.Author;
import ru.nshi.repository.AuthorRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    AuthorRepository authorRepository;

    @Test
    public void testGetAuthor() throws Exception {
        when(authorRepository.getById(anyInt()))
            .thenReturn(Optional.of(new Author(1, "null", null)));


        mvc.perform(
                get(AuthorController.MAPPING + "/{id}", 1))
            .andDo(print())
            .andExpect(status()
                .is2xxSuccessful())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1));
    }
}
