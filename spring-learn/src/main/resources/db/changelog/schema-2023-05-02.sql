--liquibase formatted sql
--changeset rassafel:increase_post_text_length
ALTER TABLE post
    ALTER COLUMN text VARCHAR(255);
