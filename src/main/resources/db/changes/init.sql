create table if not exists users(
    username varchar(63) primary key,
    password varchar(500) not null,
    enabled boolean not null default true
);
create table if not exists authorities(
    username varchar(63) not null references users,
    authority varchar(15) not null
);

create unique index ix_unique_authority on authorities (username, authority);

create table if not exists couriers(
    id bigserial primary key,
    "user" varchar(63) not null references users,
    status varchar(15) not null
);

create table if not exists parcels (
    id bigserial primary key,
    requestor varchar(63) references users not null,
    courier bigint references couriers,
    destination varchar(20) not null,
    current_loc varchar(20) not null,
    price bigint not null,
    status varchar(63) not null,
    created timestamp with time zone not null,
    updated timestamp with time zone not null
);