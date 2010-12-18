# Sequel Pro dump
# Version 2492
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.47)
# Database: lportal
# Generation Time: 2010-08-16 23:58:06 -0400
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table PlanType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlanType`;

CREATE TABLE `PlanType` (
  `planTypeId` bigint(20) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `description` text,
  `modelId` bigint(20) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `isDefault` tinyint(1) NOT NULL,
  `publishedCounterpartId` bigint(20) NOT NULL,
  `modelTypeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`planTypeId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `PlanType` WRITE;
/*!40000 ALTER TABLE `PlanType` DISABLE KEYS */;
INSERT INTO `PlanType` (`planTypeId`,`name`,`description`,`modelId`,`published`,`isDefault`,`publishedCounterpartId`,`modelTypeName`)
VALUES
	(1,'defaultUnpublished','',760,0,1,2,NULL),
	(2,'defaultPublished','',760,1,0,1,NULL),
	(3,'cancun',NULL,0,0,0,0,'plan');

/*!40000 ALTER TABLE `PlanType` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table PlanTypeAttribute
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlanTypeAttribute`;

CREATE TABLE `PlanTypeAttribute` (
  `planTypeAttributeId` bigint(20) NOT NULL,
  `planTypeId` bigint(20) NOT NULL,
  `attributeName` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`planTypeAttributeId`),
  UNIQUE KEY `UNIQUEPlanTypeIdAttributeName` (`planTypeId`,`attributeName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `PlanTypeAttribute` WRITE;
/*!40000 ALTER TABLE `PlanTypeAttribute` DISABLE KEYS */;
INSERT INTO `PlanTypeAttribute` (`planTypeAttributeId`,`planTypeId`,`attributeName`)
VALUES
	(1,1,'CO2'),
	(2,1,'TEMP'),
	(3,1,'MIN_MITIGATION_COST'),
	(4,1,'MAX_MITIGATION_COST'),
	(5,1,'EMISSIONS_DEVELOPED'),
	(6,1,'EMISSIONS_DEVELOPING_A'),
	(7,1,'EMISSIONS_DEVELOPING_B'),
	(8,1,'SEA_LEVEL'),
	(9,1,'MAX_DAMAGE_COST'),
	(10,1,'MIN_DAMAGE_COST'),
	(11,2,'CO2'),
	(12,2,'TEMP'),
	(13,2,'MIN_MITIGATION_COST'),
	(14,2,'MAX_MITIGATION_COST'),
	(15,2,'EMISSIONS_DEVELOPED'),
	(16,2,'EMISSIONS_DEVELOPING_A'),
	(17,2,'EMISSIONS_DEVELOPING_B'),
	(18,2,'SEA_LEVEL'),
	(19,2,'MAX_DAMAGE_COST'),
	(20,2,'MIN_DAMAGE_COST'),
	(21,1,'MITIGATION_COST_ERROR'),
	(22,2,'MITIGATION_COST_ERROR'),
	(23,3,'CO2'),
	(27,3,'EMISSIONS_DEVELOPED'),
	(28,3,'EMISSIONS_DEVELOPING_A'),
	(29,3,'EMISSIONS_DEVELOPING_B'),
	(31,3,'MAX_DAMAGE_COST'),
	(26,3,'MAX_MITIGATION_COST'),
	(32,3,'MIN_DAMAGE_COST'),
	(25,3,'MIN_MITIGATION_COST'),
	(34,3,'MITIGATION_COST_ERROR'),
	(30,3,'SEA_LEVEL'),
	(24,3,'TEMP');

/*!40000 ALTER TABLE `PlanTypeAttribute` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table PlanTypeColumn
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlanTypeColumn`;

CREATE TABLE `PlanTypeColumn` (
  `planTypeColumnId` bigint(20) NOT NULL,
  `planTypeId` bigint(20) NOT NULL,
  `columnName` varchar(75) DEFAULT NULL,
  `weight` int(11) NOT NULL DEFAULT '0',
  `visibleByDefault` tinyint(1) NOT NULL,
  PRIMARY KEY (`planTypeColumnId`),
  UNIQUE KEY `UNIQUEPlanTypeIdColumnName` (`planTypeId`,`columnName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `PlanTypeColumn` WRITE;
/*!40000 ALTER TABLE `PlanTypeColumn` DISABLE KEYS */;
INSERT INTO `PlanTypeColumn` (`planTypeColumnId`,`planTypeId`,`columnName`,`weight`,`visibleByDefault`)
VALUES
	(9,1,'COLUMN_DEVELOPED_EMISSIONS',6,0),
	(6,1,'CREATE_DATE',3,0),
	(5,1,'CREATOR',2,0),
	(3,1,'NAME',0,1),
	(10,1,'COLUMN_DEVELOPING_A_EMISSIONS',7,0),
	(11,1,'COLUMN_DEVELOPING_B_EMISSIONS',8,0),
	(12,1,'CO2_CONCENTRATION',9,1),
	(13,1,'TEMP_CHANGE',10,1),
	(14,1,'SEA_LEVEL_CHANGE',11,0),
	(15,1,'MITIGATION_COST',12,1),
	(16,1,'DAMAGE_COST',13,1),
	(17,2,'NAME',0,1),
	(18,2,'VOTES',1,1),
	(19,2,'CREATOR',2,0),
	(20,2,'CREATE_DATE',3,0),
	(21,2,'PUBLISH_DATE',4,0),
	(22,2,'POSITIONS',5,0),
	(23,2,'COLUMN_DEVELOPED_EMISSIONS',6,0),
	(24,2,'COLUMN_DEVELOPING_A_EMISSIONS',7,0),
	(25,2,'COLUMN_DEVELOPING_B_EMISSIONS',8,0),
	(26,2,'CO2_CONCENTRATION',9,1),
	(27,2,'TEMP_CHANGE',10,1),
	(28,2,'SEA_LEVEL_CHANGE',11,0),
	(29,2,'MITIGATION_COST',12,1),
	(30,2,'DAMAGE_COST',13,1),
	(56,3,'CO2_CONCENTRATION',9,1),
	(53,3,'COLUMN_DEVELOPED_EMISSIONS',6,0),
	(54,3,'COLUMN_DEVELOPING_A_EMISSIONS',7,0),
	(55,3,'COLUMN_DEVELOPING_B_EMISSIONS',8,0),
	(50,3,'CREATE_DATE',3,0),
	(49,3,'CREATOR',2,0),
	(60,3,'DAMAGE_COST',13,1),
	(59,3,'MITIGATION_COST',12,1),
	(47,3,'NAME',0,1),
	(52,3,'POSITIONS',5,0),
	(51,3,'PUBLISH_DATE',4,0),
	(58,3,'SEA_LEVEL_CHANGE',11,0),
	(57,3,'TEMP_CHANGE',10,1),
	(48,3,'VOTES',1,1);

/*!40000 ALTER TABLE `PlanTypeColumn` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
