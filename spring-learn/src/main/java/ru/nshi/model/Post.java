package ru.nshi.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Post {
    private Long id;
    private Long authorId;
    private String text;
    private Long views;
}
