CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(254) NOT NULL,
    user_id BIGINT NOT NULL,
    read_time INT,
    CONTRAINT fk_post_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);