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

INSERT INTO ContestPhaseColumn VALUES (28, 3,  "COMMENTS", 11);


UPDATE  `lportal`.`ContestPhase` SET  `ContestPhaseName` =  'Draft' WHERE  `ContestPhase`.`ContestPhasePK` =2;
UPDATE  `lportal`.`ContestPhase` SET  `PhaseEndDate` = '2010-09-01 00:00:00' where `ContestPhase`.`ContestPK` =1;


-- UPDATE 'PlanMeta' SET contestPhase = 3 where previousContestPhase IS NOT NULL;
-- UPDATE PlanMeta SET ContestPhase = 3, previousContestPhase = null, promoted = null WHERE ContestPhase = 4 OR promoted = 1


