TRUNCATE TABLE file;

ALTER TABLE file
    ALTER COLUMN file_content TYPE bytea USING file_content::bytea