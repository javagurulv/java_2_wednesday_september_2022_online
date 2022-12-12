DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS settings CASCADE;

CREATE TABLE IF NOT EXISTS `users` (
    `id`  BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(100) NOT NULL,
    `user_password`  VARCHAR(32) NOT NULL,
    `email` VARCHAR(200) NOT NULL,
    `send_reminder` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`)
)
;

CREATE TABLE IF NOT EXISTS `tasks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `task_description` VARCHAR(200) NOT NULL,
    `regularity` INTEGER NOT NULL,
    `Due_date` DATETIME NOT NULL,
    `End_date` DATETIME NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`)
	ON DELETE CASCADE
)
;

CREATE TABLE IF NOT EXISTS `settings` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `admin_password` VARCHAR(200) NOT NULL,
    `email_from` VARCHAR(200) NOT NULL,
    `email_password` VARCHAR(200) NOT NULL,
    `email_host` VARCHAR(200) NOT NULL,
    `email_port` VARCHAR(200) NOT NULL,
    `email_protocol` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id`)
)
;
