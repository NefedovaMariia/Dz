DROP TABLE IF EXISTS author;
CREATE TABLE author(
  ID BIGINT  AUTO_INCREMENT PRIMARY KEY,
   NAME VARCHAR(255) not null,
  constraint author_pk
    primary key (id)
);
DROP TABLE IF EXISTS books;
CREATE TABLE books(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  author_id INT,
  genre_id INT,
  TITLE VARCHAR(255)
);
DROP TABLE IF EXISTS genre;
create table genre
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) not null,
  constraint genres_pk
    primary key (id)
);


