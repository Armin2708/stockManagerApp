CREATE TABLE file
(
    id SERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_size VARCHAR(255) NOT NULL,
    file_format VARCHAR(255) NOT NULL,
    file_content BYTEA,
    FOREIGN KEY (client_id)
        REFERENCES client (id)
);

ALTER TABLE client
drop column file;