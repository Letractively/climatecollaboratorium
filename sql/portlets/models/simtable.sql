# Sequel Pro dump
# Version 1191
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.0.67)
# Database: simulation3
# Generation Time: 2009-11-12 03:40:31 -0500
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Simulation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Simulation`;

CREATE TABLE `Simulation` (
  `description` varchar(8192) default NULL,
  `id` varchar(64) NOT NULL,
  `name` varchar(64) default NULL,
  `url` varchar(64) default NULL,
  `state` varchar(64) default NULL,
  `creation` timestamp NULL default NULL,
  `compositeString` varchar(8192) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `Simulation` WRITE;
/*!40000 ALTER TABLE `Simulation` DISABLE KEYS */;
INSERT INTO `Simulation` (`description`,`id`,`name`,`url`,`state`,`creation`,`compositeString`)
VALUES
	('<p>The Climate Interactive Simulation is a lightweight GHG model, written in the Vensim simulation language, and developed by researchers from the Sustainability Institute and Rocky Mountain Institute. Although it is lightweight enough to be run on a personal computer, its results are consistent with much larger models, including the MiniCam and Merge models.  The web-based version of the model is hosted at the <a href=\'http://forio.com/simulation/international-climate-change-simulation/\'>Forio</a> website.</p>\n<h4>Further Information</h4>\nAdditional information may be found on our <a href=\"/web/guest/resources/-/wiki/Main/Essential+Background\">models page</a>\n<h4>Evaluation</h4>\nThis model has been evaluated by a review panel of respected scientists (see <a href=\'http://www.climateinteractive.org/simulations/C-ROADS/technical/scientific-review/C-ROADS%20Scientific%20Review %20Summary-1.pdf\'>Summary Statement from C-ROADS Scientific Review Panel</a>. From the summary statement: \" This very rapid simulation model reproduces the response properties of state-of- the- art three dimensional climate models very well ? well within the uncertainties of the high resolution models?and with sufficient precision to provide useful information for its intended audience.\"','240','Climate Interactive C-LEARN','http://localhost:8080/pangaea-servlet/rest/','PUBLIC','2009-08-30 19:02:33',NULL),
	('','583','IGSM','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/961','PUBLIC','2009-09-09 20:47:27',NULL),
	('','584','MERGE','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/962','PUBLIC','2009-09-09 20:47:52',NULL),
	('','585','MiniCAM','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/963','PUBLIC','2009-09-09 20:48:16',NULL),
	('','586','DICE','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/964','PUBLIC','2009-09-09 20:48:40',NULL),
	('','587','PAGE','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/965','PUBLIC','2009-09-09 20:49:00',NULL),
	('','588','Tyndall Center','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/966','PUBLIC','2009-09-09 20:49:32',NULL),
	('','681','IPCC AR4','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1061','PUBLIC','2009-09-20 22:38:10',NULL),
	('','621','SeaLevel (C-LEARN / Rahmstorf)','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1001','PUBLIC','2009-09-16 10:23:50',NULL),
	('','623','MIT Composite Model',NULL,'PUBLIC','2009-09-16 10:40:47','<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><inputs><simulation id=\"240\"/></inputs><step><simulation id=\"240\"/></step><step><simulation id=\"586\"><input internalname=\"Temperature\" map=\"all\" source=\"240.GlobalTempChange\"/></simulation><simulation id=\"587\"><input internalname=\"Temperature\" map=\"all\" source=\"240.GlobalTempChange\"/></simulation><simulation id=\"583\"><input internalname=\"Atmospheric_CO2_concentration\" map=\"all\" source=\"240.AtmosphericCO2Concentration\"/><input internalname=\"Time\" interval=\"10\" map=\"some\" source=\"240.Year\"/></simulation><simulation id=\"584\"><input internalname=\"Atmospheric_CO2_concentration\" map=\"all\" source=\"240.AtmosphericCO2Concentration\"/><input internalname=\"Time\" interval=\"10\" map=\"some\" source=\"240.Year\"/></simulation><simulation id=\"585\"><input internalname=\"Atmospheric_CO2_concentration\" map=\"all\" source=\"240.AtmosphericCO2Concentration\"/><input internalname=\"Time\" interval=\"10\" map=\"some\" source=\"240.Year\"/></simulation><simulation id=\"588\"><input internalname=\"Temperature_Change\" map=\"max\" source=\"240.GlobalTempChange\"/></simulation><simulation id=\"681\"><input internalname=\"Temperature_Change\" map=\"max\" source=\"240.GlobalTempChange\"/></simulation><simulation accumulate=\"true\" id=\"621\"><input internalname=\"Temperature\" map=\"all\" source=\"240.GlobalTempChange\"/></simulation></step><outputs><simulation id=\"240\"/><simulation id=\"621\"><remap from=\"2942\" to=\"2958\"/></simulation><simulation id=\"583\"><remap from=\"2875\" to=\"2955\"/></simulation><simulation id=\"584\"><remap from=\"2878\" to=\"2956\"/></simulation><simulation id=\"585\"><remap from=\"2881\" to=\"2957\"/></simulation><simulation id=\"586\"><remap from=\"2883\" to=\"2951\"/></simulation><simulation id=\"587\"><remap from=\"2885\" to=\"2952\"/><remap from=\"2886\" to=\"2953\"/><remap from=\"2887\" to=\"2954\"/></simulation><simulation id=\"681\"/><simulation id=\"588\"/></outputs></root>');

/*!40000 ALTER TABLE `Simulation` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
