create table like(
    id BIGSERIAL primary key,
    id_post BIGINT not null,



    foreign key (id_post) references post(id_post)


);