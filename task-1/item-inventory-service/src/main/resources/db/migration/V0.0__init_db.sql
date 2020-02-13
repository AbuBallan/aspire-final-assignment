--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `saga_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `saga_entry` (
  `saga_id` varchar(255) NOT NULL,
  `revision` varchar(255) DEFAULT NULL,
  `saga_type` varchar(255) DEFAULT NULL,
  `serialized_saga` longblob,
  PRIMARY KEY (`saga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saga_entry`
--

LOCK TABLES `saga_entry` WRITE;
/*!40000 ALTER TABLE `saga_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `saga_entry` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `token_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token_entry` (
  `processor_name` varchar(255) NOT NULL,
  `segment` int(11) NOT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) NOT NULL,
  `token` longblob,
  `token_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`processor_name`,`segment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_entry`
--

LOCK TABLES `token_entry` WRITE;
/*!40000 ALTER TABLE `token_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `token_entry` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `association_value_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `association_value_entry` (
  `id` bigint(20) NOT NULL,
  `association_key` varchar(255) NOT NULL,
  `association_value` varchar(255) DEFAULT NULL,
  `saga_id` varchar(255) NOT NULL,
  `saga_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXk45eqnxkgd8hpdn6xixn8sgft` (`saga_type`,`association_key`,`association_value`),
  KEY `IDXgv5k1v2mh6frxuy5c0hgbau94` (`saga_id`,`saga_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `association_value_entry`
--

LOCK TABLES `association_value_entry` WRITE;
/*!40000 ALTER TABLE `association_value_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `association_value_entry` ENABLE KEYS */;
UNLOCK TABLES;