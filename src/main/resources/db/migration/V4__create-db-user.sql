CREATE TABLE user(
    id bigint NOT NULL auto_increment,
    login VARCHAR(100) not null,
    password VARCHAR(100) not null,
    PRIMARY KEY(id)
);