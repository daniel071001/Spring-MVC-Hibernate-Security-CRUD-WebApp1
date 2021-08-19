CREATE TABLE users(
    id serial primary key,
    username varchar(255) unique not null,
    password varchar(255) not null,
    country varchar(255) not null
);

INSERT INTO users (username, password, country)
VALUES ('user', '1234567890','USA'),
       ('admin', '1234567899','KGZ');

CREATE TABLE roles(
    id serial primary key,
    role varchar(255) not null
);

INSERT INTO roles (role)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

CREATE TABLE user_roles(
    user_id int unique not null,
    role_id int unique not null,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2);

