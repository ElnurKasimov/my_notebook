CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    user_name varchar(50),
    password varchar(255),
    email VARCHAR(255)
);

CREATE TABLE notes (
    id UUID NOT NULL PRIMARY KEY,
    content varchar(10000),
    name varchar(50),
    access varchar(20),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id)
);