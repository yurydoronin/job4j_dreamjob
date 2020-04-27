create table if not exists accidents
(
    id      int not null,
    name    varchar(30),
    text    varchar(100) default 'text',
    address varchar(100)
--     create_date timestamp not null default now()
);

insert into accidents (id, name, text, address)
values (1, 'accident_1', 'text_1', 'address_1'),
       (2, 'accident_2', 'text_2', 'address_2'),
       (3, 'accident_3', 'text_3', 'address_3'),
       (4, 'accident_4', 'text_4', 'address_4');