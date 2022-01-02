-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lab4
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `tweets`
--

DROP TABLE IF EXISTS `tweets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tweets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `postdatetime` timestamp NULL DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `pid` int DEFAULT NULL,
  `numlikes` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `tweets_users_fk` (`uid`),
  KEY `tweets_tweets_fk` (`pid`),
  CONSTRAINT `tweets_tweets_fk` FOREIGN KEY (`pid`) REFERENCES `tweets` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tweets_users_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweets`
--

LOCK TABLES `tweets` WRITE;
/*!40000 ALTER TABLE `tweets` DISABLE KEYS */;
INSERT INTO `tweets` VALUES (78,7,'2021-06-30 17:35:28',' “Muchas cosas crecen en el jardín que nunca fueron sembradas allí.” - Thomas Fuller ',NULL,2),(79,8,'2021-06-30 17:36:31','The reason you haven\'t felt it is because it doesn\'t exist. What you call love was invented by guys like me. ',NULL,1),(80,8,'2021-06-30 17:37:31','Interessant...',78,0),(81,9,'2021-06-30 17:38:29','Hopeless emptiness. Plenty of people are onto the emptiness, but it takes real guts to see the hopelessness. ',NULL,1),(83,7,'2021-06-30 17:40:23','Big Bang Theory.',NULL,3),(84,8,'2021-06-30 17:40:43','Que bona!',83,0),(85,9,'2021-06-30 17:41:20','Amazing...',83,0),(86,10,'2021-06-30 17:49:40','Nostalgia is denial - denial of the painful present…',NULL,0);
/*!40000 ALTER TABLE `tweets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-30 19:50:48
