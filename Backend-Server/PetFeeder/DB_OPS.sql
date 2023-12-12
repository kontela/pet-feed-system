CREATE DATABASE  IF NOT EXISTS `getpet_directory`;
USE `petfeeder_db`;


CREATE TABLE file_meta (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    timeOfDay DATETIME,
    uploadDir VARCHAR(255)
);


CREATE DATABASE  IF NOT EXISTS `petfeeder_db`;
USE `petfeeder_db`;

DROP TABLE IF EXISTS `feeding_schedule`;

CREATE TABLE `feeding_schedule` (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time_of_day TIME
);

