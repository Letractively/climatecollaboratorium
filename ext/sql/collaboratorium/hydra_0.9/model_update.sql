alter table ModelGlobalPreference add column ModelCategoryID BIGINT(20);
create table ModelCategory
 modelCategoryPK BIGINT PRIMARY KEY,
 modelCategoryName VARCHAR(75) NULL,
 modelCategoryDescription LONGTEXT,
 modelCategoryDisplayWeight INTEGER;