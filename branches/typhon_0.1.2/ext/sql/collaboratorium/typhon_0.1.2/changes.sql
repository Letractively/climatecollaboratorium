UPDATE  `lportal`.`Contest` SET  `contestActive` =  '0' WHERE  `Contest`.`ContestPK` =2;
UPDATE  `lportal`.`Contest` SET  `contestActive` =  '1' WHERE  `Contest`.`ContestPK` =3;

INSERT INTO `lportal`.`Contest` (`ContestPK`, `ContestName`, `ContestDescription`, `PlanTypeId`, `created`, `updated`, `authorId`, `contestActive`, `ContestShortName`, `ContestModelDescription`, `ContestPositionsDescription`) VALUES ('3', 'How can the Climate CoLab 2010 finalists be improved?', NULL, 3, '2010-11-29 20:12:16', '2010-11-29 20:12:20', '10144', '1', '2010 contest follow on', NULL, NULL);

INSERT INTO `lportal`.`ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('6', '3', 'Sandbox', 'Sandbox', 'OPEN_FOR_SUBMISSION', '2010-11-28 12:00:00', NULL, '', '2010-11-29 20:16:10', '2010-11-29 20:16:12', '10144', '0');

INSERT INTO `lportal`.`ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('7', '3', '2010 finalists', 'Contest 2010 finalists', 'OPEN_FOR_EDIT', '2010-11-28 12:00:00', NULL, '', '2010-11-29 20:16:10', '2010-11-29 20:16:12', '10144', '1');

INSERT INTO `ContestDebate` (`id_`, `debateId`, `ContestPK`) VALUES
(305, 2512, 3),
(304, 2414, 3),
(303, 2401, 3),
(302, 115, 3),
(301, 103, 3);


INSERT INTO `ContestPhaseColumn` (`id_`, `ContestPhasePK`, `columnName`, `columnWeight`) VALUES
(60, 6, 'NAME', 0),
(64, 6, 'MITIGATION_COST_EMF', 40),
(63, 6, 'CO2_CONCENTRATION', 30),
(62, 6, 'COMMENTS', 20),
(61, 6, 'VOTES', 10),
(65, 6, 'DAMAGE_COST', 50);

INSERT INTO `ContestPhaseColumn` (`id_`, `ContestPhasePK`, `columnName`, `columnWeight`) VALUES
(74, 7, 'MITIGATION_COST_EMF', 40),
(73, 7, 'CO2_CONCENTRATION', 30),
(72, 7, 'COMMENTS', 20),
(71, 7, 'VOTES', 10),
(75, 7, 'DAMAGE_COST', 50);

ALTER TABLE  `ContestPhaseColumn` ADD  `defaultSort` TINYINT( 1 ) NULL AFTER  `columnWeight`;

UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 1 AND columnName = 'VOTES';
UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 2 AND columnName = 'NAME';
UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 3 AND columnName = 'SUPPORTERS';
UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 4 AND columnName = 'VOTES';
UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 5 AND columnName = 'VOTES';
UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 6 AND columnName = 'NAME';
UPDATE ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK = 7 AND columnName = 'VOTES';
