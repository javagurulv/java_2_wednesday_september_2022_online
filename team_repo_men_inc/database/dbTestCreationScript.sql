SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `repo_men_test` DEFAULT CHARACTER SET utf8 ;
USE `repo_men_test` ;

CREATE TABLE IF NOT EXISTS `debtors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `lists` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `debtor_id` BIGINT NOT NULL,
  `item_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

ALTER TABLE `lists` ADD FOREIGN KEY (`debtor_id`) REFERENCES `debtors`(`id`) ON DELETE CASCADE;
ALTER TABLE `lists` ADD FOREIGN KEY (`item_id`) REFERENCES `items`(`id`) ON DELETE CASCADE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;