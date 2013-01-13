ALTER TABLE  `Contest` ADD  `flagTooltip` VARCHAR( 1024 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;


drop table IF EXISTS ContestPhaseStatus;
create table ContestPhaseType (
    id_ BIGINT not null primary key, 
    name VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_general_ci not null ,
    description VARCHAR(512) CHARACTER SET utf8 COLLATE utf8_general_ci null,
    status VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_general_ci not null
);

ALTER TABLE  `ContestPhase` CHANGE  `ContestPhaseDescription`  `ContestPhaseDescriptionOverride` TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
ALTER TABLE  `ContestPhase` ADD phaseActiveOverride tinyint(1) null;
ALTER TABLE  `ContestPhase` ADD ContestPhaseType bigint not null;
ALTER TABLE  `ContestPhase` DROP  `ContestPhaseStatus` ;
ALTER TABLE  `ContestPhase` DROP  `phaseActive` ;

-- ALTER TABLE  `ContestPhase` DROP  `ContestPhaseName` ;