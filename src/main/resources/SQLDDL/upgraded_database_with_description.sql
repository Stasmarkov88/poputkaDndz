SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema easygo_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `easygo_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `easygo_db` ;

-- -----------------------------------------------------
-- Table `easygo_db`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`USER` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `gender` TINYINT(1) NOT NULL,
  `login` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `car` VARCHAR(128) NULL DEFAULT NULL,
  `phone_number` VARCHAR(20) NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `easygo_db`.`TRIP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`TRIP` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start_trip` TIMESTAMP NOT NULL,
  `driver_id` BIGINT(20) NOT NULL,
  `car_capacity` INT NOT NULL,
  `price` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `driver_id_idx` (`driver_id` ASC),
  CONSTRAINT `driver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `easygo_db`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `easygo_db`.`PNP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`PNP` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `description` VARCHAR(255) NULL,
  `isleft` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `pnp_id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `easygo_db`.`TRIP_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`TRIP_USER` (
  `user_id` BIGINT(20) NOT NULL,
  `trip_id` BIGINT(20) NOT NULL,
  INDEX `trip_id_idx` (`trip_id` ASC),
  INDEX `person_id_idx` (`user_id` ASC),
  CONSTRAINT `trip_id`
    FOREIGN KEY (`trip_id`)
    REFERENCES `easygo_db`.`TRIP` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `person_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `easygo_db`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `easygo_db`.`TRIP_POINTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`TRIP_POINTS` (
  `trip_id` BIGINT(20) NULL DEFAULT NULL,
  `pnp_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`trip_id`, `pnp_id`),
  INDEX `pnp_idx` (`pnp_id` ASC),
  CONSTRAINT `trip`
    FOREIGN KEY (`trip_id`)
    REFERENCES `easygo_db`.`TRIP` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pnp`
    FOREIGN KEY (`pnp_id`)
    REFERENCES `easygo_db`.`PNP` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `easygo_db`.`USER_ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`USER_ROLE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(128) NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `login_idx` (`login` ASC),
  CONSTRAINT `login`
    FOREIGN KEY (`login`)
    REFERENCES `easygo_db`.`USER` (`login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `easygo_db`.`PASSENGER_LANDING`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easygo_db`.`PASSENGER_LANDING` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `trip_passenger_id` BIGINT(20) NOT NULL,
  `passenger_id` BIGINT(20) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT 'нет описания',
  PRIMARY KEY (`id`),
  INDEX `trip_passenger_id` (`passenger_id` ASC),
  INDEX `passenger_id` (`trip_passenger_id` ASC),
  UNIQUE INDEX `id_unique` (`id` ASC),
  CONSTRAINT `trip_passenger_id`
    FOREIGN KEY (`trip_passenger_id`)
    REFERENCES `easygo_db`.`TRIP` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `passenger_id`
    FOREIGN KEY (`passenger_id`)
    REFERENCES `easygo_db`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
