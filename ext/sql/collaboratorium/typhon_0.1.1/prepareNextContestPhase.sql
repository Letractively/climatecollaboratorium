UPDATE  `lportal`.`ContestPhase` SET  `phaseActive` =  '0' WHERE  `ContestPhase`.`ContestPhasePK` =3;
UPDATE  `lportal`.`ContestPhase` SET  `phaseActive` =  '1' WHERE  `ContestPhase`.`ContestPhasePK` =4;
UPDATE  `lportal`.`ContestPhase` SET  `ContestPhaseName` =  'Voting phase',
`ContestPhaseDescription` =  'Proposals open for voting',
`ContestPhaseStatus` =  'VOTING' WHERE  `ContestPhase`.`ContestPhasePK` =4;


ALTER TABLE PlanMeta ADD COLUMN promoted int(1) null;
ALTER TABLE PlanMeta ADD COLUMN previousContestPhase BIGINT null;


DELETE FROM ContestPhaseColumn WHERE ContestPhasePK = 4;

INSERT INTO ContestPhaseColumn VALUES (31, 4,  "VOTES", 10);
INSERT INTO ContestPhaseColumn VALUES (32, 4,  "COMMENTS", 20);
INSERT INTO ContestPhaseColumn VALUES (33, 4,  "CO2_CONCENTRATION", 30);
INSERT INTO ContestPhaseColumn VALUES (34, 4,  "MITIGATION_COST_EMF", 40);
INSERT INTO ContestPhaseColumn VALUES (35, 4,  "DAMAGE_COST", 50);
