CREATE DATABASE  IF NOT EXISTS `pozicovna` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pozicovna`;
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
  `ziadatel_id` int NOT NULL,
  `ziadost` datetime DEFAULT NULL,
  `pozicanie` datetime DEFAULT NULL,
  `zamietnutie` datetime DEFAULT NULL,
  `vratenie` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ziadost_naradie1_idx` (`naradie_id`),
  KEY `fk_ziadost_pouzivatel1_idx` (`ziadatel_id`),
  CONSTRAINT `fk_ziadost_naradie1` FOREIGN KEY (`naradie_id`) REFERENCES `naradie` (`id`),
  CONSTRAINT `fk_ziadost_pouzivatel1` FOREIGN KEY (`ziadatel_id`) REFERENCES `pouzivatel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `akcia`
--

LOCK TABLES `akcia` WRITE;
/*!40000 ALTER TABLE `akcia` DISABLE KEYS */;
INSERT INTO `akcia` VALUES (1,3,14,'2020-12-14 21:38:00','2020-12-16 20:20:00',NULL,NULL),(2,2,13,'2020-12-15 06:00:00',NULL,NULL,NULL),(3,1,14,'2020-12-15 06:00:00',NULL,'2020-12-16 09:00:00',NULL),(4,5,13,'2020-12-15 06:00:00',NULL,'2020-12-15 18:00:00',NULL),(5,5,10,'2020-12-16 06:00:00',NULL,NULL,NULL),(6,5,9,'2020-12-15 15:00:00',NULL,NULL,NULL),(7,4,13,'2020-12-15 06:00:00',NULL,NULL,'2020-12-18 14:30:00'),(8,6,13,'2020-12-16 07:00:00','2020-12-16 10:08:00',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `druh_naradia`
--

LOCK TABLES `druh_naradia` WRITE;
/*!40000 ALTER TABLE `druh_naradia` DISABLE KEYS */;
INSERT INTO `druh_naradia` VALUES (3,'bruska'),(4,'flexa'),(5,'spinkovač'),(1,'vrtacka'),(2,'zbijacka');
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
  `je_dostupne` tinyint(1) NOT NULL,
  `druh_naradia_id` int NOT NULL,
  `vlastnik_id` int NOT NULL,
  `popis` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_naradie_druh_naradia1_idx` (`druh_naradia_id`),
  KEY `fk_naradie_user1_idx` (`vlastnik_id`),
  CONSTRAINT `fk_naradie_druh_naradia1` FOREIGN KEY (`druh_naradia_id`) REFERENCES `druh_naradia` (`id`),
  CONSTRAINT `fk_naradie_user1` FOREIGN KEY (`vlastnik_id`) REFERENCES `pouzivatel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naradie`
--

LOCK TABLES `naradie` WRITE;
/*!40000 ALTER TABLE `naradie` DISABLE KEYS */;
INSERT INTO `naradie` VALUES (1,'BOSH','a4',1,1,13,'menšia vŕtačka na bežné použitie.'),(2,'MAKITA','t600',1,2,14,'Veľká zbíjačka na betóna pod.'),(3,'BOSH','s55',0,1,13,'Vŕtanie do betónu a tvrdých mat.'),(4,'BOSH','a4',1,1,10,NULL),(5,'XIAOMI','mi2',1,4,14,NULL),(6,'MAKITA','spin2',0,5,10,'spinkovač na sieťky na okná');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pouzivatel`
--

LOCK TABLES `pouzivatel` WRITE;
/*!40000 ALTER TABLE `pouzivatel` DISABLE KEYS */;
INSERT INTO `pouzivatel` VALUES (7,'Dominik','Džama','domos234@gmail.com','$2a$10$NJgM7SKEwLBaApmv/ZZRe.','$2a$10$NJgM7SKEwLBaApmv/ZZRe.A3PpDDUHJby6.EVBVDCFuyojlXj77bW','0918062677','VnT','Bystré',NULL,NULL,'09434'),(8,'Samuel','Baran','samuelbaran@gmail.com','$2a$10$rXHTykJKm.pBKXnApfbGnu','$2a$10$rXHTykJKm.pBKXnApfbGnuQnj6jUDpTaKIfSYoI.ILjhFLRECKMI2','09185632487','VnT','Hanusovce',NULL,NULL,'09435'),(9,'Damián','Mrkvička','dm@','$2a$10$Cqi8w2N1JCHFf4G9PH4YIe','$2a$10$Cqi8w2N1JCHFf4G9PH4YIeO4S1IW5gcqhWACgR4DvQWfCEfsYOvYO','0951431501','PO','Ľubotice','Benkova','561','09564'),(10,'Jerguš','Gombit','jg@','$2a$10$RQO3SDxoDR7cINM0gNkqb.','$2a$10$RQO3SDxoDR7cINM0gNkqb.zA/iWa6b3PYT5tFKySEv8uEeMqADG2C','0954196784','VnT','Čaklov',NULL,NULL,'04257'),(11,'Michal','Breškovič','mb@','$2a$10$9h/TL6DBNy7woTDvcDihcu','$2a$10$9h/TL6DBNy7woTDvcDihcuSxVEaBeaw2CEfuOmvGIzY63C6Z.N9qy','0951456789','PO',NULL,NULL,NULL,'09484'),(12,'Jožko','Majirský','jm@','$2a$10$Ucks1Kid6wR1u5sAgtFQNu','$2a$10$Ucks1Kid6wR1u5sAgtFQNuk6m9jR3.bQYqsrBHmIacQNqzt7Be0om','0951465312','BB','Kráľova Hoľa','vrcholová',NULL,NULL),(13,'Samo','Baran','sb@','$2a$10$073jWoC2TVn3wmQ10RhErO','$2a$10$073jWoC2TVn3wmQ10RhErO4Jwhknpuy3St.jwRJdludmj1VVe8FWq','0916458723','Vnt','Hanusovce',NULL,NULL,'09151'),(14,'Domčo','Dzama','dd@','$2a$10$lZrNCJ6ZvnVYIqb9F8kZr.','$2a$10$lZrNCJ6ZvnVYIqb9F8kZr.S3UyhXih7PyMR/TDGg26ulNfdNDMx.i','0951431501','VnT','Bystre','Zemplínska','78','09434');
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

-- Dump completed on 2020-12-15 10:02:31
