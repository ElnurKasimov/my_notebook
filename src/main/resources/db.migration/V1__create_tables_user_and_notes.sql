CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    user_name VARCHAR(50),
    password VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE notes (
    id UUID NOT NULL PRIMARY KEY,
    content VARCHAR(10000),
    name VARCHAR(50),
    html VARCHAR(255),
    access VARCHAR(20),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id)
);