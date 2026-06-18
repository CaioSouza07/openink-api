CREATE TABLE reports (
     id        BIGSERIAL     PRIMARY KEY,
     type      VARCHAR(100)  NOT NULL,
     user_id   BIGINT        NOT NULL,
     post_id   BIGINT        NOT NULL,

     CONSTRAINT fk_report_user
         FOREIGN KEY (user_id)
             REFERENCES users(id),

     CONSTRAINT fk_report_post
         FOREIGN KEY (post_id)
             REFERENCES posts(id)
);