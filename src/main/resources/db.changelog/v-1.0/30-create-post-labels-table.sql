CREATE TABLE post_labels (
    post_id BIGINT NOT NULL,
    label_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (label_id) REFERENCES labels(id),
    CONSTRAINT post_label_unique UNIQUE (post_id, label_id)
);
GO