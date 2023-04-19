package ru.nshi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Author {
     public static final String TABLE = "author";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String AVATAR_COLUMN = "avatar";
    private Integer id;
    private String name;
    private String avatar;
}
