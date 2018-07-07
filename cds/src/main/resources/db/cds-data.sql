-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for crop-disease-solution
DROP DATABASE IF EXISTS `crop-disease-solution`;
CREATE DATABASE IF NOT EXISTS `crop-disease-solution` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `crop-disease-solution`;

-- Dumping structure for table crop-disease-solution.attachment
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE IF NOT EXISTS `attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(255) DEFAULT NULL,
  `name_mm` varchar(500) DEFAULT NULL,
  `description` text,
  `created_by` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table crop-disease-solution.crop
DROP TABLE IF EXISTS `crop`;
CREATE TABLE IF NOT EXISTS `crop` (
  `crop_id` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(255) NOT NULL,
  `name_mm` varchar(500) DEFAULT NULL,
  `bio_name` varchar(255) DEFAULT NULL,
  `description` text,
  `created_by` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`crop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table crop-disease-solution.crop_attachment
DROP TABLE IF EXISTS `crop_attachment`;
CREATE TABLE IF NOT EXISTS `crop_attachment` (
  `crop_id` int(11) NOT NULL,
  `attachment_id` int(11) NOT NULL,
  KEY `attachment_id` (`attachment_id`),
  KEY `crop_id` (`crop_id`),
  CONSTRAINT `crop_attachment_ibfk_1` FOREIGN KEY (`attachment_id`) REFERENCES `attachment` (`attachment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `crop_attachment_ibfk_2` FOREIGN KEY (`crop_id`) REFERENCES `crop` (`crop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table crop-disease-solution.disease
DROP TABLE IF EXISTS `disease`;
CREATE TABLE IF NOT EXISTS `disease` (
  `disease_id` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(255) NOT NULL,
  `name_mm` varchar(500) DEFAULT NULL,
  `description` text,
  `created_by` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`disease_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table crop-disease-solution.disease_attachment
DROP TABLE IF EXISTS `disease_attachment`;
CREATE TABLE IF NOT EXISTS `disease_attachment` (
  `disease_id` int(11) NOT NULL,
  `attachment_id` int(11) NOT NULL,
  KEY `attachment_id` (`attachment_id`),
  KEY `disease_id` (`disease_id`),
  CONSTRAINT `disease_attachment_ibfk_1` FOREIGN KEY (`attachment_id`) REFERENCES `attachment` (`attachment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `disease_attachment_ibfk_2` FOREIGN KEY (`disease_id`) REFERENCES `disease` (`disease_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table crop-disease-solution.solving_attachment
DROP TABLE IF EXISTS `solving_attachment`;
CREATE TABLE IF NOT EXISTS `solving_attachment` (
  `solve_id` int(11) NOT NULL,
  `attachment_id` int(11) NOT NULL,
  KEY `attachment_id` (`attachment_id`),
  KEY `solve_id` (`solve_id`),
  CONSTRAINT `solving_attachment_ibfk_1` FOREIGN KEY (`attachment_id`) REFERENCES `attachment` (`attachment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `solving_attachment_ibfk_2` FOREIGN KEY (`solve_id`) REFERENCES `solving_way` (`solve_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table crop-disease-solution.solving_way
DROP TABLE IF EXISTS `solving_way`;
CREATE TABLE IF NOT EXISTS `solving_way` (
  `solve_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  PRIMARY KEY (`solve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
