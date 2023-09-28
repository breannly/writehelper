CREATE TABLE IF NOT EXISTS writers (
    w_id BIGINT NOT NULL AUTO_INCREMENT,
    w_first_name VARCHAR(255),
    w_second_name VARCHAR(255),
    w_status VARCHAR(255),
    PRIMARY KEY(w_id)
);
GO