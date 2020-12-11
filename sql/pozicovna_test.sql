CREATE DATABASE  IF NOT EXISTS `pozicovna_test` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pozicovna_test`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: pozicovna
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `akcia`
--

DROP TABLE IF EXISTS `akcia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `akcia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naradie_id` int NOT NULL,
  `pouzivatel_id` int NOT NULL,
  `druh_akcie_id` int NOT NULL,
  `ziadost` datetime DEFAULT NULL,
  `pozicanie` datetime DEFAULT NULL,
  `zamietnutie` datetime DEFAULT NULL,
  `vratenie` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ziadost_naradie1_idx` (`naradie_id`),
  KEY `fk_ziadost_pouzivatel1_idx` (`pouzivatel_id`),
  CONSTRAINT `fk_ziadost_naradie1` FOREIGN KEY (`naradie_id`) REFERENCES `naradie` (`id`),
  CONSTRAINT `fk_ziadost_pouzivatel1` FOREIGN KEY (`pouzivatel_id`) REFERENCES `pouzivatel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `akcia`
--

LOCK TABLES `akcia` WRITE;
/*!40000 ALTER TABLE `akcia` DISABLE KEYS */;
/*!40000 ALTER TABLE `akcia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `druh_naradia`
--

DROP TABLE IF EXISTS `druh_naradia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `druh_naradia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `meno` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `meno_UNIQUE` (`meno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `druh_naradia`
--

LOCK TABLES `druh_naradia` WRITE;
/*!40000 ALTER TABLE `druh_naradia` DISABLE KEYS */;
/*!40000 ALTER TABLE `druh_naradia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategoria`
--

DROP TABLE IF EXISTS `kategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nazov` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazov_UNIQUE` (`nazov`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategoria`
--

LOCK TABLES `kategoria` WRITE;
/*!40000 ALTER TABLE `kategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `kategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategoria_druh_naradie`
--

DROP TABLE IF EXISTS `kategoria_druh_naradie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategoria_druh_naradie` (
  `druh_naradia_id` int NOT NULL,
  `kategoria_id` int NOT NULL,
  PRIMARY KEY (`kategoria_id`,`druh_naradia_id`),
  KEY `fk_kategoria_druh_naradie_kategoria1_idx` (`kategoria_id`),
  KEY `fk_kategoria_druh_naradie_druh_naradia1` (`druh_naradia_id`),
  CONSTRAINT `fk_kategoria_druh_naradie_druh_naradia1` FOREIGN KEY (`druh_naradia_id`) REFERENCES `druh_naradia` (`id`),
  CONSTRAINT `fk_kategoria_druh_naradie_kategoria1` FOREIGN KEY (`kategoria_id`) REFERENCES `kategoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategoria_druh_naradie`
--

LOCK TABLES `kategoria_druh_naradie` WRITE;
/*!40000 ALTER TABLE `kategoria_druh_naradie` DISABLE KEYS */;
/*!40000 ALTER TABLE `kategoria_druh_naradie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `naradie`
--

DROP TABLE IF EXISTS `naradie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `naradie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `znacka` varchar(45) NOT NULL,
  `typ` varchar(45) NOT NULL,
  `druh_naradia_id` int NOT NULL,
  `vlastnik_id` int NOT NULL,
  `popis` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_naradie_druh_naradia1_idx` (`druh_naradia_id`),
  KEY `fk_naradie_user1_idx` (`vlastnik_id`),
  CONSTRAINT `fk_naradie_druh_naradia1` FOREIGN KEY (`druh_naradia_id`) REFERENCES `druh_naradia` (`id`),
  CONSTRAINT `fk_naradie_user1` FOREIGN KEY (`vlastnik_id`) REFERENCES `pouzivatel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naradie`
--

LOCK TABLES `naradie` WRITE;
/*!40000 ALTER TABLE `naradie` DISABLE KEYS */;
/*!40000 ALTER TABLE `naradie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pouzivatel`
--

DROP TABLE IF EXISTS `pouzivatel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pouzivatel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `meno` varchar(45) NOT NULL,
  `priezvisko` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `sol_hash` varchar(64) NOT NULL,
  `heslo_hash` varchar(65) NOT NULL,
  `tel_cislo` varchar(45) NOT NULL,
  `okres` varchar(45) NOT NULL,
  `mesto` varchar(45) DEFAULT NULL,
  `ulica` varchar(45) DEFAULT NULL,
  `cislo_domu` varchar(10) DEFAULT NULL,
  `psc` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pouzivatel`
--

LOCK TABLES `pouzivatel` WRITE;
/*!40000 ALTER TABLE `pouzivatel` DISABLE KEYS */;
INSERT INTO `pouzivatel` VALUES (1,'Samuel','Baran','samo@gamil.com','asdfg','hesloheslo','061545313','Vranov','Hanusovne','','','094 35'),(2,'Dominik','Dzama','domos2345@gmail.com','yxcv','topHeslo','0918062677','Prešov','Bystré','Druzstevna','',''),(3,'František','Kurimský','felko@gmail.com','qwert','feroHeslo','09516545','Košice','','Kukučínova','211','091 51'),(4,'a','a','a@','$2a$10$XwHL2bIT28k08LD6MN7vt.','$2a$10$XwHL2bIT28k08LD6MN7vt.7tNXnqMbv0QRhSIGLJ299n1m10blk8W','01','vr','bb','dr','423','094');
/*!40000 ALTER TABLE `pouzivatel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-11 16:57:21
