CREATE TABLE post_labels (
    post_id BIGINT NOT NULL REFERENCES posts(id),
    label_id BIGINT NOT NULL REFERENCES labels(id),
    CONSTRAINT post_label_unique UNIQUE (post_id, label_id)
);