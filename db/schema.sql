create table if not exists post
(
    id          serial primary key not null,
    name        varchar(30),
    description text
);

create table if not exists candidate
(
    id   serial primary key not null,
    name varchar(30)
);

-- delete from post;
-- delete from candidate;
--
-- ALTER SEQUENCE post_id_seq RESTART WITH 1;
-- ALTER SEQUENCE candidate_id_seq RESTART WITH 1;