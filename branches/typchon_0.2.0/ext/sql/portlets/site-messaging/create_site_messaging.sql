# Sequel Pro dump
# Version 1630
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.42)
# Database: lportal
# Generation Time: 2010-02-01 12:14:18 -0500
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
  `messageId` bigint(20) NOT NULL DEFAULT '0',
  `fromId` bigint(20) DEFAULT NULL,
  `repliesTo` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `subject` longtext,
  `content` longtext,
  PRIMARY KEY (`messageId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table MessageRecipientStatus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MessageRecipientStatus`;

CREATE TABLE `MessageRecipientStatus` (
  `messageRecipientId` bigint(20) NOT NULL DEFAULT '0',
  `messageId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `opened` tinyint(1) DEFAULT '0',
  `archived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`messageRecipientId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table MessagingUserPreferences
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MessagingUserPreferences`;

CREATE TABLE `MessagingUserPreferences` (
  `userId` bigint(20) DEFAULT NULL,
  `emailOnSend` tinyint(1) DEFAULT '1',
  `emailOnReceipt` tinyint(1) DEFAULT '1',
  `messagingPreferencesId` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`messagingPreferencesId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
