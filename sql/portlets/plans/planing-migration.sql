-- Migration  of planning implementation --

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
    columnName VARCHAR(75),
    weight int not null default 0,
    visibleByDefault BOOLEAN not null,
    CONSTRAINT UNIQUEPlanTypeIdColumnName UNIQUE (planTypeId, columnName)
);

drop table if exists PlanTypeAttribute;
create table PlanTypeAttribute (
    planTypeAttributeId bigint not null primary key,
    planTypeId bigint not null,
    attributeName VARCHAR(75),
    CONSTRAINT UNIQUEPlanTypeIdAttributeName UNIQUE (planTypeId, attributeName)
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


INSERT INTO `PlanTypeAttribute` (`planTypeAttributeId`, `planTypeId`, `attributeName`) VALUES
(1, 1, 'CO2'),
(2, 1, 'TEMP'),
(3, 1, 'MIN_MITIGATION_COST'),
(4, 1, 'MAX_MITIGATION_COST'),
(5, 1, 'EMISSIONS_DEVELOPED'),
(6, 1, 'EMISSIONS_DEVELOPING_A'),
(7, 1, 'EMISSIONS_DEVELOPING_B'),
(8, 1, 'SEA_LEVEL'),
(9, 1, 'MAX_DAMAGE_COST'),
(10, 1, 'MIN_DAMAGE_COST'),
(11, 2, 'CO2'),
(12, 2, 'TEMP'),
(13, 2, 'MIN_MITIGATION_COST'),
(14, 2, 'MAX_MITIGATION_COST'),
(15, 2, 'EMISSIONS_DEVELOPED'),
(16, 2, 'EMISSIONS_DEVELOPING_A'),
(17, 2, 'EMISSIONS_DEVELOPING_B'),
(18, 2, 'SEA_LEVEL'),
(19, 2, 'MAX_DAMAGE_COST'),
(20, 2, 'MIN_DAMAGE_COST');



INSERT INTO `PlanTypeColumn` (`planTypeColumnId`, `planTypeId`, `columnName`, `visibleByDefault`, `weight`) VALUES
(9, 1, 'COLUMN_DEVELOPED_EMISSIONS', 0, 6),
(6, 1, 'CREATE_DATE', 0, 3),
(5, 1, 'CREATOR', 0, 2),
(3, 1, 'NAME', 1, 0),
(10, 1, 'COLUMN_DEVELOPING_A_EMISSIONS', 0, 7),
(11, 1, 'COLUMN_DEVELOPING_B_EMISSIONS', 0, 8),
(12, 1, 'CO2_CONCENTRATION', 1, 9),
(13, 1, 'TEMP_CHANGE', 1, 10),
(14, 1, 'SEA_LEVEL_CHANGE', 0, 11),
(15, 1, 'MITIGATION_COST', 1, 12),
(16, 1, 'DAMAGE_COST', 1, 13),
(17, 2, 'NAME', 1, 0),
(18, 2, 'VOTES', 1, 1),
(19, 2, 'CREATOR', 0, 2),
(20, 2, 'CREATE_DATE', 0, 3),
(21, 2, 'PUBLISH_DATE', 0, 4),
(22, 2, 'POSITIONS', 0, 5),
(23, 2, 'COLUMN_DEVELOPED_EMISSIONS', 0, 6),
(24, 2, 'COLUMN_DEVELOPING_A_EMISSIONS', 0, 7),
(25, 2, 'COLUMN_DEVELOPING_B_EMISSIONS', 0, 8),
(26, 2, 'CO2_CONCENTRATION', 1, 9),
(27, 2, 'TEMP_CHANGE', 1, 10),
(28, 2, 'SEA_LEVEL_CHANGE', 0, 11),
(29, 2, 'MITIGATION_COST', 1, 12),
(30, 2, 'DAMAGE_COST', 1, 13);

INSERT INTO `PlanType` (`planTypeId`, `name`, `description`, `modelId`, `published`, `publishedCounterpartId`, `isDefault`) VALUES
(1, 'defaultUnpublished', NULL, 623, 0, 2, 1),
(2, 'defaultPublished', NULL, 623, 1, 1, 0);

drop table if exists PlansFilterPosition;
create table PlansFilterPosition (
    userId bigint not null,
    planTypeId bigint not null,
    positionId bigint not null,
    primary key (userId, planTypeId, positionId)
);

-- Plan table modification --

-- add plantype reference
ALTER TABLE `Plan` ADD `planTypeId` BIGINT NOT NULL;

-- set all unpublished plans to planType 1 and all published to planType 2
UPDATE Plan SET planTypeId = 1 WHERE published = 0;
UPDATE Plan SET planTypeId = 2 WHERE published = 1;

ALTER TABLE `Plan`
  DROP `damageCostAvg`,
  DROP `damageCostStdDev`,
  DROP `mitigationCostAvg`,
  DROP `mitigationCostStdDev`,
  DROP `developedEmissions`,
  DROP `developingAEmissions`,
  DROP `developingBEmissions`,
  DROP `globalEmissions`,
  DROP `temperatureChange`,
  DROP `CO2`,
  DROP `seaLevelRise`,
  DROP `published`;

