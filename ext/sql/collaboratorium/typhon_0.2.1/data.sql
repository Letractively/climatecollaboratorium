INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (1, 'First phase', 'First phase', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (2, 'Second phase', 'Second phase', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (3, 'Voting phase', 'Voting phase', 'VOTING');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (4, 'Round 1', 'Round 1', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (5, 'Sandbox', 'Sandbox', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (6, 'Finalized', 'Finalized', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (7, 'Final Round', 'Final Round', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (8, 'Draft', 'Draft', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (9, '2010 finalists', '2010 finalists', 'OPEN_FOR_EDIT');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (10, 'Contest Completed', 'Contest Completed', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (11, 'Finalist selection', 'Finalist selection', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (12, 'Proposal revisions', 'Proposal revisions', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (13, 'Voting', 'Voting', 'CLOSED');
INSERT INTO ContestPhaseType(id_, name, description, status) VALUES (14, 'Completed', 'Completed', 'CLOSED');

UPDATE ContestPhase set ContestPhaseType = 1 WHERE ContestPhaseName LIKE "%First phase%";
UPDATE ContestPhase set ContestPhaseType = 2 WHERE ContestPhaseName LIKE "%Second phase%";
UPDATE ContestPhase set ContestPhaseType = 3 WHERE ContestPhaseName LIKE "%Voting phase%";
UPDATE ContestPhase set ContestPhaseType = 4 WHERE ContestPhaseName LIKE "%Round1%";
UPDATE ContestPhase set ContestPhaseType = 5 WHERE ContestPhaseName LIKE "%Sandbox%";
UPDATE ContestPhase set ContestPhaseType = 6 WHERE ContestPhaseName LIKE "%Finalized%";
UPDATE ContestPhase set ContestPhaseType = 7 WHERE ContestPhaseName LIKE "%Final Round%";
UPDATE ContestPhase set ContestPhaseType = 8 WHERE ContestPhaseName LIKE "%Draft%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%2010 finalists%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%Contest Completed%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%Finalist selection%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%Contest Completed%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%Proposal revisions%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%Voting%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%Completed%";

update ContestPhase set PhaseStartDate = '2010-11-04 13:00:00' where ContestPhasePK = 13;
update ContestPhase set PhaseStartDate = '2010-11-28 13:00:00' where ContestPhasePK = 7;
update ContestPhase set PhaseStartDate = '2011-11-04 12:00:00' where ContestPhasePK = 12;


