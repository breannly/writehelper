CREATE TABLE IF NOT EXISTS labels (
    l_id BIGINT NOT NULL AUTO_INCREMENT,
    l_post_id BIGINT NOT NULL,
    l_name VARCHAR(255),
    l_status VARCHAR(255),
    PRIMARY KEY (l_id),
    FOREIGN KEY (l_post_id) REFERENCES posts(p_id)
);
GO