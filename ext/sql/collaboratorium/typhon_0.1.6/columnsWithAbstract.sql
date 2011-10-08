INSERT INTO `ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('10', '4', '2011 finalists', 'Contest 2011 finalists', 'OPEN_FOR_EDIT', '2011-10-07 01:00:00', '2011-10-31 12:00', '', NOW(), NOW(), '10144', '1');


delete from ContestPhaseColumn WHERE ContestPhasePK = 10;
insert into ContestPhaseColumn Values(101, 10, 'CREATOR', 10, 0);
insert into ContestPhaseColumn Values(102, 10, 'ABSTRACT', 20, 0);
insert into ContestPhaseColumn Values(103, 10, 'SUPPORTERS', 30, 0);

