--liquibase formatted sql
--changeset rassafel:test_data_for_author_and_posts
insert into author(id,name) values (1, 'John');
insert into author(id,name) values (2, 'Ivan');
insert into author(id,name) values (3, 'Egor');
insert into author(id,name) values (4, 'Ilya');

insert into post(author_id, text, views) values (1, 'Test 1', 0);
insert into post(author_id, text, views) values (1, 'Test 2', 0);
insert into post(author_id, text, views) values (2, 'Ivan Test', 0);
insert into post(author_id, text, views) values (3, 'Egor', 0);
insert into post(author_id, text, views) values (1, 'Test 3', 0);
