CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE permissions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users_permissions (
    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

INSERT INTO users (email, username, password) VALUES ('admin@mail.com', 'admin', '$2a$10$7Qh8yXQXk5Rkz7Z8s7Z8sOUqP0H8Z0XK9zE3Z6s9uFvQw8J1OeYy');
INSERT INTO permissions (name) VALUES ('ROLE_USER');
INSERT INTO users_permissions (user_id, permission_id) VALUES (1, 1);
