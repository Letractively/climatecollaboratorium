alter table ModelGlobalPreference add column ModelCategoryID BIGINT(20);
CREATE TABLE `ModelCategory` (
  `modelCategoryPK` bigint(20) NOT NULL,
  `modelCategoryName` varchar(75) DEFAULT NULL,
  `modelCategoryDescription` longtext,
  `modelCategoryDisplayWeight` int(11) DEFAULT NULL,
  PRIMARY KEY (`modelCategoryPK`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;