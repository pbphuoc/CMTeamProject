-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cm_project
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'dell'),(2,'apple'),(3,'samsung');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'laptop'),(2,'desktop'),(3,'cellphone'),(4,'tablet'),(5,'smartwatch'),(6,'accessory');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `src` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,2,'/images/product/product_laptopapple1.jpg'),(2,2,'/images/product/product_laptopapple2.png'),(3,2,'/images/product/product_laptopapple3.jpg'),(4,2,'/images/product/product_laptopapple4.jpg'),(5,2,'/images/product/product_laptopapple5.jpeg'),(6,4,'/images/product/product_iPadairgen51.jpg'),(7,4,'/images/product/product_iPadairgen52.jpg'),(8,4,'/images/product/product_iPadairgen53.jpg'),(9,4,'/images/product/product_iPadairgen54.jpg'),(10,4,'/images/product/product_iPadairgen55.jpg'),(11,4,'/images/product/product_iPadairgen56.jpg'),(12,5,'/images/product/product_appleWatch71.jpg'),(13,5,'/images/product/product_appleWatch72.jpg'),(14,5,'/images/product/product_appleWatch73.jpg'),(15,5,'/images/product/product_appleWatch74.jpg'),(16,5,'/images/product/product_appleWatch75.jpg'),(17,6,'/images/product/product_iPhonecase1.jpg'),(18,6,'/images/product/product_iPhonecase2.jpg'),(19,6,'/images/product/product_iPhonecase3.jpg');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` mediumtext NOT NULL,
  `old_price` double DEFAULT '0',
  `new_price` varchar(45) NOT NULL,
  `brand_id` int NOT NULL,
  `category_id` int NOT NULL,
  `img_src` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Dell 7040 SFF Bundle Desktop i7-6700','Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A',2850,'2599.00',1,2,'/images/product/product_computerdell1.jpg',0),(2,'Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A','The 13-inch MacBook Pro laptop is a portable powerhouse. Get more done faster with a next-generation 8-core CPU, 10-core GPU and 8GB of unified memory. Go all day and into the night, thanks to the power-efficient performance of the Apple M2 chip*Thanks to its active cooling system, the 13-inch MacBook Pro can sustain pro levels of performance, so you can run CPU- and GPU-intensive tasks for hours on end.',0,'3200.00',2,1,'/images/product/product_laptopapple1.jpg',0),(3,'iPhone 14 Pro Max 128GB','iPhone 14 Pro Max 128GB',0,'1850.00',2,3,'/images/product/product_iPhone14promax.jpg',0),(4,'APPLE IPAD AIR (5 GEN) 10.9-INCH WI-FI+CELL 256GB STARLIGHT','Apple iPad Air (5th Generation) 10.9-inch 256GB Wi-Fi + Cellular Starlight iPad Air. With an immersive 10.9-inch Liquid Retina display1. The breakthrough Apple M1 chip delivers faster performance, making iPad Air a creative and mobile gaming powerhouse. Featuring Touch ID, advanced cameras, blazing-fast 5G2 and Wi-Fi 6, USB-C, and support for Magic Keyboard and Apple Pencil (2nd generation)',1000,'985.50',2,4,'/images/product/product_iPadairgen51.jpg',0),(5,'Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band','Originally released October 2021. S7 with 64-bit dual-core processor. Water resistant to 50 metres¹Always-On Retina LTPO OLED display (1,000 nits brightness). 802.11b/g/n 2.4GHz and 5GHz. Bluetooth 5.0. Built-in rechargeable lithium-ion battery (Up to 18 hours²). Third-generation optical heart sensor. Accelerometer: up to 32 g-forces with fall detection. Gyroscope. Ambient light sensor. Capacity 32GB. Ceramic and sapphire crystal back',750,'639.20',2,5,'/images/product/product_appleWatch71.jpg',0),(6,'iPhone 11 Pro Max Silicone','Designed by Apple to complement iPhone 11 Pro Max, the form of the silicone case fits snugly over the volume buttons, side button and curves of your device without adding bulk. A soft microfibre lining on the inside helps protect your iPhone. On the outside, the silky, soft-touch finish of the silicone exterior feels great in your hand. And you can keep it on all the time, even when you’re charging wirelessly. Like every Apple-designed case, it undergoes thousands of hours of testing throughout the design and manufacturing process. So not only does it look great, it’s built to protect your iPhone from scratches and drops.',0,'80.00',2,6,'/images/product/product_iPhonecase1.jpg',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'ff.pbphuoc@gmail.com','test','phuoc pham','0450028815'),(14,'phuocpham@gmail.com','test1','Ba Phuoc Pham','0450028815'),(15,'phuoc1@gmail.com','phuoc1','phuoc 1','04123456789'),(16,'phuoc2@gmail.com','phuoc2','phuoc 2','04999888777'),(17,'phuoc3@gmail.com','phuoc3','phuoc3','a');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-20 20:08:44
