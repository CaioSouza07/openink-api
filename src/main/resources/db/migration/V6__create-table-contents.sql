CREATE TABLE contents (
    id BIGSERIAL PRIMARY KEY,
    id_post BIGINT NOT NULL,
    texto VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_content_post
        FOREIGN KEY (id_post)
        REFERENCES posts (id)
);
