CREATE DATABASE  IF NOT EXISTS `teste-contabilizei` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `teste-contabilizei`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: teste-contabilizei
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) DEFAULT NULL,
  `documento` bigint(20) DEFAULT NULL,
  `tipo_documento` enum('cpf','cnpj') DEFAULT NULL,
  `fone_ddd` int(3) DEFAULT NULL,
  `fone_numero` int(11) DEFAULT NULL,
  `email` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'André Luiz Rodrigues Farias',7314935912,'cpf',41,96740459,'andrerfarias@gmail.com'),(2,' Contabilizei Tecnologia Ltda',20182807000108,'cnpj',41,3000000,'contato@contabilizei.com.br');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_produtos`
--

DROP TABLE IF EXISTS `pedido_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido_produtos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_unitario` decimal(10,2) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `data_inclusao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pedido_produtos_pedido_id_idx` (`pedido_id`),
  KEY `pedido_produtos_produto_id_idx` (`produto_id`),
  KEY `pedido_produtos_status_id_idx` (`status_id`),
  CONSTRAINT `pedido_produtos_pedido_id` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedido_produtos_produto_id` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedido_produtos_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_produtos`
--

LOCK TABLES `pedido_produtos` WRITE;
/*!40000 ALTER TABLE `pedido_produtos` DISABLE KEYS */;
INSERT INTO `pedido_produtos` VALUES (1,1,1,3,1,899.00,899.00,'2016-10-09 14:36:20'),(9,25,1,1,1,899.00,899.00,'2016-10-11 10:31:24'),(10,25,2,1,1,1478.90,1478.90,'2016-10-11 10:32:08'),(11,25,2,1,1,1478.90,1478.90,'2016-10-11 10:32:16'),(12,28,1,1,2,899.00,1798.00,'2016-10-11 10:36:23'),(13,28,2,1,1,1478.90,1478.90,'2016-10-11 10:36:29'),(14,28,1,1,1,899.00,899.00,'2016-10-11 10:51:21'),(15,28,1,1,1,899.00,899.00,'2016-10-11 10:51:34'),(16,28,1,1,1,899.00,899.00,'2016-10-11 11:00:00'),(17,28,1,1,1,899.00,899.00,'2016-10-11 11:00:14'),(18,28,1,1,1,899.00,899.00,'2016-10-11 11:01:02'),(19,28,1,1,1,899.00,899.00,'2016-10-11 11:01:35'),(20,28,1,1,1,899.00,899.00,'2016-10-11 11:01:53'),(21,28,1,1,1,899.00,899.00,'2016-10-11 11:02:03'),(22,28,1,1,1,899.00,899.00,'2016-10-11 11:02:31'),(23,28,1,1,1,899.00,899.00,'2016-10-11 11:03:33'),(24,28,1,1,1,899.00,899.00,'2016-10-11 11:03:58'),(25,28,1,1,1,899.00,899.00,'2016-10-11 11:04:24'),(26,28,1,1,1,899.00,899.00,'2016-10-11 11:04:44'),(27,28,1,1,1,899.00,899.00,'2016-10-11 11:05:21'),(28,28,1,1,1,899.00,899.00,'2016-10-11 11:05:57'),(29,28,2,1,1,1478.90,1478.90,'2016-10-11 11:06:13'),(30,28,1,1,1,899.00,899.00,'2016-10-11 11:17:53'),(31,28,1,1,1,899.00,899.00,'2016-10-11 11:18:50'),(32,29,2,1,1,1478.90,1478.90,'2016-10-11 12:24:37'),(33,31,1,1,1,899.00,899.00,'2016-10-11 12:48:26'),(34,31,2,1,1,1478.90,1478.90,'2016-10-11 12:48:50'),(35,32,1,1,1,899.00,899.00,'2016-10-11 12:50:19'),(36,33,1,1,1,899.00,899.00,'2016-10-11 13:01:55'),(37,33,2,1,1,1478.90,1478.90,'2016-10-11 13:02:00'),(38,1,2,3,1,1478.90,1478.90,'2016-10-11 13:16:47'),(39,1,1,1,2,899.00,1798.00,'2016-10-11 13:30:12'),(40,35,1,3,3,899.00,2697.00,'2016-10-11 13:36:59'),(41,35,2,3,1,1478.90,1478.90,'2016-10-11 13:37:04'),(42,35,1,1,1,899.00,899.00,'2016-10-11 13:37:11'),(43,27,1,3,2,899.00,1798.00,'2016-10-11 14:07:31'),(44,27,2,1,1,1478.90,1478.90,'2016-10-11 14:07:45'),(45,36,1,1,1,899.00,899.00,'2016-10-11 14:10:07'),(46,38,1,1,1,899.00,899.00,'2016-10-11 14:10:50');
/*!40000 ALTER TABLE `pedido_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint(20) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `data_emissao` datetime NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pedidos_cliente_id_idx` (`cliente_id`),
  KEY `pedidos_status_id_idx` (`status_id`),
  CONSTRAINT `pedidos_cliente_id` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pedidos_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,1,1798.00,'2016-10-09 14:35:32',3),(2,1,1798.00,'2016-10-09 14:35:32',3),(25,1,3856.80,'2016-10-10 15:38:57',3),(26,1,0.00,'2016-10-10 16:33:15',3),(27,1,1478.90,'2016-10-11 08:13:45',3),(28,1,20038.80,'2016-10-11 10:36:17',3),(29,1,1478.90,'2016-10-11 12:22:31',3),(30,2,0.00,'2016-10-11 12:36:58',3),(31,1,2377.90,'2016-10-11 12:48:23',3),(32,1,899.00,'2016-10-11 12:50:15',3),(33,2,2377.90,'2016-10-11 12:57:43',3),(34,2,0.00,'2016-10-11 13:17:42',3),(35,1,899.00,'2016-10-11 13:36:48',3),(36,1,899.00,'2016-10-11 14:10:03',3),(37,2,0.00,'2016-10-11 14:10:20',3),(38,2,899.00,'2016-10-11 14:10:46',3);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `descricao` text NOT NULL,
  `valor_unitario` double(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'Smartphone Moto G 4 Play','Smartphone Moto G 4 Play Dual Chip Android 6.0 Tela 5\'\' 16GB Câmera 8MP - Preto',899.00),(2,'Console PlayStation 4 500GB + Controle Dualshock 4','O PlayStation 4 é o melhor lugar para jogar jogos dinâmicos e conectados, com gráfico rico e alta velocidade, personalização inteligente, funcionalidades sociais altamente integradas e recursos de segunda tela inovadores.',1478.90);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'ativo'),(2,'inativo'),(3,'cancelado');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- Dump completed on 2016-10-11 14:16:43
