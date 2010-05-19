drop table if exists MigrationData;

create table MigrationData (
	migrationId bigint not null primary key,
	name VARCHAR(256),
	users int null,
	plans int null,
	questions int null,
	alternatives int null,
	arguments int null,
	votes int null,
	
	description  MEDIUMTEXT null,
	xml LONGTEXT not null,	
	createdDate DATETIME null,
	userId bigint,
	modifiedDate DATETIME null
);

drop table if exists MigrationMapping;
create table MigrationMapping (
   entityName VARCHAR(64) not null,
   oldId VARCHAR(64) not null,
   newId Long not null,

   userId bigint null,
   createDate datetime null,
   modifiedDate datetime null,
   PRIMARY KEY(entityName, oldId)
);
