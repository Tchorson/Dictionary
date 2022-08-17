-- MySQL Script generated by MySQL Workbench
-- Wed Aug 17 15:26:16 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
-- Author: Mateusz Tchorek

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Login` (
  `Login` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NULL,
  PRIMARY KEY (`Login`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Vocabulary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Vocabulary` (
  `Id` INT NOT NULL,
  `Word` VARCHAR(100) NULL,
  `Translation` VARCHAR(150) NULL,
  `Language` VARCHAR(45) NULL,
  `User` VARCHAR(255) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_user_login_idx` (`User` ASC) VISIBLE,
  CONSTRAINT `fk_user_login`
    FOREIGN KEY (`User`)
    REFERENCES `mydb`.`Login` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Sessions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Sessions` (
  `Id` INT NOT NULL,
  `User` VARCHAR(255) NULL,
  `Words_amount` INT NULL,
  `Correct_words` INT NULL,
  `Learn_Type` VARCHAR(45) NULL,
  `Language` VARCHAR(45) NULL,
  `Time_Start` TIMESTAMP(14) NULL,
  `Time_Stop` TIMESTAMP(14) NULL,
  `Words_List_Id` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_user_login_idx` (`User` ASC) VISIBLE,
  CONSTRAINT `fk_user_login`
    FOREIGN KEY (`User`)
    REFERENCES `mydb`.`Login` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Statistics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Statistics` (
  `User` VARCHAR(255) NOT NULL,
  `Words` INT NULL,
  `Languages` INT NULL,
  `Progress` INT NULL,
  `Avg_Time` INT NULL,
  `Sessions` INT NULL,
  `Misspells` INT NULL,
  PRIMARY KEY (`User`),
  CONSTRAINT `fk_user_login`
    FOREIGN KEY (`User`)
    REFERENCES `mydb`.`Login` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Vocabulary_Sessions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Vocabulary_Sessions` (
  `Vocabulary_Id` INT NULL,
  `Session_Id` INT NULL,
  INDEX `fk_vocabulary_vocabulary_Id_idx` (`Vocabulary_Id` ASC) VISIBLE,
  INDEX `fk_id_session_id_idx` (`Session_Id` ASC) VISIBLE,
  CONSTRAINT `fk_id_vocabulary_Id`
    FOREIGN KEY (`Vocabulary_Id`)
    REFERENCES `mydb`.`Vocabulary` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_session_id`
    FOREIGN KEY (`Session_Id`)
    REFERENCES `mydb`.`Sessions` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
