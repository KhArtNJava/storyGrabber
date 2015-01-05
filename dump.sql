CREATE DATABASE  IF NOT EXISTS `grabberRasskazov` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `grabberRasskazov`;
-- MySQL dump 10.13  Distrib 5.6.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: grabberRasskazov
-- ------------------------------------------------------
-- Server version	5.6.22

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
-- Table structure for table `stories`
--

DROP TABLE IF EXISTS `stories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stories` (
  `story_id` int(11) NOT NULL AUTO_INCREMENT,
  `storyName` varchar(777) DEFAULT NULL,
  `storyText` longtext,
  `dateAdded` datetime DEFAULT NULL,
  `size` float DEFAULT NULL,
  `OldId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`story_id`),
  KEY `oldId_idx` (`OldId`)
) ENGINE=InnoDB AUTO_INCREMENT=21125 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storiesToCategoriessRelations`
--

DROP TABLE IF EXISTS `storiesToCategoriessRelations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storiesToCategoriessRelations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `story_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rasskazIdFK_idx` (`story_id`),
  KEY `catIdFK_idx` (`category_id`),
  CONSTRAINT `cat_fk` FOREIGN KEY (`category_id`) REFERENCES `storyCategories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `st_fk` FOREIGN KEY (`story_id`) REFERENCES `stories` (`story_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32589 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storiesToWritersRelations`
--

DROP TABLE IF EXISTS `storiesToWritersRelations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storiesToWritersRelations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `story_Id` int(11) DEFAULT NULL,
  `writer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rasskazIdFK_idx` (`story_Id`),
  KEY `q1_idx` (`story_Id`),
  KEY `q2_idx` (`writer_id`),
  CONSTRAINT `stw_1_fk` FOREIGN KEY (`story_Id`) REFERENCES `stories` (`story_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stw_2_fk` FOREIGN KEY (`writer_id`) REFERENCES `storyWriters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storyCategories`
--

DROP TABLE IF EXISTS `storyCategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storyCategories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(777) DEFAULT NULL,
  `oldId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `oldId_UNIQUE` (`oldId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storyWriters`
--

DROP TABLE IF EXISTS `storyWriters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storyWriters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `writerName` varchar(777) DEFAULT NULL,
  `writerEmail` varchar(777) DEFAULT NULL,
  `oldId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5407 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'grabberRasskazov'
--

--
-- Dumping routines for database 'grabberRasskazov'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-04  1:16:56
