CREATE TABLE IF NOT EXISTS posts (
    id BIGSERIAL PRIMARY KEY,
    writer_id BIGINT NOT NULL REFERENCES writers(id),
    content VARCHAR(255),
    created TIMESTAMP,
    updated TIMESTAMP,
    status VARCHAR(255)
);