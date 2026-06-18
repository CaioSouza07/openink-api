CREATE TABLE likes (
    id BIGSERIAL PRIMARY KEY,
    id_post BIGINT NOT NULL,
    id_user BIGINT NOT NULL,

    CONSTRAINT fk_like_post
        FOREIGN KEY (id_post)
            REFERENCES posts (id),

    CONSTRAINT fk_like_user
        FOREIGN KEY (id_user)
            REFERENCES users (id),

    CONSTRAINT uk_like_user_post
        UNIQUE (id_user, id_post)
);