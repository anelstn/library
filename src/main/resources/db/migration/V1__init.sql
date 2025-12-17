CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE genre (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    pages INT NOT NULL,
    year INT NOT NULL,
    author_id BIGINT REFERENCES author(id)
);

CREATE TABLE book_genre (
    book_id BIGINT NOT NULL REFERENCES book(id),
    genre_id BIGINT NOT NULL REFERENCES genre(id),
    PRIMARY KEY (book_id, genre_id)
);