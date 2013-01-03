ALTER TABLE  `Contest` ADD  `flagTooltip` VARCHAR( 1024 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

create table ContestPhaseStatus (
    name VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_general_ci not null  primary key ,
    description VARCHAR(512) CHARACTER SET utf8 COLLATE utf8_general_ci null
);


ALTER TABLE  `ContestPhase` ADD phaseStatusDescription VARCHAR(512) CHARACTER SET utf8 COLLATE utf8_general_ci null;
