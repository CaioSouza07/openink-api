create table like(
    id BIGSERIAL primary key,
    id_post BIGINT not null,
    id_user BIGINT not null,


        constraint fk_like_post,
        foreign key (id_post),
        references posts (id_post),

        constraint fk_like_user,
        foreing key (id_user),
        references users (id_user),

        constraint uk_like_user_post,
        UNIQUE (id_user, id_post)
);