# Sequel Pro dump
# Version 1630
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.42)
# Database: lportal
# Generation Time: 2010-03-29 03:45:24 -0400
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Debate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Debate`;

CREATE TABLE `Debate` (
  `debatePK` bigint(20) NOT NULL,
  `debateId` bigint(20) DEFAULT NULL,
  `treeVersion` bigint(20) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `status` varchar(256) DEFAULT NULL,
  `rootCommentId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debatePK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateCategory`;

CREATE TABLE `DebateCategory` (
  `debateCategoryPK` bigint(20) NOT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `title` varchar(75) DEFAULT NULL,
  `brandingContentId` bigint(20) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `parentCategory` bigint(20) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`debateCategoryPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateCategoryMap
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateCategoryMap`;

CREATE TABLE `DebateCategoryMap` (
  `debateCategoryMapPK` bigint(20) NOT NULL,
  `debateCategoryPK` bigint(20) DEFAULT NULL,
  `debateId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debateCategoryMapPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateComment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateComment`;

CREATE TABLE `DebateComment` (
  `debateCommentId` bigint(20) NOT NULL,
  `debateItemId` bigint(20) DEFAULT NULL,
  `debateCommentTitle` varchar(512) DEFAULT NULL,
  `debateCommentDetail` longtext,
  `itemVersion` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`debateCommentId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateItem`;

CREATE TABLE `DebateItem` (
  `debateItemPK` bigint(20) NOT NULL,
  `debateItemId` bigint(20) DEFAULT NULL,
  `debateItemParentId` bigint(20) DEFAULT NULL,
  `debateId` bigint(20) DEFAULT NULL,
  `debateSummary` varchar(256) DEFAULT NULL,
  `debateDetail` longtext,
  `debatePostType` varchar(75) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `itemVersion` bigint(20) DEFAULT NULL,
  `treeVersion` bigint(20) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `status` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`debateItemPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateItemReference
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateItemReference`;

CREATE TABLE `DebateItemReference` (
  `debateItemReferencePK` bigint(20) NOT NULL,
  `debateItemId` bigint(20) DEFAULT NULL,
  `debateId` bigint(20) DEFAULT NULL,
  `itemVersion` bigint(20) DEFAULT NULL,
  `status` varchar(512) DEFAULT NULL,
  `text_` text,
  `url` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`debateItemReferencePK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
