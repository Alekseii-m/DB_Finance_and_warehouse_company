-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_company
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `assemblyproducts`
--

DROP TABLE IF EXISTS `assemblyproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assemblyproducts` (
  `id_AssemblyProducts` int(11) NOT NULL AUTO_INCREMENT,
  `id_Name_AssemblyProducts` int(11) NOT NULL,
  `id_NameAssemblyItem_AssemblyProducts` int(11) NOT NULL,
  `AmountAssemblyItem_AssemblyProducts` int(11) NOT NULL,
  `DateRecording_AssemblyProducts` datetime NOT NULL,
  `Additionally_AssemblyProducts` varchar(160) DEFAULT NULL,
  PRIMARY KEY (`id_AssemblyProducts`),
  KEY `id_Name_AssemblyProducts` (`id_Name_AssemblyProducts`),
  KEY `assemblyproducts_ibfk_2_idx` (`id_NameAssemblyItem_AssemblyProducts`),
  CONSTRAINT `assemblyproducts_ibfk_1` FOREIGN KEY (`id_Name_AssemblyProducts`) REFERENCES `directory_product` (`id_Product`),
  CONSTRAINT `assemblyproducts_ibfk_2` FOREIGN KEY (`id_NameAssemblyItem_AssemblyProducts`) REFERENCES `directory_product` (`id_Product`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assemblyproducts`
--

LOCK TABLES `assemblyproducts` WRITE;
/*!40000 ALTER TABLE `assemblyproducts` DISABLE KEYS */;
INSERT INTO `assemblyproducts` VALUES (2,12,11,1,'2022-01-05 00:00:00','Пример'),(3,12,1,10,'2022-01-05 00:00:00','Пример'),(4,12,13,4,'2022-01-05 00:00:00','Пример'),(5,16,11,1,'2022-01-05 00:00:00','Пример'),(6,16,1,10,'2022-01-05 00:00:00','Пример'),(7,16,13,4,'2022-01-05 00:00:00','Пример'),(8,17,11,1,'2022-01-05 00:00:00','Пример'),(9,17,1,10,'2022-01-05 00:00:00','Пример'),(10,17,13,4,'2022-01-05 00:00:00','Пример'),(11,18,11,1,'2022-01-05 00:00:00','Пример'),(12,18,1,10,'2022-01-05 00:00:00','Пример'),(14,12,13,4,'2022-04-13 00:00:00','Пример_'),(15,12,2,10,'2022-04-14 00:00:00','пример'),(16,16,2,10,'2022-04-15 00:00:00','пример'),(17,16,15,2,'2022-04-15 00:00:00','пр'),(18,16,14,3,'2022-04-15 00:00:00','пр'),(19,16,2,20,'2022-04-15 00:00:00','битбьт'),(27,18,15,1,'2022-04-25 00:00:00','Пример*');
/*!40000 ALTER TABLE `assemblyproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_sell_products`
--

DROP TABLE IF EXISTS `buy_sell_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy_sell_products` (
  `id_BuySell_Products` int(11) NOT NULL AUTO_INCREMENT,
  `id_NameProduct_BuySell_Products` int(11) NOT NULL,
  `id_OrderNumber_BuySell_Products` int(11) NOT NULL,
  `DateRecording_BuySell_Products` datetime NOT NULL,
  `Amount_BuySell_Products` int(11) NOT NULL,
  `id_ProductProvider_BuySell_Products` int(11) NOT NULL,
  `id_ProductRecipient_BuySell_Products` int(11) NOT NULL,
  `Additionally_BuySell_Products` varchar(160) DEFAULT NULL,
  `select_product_service` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id_BuySell_Products`),
  KEY `id_NameProduct_Buy-Sell_Products` (`id_NameProduct_BuySell_Products`),
  KEY `id_OrderNumber_Buy-Sell_Products` (`id_OrderNumber_BuySell_Products`),
  KEY `id_ProductProvider_Buy-Sell_Products` (`id_ProductProvider_BuySell_Products`),
  KEY `id_ProductRecipient_Buy-Sell_Products` (`id_ProductRecipient_BuySell_Products`),
  CONSTRAINT `buy_sell_products_ibfk_1` FOREIGN KEY (`id_NameProduct_BuySell_Products`) REFERENCES `directory_product` (`id_Product`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `buy_sell_products_ibfk_2` FOREIGN KEY (`id_OrderNumber_BuySell_Products`) REFERENCES `directory_orders` (`id_Orders`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `buy_sell_products_ibfk_3` FOREIGN KEY (`id_ProductProvider_BuySell_Products`) REFERENCES `directory_counterparty` (`id_Counterparty`),
  CONSTRAINT `buy_sell_products_ibfk_4` FOREIGN KEY (`id_ProductRecipient_BuySell_Products`) REFERENCES `directory_counterparty` (`id_Counterparty`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_sell_products`
--

LOCK TABLES `buy_sell_products` WRITE;
/*!40000 ALTER TABLE `buy_sell_products` DISABLE KEYS */;
INSERT INTO `buy_sell_products` VALUES (3,2,4,'2022-01-03 00:00:00',200,1,6,'Пример',NULL),(4,2,4,'2022-02-03 00:00:00',200,1,7,'Пример',NULL),(5,1,3,'2022-03-21 00:00:00',100,8,6,'Пример',NULL),(6,13,4,'2022-04-14 00:00:00',20,3,6,'Прумер',NULL),(7,2,7,'2022-04-23 00:00:00',20,1,6,'лорд',NULL),(9,13,7,'2022-04-26 00:00:00',5,8,6,'морда',NULL);
/*!40000 ALTER TABLE `buy_sell_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_sell_service`
--

DROP TABLE IF EXISTS `buy_sell_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy_sell_service` (
  `id_BuySell_Service` int(11) NOT NULL AUTO_INCREMENT,
  `id_NameService_BuySell_Service` int(11) NOT NULL,
  `id_OrderNumber_BuySell_Service` int(11) NOT NULL,
  `DateRecording_BuySell_Service` datetime NOT NULL,
  `Amount_BuySell_Service` int(11) NOT NULL,
  `id_ServiceProvider_BuySell_Service` int(11) NOT NULL,
  `id_ServiceRecipient_BuySell_Service` int(11) NOT NULL,
  `Additionally_BuySell_Service` varchar(160) DEFAULT NULL,
  `select_product_service` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id_BuySell_Service`),
  KEY `id_NameService_Buy-Sell/Service` (`id_NameService_BuySell_Service`),
  KEY `id_OrderNumber_Buy-Sell/Service` (`id_OrderNumber_BuySell_Service`),
  KEY `id_ServiceProvider_Buy-Sell/Service` (`id_ServiceProvider_BuySell_Service`),
  KEY `id_ServiceRecipient_Buy-Sell/Service` (`id_ServiceRecipient_BuySell_Service`),
  CONSTRAINT `buy_sell_service_ibfk_1` FOREIGN KEY (`id_NameService_BuySell_Service`) REFERENCES `directory_service` (`id_Service`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `buy_sell_service_ibfk_2` FOREIGN KEY (`id_OrderNumber_BuySell_Service`) REFERENCES `directory_orders` (`id_Orders`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `buy_sell_service_ibfk_3` FOREIGN KEY (`id_ServiceProvider_BuySell_Service`) REFERENCES `directory_counterparty` (`id_Counterparty`),
  CONSTRAINT `buy_sell_service_ibfk_4` FOREIGN KEY (`id_ServiceRecipient_BuySell_Service`) REFERENCES `directory_counterparty` (`id_Counterparty`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_sell_service`
--

LOCK TABLES `buy_sell_service` WRITE;
/*!40000 ALTER TABLE `buy_sell_service` DISABLE KEYS */;
INSERT INTO `buy_sell_service` VALUES (1,2,3,'2022-01-03 00:00:00',4,3,6,'Пример',NULL),(2,1,3,'2022-02-03 00:00:00',4,5,6,'Пример',NULL),(3,3,4,'2022-01-01 00:00:00',4,2,6,'Пример',NULL),(4,11,4,'2022-01-02 00:00:00',4,4,6,'Пример',NULL),(5,11,4,'2022-03-26 00:00:00',4,4,7,'Пример',NULL),(6,2,3,'2022-02-03 00:00:00',4,3,7,'Пример',NULL),(8,2,7,'2022-04-13 00:00:00',10,3,7,'Пример',NULL),(9,1,7,'2022-04-16 00:00:00',6,3,7,'лордлор',NULL),(12,2,3,'2022-04-22 00:00:00',4,3,6,'При-мер',NULL),(13,2,3,'2022-04-22 00:00:00',4,3,6,'При-мер',NULL),(14,2,3,'2022-04-22 00:00:00',4,3,6,'При-мер',NULL);
/*!40000 ALTER TABLE `buy_sell_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory_client`
--

DROP TABLE IF EXISTS `directory_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory_client` (
  `id_Client` int(11) NOT NULL AUTO_INCREMENT,
  `Name_Client` varchar(45) NOT NULL,
  `Address_Client` varchar(45) NOT NULL,
  `Phone_Client` varchar(35) DEFAULT NULL,
  `Mail_Client` varchar(40) DEFAULT NULL,
  `DateRecording_Client` datetime NOT NULL,
  `Additionally_Client` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_Client`),
  UNIQUE KEY `Name_Client_UNIQUE` (`Name_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory_client`
--

LOCK TABLES `directory_client` WRITE;
/*!40000 ALTER TABLE `directory_client` DISABLE KEYS */;
INSERT INTO `directory_client` VALUES (3,'Гагарин Юрий Алексеевич','г.Воронеж ул.Массалитинова 21 кв.14','89208888888','89208888888@mail.ru','2005-01-03 00:00:00','пример'),(4,'Крузенштерн Савелий Иванович','г.Семилуки ул.Телегина 8 кв.7','89207777777','89207777777@mail.ru','2007-01-03 00:00:00','пример'),(5,'Абрамович Изя Соломонович','гМогилев-Подольский, Цветной Бульвар 8','89202129598','89202129598@mail.ru','2022-04-13 00:00:00','Абрамович Изя Соломонович'),(7,'Лавренюк Алексей Витальевич','Молдова','730912','lavr@mail.ru','2022-04-16 00:00:00','школа');
/*!40000 ALTER TABLE `directory_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory_counterparty`
--

DROP TABLE IF EXISTS `directory_counterparty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory_counterparty` (
  `id_Counterparty` int(11) NOT NULL AUTO_INCREMENT,
  `Name_Counterparty` varchar(25) NOT NULL,
  `Company_Counterparty` varchar(25) DEFAULT NULL,
  `Address_Counterparty` varchar(45) DEFAULT NULL,
  `Phone_Counterparty` varchar(35) DEFAULT NULL,
  `Mail_Counterparty` varchar(40) DEFAULT NULL,
  `DateRecording_Counterparty` datetime NOT NULL,
  `Specialization` varchar(25) NOT NULL,
  `Additionally_Counterparty` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_Counterparty`),
  UNIQUE KEY `Name_Counterparty_UNIQUE` (`Name_Counterparty`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory_counterparty`
--

LOCK TABLES `directory_counterparty` WRITE;
/*!40000 ALTER TABLE `directory_counterparty` DISABLE KEYS */;
INSERT INTO `directory_counterparty` VALUES (1,'Ольга Иванова','Уют','г.Воронеж ул.Космонавтов 28 д.100','89201111111','89201111111@mail.ru','2007-01-03 00:00:00','магазин меб.фурнитуры','пример'),(2,'Юрий Шурухин','Вума','г.Воронеж ул.Труда проспект 68 д.12','89202222222','\'Неизвестно\'','2007-01-03 00:00:00','Фирма внутренние работы','пример'),(3,'Коля Дочкин','Вума','г.Воронеж ул.Труда проспект 68 д.12','89203333333','Неизвестно','2007-01-03 00:00:00','Фирма внутренние работы','пример'),(4,'Игорь Стародубцев','Перевозчик1','Неизвестно','89204444444','89204444444@mail.ru','2007-01-03 00:00:00','транспорт','пример'),(5,'Петр Петров','Грузали Воронежа','г.Воронеж ул.Солнечная 2 д.1','89205555555','89205555555@mail.ru','2007-01-03 00:00:00','погрузочные работы','пример'),(6,'Люба Черных','Вума','г.Воронеж ул.Труда проспект 68 д.12','89207777777','89207777777@mail.ru','2007-01-03 00:00:00','Директор','пример'),(7,'Евгений Черных','Вума','г.Воронеж ул.Труда проспект 68 д.12','89208888888','89208888888@mail.ru','2022-03-26 00:00:00','зам директора','пример'),(8,'Тамара Гвацители','Фурнитель','г.Воронеж, ул. Машиностроителей 3, корпус 3','89201212121','89201212121@mail.ru','2022-03-21 00:00:00','магазин меб.фурнитуры','пример'),(9,'Иванов Алексей','Ангстрем','г.Воронеж , Лесная поляна 10','89203334444','89203334444@mail.ru','2022-04-13 00:00:00','Расчетные работы','Пример'),(10,'Каюда Елена','Плитстройторг','Воронеж пер. Отличников д.10','+7-980-547-37-11','info@plitstroytorg.ru','2022-04-16 00:00:00','магазин меб.фурнитуры','Менеджер (Клиентский отдел)'),(11,'КЛИЕНТ','----','----','----','----','2022-04-16 00:00:00','----','Клиент - гипотетически'),(12,'ФИРМА','----','----','----','----','2022-04-16 00:00:00','----','Фирма - гипотетически');
/*!40000 ALTER TABLE `directory_counterparty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory_orders`
--

DROP TABLE IF EXISTS `directory_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory_orders` (
  `id_Orders` int(11) NOT NULL AUTO_INCREMENT,
  `id_NameClient_Orders` int(11) NOT NULL,
  `NumberOrder_Orders` varchar(16) NOT NULL,
  `AmountPrepaymentOrder_Orders` int(11) DEFAULT NULL COMMENT 'Сумма предоплаты',
  `AmountlastpaymentOrder_Orders` int(11) DEFAULT NULL COMMENT 'Сумма оставшейся оплаты ',
  `DateOrderAcception_Orders` datetime DEFAULT NULL COMMENT 'Дата принятия заказа',
  `DateDeliveryOrder_Orders` datetime DEFAULT NULL COMMENT 'Дата доставки заказа',
  `DateCompletionOrder_Orders` datetime DEFAULT NULL COMMENT 'Дата завершения заказа',
  `DatePrepaymentOrder_Orders` datetime DEFAULT NULL COMMENT 'Дата предоплаты',
  `DatelastpaymentOrder_Orders` datetime DEFAULT NULL COMMENT 'Дата, оставшейся оплаты ',
  `Coment_Orders` varchar(300) DEFAULT NULL COMMENT '\n',
  `TotalPaymentCounterparty_Orders` int(11) DEFAULT NULL COMMENT 'Оплата КонтрАг_ общ.',
  `TotalAddedCost_Orders` int(11) DEFAULT NULL COMMENT 'Добав.Стоим.общ.\n',
  `TotalCostOrder_Orders` int(11) DEFAULT NULL COMMENT 'Итог.Стоимость общ.- Стоимость заказа',
  PRIMARY KEY (`id_Orders`),
  UNIQUE KEY `NumberOrder_Orders_UNIQUE` (`NumberOrder_Orders`),
  KEY `id_NameClient_Orders` (`id_NameClient_Orders`),
  CONSTRAINT `directory_orders_ibfk_1` FOREIGN KEY (`id_NameClient_Orders`) REFERENCES `directory_client` (`id_Client`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory_orders`
--

LOCK TABLES `directory_orders` WRITE;
/*!40000 ALTER TABLE `directory_orders` DISABLE KEYS */;
INSERT INTO `directory_orders` VALUES (3,3,'20220103 1_к',70000,30000,'2022-01-03 00:00:00','2022-02-03 00:00:00','2022-02-05 00:00:00','2022-01-03 00:00:00','2022-02-03 00:00:00','Пример',7000,3000,10000),(4,4,'20220103 2_к',140000,60000,'2022-01-11 00:00:00','2022-02-03 00:00:00','2022-02-05 00:00:00','2022-01-03 00:00:00','2022-02-03 00:00:00','Пример',15000,5000,20000),(7,3,'20220412 1_ш',60000,40000,'2022-04-12 00:00:00','2022-05-05 00:00:00','2022-05-08 00:00:00','2022-04-12 00:00:00','2022-05-04 00:00:00','Пример',80000,20000,100000),(8,7,'20220412 1_ш+',60005,40005,'2022-04-13 00:00:00','2022-05-06 00:00:00','2022-05-09 00:00:00','2022-04-13 00:00:00','2022-05-05 00:00:00','Пример',80005,20005,100010);
/*!40000 ALTER TABLE `directory_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory_product`
--

DROP TABLE IF EXISTS `directory_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory_product` (
  `id_Product` int(11) NOT NULL AUTO_INCREMENT,
  `Name_Product` varchar(45) NOT NULL,
  `Product_Description` varchar(45) NOT NULL COMMENT 'Описание продукта ',
  `UnitInitialCost_Product` int(11) NOT NULL COMMENT 'Начальная стоимость продукта (закупка) ',
  `UnitWithAddedCost_Product` int(11) NOT NULL COMMENT 'Стоимость продукта (закупка) + добавленная стоимость.\n \n',
  `DateRecording_Product` datetime NOT NULL,
  `Assembly_Product` varchar(3) NOT NULL COMMENT 'Сборочный продукт(да/нет)\n',
  `Company_Product` varchar(25) NOT NULL,
  `Additionally_Product` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_Product`),
  UNIQUE KEY `Name_Product_UNIQUE` (`Name_Product`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory_product`
--

LOCK TABLES `directory_product` WRITE;
/*!40000 ALTER TABLE `directory_product` DISABLE KEYS */;
INSERT INTO `directory_product` VALUES (1,'Евровинт 7х50','Мебельная фурнитура',1,1,'2007-01-03 00:00:00','Нет','Уют','пример'),(2,'Эксцентрик Ф15 + штифт 34','Мебельная фурнитура',7,7,'2007-01-03 00:00:00','Нет','Уют','пример'),(11,'ЛДСП_2750х1830х16_Русский ламинат','Мебельные материалы',2000,2000,'2007-01-03 00:00:00','Нет','Уют','пример'),(12,'Мод.кух.нижн.№1','Мебельная фурнитура',1000,500,'2010-01-03 00:00:00','Да','Вума','пример'),(13,'Опора рег.кухон. разборная Н=100мм','Мебельная фурнитура',1,1,'2007-01-03 00:00:00','Нет','Уют','пример'),(14,'Ручка скоба 64мм_мат.хром_ тип 1','Мебельная фурнитура',1,1,'2007-01-03 00:00:00','Нет','Уют','пример'),(15,'Ручка скоба 128мм_мат.хром_ тип 1','Мебельная фурнитура',200,100,'2022-04-25 00:00:00','Нет','Плитстройторг','при'),(16,'Мод.кух.верх.№1','Мебельная фурнитура',1,1,'2007-01-03 00:00:00','Да','Вума','пример'),(17,'Мод.прихож.верх.№1','Мебельная фурнитура',1,1,'2007-01-03 00:00:00','Да','Вума','пример'),(18,'Мод.прихож.ниж.№1','Мебельная фурнитура',1,1,'2007-01-03 00:00:00','Да','Вума','пример');
/*!40000 ALTER TABLE `directory_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory_service`
--

DROP TABLE IF EXISTS `directory_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory_service` (
  `id_Service` int(11) NOT NULL AUTO_INCREMENT,
  `Name_Service` varchar(45) NOT NULL,
  `UnitInitialCost_Service` int(11) NOT NULL,
  `UnitWithAddedCost_Service` int(11) NOT NULL,
  `DateRecording_Service` datetime NOT NULL,
  `Additionally_Service` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_Service`),
  UNIQUE KEY `Name_Service_UNIQUE` (`Name_Service`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory_service`
--

LOCK TABLES `directory_service` WRITE;
/*!40000 ALTER TABLE `directory_service` DISABLE KEYS */;
INSERT INTO `directory_service` VALUES (1,'Подъем на 1 этаж',100,50,'2022-01-01 00:00:00','пример'),(2,'Сборка мебели 1ч',400,200,'2022-01-02 00:00:00','пример'),(3,'Выгрузка/загрузка авто 1ч',400,200,'2007-01-03 00:00:00','пример'),(4,'Кромление ПВХ 0,4',10,5,'2007-01-04 00:00:00','пример'),(5,'Кромление ПВХ 0,8',15,7,'2007-02-03 00:00:00','пример'),(11,'Транспорт Газель город 1ч',900,100,'2007-02-03 00:00:00','пример'),(25,'Транспорт Газель_4м город 1ч',900,100,'2007-02-03 00:00:00','пример'),(45,'Транспорт Газель_6м город 1ч',1400,400,'2022-04-24 00:00:00','+пример+');
/*!40000 ALTER TABLE `directory_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_company'
--

--
-- Dumping routines for database 'bd_company'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-28  8:08:11
