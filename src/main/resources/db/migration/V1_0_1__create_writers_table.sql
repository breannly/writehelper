CREATE TABLE IF NOT EXISTS writers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    second_name VARCHAR(255),
    status VARCHAR(255),
    PRIMARY KEY(id)
);