CREATE TABLE IF NOT EXISTS labels (
    l_id BIGINT NOT NULL AUTO_INCREMENT,
    l_name VARCHAR(255),
    l_status VARCHAR(255),
    PRIMARY KEY (l_id)
);
GO