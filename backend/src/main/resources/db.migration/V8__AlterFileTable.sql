ALTER TABLE file
    ALTER COLUMN file_size TYPE BIGINT USING file_size::BIGINT;