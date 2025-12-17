INSERT INTO author (name) VALUES ('George Orwell');
INSERT INTO author (name) VALUES ('Jane Austen');
INSERT INTO author (name) VALUES ('Mirjaqip Dulatuli');

INSERT INTO genre (name) VALUES ('Dystopian fiction');
INSERT INTO genre (name) VALUES ('Romantic novel');
INSERT INTO genre (name) VALUES ('Social novel');

INSERT INTO book (title, pages, year, author_id) VALUES ('1984', 328, 1949, 1);
INSERT INTO book (title, pages, year, author_id) VALUES ('Pride and Prejudice', 438, 1813, 2);
INSERT INTO book (title, pages, year, author_id) VALUES ('Baqytsyz Jamal', 250, 1910, 3);

INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (2, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (3, 2);