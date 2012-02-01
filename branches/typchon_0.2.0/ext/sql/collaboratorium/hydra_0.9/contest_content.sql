# Sequel Pro dump
# Version 2492
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.1.47)
# Database: lportal
# Generation Time: 2010-08-16 23:28:24 -0400
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Contest
# ------------------------------------------------------------

LOCK TABLES `Contest` WRITE;
/*!40000 ALTER TABLE `Contest` DISABLE KEYS */;
INSERT INTO `Contest` (`ContestPK`,`ContestName`,`ContestDescription`,`PlanTypeId`,`created`,`updated`,`authorId`,`contestActive`)
VALUES
	(1,'What plan should we choose in Copenhagen?','This is a contest to develop a global agreement for fossil fuel reductions and land use changes.  The results of this contest will be offered as input to the UNFCC meeting in Copenhagen in Dec. 2009.  ',2,'2010-08-06 00:00:00','2010-08-06 00:00:00',10144,0),
	(2,'What plan should we choose in Cancun?','This is a contest about Copenhagen.  And what plan we should choose in it.  When we choose a plan, we will be happy.  But no one will care.  Because a plan is little more than propaganda.  It does not mean anything, it does not change reality and it does not predict the outcome.  It is a loose commitment that people may or may not follow.  A \"global agreement\" involves too many people to be meaningful.  It is an artifact of a bygone era.',3,'2010-08-06 00:00:00','2010-08-06 00:00:00',10144,1);

/*!40000 ALTER TABLE `Contest` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ContestPhase
# ------------------------------------------------------------

LOCK TABLES `ContestPhase` WRITE;
/*!40000 ALTER TABLE `ContestPhase` DISABLE KEYS */;
INSERT INTO `ContestPhase` (`ContestPhasePK`,`ContestPK`,`ContestPhaseName`,`ContestPhaseDescription`,`ContestPhaseStatus`,`PhaseStartDate`,`PhaseEndDate`,`nextStatus`,`created`,`updated`,`authorId`)
VALUES
	(1,1,'Finalized','These are plans that have been finalized.','VOTING','2009-11-27 00:00:00','2009-11-27 00:00:00',NULL,'2009-11-27 00:00:00','2009-11-27 00:00:00',10144),
	(2,1,'Under development','These are plans that are under development.','OPEN_FOR_SUBMISSION','2009-11-27 00:00:00','2009-11-27 00:00:00',NULL,'2009-11-27 00:00:00','2009-11-27 00:00:00',10144),
	(3,2,'Round 1','These are round 1 plans.','OPEN_FOR_SUBMISSION','2010-11-01 00:00:00','2010-11-14 00:00:00',NULL,'2009-11-27 00:00:00','2009-11-27 00:00:00',10144),
	(4,2,'Round 2','These are round 2 plans.','NOT_YET_OPEN','2010-11-15 00:00:00','2010-11-25 00:00:00',NULL,'2009-11-27 00:00:00','2009-11-27 00:00:00',10144),
	(5,2,'Final Round','These are final round plans for voting.','NOT_YET_OPEN','2010-11-25 00:00:00','2010-12-05 00:00:00',NULL,'2009-11-27 00:00:00','2009-11-27 00:00:00',10144);

/*!40000 ALTER TABLE `ContestPhase` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
