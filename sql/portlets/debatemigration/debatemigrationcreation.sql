# Sequel Pro dump
# Version 1630
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.42)
# Database: lportal
# Generation Time: 2010-03-29 03:36:33 -0400
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table DebateMigration
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateMigration`;

CREATE TABLE `DebateMigration` (
  `debateMigrationPK` bigint(20) NOT NULL,
  `migrationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`debateMigrationPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateMigrationCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateMigrationCategory`;

CREATE TABLE `DebateMigrationCategory` (
  `debateMigrationCategoryPK` bigint(20) NOT NULL,
  `debateMigrationId` bigint(20) DEFAULT NULL,
  `oldMBCategoryId` bigint(20) DEFAULT NULL,
  `newCategoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debateMigrationCategoryPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateMigrationComment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateMigrationComment`;

CREATE TABLE `DebateMigrationComment` (
  `debateMigrationCommentPK` bigint(20) NOT NULL,
  `debateMigrationId` bigint(20) DEFAULT NULL,
  `oldMBMessageId` bigint(20) DEFAULT NULL,
  `oldMBThreadId` bigint(20) DEFAULT NULL,
  `newDebateCommentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debateMigrationCommentPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateMigrationDebate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateMigrationDebate`;

CREATE TABLE `DebateMigrationDebate` (
  `debateMigrationDebatePK` bigint(20) NOT NULL,
  `debateMigrationId` bigint(20) DEFAULT NULL,
  `oldMBCategoryId` bigint(20) DEFAULT NULL,
  `newDebateId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debateMigrationDebatePK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateMigrationItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateMigrationItem`;

CREATE TABLE `DebateMigrationItem` (
  `debateMigrationItemPK` bigint(20) NOT NULL,
  `debateMigrationId` bigint(20) DEFAULT NULL,
  `oldMBMessageId` bigint(20) DEFAULT NULL,
  `newDebateItemId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debateMigrationItemPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
