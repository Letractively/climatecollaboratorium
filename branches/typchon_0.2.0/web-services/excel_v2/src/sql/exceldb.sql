DROP TABLE IF EXISTS `AUTO_PK_SUPPORT`;

CREATE TABLE `AUTO_PK_SUPPORT` (
  `TABLE_NAME` char(100) NOT NULL,
  `NEXT_ID` bigint(20) NOT NULL,
  UNIQUE KEY `TABLE_NAME` (`TABLE_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ExcelModel`;

CREATE TABLE `ExcelModel` (
  `ID` varchar(128) NOT NULL,
  `Path` varchar(128) NOT NULL,
  `worksheet` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `InputParam`;

CREATE TABLE `InputParam` (
  `ID` varchar(64) NOT NULL,
  `InternalName` varchar(128) NOT NULL,
  `ModelID` varchar(64) NOT NULL,
  `RowNum` int(11) NOT NULL,
  `ColNum` int(11) NOT NULL,
  `DataType` varchar(16) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `OutputParam`;

CREATE TABLE `OutputParam` (
  `ID` varchar(128) NOT NULL,
  `InternalName` varchar(128) NOT NULL,
  `ModelID` varchar(128) NOT NULL,
  `NumRows` int(11) NOT NULL,
  `ColNum` int(11) NOT NULL,
  `RowNum` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Initialize the PK table
--
INSERT INTO `AUTO_PK_SUPPORT` (`TABLE_NAME`,`NEXT_ID`)
VALUES
	('ExcelModel',100),
	('InputParam',100),
	('OutputParam',100);

