drop table if exists Plan;

create table Plan (
	planId bigint not null primary key,
	planTypeId bigint not null,
	name VARCHAR(128) null,
	shortcontent longtext null,
	content longtext null,
	published BOOLEAN,
	companyId bigint,
	groupId bigint,
	childGroupId bigint,
	MBCategoryId bigint,
	modelId VARCHAR(75) null,
	scenarioId VARCHAR(75) null,
	topicId VARCHAR(75) null,
	votes INTEGER  null default 0,
	createDate DATE null,
	publishDate DATE null,
	userId bigint,
	userName VARCHAR(128) null,
	userScreenName VARCHAR(128) null,
	modifiedDate DATE null
);

drop table if exists PlanPosition;
create table PlanPosition (
   planId bigint not null,
   positionId bigint not null,

   userId bigint null,
   createDate datetime null,
   modifiedDate datetime null,
   userName varchar(511) null,
   PRIMARY KEY(planId, positionId)
);

drop table if exists PlansUserSettings;
create table PlansUserSettings (
    planUserSettingsId bigint not null primary key,
	userId bigint not null,
	planTypeId bigint not null,
	sortColumn VARCHAR(75),
	sortDirection VARCHAR(32),
	filterEnabled BOOLEAN default 0,
	filterPositionsAll BOOLEAN default 0,
    CONSTRAINT UNIQUEUserIdPlanTypeId UNIQUE (userId, planTypeId)
);

drop table if exists PlansFilter;
create table PlansFilter (
	userId bigint not null,
	published BOOLEAN not null,
	name VARCHAR(75) not null,
	creator VARCHAR(75) not null,
	description VARCHAR(75) not null,
	CO2From DOUBLE not null,
	CO2To DOUBLE not null,
	votesFrom DOUBLE not null,
	votesTo DOUBLE not null,
	damageFrom DOUBLE not null,
	damageTo DOUBLE not null,
	mitigationFrom DOUBLE not null,
	mitigationTo DOUBLE not null,
	dateFrom DATE not null,
	dateTo DATE not null,
	filterPositionsAll BOOLEAN not null,
	enabled BOOLEAN not null default TRUE,
	primary key (userId, published)
);

drop table if exists PlansFilterPosition;
create table PlansFilterPosition (
	userId bigint not null,
	planTypeId bigint not null,
	positionId bigint not null,
	primary key (userId, planTypeId, positionId)
);

drop table if exists PlanVote;
create table PlanVote (
	userId bigint not null primary key,
	planId bigint,
	createDate DATE null
);

drop table if exists PlanType;
create table PlanType (
    planTypeId bigint not null primary key,
    name VARCHAR(128),
    description TEXT,
    modelId bigint not null,
    published boolean not null,
    isDefault boolean not null,
    publishedCounterpartId bigint not null
);

drop table if exists PlanTypeColumn;
create table PlanTypeColumn (
    planTypeColumnId bigint not null primary key,
    planTypeId bigint not null,
    columnName VARCHAR(75)
    visibleByDefault BOOLEAN not null default 0
);

drop table if exists PlanTypeAttribute;
create table PlanTypeAttribute (
    planTypeAttributeId bigint not null primary key,
    planTypeId bigint not null,
    attributeName VARCHAR(75)
);

drop table if exists PlanColumnSettings;
create table PlanColumnSettings (
    planColumnSettingsId BIGINT not null primary key,
    planUserSettingsId BIGINT not null,
    columnName VARCHAR(75) not null,
    weight int not null default 0,
    visible BOOLEAN not null default 0,
    CONSTRAINT UNIQUEPlanUserSettingsColumnName UNIQUE (planUserSettingsId, columnName)
);

drop table if exists PlanAttributeFilter;
create table PlanAttributeFilter (
    planAttributeFilterId BIGINT not null primary key,
    attributeName varchar(75) not null,
    planUserSettingsId bigint not null,
    max double,
    min double,
    stringVal VARCHAR(512) not null,
    CONSTRAINT UNIQUEPlanAttributeFilterAttributeName UNIQUE (planUserSettingsId, attributeName)
    
);

drop table if exists PlanPropertyFilter;
create table PlanPropertyFilter (
    planPropertyFilterId BIGINT not null primary key,
    propertyName VARCHAR(75) not null,
    planUserSettingsId BIGINT not null,
    value VARCHAR(512),
    CONSTRAINT UNIQUEPlanPropertyFilterPropertyName UNIQUE (planUserSettingsId, propertyName)
);
    
       