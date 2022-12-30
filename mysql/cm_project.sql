-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cm_project
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'dell'),(2,'apple'),(3,'samsung'),(4,'LG'),(5,'Gigabyte'),(6,'Asus');
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
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,2,'/images/product/2/product_laptopapple1.jpg'),(2,2,'/images/product/2/product_laptopapple2.png'),(3,2,'/images/product/2/product_laptopapple3.jpg'),(4,2,'/images/product/2/product_laptopapple4.jpg'),(5,2,'/images/product/2/product_laptopapple5.jpeg'),(6,4,'/images/product/4/product_iPadairgen51.jpg'),(7,4,'/images/product/4/product_iPadairgen52.jpg'),(8,4,'/images/product/4/product_iPadairgen53.jpg'),(9,4,'/images/product/4/product_iPadairgen54.jpg'),(10,4,'/images/product/4/product_iPadairgen55.jpg'),(11,4,'/images/product/4/product_iPadairgen56.jpg'),(12,5,'/images/product/5/product_appleWatch71.jpg'),(13,5,'/images/product/5/product_appleWatch72.jpg'),(14,5,'/images/product/5/product_appleWatch73.jpg'),(15,5,'/images/product/5/product_appleWatch74.jpg'),(16,5,'/images/product/5/product_appleWatch75.jpg'),(17,6,'/images/product/6/product_iPhonecase1.jpg'),(18,6,'/images/product/6/product_iPhonecase2.jpg'),(19,6,'/images/product/6/product_iPhonecase3.jpg'),(20,1,'/images/product/1/product_computerdell1.jpg'),(21,3,'/images/product/3/product_iPhone14promax.jpg'),(22,7,'/images/product/7/product_laptopGIga_1.jpg'),(23,7,'/images/product/7/product_laptopGIga_2.jpg'),(24,7,'/images/product/7/product_laptopGIga_3.jpg'),(25,7,'/images/product/7/product_laptopGIga_4.jpg'),(26,8,'/images/product/8/product_laptopAsus_1.jpg'),(27,8,'/images/product/8/product_laptopAsus_2.jpg'),(28,8,'/images/product/8/product_laptopAsus_3.jpg'),(29,8,'/images/product/8/product_laptopAsus_4.jpg'),(30,9,'/images/product/9/product_laptopAsusRog_1.jpg'),(31,9,'/images/product/9/product_laptopAsusRog_2.jpg'),(32,9,'/images/product/9/product_laptopAsusRog_3.jpg'),(33,9,'/images/product/9/product_laptopAsusRog_4.jpg'),(34,10,'/images/product/10/product_laptopA15X_1.jpg'),(35,10,'/images/product/10/product_laptopA15X_2.jpg'),(36,10,'/images/product/10/product_laptopA15X_3.jpg'),(37,10,'/images/product/10/product_laptopA15X_4.jpg'),(38,11,'/images/product/11/product_lapAZen_1.jpg'),(39,11,'/images/product/11/product_lapAZen_2.jpg'),(40,11,'/images/product/11/product_lapAZen_3.jpg'),(41,11,'/images/product/11/product_lapAZen_4.jpg'),(42,12,'/images/product/12/product_lapAZE_1.jpg'),(43,12,'/images/product/12/product_lapAZE_2.jpg'),(44,12,'/images/product/12/product_lapAZE_3.jpg'),(45,12,'/images/product/12/product_lapAZE_4.jpg'),(46,13,'/images/product/13/product_lapAZ1_1.jpg'),(47,13,'/images/product/13/product_lapAZ1_2.jpg'),(48,13,'/images/product/13/product_lapAZ1_3.jpg'),(49,13,'/images/product/13/product_lapAZ1_4.jpg'),(50,14,'/images/product/14/product_lapAZ2_1.jpg'),(51,14,'/images/product/14/product_lapAZ2_2.jpg'),(52,14,'/images/product/14/product_lapAZ2_3.jpg'),(53,14,'/images/product/14/product_lapAZ2_4.jpg'),(54,15,'/images/product/15/product_lapAZ3_1.jpg'),(55,15,'/images/product/15/product_lapAZ3_2.jpg'),(56,15,'/images/product/15/product_lapAZ3_3.jpg'),(57,15,'/images/product/15/product_lapAZ3_4.jpg'),(58,16,'/images/product/16/product_lapAZ4_1.jpg'),(59,16,'/images/product/16/product_lapAZ4_2.jpg'),(60,16,'/images/product/16/product_lapAZ4_3.jpg'),(61,16,'/images/product/16/product_lapAZ4_4.jpg'),(62,17,'/images/product/17/product_lapG1_1.jpg'),(63,17,'/images/product/17/product_lapG1_2.jpg'),(64,17,'/images/product/17/product_lapG1_3.jpg'),(65,17,'/images/product/17/product_lapG1_4.jpg'),(66,18,'/images/product/18/product_lapG2_1.jpg'),(67,18,'/images/product/18/product_lapG2_2.jpg'),(68,18,'/images/product/18/product_lapG2_3.jpg'),(69,18,'/images/product/18/product_lapG2_4.jpg'),(70,19,'/images/product/19/product_lapDe1_1.jpg'),(71,19,'/images/product/19/product_lapDe1_2.jpg'),(72,19,'/images/product/19/product_lapDe1_3.jpg'),(73,19,'/images/product/19/product_lapDe1_4.jpg'),(74,20,'/images/product/20/product_lapDe2_1.jpg'),(75,20,'/images/product/20/product_lapDe2_2.jpg'),(76,20,'/images/product/20/product_lapDe2_3.jpg'),(77,20,'/images/product/20/product_lapDe2_4.jpg'),(78,21,'/images/product/21/product_lapDe3_1.jpg'),(79,21,'/images/product/21/product_lapDe3_2.jpg'),(80,21,'/images/product/21/product_lapDe3_3.jpg'),(81,21,'/images/product/21/product_lapDe3_4.jpg'),(82,22,'/images/product/22/product_lapDe4_1.jpg'),(83,22,'/images/product/22/product_lapDe4_2.jpg'),(84,22,'/images/product/22/product_lapDe4_3.jpg'),(85,22,'/images/product/22/product_lapDe4_4.jpg'),(86,23,'/images/product/23/product_lapDe5_1.jpg'),(87,23,'/images/product/23/product_lapDe5_1.jpg'),(88,23,'/images/product/23/product_lapDe5_1.jpg'),(89,23,'/images/product/23/product_lapDe5_1.jpg'),(90,24,'/images/product/24/product_lapDe6_1.jpg'),(91,24,'/images/product/24/product_lapDe6_2.jpg'),(92,24,'/images/product/24/product_lapDe6_3.jpg'),(93,24,'/images/product/24/product_lapDe6_4.jpg'),(94,25,'/images/product/25/product_lapDe7_1.jpg'),(95,25,'/images/product/25/product_lapDe7_2.jpg'),(96,25,'/images/product/25/product_lapDe7_3.jpg'),(97,25,'/images/product/25/product_lapDe7_4.jpg'),(98,26,'/images/product/26/product_lapDe8_1.jpg'),(99,26,'/images/product/26/product_lapDe8_2.jpg'),(100,26,'/images/product/26/product_lapDe8_3.jpg'),(101,26,'/images/product/26/product_lapDe8_4.jpg'),(102,27,'/images/product/27/product_lapDe9_1.jpg'),(103,27,'/images/product/27/product_lapDe9_2.jpg'),(104,27,'/images/product/27/product_lapDe9_3.jpg'),(105,27,'/images/product/27/product_lapDe9_4.jpg'),(106,28,'/images/product/28/product_lapDe10_1.jpg'),(107,28,'/images/product/28/product_lapDe10_2.jpg'),(108,28,'/images/product/28/product_lapDe10_3.jpg'),(109,28,'/images/product/28/product_lapDe10_4.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Dell 7040 SFF Bundle Desktop i7-6700','Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A',2850,'2599.00',1,2,'/images/product/1/product_computerdell1.jpg',1),(2,'Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A','The 13-inch MacBook Pro laptop is a portable powerhouse. Get more done faster with a next-generation 8-core CPU, 10-core GPU and 8GB of unified memory. Go all day and into the night, thanks to the power-efficient performance of the Apple M2 chip*Thanks to its active cooling system, the 13-inch MacBook Pro can sustain pro levels of performance, so you can run CPU- and GPU-intensive tasks for hours on end.',0,'3200.00',2,1,'/images/product/2/product_laptopapple1.jpg',2),(3,'Apple iPhone 14 Pro Max 128GB','iPhone 14 Pro Max 128GB',0,'1850.00',2,3,'/images/product/3/product_iPhone14promax.jpg',3),(4,'APPLE IPAD AIR (5 GEN) 10.9-INCH WI-FI+CELL 256GB STARLIGHT','Apple iPad Air (5th Generation) 10.9-inch 256GB Wi-Fi + Cellular Starlight iPad Air. With an immersive 10.9-inch Liquid Retina display1. The breakthrough Apple M1 chip delivers faster performance, making iPad Air a creative and mobile gaming powerhouse. Featuring Touch ID, advanced cameras, blazing-fast 5G2 and Wi-Fi 6, USB-C, and support for Magic Keyboard and Apple Pencil (2nd generation)',1000,'985.50',2,4,'/images/product/4/product_iPadairgen51.jpg',0),(5,'Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band','Originally released October 2021. S7 with 64-bit dual-core processor. Water resistant to 50 metres¹Always-On Retina LTPO OLED display (1,000 nits brightness). 802.11b/g/n 2.4GHz and 5GHz. Bluetooth 5.0. Built-in rechargeable lithium-ion battery (Up to 18 hours²). Third-generation optical heart sensor. Accelerometer: up to 32 g-forces with fall detection. Gyroscope. Ambient light sensor. Capacity 32GB. Ceramic and sapphire crystal back',750,'639.20',2,5,'/images/product/5/product_appleWatch71.jpg',5),(6,'iPhone 11 Pro Max Silicone','Designed by Apple to complement iPhone 11 Pro Max, the form of the silicone case fits snugly over the volume buttons, side button and curves of your device without adding bulk. A soft microfibre lining on the inside helps protect your iPhone. On the outside, the silky, soft-touch finish of the silicone exterior feels great in your hand. And you can keep it on all the time, even when you’re charging wirelessly. Like every Apple-designed case, it undergoes thousands of hours of testing throughout the design and manufacturing process. So not only does it look great, it’s built to protect your iPhone from scratches and drops.',0,'80.00',2,6,'/images/product/6/product_iPhonecase1.jpg',4),(7,'Laptop Gigabyte U4 UD-50VN823SO','Gigabyte U4 Laptop UD-50VN823SO – Powerful Performance',1500,'1500',5,1,'/images/product/7/product_laptopGIga_2.jpg',1),(8,'Laptop Asus Vivobook Flip 14 TP470EA EC346W','Asus VivoBook Flip 14 laptop TP470EA-EC346W - Personality, flexibility',2000,'2000',6,1,'/images/product/8/product_laptopAsus_1.jpg',2),(9,'Laptop Asus Rog Strix G15 G513IE-HN246W','Laptop Asus Rog Strix G15 G513IE-HN246W - Unique design, powerful configuration',2500.5,'2200.50',6,1,'/images/product/9/product_laptopAsusRog_1.jpg',3),(10,'Laptop ASUS VivoBook 15X A1503ZA-L1422W','Asus Vivobook 15X A1503ZA-L1422W Laptop - Slim, beautiful and powerful',1800,'1200',6,1,'/images/product/10/product_laptopA15X_1.jpg',4),(11,'Laptop ASUS ZenBook Flip UX363EA','Asus ZenBook Flip UX363EA Laptop - convenient, multifunctional',3200,'2000',6,1,'/images/product/11/product_lapAZen_1.jpg',1),(12,'Laptop ASUS Gaming ROG Zephyrus G14 GA401QC-K2199W','ASUS Gaming Laptop ROG Zephyrus G14 GA401QC-K2199W – Powerful configuration',3500,'3000',6,1,'/images/product/12/product_lapAZE_1.jpg',1),(13,'Laptop Asus VivoBook Pro X515EA-BQ3015W','Asus Vivobook X515EA-BQ3015W Laptop - Minimalist design, healthy configuration',2100,'1800',6,1,'/images/product/13/product_lapAZ1_1.jpg',2),(14,'Laptop Asus Vivobook 14X M1403QA-LY024W','Asus Vivobook 14X M1403QA-LY024W Laptop - Great power from the new generation chipset',1900,'1700',6,1,'/images/product/14/product_lapAZ2_1.jpg',2),(15,'Laptop Asus Vivobook 14 X1402ZA-EB100W','Asus Vivobook 14 X1402ZA-EB100W Laptop – Luxurious, modern design',1800,'1600',6,1,'/images/product/15/product_lapAZ3_1.jpg',2),(16,'Laptop ASUS Vivobook 14X M1403QA-LY023W','Asus Vivobook 14X M1403QA-LY023W Laptop - Convenient laptop for office people',1500,'1300',6,1,'/images/product/16/product_lapAZ4_1.jpg',2),(17,'Laptop Gigabyte G5 GE-51VN213SH','Gigabyte G5 Laptop GE-51VN213SH – Powerful Performance',1900,'1700',5,1,'/images/product/17/product_lapG1_1.jpg',2),(18,'Laptop Gigabyte G5 KD-52VN123SO','Gigabyte G5 Laptop KD-52VN123SO – Outstanding performance, powerful design',2200,'2100',5,1,'/images/product/18/product_lapG2_1.jpg',3),(19,'Laptop Dell Vostro 3510 P112F002BBL','Dell Vostro 3510 P112F002BBL Laptop Powerful, Outstanding Mobility',2100,'1950',1,1,'/images/product/19/product_lapDe1_1.jpg',5),(20,'Laptop Dell Gaming G15 5515 P105F004CGR','Dell Gaming Laptop G15 5515 P105F004CGR – Stable performance, comfortable experience',2200,'1900',1,1,'/images/product/20/product_lapDe2_1.jpg',6),(21,'Laptop Dell Vostro 3405 V4R53500u003W1','Dell Vostro 3405 V4R53500u003W1 Laptop – Compact, stable performance',1600,'1300',1,1,'/images/product/21/product_lapDe3_1.jpg',1),(22,'Dell Vostro 5620 Laptop 70282719','Dell Vostro 5620 70282719 Laptop - Outstanding Performance',1900,'1700',1,1,'/images/product/22/product_lapDe4_1.jpg',7),(23,'Laptop Dell Inspiron 7506-5903SLV','Dell Inspiron 7506-5903SLV Laptop - Office laptop with outstanding performance',2200,'1900',1,1,'/images/product/23/product_lapDe5_1.jpg',8),(24,'Laptop Dell Inspiron 15 3520 N3520-i5U085W11BLU','Dell Inspiron 15 3520 N3520-I5U085W11BLU Laptop - Outstanding portable version',1800,'1600',1,1,'/images/product/24/product_lapDe6_1.jpg',8),(25,'Laptop Dell Gaming G15 Ryzen Edition','Dell Gaming Laptop G15 Ryzen Edition 5515 70266674: Powerful Performance Laptop',2100,'1900',1,1,'/images/product/25/product_lapDe7_1.jpg',9),(26,'Laptop Dell Inspirion N5515 N5R75700U104W1','Dell Inspirion N5515 N5R75700U104W1 Laptop – Stable performance, luxurious design',3200,'3100',1,1,'/images/product/26/product_lapDe8_1.jpg',4),(27,'Laptop Dell Latitude 3520 70251603','Dell Latitude 3520 70251603 Laptop – The machine that works tirelessly',1900,'1850',1,1,'/images/product/27/product_lapDe9_1.jpg',1),(28,'Dell Vostro 3425 V4R55625U206W laptop','Dell Vostro 3425 V4R55625U206W Laptop - Luxurious design',2500,'2350',1,1,'/images/product/28/product_lapDe10_1.jpg',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'ff.pbphuoc@gmail.com','test','phuoc pham','0450028815'),(14,'phuocpham@gmail.com','test1','Ba Phuoc Pham','0450028815'),(15,'phuoc1@gmail.com','phuoc1','phuoc 1','04123456789'),(16,'phuoc2@gmail.com','phuoc2','phuoc 2','04999888777'),(17,'phuoc3@gmail.com','phuoc3','phuoc3','a'),(18,'phuocpham2@gmail.com','test','phuoc pham','0412345678');
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

-- Dump completed on 2022-12-31  2:59:43
