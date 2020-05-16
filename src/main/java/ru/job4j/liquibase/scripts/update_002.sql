create table if not exists dreamjob_users
(
    id       serial primary key not null,
    name     varchar(30),
    email    varchar(30),
    password varchar(30)
);

insert into dreamjob_users (name, email, password)
values ('Юрий', 'yuri@gmail.com', '111'),
       ('Маша', 'masha@mail.ru', '222'),
       ('Катя', 'cat@yahoo.com', '333');