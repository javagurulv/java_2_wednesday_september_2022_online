SET
    @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 1;
SET
    @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 1;
SET
    @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `rentApp` DEFAULT CHARACTER SET utf8mb4;
USE
    `rentApp`;

CREATE TABLE IF NOT EXISTS vehicle
(
    `id`                   BIGINT        NOT NULL AUTO_INCREMENT,
    `vehicle_type`         VARCHAR(100)  NOT NULL,
    `name`                 VARCHAR(100)  NOT NULL,
    `brand`                VARCHAR(100)  NOT NULL,
    `model`                VARCHAR(100)  NOT NULL,
    `is_available`         BIT(1)        NOT NULL,
    `year`                 YEAR          NOT NULL,
    `colour`               VARCHAR(100)  NOT NULL,
    `price`                DECIMAL(5, 2) NOT NULL,
    `engine_type`          VARCHAR(100)  NOT NULL,
    `plate_number`         CHAR(30)      NOT NULL,
    `transmission_type`    VARCHAR(100)  NOT NULL,
    FOREIGN KEY (`vehicle_type_id`) REFERENCES vehicle_type (`id`),
    FOREIGN KEY (`colour_id`) REFERENCES colour (`id`),
    FOREIGN KEY (`engine_type_id`) REFERENCES engine_type (`id`),
    FOREIGN KEY (`transmission_type_id`) REFERENCES transmission_type (`id`),
    UNIQUE INDEX (`plate_number`),
    UNIQUE INDEX (`vehicle_type`),
    UNIQUE INDEX (`colour`),
    UNIQUE INDEX (`engine_type`),
    UNIQUE INDEX (`transmission_type`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS passenger_car
(
    `id`               BIGINT  NOT NULL AUTO_INCREMENT,
    `vehicle_id`       BIGINT  NOT NULL,
    `passenger_amount` TINYINT NOT NULL,
    `baggageAmount`    TINYINT NOT NULL,
    `doorsAmount`      TINYINT NOT NULL,
    `air_conditioning` BIT(1)  NOT NULL,

    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`) REFERENCES vehicle (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS mini_bus
(
    `id`               BIGINT  NOT NULL AUTO_INCREMENT,
    `vehicle_id`       BIGINT  NOT NULL,
    `passenger_amount` TINYINT NOT NULL,
    `baggageAmount`    TINYINT NOT NULL,
    `doorsAmount`      TINYINT NOT NULL,
    `air_conditioning` BIT(1)  NOT NULL,

    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`) REFERENCES vehicle (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS motorcycle
(
    `id`               BIGINT  NOT NULL AUTO_INCREMENT,
    `vehicle_id`       BIGINT  NOT NULL,
    `passenger_amount` TINYINT NOT NULL,

    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`) REFERENCES vehicle (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS car_trailer
(
    `id`             BIGINT   NOT NULL AUTO_INCREMENT,
    `vehicle_id`     BIGINT   NOT NULL,
    `deck_width_cm`  SMALLINT NOT NULL,
    `deck_length_cm` SMALLINT NOT NULL,
    `deck_height_cm` SMALLINT NOT NULL,

    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`) REFERENCES vehicle (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS client
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT,
    `personal_id`    VARCHAR(12)  NOT NULL,
    `name`           VARCHAR(100) NOT NULL,
    `surname`        VARCHAR(100) NOT NULL,
    `email`          VARCHAR(255) NOT NULL,
    `phone`          VARCHAR(20)  NOT NULL,

    UNIQUE INDEX (`personal_id`),
    UNIQUE INDEX (`email`),
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS rent_deal
(
    `id`         BIGINT        NOT NULL AUTO_INCREMENT,
    `client_id`  BIGINT        NOT NULL,
    `vehicle_id` BIGINT        NOT NULL,
    `start_date` DATE          NOT NULL,
    `duration`   SMALLINT      NOT NULL,
    `end_date`   DATE          NOT NULL,
    `price`      DECIMAL(5, 2) NOT NULL,

    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`) REFERENCES vehicle (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;



SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
