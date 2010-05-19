# Sequel Pro dump
# Version 1630
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.42)
# Database: lportal
# Generation Time: 2010-03-18 01:19:09 -0400
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
  `status` varchar(75) DEFAULT NULL,
  `rootCommentId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`debatePK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table DebateComment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DebateComment`;

CREATE TABLE `DebateComment` (
  `debateCommentId` bigint(20) NOT NULL,
  `debateItemId` bigint(20) DEFAULT NULL,
  `debateCommentSummary` longtext,
  `debateCommentDetail` longtext,
  `debatePostType` varchar(75) DEFAULT NULL,
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
  `debateSummary` longtext,
  `debateDetail` longtext,
  `debatePostType` varchar(75) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `itemVersion` bigint(20) DEFAULT NULL,
  `treeVersion` bigint(20) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `status` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`debateItemPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
