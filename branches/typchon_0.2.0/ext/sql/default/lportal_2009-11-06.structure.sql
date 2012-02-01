# Sequel Pro dump
# Version 1191
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.0.67)
# Database: lportal
# Generation Time: 2009-11-06 18:55:05 -0500
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



# Dump of table Account_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Account_`;

CREATE TABLE `Account_` (
  `accountId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentAccountId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `legalName` varchar(75) default NULL,
  `legalId` varchar(75) default NULL,
  `legalType` varchar(75) default NULL,
  `sicCode` varchar(75) default NULL,
  `tickerSymbol` varchar(75) default NULL,
  `industry` varchar(75) default NULL,
  `type_` varchar(75) default NULL,
  `size_` varchar(75) default NULL,
  PRIMARY KEY  (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ActivitySubscription
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ActivitySubscription`;

CREATE TABLE `ActivitySubscription` (
  `entityId` bigint(20) NOT NULL,
  `receiverId` bigint(20) NOT NULL,
  `activitytype` varchar(511) NOT NULL,
  `portletId` varchar(60) NOT NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  PRIMARY KEY  (`entityId`,`receiverId`,`activitytype`,`portletId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table Address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Address`;

CREATE TABLE `Address` (
  `addressId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `street1` varchar(75) default NULL,
  `street2` varchar(75) default NULL,
  `street3` varchar(75) default NULL,
  `city` varchar(75) default NULL,
  `zip` varchar(75) default NULL,
  `regionId` bigint(20) default NULL,
  `countryId` bigint(20) default NULL,
  `typeId` int(11) default NULL,
  `mailing` tinyint(4) default NULL,
  `primary_` tinyint(4) default NULL,
  PRIMARY KEY  (`addressId`),
  KEY `IX_93D5AD4E` (`companyId`),
  KEY `IX_ABD7DAC0` (`companyId`,`classNameId`),
  KEY `IX_71CB1123` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_923BD178` (`companyId`,`classNameId`,`classPK`,`mailing`),
  KEY `IX_9226DBB4` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_5BC8B0D4` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table AnnouncementsDelivery
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AnnouncementsDelivery`;

CREATE TABLE `AnnouncementsDelivery` (
  `deliveryId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `type_` varchar(75) default NULL,
  `email` tinyint(4) default NULL,
  `sms` tinyint(4) default NULL,
  `website` tinyint(4) default NULL,
  PRIMARY KEY  (`deliveryId`),
  UNIQUE KEY `IX_BA4413D5` (`userId`,`type_`),
  KEY `IX_6EDB9600` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table AnnouncementsEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AnnouncementsEntry`;

CREATE TABLE `AnnouncementsEntry` (
  `uuid_` varchar(75) default NULL,
  `entryId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `title` varchar(75) default NULL,
  `content` longtext,
  `url` longtext,
  `type_` varchar(75) default NULL,
  `displayDate` datetime default NULL,
  `expirationDate` datetime default NULL,
  `priority` int(11) default NULL,
  `alert` tinyint(4) default NULL,
  PRIMARY KEY  (`entryId`),
  KEY `IX_A6EF0B81` (`classNameId`,`classPK`),
  KEY `IX_14F06A6B` (`classNameId`,`classPK`,`alert`),
  KEY `IX_D49C2E66` (`userId`),
  KEY `IX_1AFBDE08` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table AnnouncementsFlag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AnnouncementsFlag`;

CREATE TABLE `AnnouncementsFlag` (
  `flagId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `entryId` bigint(20) default NULL,
  `value` int(11) default NULL,
  PRIMARY KEY  (`flagId`),
  UNIQUE KEY `IX_4539A99C` (`userId`,`entryId`,`value`),
  KEY `IX_9C7EB9F` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table BlogsEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BlogsEntry`;

CREATE TABLE `BlogsEntry` (
  `uuid_` varchar(75) default NULL,
  `entryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `title` varchar(150) default NULL,
  `urlTitle` varchar(150) default NULL,
  `content` longtext,
  `displayDate` datetime default NULL,
  `draft` tinyint(4) default NULL,
  `allowTrackbacks` tinyint(4) default NULL,
  `trackbacks` longtext,
  PRIMARY KEY  (`entryId`),
  UNIQUE KEY `IX_DB780A20` (`groupId`,`urlTitle`),
  UNIQUE KEY `IX_1B1040FD` (`uuid_`,`groupId`),
  KEY `IX_72EF6041` (`companyId`),
  KEY `IX_E0D90212` (`companyId`,`displayDate`,`draft`),
  KEY `IX_81A50303` (`groupId`),
  KEY `IX_DA53AFD4` (`groupId`,`displayDate`,`draft`),
  KEY `IX_C07CA83D` (`groupId`,`userId`),
  KEY `IX_B88E740E` (`groupId`,`userId`,`displayDate`,`draft`),
  KEY `IX_69157A4D` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table BlogsStatsUser
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BlogsStatsUser`;

CREATE TABLE `BlogsStatsUser` (
  `statsUserId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `entryCount` int(11) default NULL,
  `lastPostDate` datetime default NULL,
  `ratingsTotalEntries` int(11) default NULL,
  `ratingsTotalScore` double default NULL,
  `ratingsAverageScore` double default NULL,
  PRIMARY KEY  (`statsUserId`),
  UNIQUE KEY `IX_82254C25` (`groupId`,`userId`),
  KEY `IX_90CDA39A` (`companyId`,`entryCount`),
  KEY `IX_43840EEB` (`groupId`),
  KEY `IX_28C78D5C` (`groupId`,`entryCount`),
  KEY `IX_BB51F1D9` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table BookmarksEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BookmarksEntry`;

CREATE TABLE `BookmarksEntry` (
  `uuid_` varchar(75) default NULL,
  `entryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `folderId` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  `url` longtext,
  `comments` longtext,
  `visits` int(11) default NULL,
  `priority` int(11) default NULL,
  PRIMARY KEY  (`entryId`),
  UNIQUE KEY `IX_EAA02A91` (`uuid_`,`groupId`),
  KEY `IX_443BDC38` (`folderId`),
  KEY `IX_E52FF7EF` (`groupId`),
  KEY `IX_E2E9F129` (`groupId`,`userId`),
  KEY `IX_B670BA39` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table BookmarksFolder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BookmarksFolder`;

CREATE TABLE `BookmarksFolder` (
  `uuid_` varchar(75) default NULL,
  `folderId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentFolderId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  PRIMARY KEY  (`folderId`),
  UNIQUE KEY `IX_DC2F8927` (`uuid_`,`groupId`),
  KEY `IX_2ABA25D7` (`companyId`),
  KEY `IX_7F703619` (`groupId`),
  KEY `IX_967799C0` (`groupId`,`parentFolderId`),
  KEY `IX_451E7AE3` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table BrowserTracker
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BrowserTracker`;

CREATE TABLE `BrowserTracker` (
  `browserTrackerId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `browserKey` bigint(20) default NULL,
  PRIMARY KEY  (`browserTrackerId`),
  UNIQUE KEY `IX_E7B95510` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table CalEvent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CalEvent`;

CREATE TABLE `CalEvent` (
  `uuid_` varchar(75) default NULL,
  `eventId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `title` varchar(75) default NULL,
  `description` longtext,
  `startDate` datetime default NULL,
  `endDate` datetime default NULL,
  `durationHour` int(11) default NULL,
  `durationMinute` int(11) default NULL,
  `allDay` tinyint(4) default NULL,
  `timeZoneSensitive` tinyint(4) default NULL,
  `type_` varchar(75) default NULL,
  `repeating` tinyint(4) default NULL,
  `recurrence` longtext,
  `remindBy` int(11) default NULL,
  `firstReminder` int(11) default NULL,
  `secondReminder` int(11) default NULL,
  PRIMARY KEY  (`eventId`),
  UNIQUE KEY `IX_5CCE79C8` (`uuid_`,`groupId`),
  KEY `IX_D6FD9496` (`companyId`),
  KEY `IX_12EE4898` (`groupId`),
  KEY `IX_4FDDD2BF` (`groupId`,`repeating`),
  KEY `IX_FCD7C63D` (`groupId`,`type_`),
  KEY `IX_F6006202` (`remindBy`),
  KEY `IX_C1AD2122` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ClassName_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ClassName_`;

CREATE TABLE `ClassName_` (
  `classNameId` bigint(20) NOT NULL,
  `value` varchar(200) default NULL,
  PRIMARY KEY  (`classNameId`),
  UNIQUE KEY `IX_B27A301F` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Company
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Company`;

CREATE TABLE `Company` (
  `companyId` bigint(20) NOT NULL,
  `accountId` bigint(20) default NULL,
  `webId` varchar(75) default NULL,
  `key_` longtext,
  `virtualHost` varchar(75) default NULL,
  `mx` varchar(75) default NULL,
  `homeURL` longtext,
  `logoId` bigint(20) default NULL,
  `system` tinyint(4) default NULL,
  PRIMARY KEY  (`companyId`),
  UNIQUE KEY `IX_975996C0` (`virtualHost`),
  UNIQUE KEY `IX_EC00543C` (`webId`),
  KEY `IX_38EFE3FD` (`logoId`),
  KEY `IX_12566EC2` (`mx`),
  KEY `IX_35E3E7C6` (`system`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table CompositeMap
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CompositeMap`;

CREATE TABLE `CompositeMap` (
  `childId` varchar(64) default NULL,
  `id` int(11) NOT NULL default '1',
  `parentId` varchar(64) default NULL,
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table Contact_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Contact_`;

CREATE TABLE `Contact_` (
  `contactId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `accountId` bigint(20) default NULL,
  `parentContactId` bigint(20) default NULL,
  `firstName` varchar(75) default NULL,
  `middleName` varchar(75) default NULL,
  `lastName` varchar(75) default NULL,
  `prefixId` int(11) default NULL,
  `suffixId` int(11) default NULL,
  `male` tinyint(4) default NULL,
  `birthday` datetime default NULL,
  `smsSn` varchar(75) default NULL,
  `aimSn` varchar(75) default NULL,
  `facebookSn` varchar(75) default NULL,
  `icqSn` varchar(75) default NULL,
  `jabberSn` varchar(75) default NULL,
  `msnSn` varchar(75) default NULL,
  `mySpaceSn` varchar(75) default NULL,
  `skypeSn` varchar(75) default NULL,
  `twitterSn` varchar(75) default NULL,
  `ymSn` varchar(75) default NULL,
  `employeeStatusId` varchar(75) default NULL,
  `employeeNumber` varchar(75) default NULL,
  `jobTitle` varchar(100) default NULL,
  `jobClass` varchar(75) default NULL,
  `hoursOfOperation` varchar(75) default NULL,
  PRIMARY KEY  (`contactId`),
  KEY `IX_66D496A3` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Counter
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Counter`;

CREATE TABLE `Counter` (
  `name` varchar(75) NOT NULL,
  `currentId` bigint(20) default NULL,
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Country
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Country`;

CREATE TABLE `Country` (
  `countryId` bigint(20) NOT NULL,
  `name` varchar(75) default NULL,
  `a2` varchar(75) default NULL,
  `a3` varchar(75) default NULL,
  `number_` varchar(75) default NULL,
  `idd_` varchar(75) default NULL,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`countryId`),
  UNIQUE KEY `IX_717B97E1` (`a2`),
  UNIQUE KEY `IX_717B9BA2` (`a3`),
  UNIQUE KEY `IX_19DA007B` (`name`),
  KEY `IX_25D734CD` (`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table CyrusUser
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CyrusUser`;

CREATE TABLE `CyrusUser` (
  `userId` varchar(75) NOT NULL,
  `password_` varchar(75) NOT NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table CyrusVirtual
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CyrusVirtual`;

CREATE TABLE `CyrusVirtual` (
  `emailAddress` varchar(75) NOT NULL,
  `userId` varchar(75) NOT NULL,
  PRIMARY KEY  (`emailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table DLFileEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DLFileEntry`;

CREATE TABLE `DLFileEntry` (
  `uuid_` varchar(75) default NULL,
  `fileEntryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `versionUserId` bigint(20) default NULL,
  `versionUserName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `folderId` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `description` longtext,
  `version` double default NULL,
  `size_` int(11) default NULL,
  `readCount` int(11) default NULL,
  `extraSettings` longtext,
  PRIMARY KEY  (`fileEntryId`),
  UNIQUE KEY `IX_8F6C75D0` (`folderId`,`name`),
  UNIQUE KEY `IX_BC2E7E6A` (`uuid_`,`groupId`),
  KEY `IX_4CB1B2B4` (`companyId`),
  KEY `IX_24A846D1` (`folderId`),
  KEY `IX_A9951F17` (`folderId`,`title`),
  KEY `IX_F4AF5636` (`groupId`),
  KEY `IX_43261870` (`groupId`,`userId`),
  KEY `IX_64F0FE40` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table DLFileRank
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DLFileRank`;

CREATE TABLE `DLFileRank` (
  `fileRankId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `folderId` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`fileRankId`),
  UNIQUE KEY `IX_CE705D48` (`companyId`,`userId`,`folderId`,`name`),
  KEY `IX_40B56512` (`folderId`,`name`),
  KEY `IX_BAFB116E` (`groupId`,`userId`),
  KEY `IX_EED06670` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table DLFileShortcut
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DLFileShortcut`;

CREATE TABLE `DLFileShortcut` (
  `uuid_` varchar(75) default NULL,
  `fileShortcutId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `folderId` bigint(20) default NULL,
  `toFolderId` bigint(20) default NULL,
  `toName` varchar(255) default NULL,
  PRIMARY KEY  (`fileShortcutId`),
  UNIQUE KEY `IX_FDB4A946` (`uuid_`,`groupId`),
  KEY `IX_E56EC6AD` (`folderId`),
  KEY `IX_CA2708A2` (`toFolderId`,`toName`),
  KEY `IX_4831EBE4` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table DLFileVersion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DLFileVersion`;

CREATE TABLE `DLFileVersion` (
  `fileVersionId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `folderId` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  `version` double default NULL,
  `size_` int(11) default NULL,
  PRIMARY KEY  (`fileVersionId`),
  UNIQUE KEY `IX_6C5E6512` (`folderId`,`name`,`version`),
  KEY `IX_9CD91DB6` (`folderId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table DLFolder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DLFolder`;

CREATE TABLE `DLFolder` (
  `uuid_` varchar(75) default NULL,
  `folderId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentFolderId` bigint(20) default NULL,
  `name` varchar(100) default NULL,
  `description` longtext,
  `lastPostDate` datetime default NULL,
  PRIMARY KEY  (`folderId`),
  UNIQUE KEY `IX_902FD874` (`groupId`,`parentFolderId`,`name`),
  UNIQUE KEY `IX_3CC1DED2` (`uuid_`,`groupId`),
  KEY `IX_A74DB14C` (`companyId`),
  KEY `IX_F2EA1ACE` (`groupId`),
  KEY `IX_49C37475` (`groupId`,`parentFolderId`),
  KEY `IX_51556082` (`parentFolderId`,`name`),
  KEY `IX_CBC408D8` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table EmailAddress
# ------------------------------------------------------------

DROP TABLE IF EXISTS `EmailAddress`;

CREATE TABLE `EmailAddress` (
  `emailAddressId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `address` varchar(75) default NULL,
  `typeId` int(11) default NULL,
  `primary_` tinyint(4) default NULL,
  PRIMARY KEY  (`emailAddressId`),
  KEY `IX_1BB072CA` (`companyId`),
  KEY `IX_49D2DEC4` (`companyId`,`classNameId`),
  KEY `IX_551A519F` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_2A2CB130` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_7B43CD8` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ExpandoColumn
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExpandoColumn`;

CREATE TABLE `ExpandoColumn` (
  `columnId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `tableId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `type_` int(11) default NULL,
  `defaultData` longtext,
  `typeSettings` longtext,
  PRIMARY KEY  (`columnId`),
  UNIQUE KEY `IX_FEFC8DA7` (`tableId`,`name`),
  KEY `IX_A8C0CBE8` (`tableId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ExpandoRow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExpandoRow`;

CREATE TABLE `ExpandoRow` (
  `rowId_` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `tableId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  PRIMARY KEY  (`rowId_`),
  UNIQUE KEY `IX_81EFBFF5` (`tableId`,`classPK`),
  KEY `IX_D3F5D7AE` (`tableId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ExpandoTable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExpandoTable`;

CREATE TABLE `ExpandoTable` (
  `tableId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `classNameId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  PRIMARY KEY  (`tableId`),
  UNIQUE KEY `IX_37562284` (`companyId`,`classNameId`,`name`),
  KEY `IX_B5AE8A85` (`companyId`,`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ExpandoValue
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExpandoValue`;

CREATE TABLE `ExpandoValue` (
  `valueId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `tableId` bigint(20) default NULL,
  `columnId` bigint(20) default NULL,
  `rowId_` bigint(20) default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `data_` longtext,
  PRIMARY KEY  (`valueId`),
  UNIQUE KEY `IX_9DDD21E5` (`columnId`,`rowId_`),
  UNIQUE KEY `IX_D27B03E7` (`tableId`,`columnId`,`classPK`),
  KEY `IX_B29FEF17` (`classNameId`,`classPK`),
  KEY `IX_F7DD0987` (`columnId`),
  KEY `IX_9112A7A0` (`rowId_`),
  KEY `IX_F0566A77` (`tableId`),
  KEY `IX_1BD3F4C` (`tableId`,`classPK`),
  KEY `IX_CA9AFB7C` (`tableId`,`columnId`),
  KEY `IX_B71E92D5` (`tableId`,`rowId_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Group_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Group_`;

CREATE TABLE `Group_` (
  `groupId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `creatorUserId` bigint(20) default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `parentGroupId` bigint(20) default NULL,
  `liveGroupId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `type_` int(11) default NULL,
  `typeSettings` longtext,
  `friendlyURL` varchar(100) default NULL,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`groupId`),
  UNIQUE KEY `IX_D0D5E397` (`companyId`,`classNameId`,`classPK`),
  UNIQUE KEY `IX_5DE0BE11` (`companyId`,`classNameId`,`liveGroupId`,`name`),
  UNIQUE KEY `IX_5BDDB872` (`companyId`,`friendlyURL`),
  UNIQUE KEY `IX_BBCA55B` (`companyId`,`liveGroupId`,`name`),
  UNIQUE KEY `IX_5AA68501` (`companyId`,`name`),
  KEY `IX_16218A38` (`liveGroupId`),
  KEY `IX_7B590A7A` (`type_`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Groups_Orgs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Groups_Orgs`;

CREATE TABLE `Groups_Orgs` (
  `groupId` bigint(20) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  PRIMARY KEY  (`groupId`,`organizationId`),
  KEY `IX_75267DCA` (`groupId`),
  KEY `IX_6BBB7682` (`organizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Groups_Permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Groups_Permissions`;

CREATE TABLE `Groups_Permissions` (
  `groupId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`groupId`,`permissionId`),
  KEY `IX_C48736B` (`groupId`),
  KEY `IX_EC97689D` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Groups_Roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Groups_Roles`;

CREATE TABLE `Groups_Roles` (
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`groupId`,`roleId`),
  KEY `IX_84471FD2` (`groupId`),
  KEY `IX_3103EF3D` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Groups_UserGroups
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Groups_UserGroups`;

CREATE TABLE `Groups_UserGroups` (
  `groupId` bigint(20) NOT NULL,
  `userGroupId` bigint(20) NOT NULL,
  PRIMARY KEY  (`groupId`,`userGroupId`),
  KEY `IX_31FB749A` (`groupId`),
  KEY `IX_3B69160F` (`userGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table IGFolder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `IGFolder`;

CREATE TABLE `IGFolder` (
  `uuid_` varchar(75) default NULL,
  `folderId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentFolderId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  PRIMARY KEY  (`folderId`),
  UNIQUE KEY `IX_9BBAFB1E` (`groupId`,`parentFolderId`,`name`),
  UNIQUE KEY `IX_B10EFD68` (`uuid_`,`groupId`),
  KEY `IX_60214CF6` (`companyId`),
  KEY `IX_206498F8` (`groupId`),
  KEY `IX_1A605E9F` (`groupId`,`parentFolderId`),
  KEY `IX_F73C0982` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table IGImage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `IGImage`;

CREATE TABLE `IGImage` (
  `uuid_` varchar(75) default NULL,
  `imageId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `folderId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `smallImageId` bigint(20) default NULL,
  `largeImageId` bigint(20) default NULL,
  `custom1ImageId` bigint(20) default NULL,
  `custom2ImageId` bigint(20) default NULL,
  PRIMARY KEY  (`imageId`),
  UNIQUE KEY `IX_E97342D9` (`uuid_`,`groupId`),
  KEY `IX_E597322D` (`custom1ImageId`),
  KEY `IX_D9E0A34C` (`custom2ImageId`),
  KEY `IX_4438CA80` (`folderId`),
  KEY `IX_BCB13A3F` (`folderId`,`name`),
  KEY `IX_63820A7` (`groupId`),
  KEY `IX_BE79E1E1` (`groupId`,`userId`),
  KEY `IX_64F0B572` (`largeImageId`),
  KEY `IX_D3D32126` (`smallImageId`),
  KEY `IX_265BB0F1` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Image
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Image`;

CREATE TABLE `Image` (
  `imageId` bigint(20) NOT NULL,
  `modifiedDate` datetime default NULL,
  `text_` longtext,
  `type_` varchar(75) default NULL,
  `height` int(11) default NULL,
  `width` int(11) default NULL,
  `size_` int(11) default NULL,
  PRIMARY KEY  (`imageId`),
  KEY `IX_6A925A4D` (`size_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalArticle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalArticle`;

CREATE TABLE `JournalArticle` (
  `uuid_` varchar(75) default NULL,
  `id_` bigint(20) NOT NULL,
  `resourcePrimKey` bigint(20) default NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `articleId` varchar(75) default NULL,
  `version` double default NULL,
  `title` varchar(100) default NULL,
  `urlTitle` varchar(150) default NULL,
  `description` longtext,
  `content` longtext,
  `type_` varchar(75) default NULL,
  `structureId` varchar(75) default NULL,
  `templateId` varchar(75) default NULL,
  `displayDate` datetime default NULL,
  `approved` tinyint(4) default NULL,
  `approvedByUserId` bigint(20) default NULL,
  `approvedByUserName` varchar(75) default NULL,
  `approvedDate` datetime default NULL,
  `expired` tinyint(4) default NULL,
  `expirationDate` datetime default NULL,
  `reviewDate` datetime default NULL,
  `indexable` tinyint(4) default NULL,
  `smallImage` tinyint(4) default NULL,
  `smallImageId` bigint(20) default NULL,
  `smallImageURL` varchar(75) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `IX_85C52EEC` (`groupId`,`articleId`,`version`),
  UNIQUE KEY `IX_3463D95B` (`uuid_`,`groupId`),
  KEY `IX_DFF98523` (`companyId`),
  KEY `IX_9356F865` (`groupId`),
  KEY `IX_68C0F69C` (`groupId`,`articleId`),
  KEY `IX_8DBF1387` (`groupId`,`articleId`,`approved`),
  KEY `IX_2E207659` (`groupId`,`structureId`),
  KEY `IX_8DEAE14E` (`groupId`,`templateId`),
  KEY `IX_22882D02` (`groupId`,`urlTitle`),
  KEY `IX_76186981` (`resourcePrimKey`,`approved`),
  KEY `IX_EF9B7028` (`smallImageId`),
  KEY `IX_F029602F` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalArticleImage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalArticleImage`;

CREATE TABLE `JournalArticleImage` (
  `articleImageId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `articleId` varchar(75) default NULL,
  `version` double default NULL,
  `elInstanceId` varchar(75) default NULL,
  `elName` varchar(75) default NULL,
  `languageId` varchar(75) default NULL,
  `tempImage` tinyint(4) default NULL,
  PRIMARY KEY  (`articleImageId`),
  UNIQUE KEY `IX_103D6207` (`groupId`,`articleId`,`version`,`elInstanceId`,`elName`,`languageId`),
  KEY `IX_3B51BB68` (`groupId`),
  KEY `IX_158B526F` (`groupId`,`articleId`,`version`),
  KEY `IX_D4121315` (`tempImage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalArticleResource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalArticleResource`;

CREATE TABLE `JournalArticleResource` (
  `resourcePrimKey` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `articleId` varchar(75) default NULL,
  PRIMARY KEY  (`resourcePrimKey`),
  UNIQUE KEY `IX_88DF994A` (`groupId`,`articleId`),
  KEY `IX_F8433677` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalContentSearch
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalContentSearch`;

CREATE TABLE `JournalContentSearch` (
  `contentSearchId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `privateLayout` tinyint(4) default NULL,
  `layoutId` bigint(20) default NULL,
  `portletId` varchar(200) default NULL,
  `articleId` varchar(75) default NULL,
  PRIMARY KEY  (`contentSearchId`),
  UNIQUE KEY `IX_C3AA93B8` (`groupId`,`privateLayout`,`layoutId`,`portletId`,`articleId`),
  KEY `IX_6838E427` (`groupId`,`articleId`),
  KEY `IX_20962903` (`groupId`,`privateLayout`),
  KEY `IX_7CC7D73E` (`groupId`,`privateLayout`,`articleId`),
  KEY `IX_B3B318DC` (`groupId`,`privateLayout`,`layoutId`),
  KEY `IX_7ACC74C9` (`groupId`,`privateLayout`,`layoutId`,`portletId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalFeed
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalFeed`;

CREATE TABLE `JournalFeed` (
  `uuid_` varchar(75) default NULL,
  `id_` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `feedId` varchar(75) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `type_` varchar(75) default NULL,
  `structureId` varchar(75) default NULL,
  `templateId` varchar(75) default NULL,
  `rendererTemplateId` varchar(75) default NULL,
  `delta` int(11) default NULL,
  `orderByCol` varchar(75) default NULL,
  `orderByType` varchar(75) default NULL,
  `targetLayoutFriendlyUrl` varchar(75) default NULL,
  `targetPortletId` varchar(75) default NULL,
  `contentField` varchar(75) default NULL,
  `feedType` varchar(75) default NULL,
  `feedVersion` double default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `IX_65576CBC` (`groupId`,`feedId`),
  UNIQUE KEY `IX_39031F51` (`uuid_`,`groupId`),
  KEY `IX_35A2DB2F` (`groupId`),
  KEY `IX_50C36D79` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalStructure
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalStructure`;

CREATE TABLE `JournalStructure` (
  `uuid_` varchar(75) default NULL,
  `id_` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `structureId` varchar(75) default NULL,
  `parentStructureId` varchar(75) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `xsd` longtext,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `IX_AB6E9996` (`groupId`,`structureId`),
  UNIQUE KEY `IX_42E86E58` (`uuid_`,`groupId`),
  KEY `IX_B97F5608` (`groupId`),
  KEY `IX_CA0BD48C` (`groupId`,`parentStructureId`),
  KEY `IX_8831E4FC` (`structureId`),
  KEY `IX_6702CA92` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table JournalTemplate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JournalTemplate`;

CREATE TABLE `JournalTemplate` (
  `uuid_` varchar(75) default NULL,
  `id_` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `templateId` varchar(75) default NULL,
  `structureId` varchar(75) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `xsl` longtext,
  `langType` varchar(75) default NULL,
  `cacheable` tinyint(4) default NULL,
  `smallImage` tinyint(4) default NULL,
  `smallImageId` bigint(20) default NULL,
  `smallImageURL` varchar(75) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `IX_E802AA3C` (`groupId`,`templateId`),
  UNIQUE KEY `IX_62D1B3AD` (`uuid_`,`groupId`),
  KEY `IX_77923653` (`groupId`),
  KEY `IX_1701CB2B` (`groupId`,`structureId`),
  KEY `IX_25FFB6FA` (`smallImageId`),
  KEY `IX_1B12CA20` (`templateId`),
  KEY `IX_2857419D` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Layout
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Layout`;

CREATE TABLE `Layout` (
  `plid` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `privateLayout` tinyint(4) default NULL,
  `layoutId` bigint(20) default NULL,
  `parentLayoutId` bigint(20) default NULL,
  `name` longtext,
  `title` longtext,
  `description` longtext,
  `type_` varchar(75) default NULL,
  `typeSettings` longtext,
  `hidden_` tinyint(4) default NULL,
  `friendlyURL` varchar(100) default NULL,
  `iconImage` tinyint(4) default NULL,
  `iconImageId` bigint(20) default NULL,
  `themeId` varchar(75) default NULL,
  `colorSchemeId` varchar(75) default NULL,
  `wapThemeId` varchar(75) default NULL,
  `wapColorSchemeId` varchar(75) default NULL,
  `css` longtext,
  `priority` int(11) default NULL,
  `dlFolderId` bigint(20) default NULL,
  PRIMARY KEY  (`plid`),
  UNIQUE KEY `IX_BC2C4231` (`groupId`,`privateLayout`,`friendlyURL`),
  UNIQUE KEY `IX_7162C27C` (`groupId`,`privateLayout`,`layoutId`),
  KEY `IX_C7FBC998` (`companyId`),
  KEY `IX_FAD05595` (`dlFolderId`),
  KEY `IX_C099D61A` (`groupId`),
  KEY `IX_705F5AA3` (`groupId`,`privateLayout`),
  KEY `IX_6DE88B06` (`groupId`,`privateLayout`,`parentLayoutId`),
  KEY `IX_1A1B61D2` (`groupId`,`privateLayout`,`type_`),
  KEY `IX_23922F7D` (`iconImageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table LayoutSet
# ------------------------------------------------------------

DROP TABLE IF EXISTS `LayoutSet`;

CREATE TABLE `LayoutSet` (
  `layoutSetId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `privateLayout` tinyint(4) default NULL,
  `logo` tinyint(4) default NULL,
  `logoId` bigint(20) default NULL,
  `themeId` varchar(75) default NULL,
  `colorSchemeId` varchar(75) default NULL,
  `wapThemeId` varchar(75) default NULL,
  `wapColorSchemeId` varchar(75) default NULL,
  `css` longtext,
  `pageCount` int(11) default NULL,
  `virtualHost` varchar(75) default NULL,
  PRIMARY KEY  (`layoutSetId`),
  UNIQUE KEY `IX_48550691` (`groupId`,`privateLayout`),
  KEY `IX_A40B8BEC` (`groupId`),
  KEY `IX_5ABC2905` (`virtualHost`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ListType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ListType`;

CREATE TABLE `ListType` (
  `listTypeId` int(11) NOT NULL,
  `name` varchar(75) default NULL,
  `type_` varchar(75) default NULL,
  PRIMARY KEY  (`listTypeId`),
  KEY `IX_2932DD37` (`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBBan
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBBan`;

CREATE TABLE `MBBan` (
  `banId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `banUserId` bigint(20) default NULL,
  PRIMARY KEY  (`banId`),
  UNIQUE KEY `IX_8ABC4E3B` (`groupId`,`banUserId`),
  KEY `IX_69951A25` (`banUserId`),
  KEY `IX_5C3FF12A` (`groupId`),
  KEY `IX_48814BBA` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBCategory`;

CREATE TABLE `MBCategory` (
  `uuid_` varchar(75) default NULL,
  `categoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentCategoryId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `threadCount` int(11) default NULL,
  `messageCount` int(11) default NULL,
  `lastPostDate` datetime default NULL,
  PRIMARY KEY  (`categoryId`),
  UNIQUE KEY `IX_F7D28C2F` (`uuid_`,`groupId`),
  KEY `IX_BC735DCF` (`companyId`),
  KEY `IX_BB870C11` (`groupId`),
  KEY `IX_ED292508` (`groupId`,`parentCategoryId`),
  KEY `IX_C2626EDB` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBDiscussion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBDiscussion`;

CREATE TABLE `MBDiscussion` (
  `discussionId` bigint(20) NOT NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `threadId` bigint(20) default NULL,
  PRIMARY KEY  (`discussionId`),
  UNIQUE KEY `IX_33A4DE38` (`classNameId`,`classPK`),
  UNIQUE KEY `IX_B5CA2DC` (`threadId`),
  KEY `IX_79D0120B` (`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBMailingList
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBMailingList`;

CREATE TABLE `MBMailingList` (
  `uuid_` varchar(75) default NULL,
  `mailingListId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `categoryId` bigint(20) default NULL,
  `emailAddress` varchar(75) default NULL,
  `inProtocol` varchar(75) default NULL,
  `inServerName` varchar(75) default NULL,
  `inServerPort` int(11) default NULL,
  `inUseSSL` tinyint(4) default NULL,
  `inUserName` varchar(75) default NULL,
  `inPassword` varchar(75) default NULL,
  `inReadInterval` int(11) default NULL,
  `outEmailAddress` varchar(75) default NULL,
  `outCustom` tinyint(4) default NULL,
  `outServerName` varchar(75) default NULL,
  `outServerPort` int(11) default NULL,
  `outUseSSL` tinyint(4) default NULL,
  `outUserName` varchar(75) default NULL,
  `outPassword` varchar(75) default NULL,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`mailingListId`),
  UNIQUE KEY `IX_ADA16FE7` (`categoryId`),
  UNIQUE KEY `IX_E858F170` (`uuid_`,`groupId`),
  KEY `IX_BFEB984F` (`active_`),
  KEY `IX_4115EC7A` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBMessage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBMessage`;

CREATE TABLE `MBMessage` (
  `uuid_` varchar(75) default NULL,
  `messageId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `categoryId` bigint(20) default NULL,
  `threadId` bigint(20) default NULL,
  `parentMessageId` bigint(20) default NULL,
  `subject` varchar(75) default NULL,
  `body` longtext,
  `attachments` tinyint(4) default NULL,
  `anonymous` tinyint(4) default NULL,
  `priority` double default NULL,
  PRIMARY KEY  (`messageId`),
  UNIQUE KEY `IX_8D12316E` (`uuid_`,`groupId`),
  KEY `IX_3C865EE5` (`categoryId`),
  KEY `IX_138C7F1E` (`categoryId`,`threadId`),
  KEY `IX_51A8D44D` (`classNameId`,`classPK`),
  KEY `IX_B1432D30` (`companyId`),
  KEY `IX_5B153FB2` (`groupId`),
  KEY `IX_8EB8C5EC` (`groupId`,`userId`),
  KEY `IX_75B95071` (`threadId`),
  KEY `IX_A7038CD7` (`threadId`,`parentMessageId`),
  KEY `IX_C57B16BC` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBMessageFlag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBMessageFlag`;

CREATE TABLE `MBMessageFlag` (
  `messageFlagId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `modifiedDate` datetime default NULL,
  `threadId` bigint(20) default NULL,
  `messageId` bigint(20) default NULL,
  `flag` int(11) default NULL,
  PRIMARY KEY  (`messageFlagId`),
  UNIQUE KEY `IX_E9EB6194` (`userId`,`messageId`,`flag`),
  KEY `IX_D180D4AE` (`messageId`),
  KEY `IX_A6973A8E` (`messageId`,`flag`),
  KEY `IX_C1C9A8FD` (`threadId`),
  KEY `IX_3CFD579D` (`threadId`,`flag`),
  KEY `IX_7B2917BE` (`userId`),
  KEY `IX_2EA537D7` (`userId`,`threadId`,`flag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBStatsUser
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBStatsUser`;

CREATE TABLE `MBStatsUser` (
  `statsUserId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `messageCount` int(11) default NULL,
  `lastPostDate` datetime default NULL,
  PRIMARY KEY  (`statsUserId`),
  UNIQUE KEY `IX_9168E2C9` (`groupId`,`userId`),
  KEY `IX_A00A898F` (`groupId`),
  KEY `IX_FAB5A88B` (`groupId`,`messageCount`),
  KEY `IX_847F92B5` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MBThread
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MBThread`;

CREATE TABLE `MBThread` (
  `threadId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `categoryId` bigint(20) default NULL,
  `rootMessageId` bigint(20) default NULL,
  `messageCount` int(11) default NULL,
  `viewCount` int(11) default NULL,
  `lastPostByUserId` bigint(20) default NULL,
  `lastPostDate` datetime default NULL,
  `priority` double default NULL,
  PRIMARY KEY  (`threadId`),
  KEY `IX_CB854772` (`categoryId`),
  KEY `IX_19D8B60A` (`categoryId`,`lastPostDate`),
  KEY `IX_95C0EA45` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MembershipRequest
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MembershipRequest`;

CREATE TABLE `MembershipRequest` (
  `membershipRequestId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `groupId` bigint(20) default NULL,
  `comments` longtext,
  `replyComments` longtext,
  `replyDate` datetime default NULL,
  `replierUserId` bigint(20) default NULL,
  `statusId` int(11) default NULL,
  PRIMARY KEY  (`membershipRequestId`),
  KEY `IX_8A1CC4B` (`groupId`),
  KEY `IX_C28C72EC` (`groupId`,`statusId`),
  KEY `IX_66D70879` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table MetaData
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MetaData`;

CREATE TABLE `MetaData` (
  `description` varchar(1028) default NULL,
  `id` varchar(64) NOT NULL,
  `internalname` varchar(64) default NULL,
  `name` varchar(64) default NULL,
  `profile` varchar(64) default NULL,
  `vartype` varchar(64) default NULL,
  `units` varchar(64) default NULL,
  `labels` varchar(128) default NULL,
  `external` varchar(128) default NULL,
  `varcontext` varchar(64) default NULL,
  `indexingid` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table MetaDataDefaults
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MetaDataDefaults`;

CREATE TABLE `MetaDataDefaults` (
  `defaultval` varchar(128) default NULL,
  `id` varchar(64) NOT NULL,
  `max` varchar(128) default NULL,
  `metadata` varchar(64) default NULL,
  `min` varchar(128) default NULL,
  `categories` varchar(1024) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table ModelDiscussion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ModelDiscussion`;

CREATE TABLE `ModelDiscussion` (
  `modelId` bigint(20) NOT NULL,
  `categoryId` bigint(20) NOT NULL,
  `modelDiscussionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`modelDiscussionId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table ModelPosition
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ModelPosition`;

CREATE TABLE `ModelPosition` (
  `modelId` bigint(20) NOT NULL,
  `positionId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `userName` varchar(511) default NULL,
  PRIMARY KEY  (`modelId`,`positionId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table OrgGroupPermission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrgGroupPermission`;

CREATE TABLE `OrgGroupPermission` (
  `organizationId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`organizationId`,`groupId`,`permissionId`),
  KEY `IX_A425F71A` (`groupId`),
  KEY `IX_6C53DA4E` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table OrgGroupRole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrgGroupRole`;

CREATE TABLE `OrgGroupRole` (
  `organizationId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`organizationId`,`groupId`,`roleId`),
  KEY `IX_4A527DD3` (`groupId`),
  KEY `IX_AB044D1C` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table OrgLabor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrgLabor`;

CREATE TABLE `OrgLabor` (
  `orgLaborId` bigint(20) NOT NULL,
  `organizationId` bigint(20) default NULL,
  `typeId` int(11) default NULL,
  `sunOpen` int(11) default NULL,
  `sunClose` int(11) default NULL,
  `monOpen` int(11) default NULL,
  `monClose` int(11) default NULL,
  `tueOpen` int(11) default NULL,
  `tueClose` int(11) default NULL,
  `wedOpen` int(11) default NULL,
  `wedClose` int(11) default NULL,
  `thuOpen` int(11) default NULL,
  `thuClose` int(11) default NULL,
  `friOpen` int(11) default NULL,
  `friClose` int(11) default NULL,
  `satOpen` int(11) default NULL,
  `satClose` int(11) default NULL,
  PRIMARY KEY  (`orgLaborId`),
  KEY `IX_6AF0D434` (`organizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Organization_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Organization_`;

CREATE TABLE `Organization_` (
  `organizationId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `parentOrganizationId` bigint(20) default NULL,
  `leftOrganizationId` bigint(20) default NULL,
  `rightOrganizationId` bigint(20) default NULL,
  `name` varchar(100) default NULL,
  `type_` varchar(75) default NULL,
  `recursable` tinyint(4) default NULL,
  `regionId` bigint(20) default NULL,
  `countryId` bigint(20) default NULL,
  `statusId` int(11) default NULL,
  `comments` longtext,
  PRIMARY KEY  (`organizationId`),
  UNIQUE KEY `IX_E301BDF5` (`companyId`,`name`),
  KEY `IX_834BCEB6` (`companyId`),
  KEY `IX_418E4522` (`companyId`,`parentOrganizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PasswordPolicy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PasswordPolicy`;

CREATE TABLE `PasswordPolicy` (
  `passwordPolicyId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `defaultPolicy` tinyint(4) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `changeable` tinyint(4) default NULL,
  `changeRequired` tinyint(4) default NULL,
  `minAge` bigint(20) default NULL,
  `checkSyntax` tinyint(4) default NULL,
  `allowDictionaryWords` tinyint(4) default NULL,
  `minLength` int(11) default NULL,
  `history` tinyint(4) default NULL,
  `historyCount` int(11) default NULL,
  `expireable` tinyint(4) default NULL,
  `maxAge` bigint(20) default NULL,
  `warningTime` bigint(20) default NULL,
  `graceLimit` int(11) default NULL,
  `lockout` tinyint(4) default NULL,
  `maxFailure` int(11) default NULL,
  `lockoutDuration` bigint(20) default NULL,
  `requireUnlock` tinyint(4) default NULL,
  `resetFailureCount` bigint(20) default NULL,
  PRIMARY KEY  (`passwordPolicyId`),
  UNIQUE KEY `IX_3FBFA9F4` (`companyId`,`name`),
  KEY `IX_2C1142E` (`companyId`,`defaultPolicy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PasswordPolicyRel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PasswordPolicyRel`;

CREATE TABLE `PasswordPolicyRel` (
  `passwordPolicyRelId` bigint(20) NOT NULL,
  `passwordPolicyId` bigint(20) default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  PRIMARY KEY  (`passwordPolicyRelId`),
  KEY `IX_C3A17327` (`classNameId`,`classPK`),
  KEY `IX_ED7CF243` (`passwordPolicyId`,`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PasswordTracker
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PasswordTracker`;

CREATE TABLE `PasswordTracker` (
  `passwordTrackerId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `password_` varchar(75) default NULL,
  PRIMARY KEY  (`passwordTrackerId`),
  KEY `IX_326F75BD` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Permission_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Permission_`;

CREATE TABLE `Permission_` (
  `permissionId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `actionId` varchar(75) default NULL,
  `resourceId` bigint(20) default NULL,
  PRIMARY KEY  (`permissionId`),
  UNIQUE KEY `IX_4D19C2B8` (`actionId`,`resourceId`),
  KEY `IX_F090C113` (`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Phone
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Phone`;

CREATE TABLE `Phone` (
  `phoneId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `number_` varchar(75) default NULL,
  `extension` varchar(75) default NULL,
  `typeId` int(11) default NULL,
  `primary_` tinyint(4) default NULL,
  PRIMARY KEY  (`phoneId`),
  KEY `IX_9F704A14` (`companyId`),
  KEY `IX_A2E4AFBA` (`companyId`,`classNameId`),
  KEY `IX_9A53569` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_812CE07A` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_F202B9CE` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Plan
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Plan`;

CREATE TABLE `Plan` (
  `planId` bigint(20) NOT NULL,
  `name` varchar(128) default NULL,
  `shortcontent` longtext,
  `content` longtext,
  `published` tinyint(1) default NULL,
  `companyId` bigint(20) default NULL,
  `groupId` bigint(20) default NULL,
  `childGroupId` bigint(20) default NULL,
  `MBCategoryId` bigint(20) default NULL,
  `modelId` varchar(75) default NULL,
  `scenarioId` varchar(75) default NULL,
  `topicId` varchar(75) default NULL,
  `votes` int(11) default '0',
  `damageCostAvg` double default NULL,
  `damageCostStdDev` double default NULL,
  `mitigationCostAvg` double default NULL,
  `mitigationCostStdDev` double default NULL,
  `developedEmissions` double default NULL,
  `developingAEmissions` double default NULL,
  `developingBEmissions` double default NULL,
  `globalEmissions` double default NULL,
  `temperatureChange` double default NULL,
  `CO2` double default NULL,
  `seaLevelRise` int(11) default NULL,
  `createDate` date default NULL,
  `publishDate` date default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(128) default NULL,
  `userScreenName` varchar(128) default NULL,
  `modifiedDate` date default NULL,
  PRIMARY KEY  (`planId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PlanPosition
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlanPosition`;

CREATE TABLE `PlanPosition` (
  `planId` bigint(20) NOT NULL,
  `positionId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `userName` varchar(511) default NULL,
  PRIMARY KEY  (`planId`,`positionId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PlanVote
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlanVote`;

CREATE TABLE `PlanVote` (
  `userId` bigint(20) NOT NULL,
  `planId` bigint(20) default NULL,
  `createDate` date default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PlansFilter
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlansFilter`;

CREATE TABLE `PlansFilter` (
  `userId` bigint(20) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `name` varchar(75) NOT NULL,
  `creator` varchar(75) NOT NULL,
  `description` varchar(75) NOT NULL,
  `CO2From` double NOT NULL,
  `CO2To` double NOT NULL,
  `votesFrom` double NOT NULL,
  `votesTo` double NOT NULL,
  `damageFrom` double NOT NULL,
  `damageTo` double NOT NULL,
  `mitigationFrom` double NOT NULL,
  `mitigationTo` double NOT NULL,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  `filterPositionsAll` tinyint(1) NOT NULL,
  `enabled` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`userId`,`published`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PlansFilterPosition
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlansFilterPosition`;

CREATE TABLE `PlansFilterPosition` (
  `userId` bigint(20) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `positionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`published`,`positionId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PlansUserSettings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PlansUserSettings`;

CREATE TABLE `PlansUserSettings` (
  `userId` bigint(20) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `showName` tinyint(1) default NULL,
  `showDate` tinyint(1) default NULL,
  `showCO2` tinyint(1) default NULL,
  `showMitigationCost` tinyint(1) default NULL,
  `showDamageCost` tinyint(1) default NULL,
  `showPositions` tinyint(1) default NULL,
  `showVotes` tinyint(1) default NULL,
  `showDevelopedEmissions` tinyint(1) default NULL,
  `showDevelopingAEmissions` tinyint(1) default NULL,
  `showDevelopingBEmissions` tinyint(1) default NULL,
  `showGlobalEmissions` tinyint(1) default NULL,
  `showTemperatureChange` tinyint(1) default NULL,
  `showSeaLevelRise` tinyint(1) default NULL,
  `showCreator` tinyint(1) default NULL,
  PRIMARY KEY  (`userId`,`published`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PluginSetting
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PluginSetting`;

CREATE TABLE `PluginSetting` (
  `pluginSettingId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `pluginId` varchar(75) default NULL,
  `pluginType` varchar(75) default NULL,
  `roles` longtext,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`pluginSettingId`),
  UNIQUE KEY `IX_7171B2E8` (`companyId`,`pluginId`,`pluginType`),
  KEY `IX_B9746445` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PollsChoice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PollsChoice`;

CREATE TABLE `PollsChoice` (
  `uuid_` varchar(75) default NULL,
  `choiceId` bigint(20) NOT NULL,
  `questionId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` varchar(1000) default NULL,
  PRIMARY KEY  (`choiceId`),
  UNIQUE KEY `IX_D76DD2CF` (`questionId`,`name`),
  KEY `IX_EC370F10` (`questionId`),
  KEY `IX_6660B399` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PollsQuestion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PollsQuestion`;

CREATE TABLE `PollsQuestion` (
  `uuid_` varchar(75) default NULL,
  `questionId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `title` varchar(500) default NULL,
  `description` longtext,
  `expirationDate` datetime default NULL,
  `lastVoteDate` datetime default NULL,
  PRIMARY KEY  (`questionId`),
  UNIQUE KEY `IX_F3C9F36` (`uuid_`,`groupId`),
  KEY `IX_9FF342EA` (`groupId`),
  KEY `IX_51F087F4` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PollsVote
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PollsVote`;

CREATE TABLE `PollsVote` (
  `voteId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `questionId` bigint(20) default NULL,
  `choiceId` bigint(20) default NULL,
  `voteDate` datetime default NULL,
  PRIMARY KEY  (`voteId`),
  UNIQUE KEY `IX_1BBFD4D3` (`questionId`,`userId`),
  KEY `IX_D5DF7B54` (`choiceId`),
  KEY `IX_12112599` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Portlet
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Portlet`;

CREATE TABLE `Portlet` (
  `id_` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `portletId` varchar(200) default NULL,
  `roles` longtext,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `IX_12B5E51D` (`companyId`,`portletId`),
  KEY `IX_80CC9508` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PortletItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PortletItem`;

CREATE TABLE `PortletItem` (
  `portletItemId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `name` varchar(75) default NULL,
  `portletId` varchar(75) default NULL,
  `classNameId` bigint(20) default NULL,
  PRIMARY KEY  (`portletItemId`),
  KEY `IX_96BDD537` (`groupId`,`classNameId`),
  KEY `IX_D699243F` (`groupId`,`name`,`portletId`,`classNameId`),
  KEY `IX_2C61314E` (`groupId`,`portletId`),
  KEY `IX_E922D6C0` (`groupId`,`portletId`,`classNameId`),
  KEY `IX_8E71167F` (`groupId`,`portletId`,`classNameId`,`name`),
  KEY `IX_33B8CE8D` (`groupId`,`portletId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table PortletPreferences
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PortletPreferences`;

CREATE TABLE `PortletPreferences` (
  `portletPreferencesId` bigint(20) NOT NULL,
  `ownerId` bigint(20) default NULL,
  `ownerType` int(11) default NULL,
  `plid` bigint(20) default NULL,
  `portletId` varchar(200) default NULL,
  `preferences` longtext,
  PRIMARY KEY  (`portletPreferencesId`),
  UNIQUE KEY `IX_C7057FF7` (`ownerId`,`ownerType`,`plid`,`portletId`),
  KEY `IX_E4F13E6E` (`ownerId`,`ownerType`,`plid`),
  KEY `IX_F15C1C4F` (`plid`),
  KEY `IX_D340DB76` (`plid`,`portletId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_BLOB_TRIGGERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_BLOB_TRIGGERS`;

CREATE TABLE `QUARTZ_BLOB_TRIGGERS` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY  (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_CALENDARS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_CALENDARS`;

CREATE TABLE `QUARTZ_CALENDARS` (
  `CALENDAR_NAME` varchar(80) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY  (`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_CRON_TRIGGERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_CRON_TRIGGERS`;

CREATE TABLE `QUARTZ_CRON_TRIGGERS` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `CRON_EXPRESSION` varchar(80) NOT NULL,
  `TIME_ZONE_ID` varchar(80) default NULL,
  PRIMARY KEY  (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_FIRED_TRIGGERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_FIRED_TRIGGERS`;

CREATE TABLE `QUARTZ_FIRED_TRIGGERS` (
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `IS_VOLATILE` tinyint(4) NOT NULL,
  `INSTANCE_NAME` varchar(80) NOT NULL,
  `FIRED_TIME` bigint(20) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(80) default NULL,
  `JOB_GROUP` varchar(80) default NULL,
  `IS_STATEFUL` tinyint(4) default NULL,
  `REQUESTS_RECOVERY` tinyint(4) default NULL,
  PRIMARY KEY  (`ENTRY_ID`),
  KEY `IX_804154AF` (`INSTANCE_NAME`),
  KEY `IX_BAB9A1F7` (`JOB_GROUP`),
  KEY `IX_ADEE6A17` (`JOB_NAME`),
  KEY `IX_64B194F2` (`TRIGGER_GROUP`),
  KEY `IX_5FEABBC` (`TRIGGER_NAME`),
  KEY `IX_20D8706C` (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_JOB_DETAILS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_JOB_DETAILS`;

CREATE TABLE `QUARTZ_JOB_DETAILS` (
  `JOB_NAME` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `DESCRIPTION` varchar(120) default NULL,
  `JOB_CLASS_NAME` varchar(128) NOT NULL,
  `IS_DURABLE` tinyint(4) NOT NULL,
  `IS_VOLATILE` tinyint(4) NOT NULL,
  `IS_STATEFUL` tinyint(4) NOT NULL,
  `REQUESTS_RECOVERY` tinyint(4) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY  (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_JOB_LISTENERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_JOB_LISTENERS`;

CREATE TABLE `QUARTZ_JOB_LISTENERS` (
  `JOB_NAME` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `JOB_LISTENER` varchar(80) NOT NULL,
  PRIMARY KEY  (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_LOCKS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_LOCKS`;

CREATE TABLE `QUARTZ_LOCKS` (
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY  (`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_PAUSED_TRIGGER_GRPS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_PAUSED_TRIGGER_GRPS`;

CREATE TABLE `QUARTZ_PAUSED_TRIGGER_GRPS` (
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  PRIMARY KEY  (`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_SCHEDULER_STATE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_SCHEDULER_STATE`;

CREATE TABLE `QUARTZ_SCHEDULER_STATE` (
  `INSTANCE_NAME` varchar(80) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(20) NOT NULL,
  `CHECKIN_INTERVAL` bigint(20) NOT NULL,
  PRIMARY KEY  (`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_SIMPLE_TRIGGERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_SIMPLE_TRIGGERS`;

CREATE TABLE `QUARTZ_SIMPLE_TRIGGERS` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `REPEAT_COUNT` bigint(20) NOT NULL,
  `REPEAT_INTERVAL` bigint(20) NOT NULL,
  `TIMES_TRIGGERED` bigint(20) NOT NULL,
  PRIMARY KEY  (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_TRIGGERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_TRIGGERS`;

CREATE TABLE `QUARTZ_TRIGGERS` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `JOB_NAME` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `IS_VOLATILE` tinyint(4) NOT NULL,
  `DESCRIPTION` varchar(120) default NULL,
  `NEXT_FIRE_TIME` bigint(20) default NULL,
  `PREV_FIRE_TIME` bigint(20) default NULL,
  `PRIORITY` int(11) default NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(20) NOT NULL,
  `END_TIME` bigint(20) default NULL,
  `CALENDAR_NAME` varchar(80) default NULL,
  `MISFIRE_INSTR` int(11) default NULL,
  `JOB_DATA` blob,
  PRIMARY KEY  (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IX_F7655CC3` (`NEXT_FIRE_TIME`),
  KEY `IX_9955EFB5` (`TRIGGER_STATE`),
  KEY `IX_8040C593` (`TRIGGER_STATE`,`NEXT_FIRE_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table QUARTZ_TRIGGER_LISTENERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `QUARTZ_TRIGGER_LISTENERS`;

CREATE TABLE `QUARTZ_TRIGGER_LISTENERS` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `TRIGGER_LISTENER` varchar(80) NOT NULL,
  PRIMARY KEY  (`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_LISTENER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table RatingsEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RatingsEntry`;

CREATE TABLE `RatingsEntry` (
  `entryId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `score` double default NULL,
  PRIMARY KEY  (`entryId`),
  UNIQUE KEY `IX_B47E3C11` (`userId`,`classNameId`,`classPK`),
  KEY `IX_16184D57` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table RatingsStats
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RatingsStats`;

CREATE TABLE `RatingsStats` (
  `statsId` bigint(20) NOT NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `totalEntries` int(11) default NULL,
  `totalScore` double default NULL,
  `averageScore` double default NULL,
  PRIMARY KEY  (`statsId`),
  UNIQUE KEY `IX_A6E99284` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Region
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Region`;

CREATE TABLE `Region` (
  `regionId` bigint(20) NOT NULL,
  `countryId` bigint(20) default NULL,
  `regionCode` varchar(75) default NULL,
  `name` varchar(75) default NULL,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`regionId`),
  KEY `IX_2D9A426F` (`active_`),
  KEY `IX_16D87CA7` (`countryId`),
  KEY `IX_11FB3E42` (`countryId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Release_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Release_`;

CREATE TABLE `Release_` (
  `releaseId` bigint(20) NOT NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `buildNumber` int(11) default NULL,
  `buildDate` datetime default NULL,
  `verified` tinyint(4) default NULL,
  `testString` varchar(1024) default NULL,
  PRIMARY KEY  (`releaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ResourceAction
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ResourceAction`;

CREATE TABLE `ResourceAction` (
  `resourceActionId` bigint(20) NOT NULL,
  `name` varchar(75) default NULL,
  `actionId` varchar(75) default NULL,
  `bitwiseValue` bigint(20) default NULL,
  PRIMARY KEY  (`resourceActionId`),
  UNIQUE KEY `IX_EDB9986E` (`name`,`actionId`),
  KEY `IX_81F2DB09` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ResourceCode
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ResourceCode`;

CREATE TABLE `ResourceCode` (
  `codeId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  `scope` int(11) default NULL,
  PRIMARY KEY  (`codeId`),
  UNIQUE KEY `IX_A32C097E` (`companyId`,`name`,`scope`),
  KEY `IX_717FDD47` (`companyId`),
  KEY `IX_AACAFF40` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ResourcePermission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ResourcePermission`;

CREATE TABLE `ResourcePermission` (
  `resourcePermissionId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  `scope` int(11) default NULL,
  `primKey` varchar(255) default NULL,
  `roleId` bigint(20) default NULL,
  `actionIds` bigint(20) default NULL,
  PRIMARY KEY  (`resourcePermissionId`),
  UNIQUE KEY `IX_8D83D0CE` (`companyId`,`name`,`scope`,`primKey`,`roleId`),
  KEY `IX_60B99860` (`companyId`,`name`,`scope`),
  KEY `IX_2200AA69` (`companyId`,`name`,`scope`,`primKey`),
  KEY `IX_A37A0588` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Resource_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Resource_`;

CREATE TABLE `Resource_` (
  `resourceId` bigint(20) NOT NULL,
  `codeId` bigint(20) default NULL,
  `primKey` varchar(255) default NULL,
  PRIMARY KEY  (`resourceId`),
  UNIQUE KEY `IX_67DE7856` (`codeId`,`primKey`),
  KEY `IX_2578FBD3` (`codeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Role_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Role_`;

CREATE TABLE `Role_` (
  `roleId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `title` longtext,
  `description` longtext,
  `type_` int(11) default NULL,
  `subtype` varchar(75) default NULL,
  PRIMARY KEY  (`roleId`),
  UNIQUE KEY `IX_A88E424E` (`companyId`,`classNameId`,`classPK`),
  UNIQUE KEY `IX_EBC931B8` (`companyId`,`name`),
  KEY `IX_449A10B9` (`companyId`),
  KEY `IX_CBE204` (`type_`,`subtype`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Roles_Permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Roles_Permissions`;

CREATE TABLE `Roles_Permissions` (
  `roleId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`roleId`,`permissionId`),
  KEY `IX_7A3619C6` (`permissionId`),
  KEY `IX_E04E486D` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCFrameworkVersi_SCProductVers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCFrameworkVersi_SCProductVers`;

CREATE TABLE `SCFrameworkVersi_SCProductVers` (
  `frameworkVersionId` bigint(20) NOT NULL,
  `productVersionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`frameworkVersionId`,`productVersionId`),
  KEY `IX_3BB93ECA` (`frameworkVersionId`),
  KEY `IX_E8D33FF9` (`productVersionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCFrameworkVersion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCFrameworkVersion`;

CREATE TABLE `SCFrameworkVersion` (
  `frameworkVersionId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `name` varchar(75) default NULL,
  `url` longtext,
  `active_` tinyint(4) default NULL,
  `priority` int(11) default NULL,
  PRIMARY KEY  (`frameworkVersionId`),
  KEY `IX_C98C0D78` (`companyId`),
  KEY `IX_272991FA` (`groupId`),
  KEY `IX_6E1764F` (`groupId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCLicense
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCLicense`;

CREATE TABLE `SCLicense` (
  `licenseId` bigint(20) NOT NULL,
  `name` varchar(75) default NULL,
  `url` longtext,
  `openSource` tinyint(4) default NULL,
  `active_` tinyint(4) default NULL,
  `recommended` tinyint(4) default NULL,
  PRIMARY KEY  (`licenseId`),
  KEY `IX_1C841592` (`active_`),
  KEY `IX_5327BB79` (`active_`,`recommended`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCLicenses_SCProductEntries
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCLicenses_SCProductEntries`;

CREATE TABLE `SCLicenses_SCProductEntries` (
  `licenseId` bigint(20) NOT NULL,
  `productEntryId` bigint(20) NOT NULL,
  PRIMARY KEY  (`licenseId`,`productEntryId`),
  KEY `IX_27006638` (`licenseId`),
  KEY `IX_D7710A66` (`productEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCProductEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCProductEntry`;

CREATE TABLE `SCProductEntry` (
  `productEntryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `name` varchar(75) default NULL,
  `type_` varchar(75) default NULL,
  `tags` varchar(255) default NULL,
  `shortDescription` longtext,
  `longDescription` longtext,
  `pageURL` longtext,
  `author` varchar(75) default NULL,
  `repoGroupId` varchar(75) default NULL,
  `repoArtifactId` varchar(75) default NULL,
  PRIMARY KEY  (`productEntryId`),
  KEY `IX_5D25244F` (`companyId`),
  KEY `IX_72F87291` (`groupId`),
  KEY `IX_98E6A9CB` (`groupId`,`userId`),
  KEY `IX_7311E812` (`repoGroupId`,`repoArtifactId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCProductScreenshot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCProductScreenshot`;

CREATE TABLE `SCProductScreenshot` (
  `productScreenshotId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `groupId` bigint(20) default NULL,
  `productEntryId` bigint(20) default NULL,
  `thumbnailId` bigint(20) default NULL,
  `fullImageId` bigint(20) default NULL,
  `priority` int(11) default NULL,
  PRIMARY KEY  (`productScreenshotId`),
  KEY `IX_AE8224CC` (`fullImageId`),
  KEY `IX_467956FD` (`productEntryId`),
  KEY `IX_DA913A55` (`productEntryId`,`priority`),
  KEY `IX_6C572DAC` (`thumbnailId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SCProductVersion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SCProductVersion`;

CREATE TABLE `SCProductVersion` (
  `productVersionId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `productEntryId` bigint(20) default NULL,
  `version` varchar(75) default NULL,
  `changeLog` longtext,
  `downloadPageURL` longtext,
  `directDownloadURL` varchar(2000) default NULL,
  `repoStoreArtifact` tinyint(4) default NULL,
  PRIMARY KEY  (`productVersionId`),
  KEY `IX_7020130F` (`directDownloadURL`(767)),
  KEY `IX_8377A211` (`productEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Scenario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Scenario`;

CREATE TABLE `Scenario` (
  `creation` datetime default NULL,
  `description` varchar(8192) default NULL,
  `id` varchar(64) NOT NULL,
  `user` varchar(64) default NULL,
  `simId` varchar(64) default NULL,
  `name` varchar(64) default NULL,
  `state` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table ServiceComponent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ServiceComponent`;

CREATE TABLE `ServiceComponent` (
  `serviceComponentId` bigint(20) NOT NULL,
  `buildNamespace` varchar(75) default NULL,
  `buildNumber` bigint(20) default NULL,
  `buildDate` bigint(20) default NULL,
  `data_` longtext,
  PRIMARY KEY  (`serviceComponentId`),
  UNIQUE KEY `IX_4F0315B8` (`buildNamespace`,`buildNumber`),
  KEY `IX_7338606F` (`buildNamespace`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Shard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Shard`;

CREATE TABLE `Shard` (
  `shardId` bigint(20) NOT NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  PRIMARY KEY  (`shardId`),
  KEY `IX_DA5F4359` (`classNameId`,`classPK`),
  KEY `IX_941BA8C3` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingCart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingCart`;

CREATE TABLE `ShoppingCart` (
  `cartId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `itemIds` longtext,
  `couponCodes` varchar(75) default NULL,
  `altShipping` int(11) default NULL,
  `insure` tinyint(4) default NULL,
  PRIMARY KEY  (`cartId`),
  UNIQUE KEY `IX_FC46FE16` (`groupId`,`userId`),
  KEY `IX_C28B41DC` (`groupId`),
  KEY `IX_54101CC8` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingCategory`;

CREATE TABLE `ShoppingCategory` (
  `categoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentCategoryId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  PRIMARY KEY  (`categoryId`),
  KEY `IX_5F615D3E` (`groupId`),
  KEY `IX_1E6464F5` (`groupId`,`parentCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingCoupon
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingCoupon`;

CREATE TABLE `ShoppingCoupon` (
  `couponId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `code_` varchar(75) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `startDate` datetime default NULL,
  `endDate` datetime default NULL,
  `active_` tinyint(4) default NULL,
  `limitCategories` longtext,
  `limitSkus` longtext,
  `minOrder` double default NULL,
  `discount` double default NULL,
  `discountType` varchar(75) default NULL,
  PRIMARY KEY  (`couponId`),
  UNIQUE KEY `IX_DC60CFAE` (`code_`),
  KEY `IX_3251AF16` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingItem`;

CREATE TABLE `ShoppingItem` (
  `itemId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `categoryId` bigint(20) default NULL,
  `sku` varchar(75) default NULL,
  `name` varchar(200) default NULL,
  `description` longtext,
  `properties` longtext,
  `fields_` tinyint(4) default NULL,
  `fieldsQuantities` longtext,
  `minQuantity` int(11) default NULL,
  `maxQuantity` int(11) default NULL,
  `price` double default NULL,
  `discount` double default NULL,
  `taxable` tinyint(4) default NULL,
  `shipping` double default NULL,
  `useShippingFormula` tinyint(4) default NULL,
  `requiresShipping` tinyint(4) default NULL,
  `stockQuantity` int(11) default NULL,
  `featured_` tinyint(4) default NULL,
  `sale_` tinyint(4) default NULL,
  `smallImage` tinyint(4) default NULL,
  `smallImageId` bigint(20) default NULL,
  `smallImageURL` varchar(75) default NULL,
  `mediumImage` tinyint(4) default NULL,
  `mediumImageId` bigint(20) default NULL,
  `mediumImageURL` varchar(75) default NULL,
  `largeImage` tinyint(4) default NULL,
  `largeImageId` bigint(20) default NULL,
  `largeImageURL` varchar(75) default NULL,
  PRIMARY KEY  (`itemId`),
  UNIQUE KEY `IX_1C717CA6` (`companyId`,`sku`),
  KEY `IX_C8EACF2E` (`categoryId`),
  KEY `IX_903DC750` (`largeImageId`),
  KEY `IX_D217AB30` (`mediumImageId`),
  KEY `IX_FF203304` (`smallImageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingItemField
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingItemField`;

CREATE TABLE `ShoppingItemField` (
  `itemFieldId` bigint(20) NOT NULL,
  `itemId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `values_` longtext,
  `description` longtext,
  PRIMARY KEY  (`itemFieldId`),
  KEY `IX_6D5F9B87` (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingItemPrice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingItemPrice`;

CREATE TABLE `ShoppingItemPrice` (
  `itemPriceId` bigint(20) NOT NULL,
  `itemId` bigint(20) default NULL,
  `minQuantity` int(11) default NULL,
  `maxQuantity` int(11) default NULL,
  `price` double default NULL,
  `discount` double default NULL,
  `taxable` tinyint(4) default NULL,
  `shipping` double default NULL,
  `useShippingFormula` tinyint(4) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`itemPriceId`),
  KEY `IX_EA6FD516` (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingOrder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingOrder`;

CREATE TABLE `ShoppingOrder` (
  `orderId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `number_` varchar(75) default NULL,
  `tax` double default NULL,
  `shipping` double default NULL,
  `altShipping` varchar(75) default NULL,
  `requiresShipping` tinyint(4) default NULL,
  `insure` tinyint(4) default NULL,
  `insurance` double default NULL,
  `couponCodes` varchar(75) default NULL,
  `couponDiscount` double default NULL,
  `billingFirstName` varchar(75) default NULL,
  `billingLastName` varchar(75) default NULL,
  `billingEmailAddress` varchar(75) default NULL,
  `billingCompany` varchar(75) default NULL,
  `billingStreet` varchar(75) default NULL,
  `billingCity` varchar(75) default NULL,
  `billingState` varchar(75) default NULL,
  `billingZip` varchar(75) default NULL,
  `billingCountry` varchar(75) default NULL,
  `billingPhone` varchar(75) default NULL,
  `shipToBilling` tinyint(4) default NULL,
  `shippingFirstName` varchar(75) default NULL,
  `shippingLastName` varchar(75) default NULL,
  `shippingEmailAddress` varchar(75) default NULL,
  `shippingCompany` varchar(75) default NULL,
  `shippingStreet` varchar(75) default NULL,
  `shippingCity` varchar(75) default NULL,
  `shippingState` varchar(75) default NULL,
  `shippingZip` varchar(75) default NULL,
  `shippingCountry` varchar(75) default NULL,
  `shippingPhone` varchar(75) default NULL,
  `ccName` varchar(75) default NULL,
  `ccType` varchar(75) default NULL,
  `ccNumber` varchar(75) default NULL,
  `ccExpMonth` int(11) default NULL,
  `ccExpYear` int(11) default NULL,
  `ccVerNumber` varchar(75) default NULL,
  `comments` longtext,
  `ppTxnId` varchar(75) default NULL,
  `ppPaymentStatus` varchar(75) default NULL,
  `ppPaymentGross` double default NULL,
  `ppReceiverEmail` varchar(75) default NULL,
  `ppPayerEmail` varchar(75) default NULL,
  `sendOrderEmail` tinyint(4) default NULL,
  `sendShippingEmail` tinyint(4) default NULL,
  PRIMARY KEY  (`orderId`),
  UNIQUE KEY `IX_D7D6E87A` (`number_`),
  KEY `IX_1D15553E` (`groupId`),
  KEY `IX_119B5630` (`groupId`,`userId`,`ppPaymentStatus`),
  KEY `IX_F474FD89` (`ppTxnId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ShoppingOrderItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ShoppingOrderItem`;

CREATE TABLE `ShoppingOrderItem` (
  `orderItemId` bigint(20) NOT NULL,
  `orderId` bigint(20) default NULL,
  `itemId` varchar(75) default NULL,
  `sku` varchar(75) default NULL,
  `name` varchar(200) default NULL,
  `description` longtext,
  `properties` longtext,
  `price` double default NULL,
  `quantity` int(11) default NULL,
  `shippedDate` datetime default NULL,
  PRIMARY KEY  (`orderItemId`),
  KEY `IX_B5F82C7A` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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



# Dump of table SimulationToMetaDataMapping
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SimulationToMetaDataMapping`;

CREATE TABLE `SimulationToMetaDataMapping` (
  `metaDataId` varchar(64) default NULL,
  `simulationInput` varchar(64) default NULL,
  `simulationOutput` varchar(64) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table SocialActivity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SocialActivity`;

CREATE TABLE `SocialActivity` (
  `activityId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `mirrorActivityId` bigint(20) default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `type_` int(11) default NULL,
  `extraData` longtext,
  `receiverUserId` bigint(20) default NULL,
  PRIMARY KEY  (`activityId`),
  UNIQUE KEY `IX_8F32DEC9` (`groupId`,`userId`,`createDate`,`classNameId`,`classPK`,`type_`,`receiverUserId`),
  KEY `IX_82E39A0C` (`classNameId`),
  KEY `IX_A853C757` (`classNameId`,`classPK`),
  KEY `IX_64B1BC66` (`companyId`),
  KEY `IX_2A2468` (`groupId`),
  KEY `IX_1271F25F` (`mirrorActivityId`),
  KEY `IX_1F00C374` (`mirrorActivityId`,`classNameId`,`classPK`),
  KEY `IX_121CA3CB` (`receiverUserId`),
  KEY `IX_3504B8BC` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SocialRelation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SocialRelation`;

CREATE TABLE `SocialRelation` (
  `uuid_` varchar(75) default NULL,
  `relationId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `userId1` bigint(20) default NULL,
  `userId2` bigint(20) default NULL,
  `type_` int(11) default NULL,
  PRIMARY KEY  (`relationId`),
  UNIQUE KEY `IX_12A92145` (`userId1`,`userId2`,`type_`),
  KEY `IX_61171E99` (`companyId`),
  KEY `IX_95135D1C` (`companyId`,`type_`),
  KEY `IX_C31A64C6` (`type_`),
  KEY `IX_5A40CDCC` (`userId1`),
  KEY `IX_4B52BE89` (`userId1`,`type_`),
  KEY `IX_5A40D18D` (`userId2`),
  KEY `IX_3F9C2FA8` (`userId2`,`type_`),
  KEY `IX_F0CA24A5` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SocialRequest
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SocialRequest`;

CREATE TABLE `SocialRequest` (
  `uuid_` varchar(75) default NULL,
  `requestId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `type_` int(11) default NULL,
  `extraData` longtext,
  `receiverUserId` bigint(20) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`requestId`),
  UNIQUE KEY `IX_36A90CA7` (`userId`,`classNameId`,`classPK`,`type_`,`receiverUserId`),
  UNIQUE KEY `IX_4F973EFE` (`uuid_`,`groupId`),
  KEY `IX_D3425487` (`classNameId`,`classPK`,`type_`,`receiverUserId`,`status`),
  KEY `IX_A90FE5A0` (`companyId`),
  KEY `IX_32292ED1` (`receiverUserId`),
  KEY `IX_D9380CB7` (`receiverUserId`,`status`),
  KEY `IX_80F7A9C2` (`userId`),
  KEY `IX_CC86A444` (`userId`,`classNameId`,`classPK`,`type_`,`status`),
  KEY `IX_AB5906A8` (`userId`,`status`),
  KEY `IX_49D5872C` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Subscription
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Subscription`;

CREATE TABLE `Subscription` (
  `subscriptionId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `frequency` varchar(75) default NULL,
  PRIMARY KEY  (`subscriptionId`),
  UNIQUE KEY `IX_2E1A92D4` (`companyId`,`userId`,`classNameId`,`classPK`),
  KEY `IX_786D171A` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_54243AFD` (`userId`),
  KEY `IX_E8F34171` (`userId`,`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TagsAsset
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TagsAsset`;

CREATE TABLE `TagsAsset` (
  `assetId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `visible` tinyint(4) default NULL,
  `startDate` datetime default NULL,
  `endDate` datetime default NULL,
  `publishDate` datetime default NULL,
  `expirationDate` datetime default NULL,
  `mimeType` varchar(75) default NULL,
  `title` varchar(255) default NULL,
  `description` longtext,
  `summary` longtext,
  `url` longtext,
  `height` int(11) default NULL,
  `width` int(11) default NULL,
  `priority` double default NULL,
  `viewCount` int(11) default NULL,
  PRIMARY KEY  (`assetId`),
  UNIQUE KEY `IX_1AB6D6D2` (`classNameId`,`classPK`),
  KEY `IX_AB3D8BCB` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TagsAssets_TagsEntries
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TagsAssets_TagsEntries`;

CREATE TABLE `TagsAssets_TagsEntries` (
  `assetId` bigint(20) NOT NULL,
  `entryId` bigint(20) NOT NULL,
  PRIMARY KEY  (`assetId`,`entryId`),
  KEY `IX_B22F3A1` (`assetId`),
  KEY `IX_A02A8023` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TagsEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TagsEntry`;

CREATE TABLE `TagsEntry` (
  `entryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `parentEntryId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `vocabularyId` bigint(20) default NULL,
  PRIMARY KEY  (`entryId`),
  KEY `IX_EE55ED49` (`parentEntryId`,`vocabularyId`),
  KEY `IX_28E8954` (`vocabularyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TagsProperty
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TagsProperty`;

CREATE TABLE `TagsProperty` (
  `propertyId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `entryId` bigint(20) default NULL,
  `key_` varchar(75) default NULL,
  `value` varchar(255) default NULL,
  PRIMARY KEY  (`propertyId`),
  UNIQUE KEY `IX_F505253D` (`entryId`,`key_`),
  KEY `IX_C134234` (`companyId`),
  KEY `IX_EB974D08` (`companyId`,`key_`),
  KEY `IX_5200A629` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TagsSource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TagsSource`;

CREATE TABLE `TagsSource` (
  `sourceId` bigint(20) NOT NULL,
  `parentSourceId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `acronym` varchar(75) default NULL,
  PRIMARY KEY  (`sourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TagsVocabulary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TagsVocabulary`;

CREATE TABLE `TagsVocabulary` (
  `vocabularyId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `name` varchar(75) default NULL,
  `description` varchar(75) default NULL,
  `folksonomy` tinyint(4) default NULL,
  PRIMARY KEY  (`vocabularyId`),
  UNIQUE KEY `IX_F9E51044` (`groupId`,`name`),
  KEY `IX_E0D51848` (`companyId`,`folksonomy`),
  KEY `IX_9F26308A` (`groupId`,`folksonomy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TasksProposal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TasksProposal`;

CREATE TABLE `TasksProposal` (
  `proposalId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` varchar(75) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `publishDate` datetime default NULL,
  `dueDate` datetime default NULL,
  PRIMARY KEY  (`proposalId`),
  UNIQUE KEY `IX_181A4A1B` (`classNameId`,`classPK`),
  KEY `IX_7FB27324` (`groupId`),
  KEY `IX_6EEC675E` (`groupId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TasksReview
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TasksReview`;

CREATE TABLE `TasksReview` (
  `reviewId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `proposalId` bigint(20) default NULL,
  `assignedByUserId` bigint(20) default NULL,
  `assignedByUserName` varchar(75) default NULL,
  `stage` int(11) default NULL,
  `completed` tinyint(4) default NULL,
  `rejected` tinyint(4) default NULL,
  PRIMARY KEY  (`reviewId`),
  UNIQUE KEY `IX_5C6BE4C7` (`userId`,`proposalId`),
  KEY `IX_4D0C7F8D` (`proposalId`),
  KEY `IX_70AFEA01` (`proposalId`,`stage`),
  KEY `IX_1894B29A` (`proposalId`,`stage`,`completed`),
  KEY `IX_41AFC20C` (`proposalId`,`stage`,`completed`,`rejected`),
  KEY `IX_36F512E6` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Tuple
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tuple`;

CREATE TABLE `Tuple` (
  `id` varchar(64) NOT NULL,
  `variableId` varchar(64) default NULL,
  `seq` int(11) default NULL,
  `value` varchar(4096) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table UserGroup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserGroup`;

CREATE TABLE `UserGroup` (
  `userGroupId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `parentUserGroupId` bigint(20) default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  PRIMARY KEY  (`userGroupId`),
  UNIQUE KEY `IX_23EAD0D` (`companyId`,`name`),
  KEY `IX_524FEFCE` (`companyId`),
  KEY `IX_69771487` (`companyId`,`parentUserGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table UserGroupRole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserGroupRole`;

CREATE TABLE `UserGroupRole` (
  `userId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`groupId`,`roleId`),
  KEY `IX_1B988D7A` (`groupId`),
  KEY `IX_871412DF` (`groupId`,`roleId`),
  KEY `IX_887A2C95` (`roleId`),
  KEY `IX_887BE56A` (`userId`),
  KEY `IX_4D040680` (`userId`,`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table UserIdMapper
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserIdMapper`;

CREATE TABLE `UserIdMapper` (
  `userIdMapperId` bigint(20) NOT NULL,
  `userId` bigint(20) default NULL,
  `type_` varchar(75) default NULL,
  `description` varchar(75) default NULL,
  `externalUserId` varchar(75) default NULL,
  PRIMARY KEY  (`userIdMapperId`),
  UNIQUE KEY `IX_41A32E0D` (`type_`,`externalUserId`),
  UNIQUE KEY `IX_D1C44A6E` (`userId`,`type_`),
  KEY `IX_E60EA987` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table UserTracker
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserTracker`;

CREATE TABLE `UserTracker` (
  `userTrackerId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `modifiedDate` datetime default NULL,
  `sessionId` varchar(200) default NULL,
  `remoteAddr` varchar(75) default NULL,
  `remoteHost` varchar(75) default NULL,
  `userAgent` varchar(200) default NULL,
  PRIMARY KEY  (`userTrackerId`),
  KEY `IX_29BA1CF5` (`companyId`),
  KEY `IX_46B0AE8E` (`sessionId`),
  KEY `IX_E4EFBA8D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table UserTrackerPath
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserTrackerPath`;

CREATE TABLE `UserTrackerPath` (
  `userTrackerPathId` bigint(20) NOT NULL,
  `userTrackerId` bigint(20) default NULL,
  `path_` longtext,
  `pathDate` datetime default NULL,
  PRIMARY KEY  (`userTrackerPathId`),
  KEY `IX_14D8BCC0` (`userTrackerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table User_
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User_`;

CREATE TABLE `User_` (
  `uuid_` varchar(75) default NULL,
  `userId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `defaultUser` tinyint(4) default NULL,
  `contactId` bigint(20) default NULL,
  `password_` varchar(75) default NULL,
  `passwordEncrypted` tinyint(4) default NULL,
  `passwordReset` tinyint(4) default NULL,
  `passwordModifiedDate` datetime default NULL,
  `reminderQueryQuestion` varchar(75) default NULL,
  `reminderQueryAnswer` varchar(75) default NULL,
  `graceLoginCount` int(11) default NULL,
  `screenName` varchar(75) default NULL,
  `emailAddress` varchar(75) default NULL,
  `openId` varchar(1024) default NULL,
  `portraitId` bigint(20) default NULL,
  `languageId` varchar(75) default NULL,
  `timeZoneId` varchar(75) default NULL,
  `greeting` varchar(255) default NULL,
  `comments` longtext,
  `firstName` varchar(75) default NULL,
  `middleName` varchar(75) default NULL,
  `lastName` varchar(75) default NULL,
  `jobTitle` varchar(75) default NULL,
  `loginDate` datetime default NULL,
  `loginIP` varchar(75) default NULL,
  `lastLoginDate` datetime default NULL,
  `lastLoginIP` varchar(75) default NULL,
  `lastFailedLoginDate` datetime default NULL,
  `failedLoginAttempts` int(11) default NULL,
  `lockout` tinyint(4) default NULL,
  `lockoutDate` datetime default NULL,
  `agreedToTermsOfUse` tinyint(4) default NULL,
  `active_` tinyint(4) default NULL,
  PRIMARY KEY  (`userId`),
  UNIQUE KEY `IX_615E9F7A` (`companyId`,`emailAddress`),
  UNIQUE KEY `IX_C5806019` (`companyId`,`screenName`),
  UNIQUE KEY `IX_9782AD88` (`companyId`,`userId`),
  UNIQUE KEY `IX_5ADBE171` (`contactId`),
  KEY `IX_3A1E834E` (`companyId`),
  KEY `IX_6EF03E4E` (`companyId`,`defaultUser`),
  KEY `IX_762F63C6` (`emailAddress`),
  KEY `IX_A9ED7DD3` (`openId`(767)),
  KEY `IX_A18034A4` (`portraitId`),
  KEY `IX_E0422BDA` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Users_Groups
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users_Groups`;

CREATE TABLE `Users_Groups` (
  `userId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`groupId`),
  KEY `IX_C4F9E699` (`groupId`),
  KEY `IX_F10B6C6B` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Users_Orgs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users_Orgs`;

CREATE TABLE `Users_Orgs` (
  `userId` bigint(20) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`organizationId`),
  KEY `IX_7EF4EC0E` (`organizationId`),
  KEY `IX_FB646CA6` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Users_Permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users_Permissions`;

CREATE TABLE `Users_Permissions` (
  `userId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`permissionId`),
  KEY `IX_8AE58A91` (`permissionId`),
  KEY `IX_C26AA64D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Users_Roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users_Roles`;

CREATE TABLE `Users_Roles` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`roleId`),
  KEY `IX_C19E5F31` (`roleId`),
  KEY `IX_C1A01806` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Users_UserGroups
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users_UserGroups`;

CREATE TABLE `Users_UserGroups` (
  `userGroupId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userGroupId`,`userId`),
  KEY `IX_66FF2503` (`userGroupId`),
  KEY `IX_BE8102D6` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Variable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Variable`;

CREATE TABLE `Variable` (
  `id` varchar(64) NOT NULL,
  `meta` varchar(64) default NULL,
  `scenarioInput` varchar(64) default NULL,
  `scenarioOutput` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table Vocabulary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Vocabulary`;

CREATE TABLE `Vocabulary` (
  `vocabularyId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `name` varchar(75) default NULL,
  `description` varchar(75) default NULL,
  `folksonomy` tinyint(4) default NULL,
  PRIMARY KEY  (`vocabularyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WOL_MeetupsEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WOL_MeetupsEntry`;

CREATE TABLE `WOL_MeetupsEntry` (
  `meetupsEntryId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `title` varchar(75) default NULL,
  `description` longtext,
  `startDate` datetime default NULL,
  `endDate` datetime default NULL,
  `totalAttendees` int(11) default NULL,
  `maxAttendees` int(11) default NULL,
  `price` double default NULL,
  `thumbnailId` bigint(20) default NULL,
  PRIMARY KEY  (`meetupsEntryId`),
  KEY `IX_79AEBAB6` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WOL_MeetupsRegistration
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WOL_MeetupsRegistration`;

CREATE TABLE `WOL_MeetupsRegistration` (
  `meetupsRegistrationId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `meetupsEntryId` bigint(20) default NULL,
  `status` int(11) default NULL,
  `comments` longtext,
  PRIMARY KEY  (`meetupsRegistrationId`),
  KEY `IX_83877F55` (`meetupsEntryId`),
  KEY `IX_C28F993B` (`meetupsEntryId`,`status`),
  KEY `IX_4262CE8F` (`userId`,`meetupsEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WOL_SVNRepository
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WOL_SVNRepository`;

CREATE TABLE `WOL_SVNRepository` (
  `svnRepositoryId` bigint(20) NOT NULL,
  `url` varchar(200) default NULL,
  `revisionNumber` bigint(20) default NULL,
  PRIMARY KEY  (`svnRepositoryId`),
  KEY `IX_75C012F` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WOL_SVNRevision
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WOL_SVNRevision`;

CREATE TABLE `WOL_SVNRevision` (
  `svnRevisionId` bigint(20) NOT NULL,
  `svnUserId` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `svnRepositoryId` bigint(20) default NULL,
  `revisionNumber` bigint(20) default NULL,
  `comments` longtext,
  PRIMARY KEY  (`svnRevisionId`),
  KEY `IX_1AF89E5F` (`svnRepositoryId`),
  KEY `IX_8645F460` (`svnUserId`),
  KEY `IX_25E1E8E0` (`svnUserId`,`svnRepositoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WOL_WallEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WOL_WallEntry`;

CREATE TABLE `WOL_WallEntry` (
  `wallEntryId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `comments` longtext,
  PRIMARY KEY  (`wallEntryId`),
  KEY `IX_CB1E7CE7` (`groupId`),
  KEY `IX_E65FFE21` (`groupId`,`userId`),
  KEY `IX_C7F3D45D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WebDAVProps
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WebDAVProps`;

CREATE TABLE `WebDAVProps` (
  `webDavPropsId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `props` longtext,
  PRIMARY KEY  (`webDavPropsId`),
  UNIQUE KEY `IX_97DFA146` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Website
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Website`;

CREATE TABLE `Website` (
  `websiteId` bigint(20) NOT NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `classNameId` bigint(20) default NULL,
  `classPK` bigint(20) default NULL,
  `url` longtext,
  `typeId` int(11) default NULL,
  `primary_` tinyint(4) default NULL,
  PRIMARY KEY  (`websiteId`),
  KEY `IX_96F07007` (`companyId`),
  KEY `IX_4F0F0CA7` (`companyId`,`classNameId`),
  KEY `IX_F960131C` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_1AA07A6D` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_F75690BB` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WikiNode
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WikiNode`;

CREATE TABLE `WikiNode` (
  `uuid_` varchar(75) default NULL,
  `nodeId` bigint(20) NOT NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `name` varchar(75) default NULL,
  `description` longtext,
  `lastPostDate` datetime default NULL,
  PRIMARY KEY  (`nodeId`),
  UNIQUE KEY `IX_920CD8B1` (`groupId`,`name`),
  UNIQUE KEY `IX_7609B2AE` (`uuid_`,`groupId`),
  KEY `IX_5D6FE3F0` (`companyId`),
  KEY `IX_B480A672` (`groupId`),
  KEY `IX_6C112D7C` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WikiPage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WikiPage`;

CREATE TABLE `WikiPage` (
  `uuid_` varchar(75) default NULL,
  `pageId` bigint(20) NOT NULL,
  `resourcePrimKey` bigint(20) default NULL,
  `groupId` bigint(20) default NULL,
  `companyId` bigint(20) default NULL,
  `userId` bigint(20) default NULL,
  `userName` varchar(75) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  `nodeId` bigint(20) default NULL,
  `title` varchar(255) default NULL,
  `version` double default NULL,
  `minorEdit` tinyint(4) default NULL,
  `content` longtext,
  `summary` longtext,
  `format` varchar(75) default NULL,
  `head` tinyint(4) default NULL,
  `parentTitle` varchar(75) default NULL,
  `redirectTitle` varchar(75) default NULL,
  PRIMARY KEY  (`pageId`),
  UNIQUE KEY `IX_3D4AF476` (`nodeId`,`title`,`version`),
  UNIQUE KEY `IX_899D3DFB` (`uuid_`,`groupId`),
  KEY `IX_A2001730` (`format`),
  KEY `IX_C8A9C476` (`nodeId`),
  KEY `IX_E7F635CA` (`nodeId`,`head`),
  KEY `IX_65E84AF4` (`nodeId`,`head`,`parentTitle`),
  KEY `IX_46EEF3C8` (`nodeId`,`parentTitle`),
  KEY `IX_1ECC7656` (`nodeId`,`redirectTitle`),
  KEY `IX_997EEDD2` (`nodeId`,`title`),
  KEY `IX_E745EA26` (`nodeId`,`title`,`head`),
  KEY `IX_9C0E478F` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table WikiPageResource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WikiPageResource`;

CREATE TABLE `WikiPageResource` (
  `resourcePrimKey` bigint(20) NOT NULL,
  `nodeId` bigint(20) default NULL,
  `title` varchar(75) default NULL,
  PRIMARY KEY  (`resourcePrimKey`),
  UNIQUE KEY `IX_21277664` (`nodeId`,`title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
