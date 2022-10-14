DROP TABLE IF EXISTS reader_books CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS readers CASCADE;

CREATE TABLE IF NOT EXISTS `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `author` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `books`  ADD `page_count` INT;

ALTER TABLE `books`  ADD `description` VARCHAR(1000);


CREATE TABLE IF NOT EXISTS `readers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(200) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `reader_books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `reader_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `book_out_date` DATETIME NOT NULL,
  `book_return_date` DATETIME,
  PRIMARY KEY (id)
);


ALTER TABLE `reader_books`
ADD FOREIGN KEY (`book_id`) REFERENCES `books`(`id`);

ALTER TABLE `reader_books`
ADD FOREIGN KEY (`reader_id`) REFERENCES `readers`(`id`);
