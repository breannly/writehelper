CREATE TABLE IF NOT EXISTS posts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    writer_id BIGINT NOT NULL,
    content VARCHAR(255),
    created TIMESTAMP,
    updated TIMESTAMP,
    status VARCHAR(255),
    PRIMARY KEY(id),
    FOREIGN KEY(writer_id) REFERENCES writers(id)
);