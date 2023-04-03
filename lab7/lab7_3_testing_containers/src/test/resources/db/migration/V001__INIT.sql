CREATE TABLE book (
  id BIGSERIAL PRIMARY KEY,
  title varchar(255) not null,
  author varchar(255) not null,
  publisher varchar(255) not null
);