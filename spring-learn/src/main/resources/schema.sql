

CREATE TABLE IF NOT EXISTS author (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(63) UNIQUE,
    avatar varchar(255) NULL
);

CREATE TABLE IF NOT EXISTS post (
    id serial PRIMARY KEY,
    author_id int,
    text varchar(255),
    views long
);

ALTER TABLE post ADD CONSTRAINT fk_post_author_id
FOREIGN KEY (author_id)
REFERENCES author (id);

--CREATE TABLE IF NOT EXISTS post_views (
--
--);
