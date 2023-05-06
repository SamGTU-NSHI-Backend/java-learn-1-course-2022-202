--liquibase formatted sql
--changeset rassafel:add_author_avatar
ALTER TABLE author ADD avatar varchar(255) NULL;
