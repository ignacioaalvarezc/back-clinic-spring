create table patient(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone_number varchar(20) not null,
    dni varchar(14) not null unique,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,
    status tinyint not null,
    primary key(id)
);