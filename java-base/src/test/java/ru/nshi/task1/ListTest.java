package ru.nshi.task1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class ListTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException {
        Data data = mapper.readValue("{\"values\": [1, 2, 3]}", Data.class);
        for (int value : data.values) {
            System.out.print(value + " ");
        }
    }

    static class Data {
        @JsonProperty("values")
        int[] values;
    }
}
