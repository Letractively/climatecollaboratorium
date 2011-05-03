insert into PlanType VALUES (4, 'regional plans May 2011', '', 0, 0, 0, 0, "", NULL, NULL);

insert into PlanTypeAttribute VALUES (40, 4, 'REGION');
insert into PlanTypeAttribute VALUES (41, 4, 'SUBREGION');

INSERT INTO `lportal`.`Contest` (`ContestPK`, `ContestName`, `ContestDescription`, `PlanTypeId`, `created`, `updated`, `authorId`, `contestActive`, `ContestShortName`, `ContestModelDescription`, `ContestPositionsDescription`) VALUES ('5', 'Placeholder for contest name REGIONS (IV 2011)', 'Placeholder for contest description REGIONS (IV 2011)', '4', '2011-04-04 15:42:54', '2011-04-04 15:42:56', '10144', '1', 'Contest REGIONS 2011 short', 'Model description', 'Positions description');

-- UPDATE  `lportal`.`Contest` SET  `contestActive` =  '0' WHERE  `Contest`.`ContestPK` =3;

INSERT INTO `lportal`.`ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('9', '5', '2011 REGIONS First phase', 'Phase description', 'OPEN_FOR_EDIT', '2011-04-04 15:44:34', '2011-04-30 15:44:37', NULL, '2011-04-04 15:44:48', '2011-04-04 15:44:50', '10144', '1');

-- insert into ContestPhaseColumn VALUES (90, 9, 'NAME', 0, 1);
insert into ContestPhaseColumn VALUES (91, 9, 'SUPPORTERS', 10, 0);
-- insert into ContestPhaseColumn VALUES (92, 9, 'CO2_CONCENTRATION', 20, 0);
-- insert into ContestPhaseColumn VALUES (93, 9, 'MITIGATION_COST', 30, 0);
-- insert into ContestPhaseColumn VALUES (94, 9, 'DAMAGE_COST', 40, 0);
Insert into ContestPhaseColumn values (95, 9, 'IS_PLAN_OPEN', 50, 0);
insert into ContestPhaseColumn VALUES (96, 9, 'SEEKING_ASSISTANCE', 60, 0);
Insert into ContestPhaseColumn values (97, 9, 'REGION', 70, 0);
insert into ContestPhaseColumn VALUES (98, 9, 'SUBREGION', 80, 0);

update ContestPhase set ContestPhaseStatus = 'OPEN_FOR_SUBMISSION' WHERE ContestPhasePK = 9;
