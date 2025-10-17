create table if not exists location(
    id bigint primary key,
    address varchar(255),
    room varchar(255),
    shelf varchar(255)
);
create sequence location_seq start with 1 increment by 1;

create table if not exists Book (
    id bigint primary key,
    title varchar(255),
    location_id bigint
);
create sequence book_seq start with 1 increment by 1;

create table if not exists Author (
    id bigint primary key,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255)
);
create sequence author_seq start with 1 increment by 1;

create table if not exists book_author (
    book_id bigint,
    author_id bigint
);

