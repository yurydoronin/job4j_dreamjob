create table if not exists users
(
    id          int not null,
    name        varchar(30),
    create_date timestamp not null default now()
);