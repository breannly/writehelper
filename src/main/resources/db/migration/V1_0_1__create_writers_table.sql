CREATE TABLE IF NOT EXISTS writers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    second_name VARCHAR(255),
    status VARCHAR(255)
);