create table if not exists users(
    id bigserial primary key,
    login varchar(255) not null,
    role varchar(15) not null
);

create table if not exists parcels (
    id bigserial primary key,
    requestor bigint references users not null,
    recipient bigint references users not null,
    courier bigint references couriers,
    destination varchar(20) not null,
    current_loc varchar(20) not null,
    price bigint not null,
    status varchar(63) not null
);

create table if not exists couriers(
    id bigserial primary key,
    user_info bigint not null references users,
    status varchar(15) not null
);