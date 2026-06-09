create table contents (
    id BIGSERIAL primary key,
    id_post BIGINT not null,
    texto varchar(255),

    constraint fk_content_post,
    foreing key (id_post),
    references posts (id_post)
);