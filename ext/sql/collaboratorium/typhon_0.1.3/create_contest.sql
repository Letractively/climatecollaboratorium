INSERT INTO `lportal`.`Contest` (`ContestPK`, `ContestName`, `ContestDescription`, `PlanTypeId`, `created`, `updated`, `authorId`, `contestActive`, `ContestShortName`, `ContestModelDescription`, `ContestPositionsDescription`) VALUES ('4', 'Placeholder for contest name (IV 2011)', 'Placeholder for contest description (IV 2011)', '3', '2011-04-04 15:42:54', '2011-04-04 15:42:56', '10144', '1', 'Contest 2011 short', 'Model description', 'Positions description');

UPDATE  `lportal`.`Contest` SET  `contestActive` =  '0' WHERE  `Contest`.`ContestPK` =3;

INSERT INTO `lportal`.`ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('8', '4', '2011 First phase', 'Phase description', 'OPEN_FOR_EDIT', '2011-04-04 15:44:34', '2011-04-30 15:44:37', NULL, '2011-04-04 15:44:48', '2011-04-04 15:44:50', '10144', '1');

insert into ContestPhaseColumn VALUES (80, 8, 'NAME', 0, 1);
insert into ContestPhaseColumn VALUES (81, 8, 'SUPPORTERS', 10, 0);
insert into ContestPhaseColumn VALUES (82, 8, 'CO2_CONCENTRATION', 20, 0);
insert into ContestPhaseColumn VALUES (83, 8, 'MITIGATION_COST', 30, 0);
insert into ContestPhaseColumn VALUES (84, 8, 'DAMAGE_COST', 40, 0);
Insert into ContestPhaseColumn values (85, 8, 'IS_PLAN_OPEN', 50, 0);
insert into ContestPhaseColumn VALUES (86, 8, 'SEEKING_ASSISTANCE', 60, 0);