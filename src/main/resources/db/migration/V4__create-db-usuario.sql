CREATE TABLE user(
    id bigint NOT NULL auto_increment,
    login VARCHAR(100) not null,
    clave VARCHAR(100) not null unique,
    PRIMARY KEY(id)
);