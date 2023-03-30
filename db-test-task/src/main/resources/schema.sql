CREATE TABLE tag (
    id SERIAL PRIMARY KEY,
    name VARCHAR(63)
);

CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    name VARCHAR(63),
    description VARCHAR(255),
    tag_id INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

ALTER TABLE task ADD CONSTRAINT fk_task_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id);
ALTER TABLE task ADD CONSTRAINT uq_task_name UNIQUE (name);
