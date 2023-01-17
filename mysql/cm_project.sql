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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'dell'),(2,'apple'),(3,'samsung'),(4,'LG'),(5,'Gigabyte'),(6,'Asus'),(7,'Oppo');
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
  `img_src` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'laptop','/images/category/category_laptop.png'),(2,'desktop','/images/category/category_computer.png'),(3,'cellphone','/images/category/category_cellphone.png'),(4,'tablet','/images/category/category_tablet.png'),(5,'smartwatch','/images/category/category_smartwatch.png'),(6,'accessory','/images/category/category_accessory.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,2,'/images/product/2/product_laptopapple1.jpg'),(2,2,'/images/product/2/product_laptopapple2.png'),(3,2,'/images/product/2/product_laptopapple3.jpg'),(4,2,'/images/product/2/product_laptopapple4.jpg'),(5,2,'/images/product/2/product_laptopapple5.jpeg'),(6,4,'/images/product/4/product_iPadairgen51.jpg'),(7,4,'/images/product/4/product_iPadairgen52.jpg'),(8,4,'/images/product/4/product_iPadairgen53.jpg'),(9,4,'/images/product/4/product_iPadairgen54.jpg'),(10,4,'/images/product/4/product_iPadairgen55.jpg'),(11,4,'/images/product/4/product_iPadairgen56.jpg'),(12,5,'/images/product/5/product_appleWatch71.jpg'),(13,5,'/images/product/5/product_appleWatch72.jpg'),(14,5,'/images/product/5/product_appleWatch73.jpg'),(15,5,'/images/product/5/product_appleWatch74.jpg'),(16,5,'/images/product/5/product_appleWatch75.jpg'),(17,6,'/images/product/6/product_iPhonecase1.jpg'),(18,6,'/images/product/6/product_iPhonecase2.jpg'),(19,6,'/images/product/6/product_iPhonecase3.jpg'),(20,1,'/images/product/1/product_computerdell1.jpg'),(21,3,'/images/product/3/product_iPhone14promax.jpg'),(22,7,'/images/product/7/product_laptopGIga_1.jpg'),(23,7,'/images/product/7/product_laptopGIga_2.jpg'),(24,7,'/images/product/7/product_laptopGIga_3.jpg'),(25,7,'/images/product/7/product_laptopGIga_4.jpg'),(26,8,'/images/product/8/product_laptopAsus_1.jpg'),(27,8,'/images/product/8/product_laptopAsus_2.jpg'),(28,8,'/images/product/8/product_laptopAsus_3.jpg'),(29,8,'/images/product/8/product_laptopAsus_4.jpg'),(30,9,'/images/product/9/product_laptopAsusRog_1.jpg'),(31,9,'/images/product/9/product_laptopAsusRog_2.jpg'),(32,9,'/images/product/9/product_laptopAsusRog_3.jpg'),(33,9,'/images/product/9/product_laptopAsusRog_4.jpg'),(34,10,'/images/product/10/product_laptopA15X_1.jpg'),(35,10,'/images/product/10/product_laptopA15X_2.jpg'),(36,10,'/images/product/10/product_laptopA15X_3.jpg'),(37,10,'/images/product/10/product_laptopA15X_4.jpg'),(38,11,'/images/product/11/product_lapAZen_1.jpg'),(39,11,'/images/product/11/product_lapAZen_2.jpg'),(40,11,'/images/product/11/product_lapAZen_3.jpg'),(41,11,'/images/product/11/product_lapAZen_4.jpg'),(42,12,'/images/product/12/product_lapAZE_1.jpg'),(43,12,'/images/product/12/product_lapAZE_2.jpg'),(44,12,'/images/product/12/product_lapAZE_3.jpg'),(45,12,'/images/product/12/product_lapAZE_4.jpg'),(46,13,'/images/product/13/product_lapAZ1_1.jpg'),(47,13,'/images/product/13/product_lapAZ1_2.jpg'),(48,13,'/images/product/13/product_lapAZ1_3.jpg'),(49,13,'/images/product/13/product_lapAZ1_4.jpg'),(50,14,'/images/product/14/product_lapAZ2_1.jpg'),(51,14,'/images/product/14/product_lapAZ2_2.jpg'),(52,14,'/images/product/14/product_lapAZ2_3.jpg'),(53,14,'/images/product/14/product_lapAZ2_4.jpg'),(54,15,'/images/product/15/product_lapAZ3_1.jpg'),(55,15,'/images/product/15/product_lapAZ3_2.jpg'),(56,15,'/images/product/15/product_lapAZ3_3.jpg'),(57,15,'/images/product/15/product_lapAZ3_4.jpg'),(58,16,'/images/product/16/product_lapAZ4_1.jpg'),(59,16,'/images/product/16/product_lapAZ4_2.jpg'),(60,16,'/images/product/16/product_lapAZ4_3.jpg'),(61,16,'/images/product/16/product_lapAZ4_4.jpg'),(62,17,'/images/product/17/product_lapG1_1.jpg'),(63,17,'/images/product/17/product_lapG1_2.jpg'),(64,17,'/images/product/17/product_lapG1_3.jpg'),(65,17,'/images/product/17/product_lapG1_4.jpg'),(66,18,'/images/product/18/product_lapG2_1.jpg'),(67,18,'/images/product/18/product_lapG2_2.jpg'),(68,18,'/images/product/18/product_lapG2_3.jpg'),(69,18,'/images/product/18/product_lapG2_4.jpg'),(70,19,'/images/product/19/product_lapDe1_1.jpg'),(71,19,'/images/product/19/product_lapDe1_2.jpg'),(72,19,'/images/product/19/product_lapDe1_3.jpg'),(73,19,'/images/product/19/product_lapDe1_4.jpg'),(74,20,'/images/product/20/product_lapDe2_1.jpg'),(75,20,'/images/product/20/product_lapDe2_2.jpg'),(76,20,'/images/product/20/product_lapDe2_3.jpg'),(77,20,'/images/product/20/product_lapDe2_4.jpg'),(78,21,'/images/product/21/product_lapDe3_1.jpg'),(79,21,'/images/product/21/product_lapDe3_2.jpg'),(80,21,'/images/product/21/product_lapDe3_3.jpg'),(81,21,'/images/product/21/product_lapDe3_4.jpg'),(82,22,'/images/product/22/product_lapDe4_1.jpg'),(83,22,'/images/product/22/product_lapDe4_2.jpg'),(84,22,'/images/product/22/product_lapDe4_3.jpg'),(85,22,'/images/product/22/product_lapDe4_4.jpg'),(86,23,'/images/product/23/product_lapDe5_1.jpg'),(87,23,'/images/product/23/product_lapDe5_1.jpg'),(88,23,'/images/product/23/product_lapDe5_1.jpg'),(89,23,'/images/product/23/product_lapDe5_1.jpg'),(90,24,'/images/product/24/product_lapDe6_1.jpg'),(91,24,'/images/product/24/product_lapDe6_2.jpg'),(92,24,'/images/product/24/product_lapDe6_3.jpg'),(93,24,'/images/product/24/product_lapDe6_4.jpg'),(94,25,'/images/product/25/product_lapDe7_1.jpg'),(95,25,'/images/product/25/product_lapDe7_2.jpg'),(96,25,'/images/product/25/product_lapDe7_3.jpg'),(97,25,'/images/product/25/product_lapDe7_4.jpg'),(98,26,'/images/product/26/product_lapDe8_1.jpg'),(99,26,'/images/product/26/product_lapDe8_2.jpg'),(100,26,'/images/product/26/product_lapDe8_3.jpg'),(101,26,'/images/product/26/product_lapDe8_4.jpg'),(102,27,'/images/product/27/product_lapDe9_1.jpg'),(103,27,'/images/product/27/product_lapDe9_2.jpg'),(104,27,'/images/product/27/product_lapDe9_3.jpg'),(105,27,'/images/product/27/product_lapDe9_4.jpg'),(106,28,'/images/product/28/product_lapDe10_1.jpg'),(107,28,'/images/product/28/product_lapDe10_2.jpg'),(108,28,'/images/product/28/product_lapDe10_3.jpg'),(109,28,'/images/product/28/product_lapDe10_4.jpg'),(110,29,'/images/product/29/product_phone1_1.jpg'),(111,29,'/images/product/29/product_phone1_2.jpg'),(112,29,'/images/product/29/product_phone1_3.jpg'),(113,29,'/images/product/29/product_phone1_4.jpg'),(114,30,'/images/product/30/product_phone2_1.jpg'),(115,30,'/images/product/30/product_phone2_2.jpg'),(116,30,'/images/product/30/product_phone2_3.jpg'),(117,30,'/images/product/30/product_phone2_4.jpg'),(118,31,'/images/product/31/product_phone3_1.jpg'),(119,31,'/images/product/31/product_phone3_2.jpg'),(120,31,'/images/product/31/product_phone3_3.jpg'),(121,31,'/images/product/31/product_phone3_4.jpg'),(122,32,'/images/product/32/product_phone4_1.jpg'),(123,32,'/images/product/32/product_phone4_2.jpg'),(124,32,'/images/product/32/product_phone4_3.jpg'),(125,32,'/images/product/32/product_phone4_4.jpg'),(126,33,'/images/product/33/product_phone5_1.jpg'),(127,33,'/images/product/33/product_phone5_2.jpg'),(128,33,'/images/product/33/product_phone5_3.jpg'),(129,33,'/images/product/33/product_phone5_4.jpg'),(130,34,'/images/product/34/product_phone6_1.jpg'),(131,34,'/images/product/34/product_phone6_2.jpg'),(132,34,'/images/product/34/product_phone6_3.jpg'),(133,34,'/images/product/34/product_phone6_4.jpg'),(134,35,'/images/product/35/product_phone7_1.jpg'),(135,35,'/images/product/35/product_phone7_2.jpg'),(136,35,'/images/product/35/product_phone7_3.jpg'),(137,35,'/images/product/35/product_phone7_4.jpg'),(138,36,'/images/product/36/product_phone8_1.jpg'),(139,36,'/images/product/36/product_phone8_2.jpg'),(140,36,'/images/product/36/product_phone8_3.jpg'),(141,36,'/images/product/36/product_phone8_4.jpg'),(142,37,'/images/product/37/product_phone9_1.jpg'),(143,37,'/images/product/37/product_phone9_2.jpg'),(144,37,'/images/product/37/product_phone9_3.jpg'),(145,37,'/images/product/37/product_phone9_4.jpg'),(146,38,'/images/product/38/product_phone10_1.jpg'),(147,38,'/images/product/38/product_phone10_2.jpg'),(148,38,'/images/product/38/product_phone10_3.jpg'),(149,38,'/images/product/38/product_phone10_4.jpg'),(150,39,'/images/product/39/product_phone11_1.jpg'),(151,39,'/images/product/39/product_phone11_2.jpg'),(152,39,'/images/product/39/product_phone11_3.jpg'),(153,39,'/images/product/39/product_phone11_4.jpg'),(154,40,'/images/product/40/product_phone12_1.jpg'),(155,40,'/images/product/40/product_phone12_2.jpg'),(156,40,'/images/product/40/product_phone12_3.jpg'),(157,40,'/images/product/40/product_phone12_4.jpg'),(158,41,'/images/product/41/product_phone13_1.jpg'),(159,41,'/images/product/41/product_phone13_2.jpg'),(160,41,'/images/product/41/product_phone13_3.jpg'),(161,41,'/images/product/41/product_phone13_4.jpg'),(162,42,'/images/product/42/product_phone14_1.jpg'),(163,42,'/images/product/42/product_phone14_2.jpg'),(164,42,'/images/product/42/product_phone14_3.jpg'),(165,42,'/images/product/42/product_phone14_4.jpg');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `fk_product_id_idx` (`product_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'pending'),(2,'received'),(3,'processed'),(4,'ready'),(5,'finished');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_date` varchar(15) NOT NULL,
  `checkout_email` varchar(45) NOT NULL,
  `checkout_fullname` varchar(45) NOT NULL,
  `checkout_phone` varchar(45) NOT NULL,
  `receiver_fullname` varchar(45) NOT NULL,
  `receiver_phone` varchar(45) NOT NULL,
  `receiver_address` varchar(45) DEFAULT NULL,
  `receive_method_id` int NOT NULL,
  `payment_type_id` int NOT NULL,
  `payment_date` varchar(15) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `shipping` double NOT NULL DEFAULT '0',
  `total` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'14/01/2023','ff.pbphuoc@gmail.com','Phuoc Pham','04987654321','Chau Vuong','04123456789','123 abc street, inala, qld, 4077',1,1,'',1,0,12345);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
INSERT INTO `payment_type` VALUES (1,'transfer'),(2,'card'),(3,'store');
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
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
  `new_price` double NOT NULL DEFAULT '0',
  `brand_id` int NOT NULL,
  `category_id` int NOT NULL,
  `img_src` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`),
  KEY `index_category` (`category_id`) /*!80000 INVISIBLE */,
  KEY `index_brand` (`brand_id`) /*!80000 INVISIBLE */,
  KEY `index_price` (`new_price`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Dell 7040 SFF Bundle Desktop i7-6700','Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A',2850,2599,1,2,'/images/product/1/product_computerdell1.jpg',1),(2,'Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A','The 13-inch MacBook Pro laptop is a portable powerhouse. Get more done faster with a next-generation 8-core CPU, 10-core GPU and 8GB of unified memory. Go all day and into the night, thanks to the power-efficient performance of the Apple M2 chip*Thanks to its active cooling system, the 13-inch MacBook Pro can sustain pro levels of performance, so you can run CPU- and GPU-intensive tasks for hours on end.',0,3200,2,1,'/images/product/2/product_laptopapple1.jpg',2),(3,'Apple iPhone 14 Pro Max 128GB','iPhone 14 Pro Max 128GB',0,1850,2,3,'/images/product/3/product_iPhone14promax.jpg',3),(4,'APPLE IPAD AIR (5 GEN) 10.9-INCH WI-FI+CELL 256GB STARLIGHT','Apple iPad Air (5th Generation) 10.9-inch 256GB Wi-Fi + Cellular Starlight iPad Air. With an immersive 10.9-inch Liquid Retina display1. The breakthrough Apple M1 chip delivers faster performance, making iPad Air a creative and mobile gaming powerhouse. Featuring Touch ID, advanced cameras, blazing-fast 5G2 and Wi-Fi 6, USB-C, and support for Magic Keyboard and Apple Pencil (2nd generation)',1000,985.5,2,4,'/images/product/4/product_iPadairgen51.jpg',0),(5,'Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band','Originally released October 2021. S7 with 64-bit dual-core processor. Water resistant to 50 metres¹Always-On Retina LTPO OLED display (1,000 nits brightness). 802.11b/g/n 2.4GHz and 5GHz. Bluetooth 5.0. Built-in rechargeable lithium-ion battery (Up to 18 hours²). Third-generation optical heart sensor. Accelerometer: up to 32 g-forces with fall detection. Gyroscope. Ambient light sensor. Capacity 32GB. Ceramic and sapphire crystal back',750,639.2,2,5,'/images/product/5/product_appleWatch71.jpg',5),(6,'iPhone 11 Pro Max Silicone','Designed by Apple to complement iPhone 11 Pro Max, the form of the silicone case fits snugly over the volume buttons, side button and curves of your device without adding bulk. A soft microfibre lining on the inside helps protect your iPhone. On the outside, the silky, soft-touch finish of the silicone exterior feels great in your hand. And you can keep it on all the time, even when you’re charging wirelessly. Like every Apple-designed case, it undergoes thousands of hours of testing throughout the design and manufacturing process. So not only does it look great, it’s built to protect your iPhone from scratches and drops.',0,80,2,6,'/images/product/6/product_iPhonecase1.jpg',4),(7,'Laptop Gigabyte U4 UD-50VN823SO','Gigabyte U4 Laptop UD-50VN823SO – Powerful Performance',1500,1500,5,1,'/images/product/7/product_laptopGIga_2.jpg',1),(8,'Laptop Asus Vivobook Flip 14 TP470EA EC346W','Asus VivoBook Flip 14 laptop TP470EA-EC346W - Personality, flexibility',2000,2000,6,1,'/images/product/8/product_laptopAsus_1.jpg',2),(9,'Laptop Asus Rog Strix G15 G513IE-HN246W','Laptop Asus Rog Strix G15 G513IE-HN246W - Unique design, powerful configuration',2500.5,2200.5,6,1,'/images/product/9/product_laptopAsusRog_1.jpg',3),(10,'Laptop ASUS VivoBook 15X A1503ZA-L1422W','Asus Vivobook 15X A1503ZA-L1422W Laptop - Slim, beautiful and powerful',1800,1200,6,1,'/images/product/10/product_laptopA15X_1.jpg',4),(11,'Laptop ASUS ZenBook Flip UX363EA','Asus ZenBook Flip UX363EA Laptop - convenient, multifunctional',3200,2000,6,1,'/images/product/11/product_lapAZen_1.jpg',1),(12,'Laptop ASUS Gaming ROG Zephyrus G14 GA401QC-K2199W','ASUS Gaming Laptop ROG Zephyrus G14 GA401QC-K2199W – Powerful configuration',3500,3000,6,1,'/images/product/12/product_lapAZE_1.jpg',1),(13,'Laptop Asus VivoBook Pro X515EA-BQ3015W','Asus Vivobook X515EA-BQ3015W Laptop - Minimalist design, healthy configuration',2100,1800,6,1,'/images/product/13/product_lapAZ1_1.jpg',2),(14,'Laptop Asus Vivobook 14X M1403QA-LY024W','Asus Vivobook 14X M1403QA-LY024W Laptop - Great power from the new generation chipset',1900,1700,6,1,'/images/product/14/product_lapAZ2_1.jpg',2),(15,'Laptop Asus Vivobook 14 X1402ZA-EB100W','Asus Vivobook 14 X1402ZA-EB100W Laptop – Luxurious, modern design',1800,1600,6,1,'/images/product/15/product_lapAZ3_1.jpg',2),(16,'Laptop ASUS Vivobook 14X M1403QA-LY023W','Asus Vivobook 14X M1403QA-LY023W Laptop - Convenient laptop for office people',1500,1300,6,1,'/images/product/16/product_lapAZ4_1.jpg',2),(17,'Laptop Gigabyte G5 GE-51VN213SH','Gigabyte G5 Laptop GE-51VN213SH – Powerful Performance',1900,1700,5,1,'/images/product/17/product_lapG1_1.jpg',2),(18,'Laptop Gigabyte G5 KD-52VN123SO','Gigabyte G5 Laptop KD-52VN123SO – Outstanding performance, powerful design',2200,2100,5,1,'/images/product/18/product_lapG2_1.jpg',3),(19,'Laptop Dell Vostro 3510 P112F002BBL','Dell Vostro 3510 P112F002BBL Laptop Powerful, Outstanding Mobility',2100,1950,1,1,'/images/product/19/product_lapDe1_1.jpg',5),(20,'Laptop Dell Gaming G15 5515 P105F004CGR','Dell Gaming Laptop G15 5515 P105F004CGR – Stable performance, comfortable experience',2200,1900,1,1,'/images/product/20/product_lapDe2_1.jpg',6),(21,'Laptop Dell Vostro 3405 V4R53500u003W1','Dell Vostro 3405 V4R53500u003W1 Laptop – Compact, stable performance',1600,1300,1,1,'/images/product/21/product_lapDe3_1.jpg',1),(22,'Dell Vostro 5620 Laptop 70282719','Dell Vostro 5620 70282719 Laptop - Outstanding Performance',1900,1700,1,1,'/images/product/22/product_lapDe4_1.jpg',7),(23,'Laptop Dell Inspiron 7506-5903SLV','Dell Inspiron 7506-5903SLV Laptop - Office laptop with outstanding performance',2200,1900,1,1,'/images/product/23/product_lapDe5_1.jpg',8),(24,'Laptop Dell Inspiron 15 3520 N3520-i5U085W11BLU','Dell Inspiron 15 3520 N3520-I5U085W11BLU Laptop - Outstanding portable version',1800,1600,1,1,'/images/product/24/product_lapDe6_1.jpg',8),(25,'Laptop Dell Gaming G15 Ryzen Edition','Dell Gaming Laptop G15 Ryzen Edition 5515 70266674: Powerful Performance Laptop',2100,1900,1,1,'/images/product/25/product_lapDe7_1.jpg',9),(26,'Laptop Dell Inspirion N5515 N5R75700U104W1','Dell Inspirion N5515 N5R75700U104W1 Laptop – Stable performance, luxurious design',3200,3100,1,1,'/images/product/26/product_lapDe8_1.jpg',4),(27,'Laptop Dell Latitude 3520 70251603','Dell Latitude 3520 70251603 Laptop – The machine that works tirelessly',1900,1850,1,1,'/images/product/27/product_lapDe9_1.jpg',1),(28,'Dell Vostro 3425 V4R55625U206W laptop','Dell Vostro 3425 V4R55625U206W Laptop - Luxurious design',2500,2350,1,1,'/images/product/28/product_lapDe10_1.jpg',1),(29,'iPhone 11 64GB I Genuine VN/A','Apple always pleases iFan followers with iPhone lines in each price segment. In particular, the  iPhone 11 version  has just been launched but has dominated the smartphone market worldwide with affordable prices.',1800,1800,2,3,'/images/product/29/product_phone1_1.jpg',1),(30,'iPhone 12 Pro Max 128GB I Genuine VN/A','The iPhone 12 Pro Max is about to run out of stock, you can refer to the iPhone 13 Pro Max with many upgrades in configuration, camera, and screen at extremely attractive prices at CellphoneS system.',2200,2150,2,3,'/images/product/30/product_phone2_1.jpg',2),(31,'iPhone 13 256GB | Genuine VN/A','iPhone 13 256GB is considered an iPhone line with an impressive design, evoking luxurious and impressive beauty and outstanding graphics processing ability, becoming an attractive version, creating a unique point for users.',2400,2400,2,3,'/images/product/31/product_phone3_1.jpg',3),(32,'Samsung Galaxy S22 Ultra (8GB - 128GB)','True to the previously rumored information, the new flagship model of the Korean giant was launched with the name Samsung Galaxy S22 Ultra with many valuable improvements. This high-end phone model from Samsung has many changes from design, configuration to camera. So what\'s new with this super product, how much does it cost and should you buy it? Let\'s find out the details right below!',2200,2100,3,3,'/images/product/32/product_phone4_1.jpg',4),(33,'Samsung Galaxy Z Flip4 128GB','Continuing to be a unique, attractive and new folding screen smartphone from the Korean technology company, it is expected to be called Samsung Galaxy Z Flip 4 with many upgrades. This promises to be an explosive super product in the near future and attracts the attention of a large number of users with many improvements from appearance, camera, processor and upgraded battery.',2100,2000,3,3,'/images/product/33/product_phone5_1.jpg',5),(34,'Samsung Galaxy Z Fold4','In addition to the regular Galaxy Note 20 version, Samsung also launched the Note 20 Ultra 5G for high data connectivity and a luxurious and eye-catching monolithic design. This will be the perfect choice for you to use without being outdated after a long time of launch.',2000,2000,3,3,'/images/product/34/product_phone6_1.jpg',4),(35,'Samsung Galaxy Z Fold3 5G','With the amazing success and always sold out from launch to the present time of the previous two generations, Fold 1 and Z Fold 2. This version of  Samsung Galaxy Z Fold3 was launched with high perfection in terms of design. design and performance. Give users a much more enjoyable experience.',1900,1850,3,3,'/images/product/35/product_phone7_1.jpg',1),(36,'Samsung Galaxy A73 (5G) 256GB','Lightning- fast 5G : gives you the ultimate experience with powerful connection speeds. You can enjoy and instantly share any entertainment content and connect with people quickly.',1200,1200,3,3,'/images/product/36/product_phone8_1.jpg',2),(37,'OPPO Reno8','OPPO Reno8 - the leading portrait photography expert thanks to the combination of high-end Sony IMX709 (front camera) and IMX766 (rear camera) sensors with Micro30x lens . In addition, the Reno 8 version gives users the ability to balance life according to the 8-hour time frame to help work efficiently and fully rest. The performance that OPPO Reno 8 owns 8GB RAM , 256GB internal memory comes with MediaTek Dimensity 1300 processor to help optimize efficient tasks and top-notch gaming performance.',1100,1050,7,3,'/images/product/37/product_phone9_1.jpg',2),(38,'OPPO A57','OPPO A57 phone - Large battery, wide screen meets entertainment',1000,1000,7,3,'/images/product/38/product_phone10_1.jpg',3),(39,'OPPO Reno 7 Pro','OPPO Reno7 Pro is a new phone model in the OPPO Reno series with outstanding design and performance. OPPO Reno7 Pro phone  possesses colors with beautiful effects, square frames. The device is equipped with a 6.5-inch screen with a 90Hz refresh rate for smooth visual experiences. Phone performance meets all needs with MediaTek 1200-MAX chip and 4500 mah battery that supports 65W fast charging.',1500,1400,7,3,'/images/product/39/product_phone11_1.jpg',4),(40,'Oppo A16','In July 2021, OPPO has just launched the OPPO A16 phone   in the low-cost segment but with impressive performance. This officially adds a new smartphone in the low-cost segment under VND 4 million from  OPPO phone company  for users to choose from.',1200,1100,7,3,'/images/product/40/product_phone12_1.jpg',5),(41,'OPPO A17K','OPPO A17K has a 6.56-inch LCD screen design with full HD + resolution. At the same time compared to its predecessor, the A17K is upgraded with 4GB RAM and 64GB internal memory to help users store more.',1500,1400,7,3,'/images/product/41/product_phone13_1.jpg',1),(42,'OPPO A95','OPPO A95  has an outstanding design in the mid-range segment accompanied by a large capacity battery and impressive camera system. If you are looking for a breakthrough smartphone compared to the old lines, with this machine, the company has focused on improving maximum performance and more luxurious and sophisticated design lines, this will be the choice. Smart choice for you.',1300,1200,7,3,'/images/product/42/product_phone14_1.jpg',8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receive_type`
--

DROP TABLE IF EXISTS `receive_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receive_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receive_type`
--

LOCK TABLES `receive_type` WRITE;
/*!40000 ALTER TABLE `receive_type` DISABLE KEYS */;
INSERT INTO `receive_type` VALUES (1,'pickup'),(2,'delivery');
/*!40000 ALTER TABLE `receive_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting_sortby`
--

DROP TABLE IF EXISTS `setting_sortby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting_sortby` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `selected` varchar(45) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting_sortby`
--

LOCK TABLES `setting_sortby` WRITE;
/*!40000 ALTER TABLE `setting_sortby` DISABLE KEYS */;
INSERT INTO `setting_sortby` VALUES (-5,'Price High To Low',' '),(-2,'Name Z To A',' '),(-1,'New To Old',' '),(0,'Relevancy','selected'),(1,'Old To New',' '),(2,'Name A To Z',' '),(5,'Price Low To High',' ');
/*!40000 ALTER TABLE `setting_sortby` ENABLE KEYS */;
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
  `phone_number` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'ff.pbphuoc@gmail.com','test','phuoc pham','0450028815'),(14,'phuocpham@gmail.com','test1','Ba Phuoc Pham','0450028815'),(15,'phuoc1@gmail.com','phuoc1','phuoc 1','04123456789'),(16,'phuoc2@gmail.com','phuoc2','phuoc 2','04999888777'),(17,'phuoc3@gmail.com','phuoc3','phuoc3','a'),(18,'phuocpham2@gmail.com','test','phuoc pham','0412345678'),(19,'minh@gmail.com','123456','hoang minh','0999888777');
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

-- Dump completed on 2023-01-17 13:44:27
