# Sequel Pro dump
# Version 1630
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.42)
# Database: exceldb2
# Generation Time: 2010-04-27 15:47:26 -0400
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table AUTO_PK_SUPPORT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AUTO_PK_SUPPORT`;

CREATE TABLE `AUTO_PK_SUPPORT` (
  `TABLE_NAME` char(100) NOT NULL,
  `NEXT_ID` bigint(20) NOT NULL,
  UNIQUE KEY `TABLE_NAME` (`TABLE_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `AUTO_PK_SUPPORT` WRITE;
/*!40000 ALTER TABLE `AUTO_PK_SUPPORT` DISABLE KEYS */;
INSERT INTO `AUTO_PK_SUPPORT` (`TABLE_NAME`,`NEXT_ID`)
VALUES
	('ExcelModel',1080),
	('InputParam',1760),
	('OutputParam',1720);

/*!40000 ALTER TABLE `AUTO_PK_SUPPORT` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ExcelModel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExcelModel`;

CREATE TABLE `ExcelModel` (
  `ID` varchar(128) NOT NULL,
  `Name` varchar(64) NOT NULL,
  `Path` varchar(128) NOT NULL,
  `worksheet` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `ExcelModel` WRITE;
/*!40000 ALTER TABLE `ExcelModel` DISABLE KEYS */;
INSERT INTO `ExcelModel` (`ID`,`Name`,`Path`,`worksheet`)
VALUES
	('1061','none-given.0','/var/excelserver/IPCC_Qualitative.xls',0),
	('928','none-given.0','/var/excelserver/MERGE.xls',0),
	('1060','none-given.0','/var/excelserver/none-given1253500649330.xls',0),
	('1040','none-given.0','/var/excelserver/none-given1253499485757.xls',0),
	('961','none-given.0','/var/excelserver/IGSM.xls',0),
	('962','none-given.0','/var/excelserver/MERGE.xls',0),
	('963','none-given.0','/var/excelserver/MiniCAM.xls',0),
	('964','none-given.0','/var/excelserver/DICE.xls',0),
	('965','none-given.0','/var/excelserver/PAGE.xls',0),
	('966','none-given.0','/var/excelserver/STERN.xls',0),
	('1020','none-given.0','/var/excelserver/none-given1253498963027.xls',0),
	('1001','none-given.0','/var/excelserver/SEALEVEL.xls',0),
	('1000','none-given.0','/var/excelserver/none-given1253110895465.xls',0);

/*!40000 ALTER TABLE `ExcelModel` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table InputParam
# ------------------------------------------------------------

DROP TABLE IF EXISTS `InputParam`;

CREATE TABLE `InputParam` (
  `ColNum` int(11) NOT NULL,
  `Description` varchar(1024) DEFAULT NULL,
  `ID` varchar(64) NOT NULL,
  `InternalName` varchar(128) NOT NULL,
  `ModelID` varchar(64) NOT NULL,
  `Name` varchar(64) NOT NULL,
  `RowNum` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `InputParam` WRITE;
/*!40000 ALTER TABLE `InputParam` DISABLE KEYS */;
INSERT INTO `InputParam` (`ColNum`,`Description`,`ID`,`InternalName`,`ModelID`,`Name`,`RowNum`)
VALUES
	(0,'Degrees Temperature Change - not dependant on time','1741','Temperature_Change','1061','Temperature Change',1),
	(0,'Year','1615','Time','928','Time',1),
	(1,'Atmospheric CO2 concentration','1616','Atmospheric_CO2_concentration','928','Atmospheric CO2 concentration',1),
	(0,'Degrees Temperature Change - not dependant on time','1740','Temperature_Change','1060','Temperature Change',1),
	(1,'Atmospheric CO2 concentration','1641','Atmospheric_CO2_concentration','961','Atmospheric CO2 concentration',1),
	(0,'Year','1642','Time','961','Time',1),
	(1,'Atmospheric CO2 concentration','1643','Atmospheric_CO2_concentration','962','Atmospheric CO2 concentration',1),
	(0,'Year','1644','Time','962','Time',1),
	(1,'Atmospheric CO2 concentration','1645','Atmospheric_CO2_concentration','963','Atmospheric CO2 concentration',1),
	(0,'Year','1646','Time','963','Time',1),
	(0,'Degrees Temperature Change','1647','Temperature','964','Temperature',1),
	(0,'Degrees Temperature Change','1648','Temperature','965','Temperature',1),
	(0,'Degrees Temperature Change - not dependant on time','1649','Temperature_Change','966','Temperature Change',1),
	(0,'Degrees Temperature Change - not dependant on time','1720','Temperature_Change','1040','Temperature Change',1),
	(0,'Degrees Temperature Change - not dependant on time','1700','Temperature_Change','1020','Temperature Change',1),
	(0,'Degrees Temperature Change','1680','Temperature','1001','Temperature',1);

/*!40000 ALTER TABLE `InputParam` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table OutputParam
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OutputParam`;

CREATE TABLE `OutputParam` (
  `ColNum` int(11) NOT NULL,
  `Description` varchar(1024) DEFAULT NULL,
  `ID` varchar(128) NOT NULL,
  `InternalName` varchar(128) NOT NULL,
  `ModelID` varchar(128) NOT NULL,
  `Name` varchar(128) NOT NULL,
  `NumColumns` int(11) NOT NULL,
  `NumRows` int(11) NOT NULL,
  `RowNum` int(11) NOT NULL,
  `Type` varchar(64) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `OutputParam` WRITE;
/*!40000 ALTER TABLE `OutputParam` DISABLE KEYS */;
INSERT INTO `OutputParam` (`ColNum`,`Description`,`ID`,`InternalName`,`ModelID`,`Name`,`NumColumns`,`NumRows`,`RowNum`,`Type`)
VALUES
	(3,'Health Impacts','1712','Health1_output','1061','Health1',1,1,25,'FREE'),
	(1,'Water Impacts','1713','Water1_output','1061','Water1',1,1,25,'FREE'),
	(4,'Land Impacts','1709','Land_Coastal1_output','1061','Land/Coastal1',1,1,25,'FREE'),
	(2,'Food Impacts','1710','Food_Agriculture1_output','1061','Food/Agriculture1',1,1,25,'FREE'),
	(6,'Abrupt and Large-Scale Impacts','1711','Abrupt_Singular_Events1_output','1061','Abrupt/Singular Events1',1,1,25,'FREE'),
	(0,'Temperature Change','1707','Temperature_Change1_output','1061','Temperature Change1',1,1,25,'RANGE'),
	(5,'Environment Impacts','1708','Environment_Ecosystems1_output','1061','Environment/Ecosystems1',1,1,25,'FREE'),
	(2,'Food Impacts','1705','Food_Agriculture1_output','1060','Food/Agriculture1',1,1,25,'FREE'),
	(3,'Health Impacts','1706','Health1_output','1060','Health1',1,1,25,'FREE'),
	(5,'Environment Impacts','1702','Environment_Ecosystems1_output','1060','Environment/Ecosystems1',1,1,25,'FREE'),
	(4,'Land Impacts','1703','Land_Coastal1_output','1060','Land/Coastal1',1,1,25,'FREE'),
	(1,'Water Impacts','1704','Water1_output','1060','Water1',1,1,25,'FREE'),
	(6,'Abrupt and Large-Scale Impacts','1700','Abrupt_Singular_Events1_output','1060','Abrupt/Singular Events1',1,1,25,'FREE'),
	(0,'Temperature Change','1701','Temperature_Change1_output','1060','Temperature Change1',1,1,25,'RANGE'),
	(2,'Food Impacts','1686','Food_Agriculture_output','1040','Food/Agriculture',1,1,25,'FREE'),
	(0,'Temperature Change','1685','Temperature_Change_output','1040','Temperature Change',1,1,25,'RANGE'),
	(3,'Health Impacts','1684','Health_output','1040','Health',1,1,25,'FREE'),
	(6,'Abrupt and Large-Scale Impacts','1683','Abrupt_Singular_Events_output','1040','Abrupt/Singular Events',1,1,25,'FREE'),
	(1,'Water Impacts','1682','Water_output','1040','Water',1,1,25,'FREE'),
	(2,'Food Impacts','1666','Food_Agriculture_output','1020','Food/Agriculture',1,1,25,'FREE'),
	(4,'Land Impacts','1680','Land_Coastal_output','1040','Land/Coastal',1,1,25,'FREE'),
	(5,'Environment Impacts','1681','Environment_Ecosystems_output','1040','Environment/Ecosystems',1,1,25,'FREE'),
	(0,'This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','1574','_Change_in_GDP_vs__baseline_merge_output','928','%Change in GDP vs. baseline.merge',1,1,25,'RANGE'),
	(1,'Water Impacts','1664','Water_output','1020','Water',1,1,25,'FREE'),
	(5,'Environment Impacts','1665','Environment_Ecosystems_output','1020','Environment/Ecosystems',1,1,25,'FREE'),
	(0,'This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','1601','_Change_in_GDP_vs__baseline_igsm_output','961','%Change in GDP vs. baseline.igsm',1,1,25,'RANGE'),
	(0,'This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','1602','_Change_in_GDP_vs__baseline_merge_output','962','%Change in GDP vs. baseline.merge',1,1,25,'RANGE'),
	(0,'This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','1603','_Change_in_GDP_vs__baseline_minicam_output','963','%Change in GDP vs. baseline.minicam',1,1,25,'RANGE'),
	(0,'%Change (GDP)','1604','Percent_Change_GDP_vs__Basline_output','964','Percent Change GDP vs. Basline',1,1,25,'RANGE'),
	(1,'%Change (5%)','1605','PercentChange5_output','965','PercentChange5',1,1,25,'RANGE'),
	(2,'%Change (95%)','1606','PercentChange95_output','965','PercentChange95',1,1,25,'RANGE'),
	(0,'%Change (Mean)','1607','Mean_Percent_Change_GDP_vs__Basline_output','965','Mean Percent Change GDP vs. Basline',1,1,25,'RANGE'),
	(1,'Water Impacts','1608','Water_Impacts_output','966','Water Impacts',1,1,25,'FREE'),
	(0,'Temperature Change','1609','Temperature_Change_output','966','Temperature Change',1,1,25,'RANGE'),
	(6,'Abrupt and Large-Scale Impacts','1610','Abrupt_and_Large_Scale_Impacts_output','966','Abrupt and Large-Scale Impacts',1,1,25,'FREE'),
	(4,'Land Impacts','1611','Land_Impacts_output','966','Land Impacts',1,1,25,'FREE'),
	(3,'Health Impacts','1612','Health_Impacts_output','966','Health Impacts',1,1,25,'FREE'),
	(5,'Environment Impacts','1613','Environment_Impacts_output','966','Environment Impacts',1,1,25,'FREE'),
	(2,'Food Impacts','1614','Food_Impacts_output','966','Food Impacts',1,1,25,'FREE'),
	(4,'Land Impacts','1663','Land_Coastal_output','1020','Land/Coastal',1,1,25,'FREE'),
	(0,'Temperature Change','1661','Temperature_Change_output','1020','Temperature Change',1,1,25,'RANGE'),
	(6,'Abrupt and Large-Scale Impacts','1662','Abrupt_Singular_Events_output','1020','Abrupt/Singular Events',1,1,25,'FREE'),
	(0,'mm','1640','Sea_Level_Rise_output','1000','Sea Level Rise',1,1,25,'RANGE'),
	(0,'mm','1641','Sea_Level_Rise_output','1001','Sea Level Rise',1,1,25,'RANGE'),
	(3,'Health Impacts','1660','Health_output','1020','Health',1,1,25,'FREE');

/*!40000 ALTER TABLE `OutputParam` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
