--liquibase formatted sql
--changeset rassafel:init_author
CREATE TABLE author (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(63) UNIQUE
);

--changeset rassafel:init_post
CREATE TABLE post (
    id serial PRIMARY KEY,
    author_id int,
    text varchar(127),
    views long
);

--changeset rassafel:init_author_and_post_constraints
ALTER TABLE post ADD CONSTRAINT fk_post_author_id
FOREIGN KEY (author_id)
REFERENCES author (id);
