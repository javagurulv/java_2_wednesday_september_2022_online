SET
    @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 1;
SET
    @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 1;
SET
    @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

DROP DATABASE IF EXISTS `rentApp`;

CREATE SCHEMA IF NOT EXISTS `rentApp` DEFAULT CHARACTER SET utf8mb4;
USE
    `rentApp`;

CREATE TABLE IF NOT EXISTS vehicle
(
    `id`                BIGINT        NOT NULL AUTO_INCREMENT,
    `vehicle_type`      VARCHAR(100)  NOT NULL,
    `brand`             VARCHAR(100)  NOT NULL,
    `model`             VARCHAR(100)  NOT NULL,
    `is_available`      BIT(1)        NOT NULL,
    `year`              YEAR          NOT NULL,
    `colour`            VARCHAR(100)  NOT NULL,
    `price`             DECIMAL(5, 2) NOT NULL,
    `engine_type`       VARCHAR(100)  NOT NULL,
    `plate_number`      CHAR(30)      NOT NULL,
    `transmission_type` VARCHAR(100)  NOT NULL,

    INDEX (`vehicle_type`),
    INDEX (`is_available`),
    INDEX (`transmission_type`),
    UNIQUE INDEX (`plate_number`),
    PRIMARY KEY (`id`, `vehicle_type`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS passenger_car
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT,
    `vehicle_id`       BIGINT       NOT NULL,
    `vehicle_type`     VARCHAR(100) NOT NULL DEFAULT 'PASSENGER_CAR',
    `passenger_amount` TINYINT      NOT NULL,
    `baggageAmount`    TINYINT      NOT NULL,
    `doorsAmount`      TINYINT      NOT NULL,
    `air_conditioning` BIT(1)       NOT NULL,

    CHECK (`vehicle_type` = 'PASSENGER_CAR'),
    INDEX (`passenger_amount`),
    INDEX (`baggageAmount`),
    INDEX (`doorsAmount`),
    INDEX (`air_conditioning`),
    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`, `vehicle_type`) REFERENCES vehicle (`id`, `vehicle_type`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS mini_bus
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT,
    `vehicle_id`       BIGINT       NOT NULL,
    `vehicle_type`     VARCHAR(100) NOT NULL DEFAULT 'MINIBUS',
    `passenger_amount` TINYINT      NOT NULL,
    `baggageAmount`    TINYINT      NOT NULL,
    `doorsAmount`      TINYINT      NOT NULL,
    `air_conditioning` BIT(1)       NOT NULL,

    CHECK (`vehicle_type` = 'MINIBUS'),
    INDEX (`passenger_amount`),
    INDEX (`baggageAmount`),
    INDEX (`doorsAmount`),
    INDEX (`air_conditioning`),
    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`, `vehicle_type`) REFERENCES vehicle (`id`, `vehicle_type`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS motorcycle
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT,
    `vehicle_id`       BIGINT       NOT NULL,
    `vehicle_type`     VARCHAR(100) NOT NULL DEFAULT 'MOTORCYCLE',
    `passenger_amount` TINYINT      NOT NULL,

    CHECK (`vehicle_type` = 'MOTORCYCLE'),
    INDEX (`passenger_amount`),
    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`, `vehicle_type`) REFERENCES vehicle (`id`, `vehicle_type`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS car_trailer
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT,
    `vehicle_id`      BIGINT       NOT NULL,
    `vehicle_type`    VARCHAR(100) NOT NULL DEFAULT 'CAR_TRAILER',
    `deck_width_cm`   SMALLINT     NOT NULL,
    `deck_length_cm`  SMALLINT     NOT NULL,
    `deck_height_cm`  SMALLINT     NOT NULL,
    `empty_weight_kg` SMALLINT     NOT NULL,
    `max_weight_kg`   SMALLINT     NOT NULL,

    CHECK (`vehicle_type` = 'CAR_TRAILER'),
    INDEX (`deck_width_cm`),
    INDEX (`deck_length_cm`),
    INDEX (`deck_height_cm`),
    INDEX (`empty_weight_kg`),
    INDEX (`max_weight_kg`),
    UNIQUE INDEX (`vehicle_id`),
    FOREIGN KEY (`vehicle_id`, `vehicle_type`) REFERENCES vehicle (`id`, `vehicle_type`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;

CREATE TABLE IF NOT EXISTS client
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `personal_id` VARCHAR(12)  NOT NULL,
    `name`        VARCHAR(100) NOT NULL,
    `surname`     VARCHAR(100) NOT NULL,
    `email`       VARCHAR(255) NOT NULL,
    `phone`       VARCHAR(20)  NOT NULL,

    UNIQUE INDEX (`personal_id`),
    UNIQUE INDEX (`email`),
    PRIMARY KEY (`id`)
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
    `cost`       DECIMAL(5, 2) NOT NULL,

    UNIQUE INDEX (`client_id`, `vehicle_id`, `start_date`),
    UNIQUE INDEX (`client_id`, `vehicle_id`, `end_date`),
    FOREIGN KEY (`client_id`) REFERENCES client (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`vehicle_id`) REFERENCES vehicle (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0001;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
