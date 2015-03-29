CREATE DATABASE IF NOT EXISTS `vars_schema` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vars_schema`;
-- MySQL dump 10.13 Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1 Database: vars_schema
-- ------------------------------------------------------
-- Server version 5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`project_id` int(11) NOT NULL,
`tester_id` int(11) NOT NULL,
`description` varchar(200) DEFAULT NULL,
`amount` decimal(10,2) NOT NULL DEFAULT '0.00',
PRIMARY KEY (`id`),
KEY `fk_bid_project_id_idx` (`project_id`),
KEY `fk_bid_tester_id_idx` (`tester_id`),
CONSTRAINT `fk_bid_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `fk_bid_tester_id` FOREIGN KEY (`tester_id`) REFERENCES `tester` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `developer`
--

DROP TABLE IF EXISTS `developer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developer` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL,
PRIMARY KEY (`id`),
KEY `fk_developer_user_id_idx` (`user_id`),
CONSTRAINT `fk_developer_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(100) NOT NULL,
`description` varchar(200) DEFAULT NULL,
`domain` varchar(200) DEFAULT NULL,
`devp_id` int(11) NOT NULL,
`tester_id` int(11) DEFAULT NULL,
`project_url` VARCHAR(100) DEFAULT NULL,
`min_budget` INT(10) NOT NULL,
`max_budget` INT(10) NOT NULL,
`project_skills` VARCHAR(200) DEFAULT NULL,
`project_users` VARCHAR(100) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `fk_project_devp_id_idx` (`devp_id`),
KEY `fk_project_tester_id_idx` (`tester_id`),
CONSTRAINT `fk_project_tester_id` FOREIGN KEY (`tester_id`) REFERENCES `tester` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `fk_project_devp_id` FOREIGN KEY (`devp_id`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tester`
--

DROP TABLE IF EXISTS `tester`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tester` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `fk_tester_user_id_idx` (`user_id`),
CONSTRAINT `fk_tester_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tester_rating`
--
DROP TABLE IF EXISTS `tester_rating`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tester_rating` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`tester_id` int(11) NOT NULL,
`developer_id` int(11) NOT NULL,
`rating` decimal(3,2) NOT NULL,
`ratingdate` VARCHAR(100) NOT NULL, 
PRIMARY KEY (`id`),
KEY `fk_rating_devp_id_idx` (`developer_id`),
KEY `fk_rating_tester_id_idx` (`tester_id`),
CONSTRAINT `fk_rating_devp_id` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `fk_rating_tester_id` FOREIGN KEY (`tester_id`) REFERENCES `tester` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(100) NOT NULL,
`password` varchar(100) NOT NULL,
`first_name` varchar(100) DEFAULT NULL,
`last_name` varchar(100) DEFAULT NULL,
`istester` tinyint(4) NOT NULL,
`linkedin_id` varchar(50) DEFAULT NULL,
`linkedin_url` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-21 11:49:31

ALTER TABLE `vars_schema`.`project` 
ADD COLUMN `results` VARCHAR(200) NULL AFTER `project_users`;
