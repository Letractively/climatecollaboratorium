INSERT INTO ContestPhaseType(id, name, description, status) VALUES (1, 'First phase', 'First phase', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (2, 'Second phase', 'Second phase', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (3, 'Voting phase', 'Voting phase', 'VOTING');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (4, 'Round 1', 'Round 1', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (5, 'Sandbox', 'Sandbox', 'OPEN_FOR_SUBMISSION');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (6, 'Finalized', 'Finalized', 'CLOSED');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (7, 'Final Round', 'Final Round', 'CLOSED');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (8, 'Draft', 'Draft', 'CLOSED');
INSERT INTO ContestPhaseType(id, name, description, status) VALUES (9, '2010 finalists', '2010 finalists', 'OPEN_FOR_EDIT');

UPDATE ContestPhase set ContestPhaseType = 1 WHERE ContestPhaseName LIKE "%First phase%";
UPDATE ContestPhase set ContestPhaseType = 2 WHERE ContestPhaseName LIKE "%Second phase%";
UPDATE ContestPhase set ContestPhaseType = 3 WHERE ContestPhaseName LIKE "%Voting phase%";
UPDATE ContestPhase set ContestPhaseType = 4 WHERE ContestPhaseName LIKE "%Round1%";
UPDATE ContestPhase set ContestPhaseType = 5 WHERE ContestPhaseName LIKE "%Sandbox%";
UPDATE ContestPhase set ContestPhaseType = 6 WHERE ContestPhaseName LIKE "%Finalized%";
UPDATE ContestPhase set ContestPhaseType = 7 WHERE ContestPhaseName LIKE "%Final Round%";
UPDATE ContestPhase set ContestPhaseType = 8 WHERE ContestPhaseName LIKE "%Draft%";
UPDATE ContestPhase set ContestPhaseType = 9 WHERE ContestPhaseName LIKE "%2010 finalists%";