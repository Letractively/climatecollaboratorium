drop table if exists ActivitySubscription;

CREATE TABLE  `lportal`.`ActivitySubscription` (
  `entityId` bigint(20) NOT NULL,
  `receiverId` bigint(20) NOT NULL,
  `activitytype` varchar(511) NOT NULL,
  `portletId` varchar(60) default NULL,
  `createDate` datetime default NULL,
  `modifiedDate` datetime default NULL,
  PRIMARY KEY  (`entityId`,`receiverId`,`activitytype`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1