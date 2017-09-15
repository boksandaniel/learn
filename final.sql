CREATE DATABASE  IF NOT EXISTS `Proiect2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `Proiect2`;
-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: Proiect2
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.17.04.1

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
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignments` (
  `assignmentId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  PRIMARY KEY (`assignmentId`),
  KEY `projects_projectId_fk` (`projectId`),
  KEY `users_userId_fk` (`userId`),
  CONSTRAINT `projects_projectId_fk` FOREIGN KEY (`projectId`) REFERENCES `projects` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_userId_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (11,3,1),(12,2,2),(13,1,2);
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobs` (
  `jobId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (10,'Admin','ROLE_ADMIN'),(13,'Tester','ROLE_USER');
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(45) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Test','2017-08-10','2017-08-11'),(2,'Timetable','2017-08-16','2017-09-15');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetables`
--

DROP TABLE IF EXISTS `timetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timetables` (
  `timetableId` int(11) NOT NULL AUTO_INCREMENT,
  `assignmentId` int(11) NOT NULL,
  `date` date NOT NULL,
  `hoursWorked` int(11) NOT NULL,
  PRIMARY KEY (`timetableId`),
  KEY `assignments_assignmentId_fk` (`assignmentId`),
  CONSTRAINT `assignments_assignmentId_fk` FOREIGN KEY (`assignmentId`) REFERENCES `assignments` (`assignmentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetables`
--

LOCK TABLES `timetables` WRITE;
/*!40000 ALTER TABLE `timetables` DISABLE KEYS */;
INSERT INTO `timetables` VALUES (1,11,'2017-09-10',5),(2,12,'2017-09-10',5),(3,13,'2017-09-11',8),(4,11,'2017-09-10',8),(5,11,'2017-09-10',8),(6,12,'2017-09-10',8),(7,11,'2017-09-10',8),(8,13,'2017-09-10',8),(9,13,'2017-11-01',45),(10,13,'2017-09-10',45),(11,13,'2017-09-10',45),(12,13,'2017-09-10',45);
/*!40000 ALTER TABLE `timetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `jobId` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `jobs_jobId_fk` (`jobId`),
  CONSTRAINT `jobs_jobId_fk` FOREIGN KEY (`jobId`) REFERENCES `jobs` (`jobId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ion','Ionoi','admin1','admin',1,10,'admin@admin.com'),(2,'Samsung Galaxy','Nistor','lead_MM','12345',1,13,'user1@admin.com'),(3,'Ardelean','2000 euro','bossu_bossilor','boss',1,13,'bossu@boss.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-15 10:35:35
