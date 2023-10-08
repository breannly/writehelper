CREATE TABLE IF NOT EXISTS posts (
    p_id BIGINT NOT NULL AUTO_INCREMENT,
    p_writer_id BIGINT NOT NULL,
    p_content VARCHAR(255),
    p_created TIMESTAMP,
    p_updated TIMESTAMP,
    p_status VARCHAR(255),
    PRIMARY KEY(p_id),
    FOREIGN KEY(p_writer_id) REFERENCES writers(w_id)
);
GO