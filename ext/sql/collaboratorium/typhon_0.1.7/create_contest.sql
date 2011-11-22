update Contest SET contestActive = 0;
update ContestPhase SET phaseActive = 0;
DELETE FROM PlanType WHERE planTypeId = 5;
INSERT INTO `PlanType` (`planTypeId`, `name`, `description`, `modelId`, `published`, `isDefault`, `publishedCounterpartId`, `modelTypeName`, `defaultModelId`, `defaultScenarioId`) VALUES
(5, 'Plans November 2011', '', 0, 0, 0, 0, 'plan', 17, NULL);



DELETE FROM `PlanTypeAttribute` WHERE planTypeId = 5;
INSERT INTO `PlanTypeAttribute` (`planTypeAttributeId`, `planTypeId`, `attributeName`) VALUES
(500, 5, 'ABSTRACT');


DELETE FROM `PlanTypeColumn` WHERE planTypeId = 5;
INSERT INTO `PlanTypeColumn` (`planTypeColumnId`, `planTypeId`, `columnName`, `weight`, `visibleByDefault`) VALUES
(500, 5, 'NAME', 0, 1),
(501, 5, 'VOTES', 1, 1),
(502, 5, 'CREATOR', 2, 0),
(503, 5, 'CREATE_DATE', 3, 0),
(504, 5, 'PUBLISH_DATE', 4, 0),
(505, 5, 'POSITIONS', 5, 0),
(506, 5, 'COLUMN_DEVELOPED_EMISSIONS', 6, 0),
(507, 5, 'COLUMN_DEVELOPING_A_EMISSIONS', 7, 0),
(508, 5, 'COLUMN_DEVELOPING_B_EMISSIONS', 8, 0),
(509, 5, 'CO2_CONCENTRATION', 9, 1),
(510, 5, 'TEMP_CHANGE', 10, 1),
(511, 5, 'SEA_LEVEL_CHANGE', 11, 0),
(512, 5, 'MITIGATION_COST_EMF', 12, 1),
(513, 5, 'DAMAGE_COST', 13, 1);



delete from `lportal`.`Contest` WHERE ContestPK = 6;
INSERT INTO `lportal`.`Contest` (`ContestPK`, `ContestName`, `ContestDescription`, `PlanTypeId`, `created`, `updated`, `authorId`, `contestActive`, `ContestShortName`, `ContestModelDescription`, `ContestPositionsDescription`) VALUES ('6', 'Placeholder for contest name (XI 2011)', 'Placeholder for contest description (XI 2011)', '5', '2011-11-22 20:00:00', '2011-11-22 20:00:00', '10144', '1', 'Contest 2011.XI short', 'Model description', 'Positions description');


delete from ContestPhase where ContestPhasePK = 60;
INSERT INTO `lportal`.`ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('60', '6', '2011.XI First phase', 'Phase description', 'OPEN_FOR_EDIT', '2011-11-20 15:44:34', '2011-12-30 15:44:37', NULL, '2011-11-20 15:44:48', '2011-11-20 15:44:50', '10144', '1');

delete from ContestPhaseColumn where ContestPhasePK = 60;
insert into ContestPhaseColumn VALUES (600, 60, 'NAME', 0, 1);
insert into ContestPhaseColumn VALUES (601, 60, 'SUPPORTERS', 10, 0);
insert into ContestPhaseColumn VALUES (602, 60, 'CO2_CONCENTRATION', 20, 0);
insert into ContestPhaseColumn VALUES (603, 60, 'MITIGATION_COST', 30, 0);
insert into ContestPhaseColumn VALUES (604, 60, 'DAMAGE_COST', 40, 0);
Insert into ContestPhaseColumn values (605, 60, 'IS_PLAN_OPEN', 50, 0);
insert into ContestPhaseColumn VALUES (606, 60, 'SEEKING_ASSISTANCE', 60, 0);

update ContestPhase set ContestPhaseStatus = 'OPEN_FOR_SUBMISSION' WHERE ContestPhasePK = 60;
