delete from mbcategory where name='ModelDebates';


drop table if exists ModelPosition;
create table ModelPosition (
   modelId bigint not null,
   positionId bigint not null,
   userId bigint null,
   createDate datetime null,
   modifiedDate datetime null,
   userName varchar(511) null,
   PRIMARY KEY(modelId, positionId)
);

drop table if exists ModelDiscussion;
CREATE TABLE `ModelDiscussion` (
  `modelId` bigint(20) NOT NULL,
  `categoryId` bigint(20) NOT NULL,
  `modelDiscussionId` bigint(20) NOT NULL,
  PRIMARY KEY  (`modelDiscussionId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
