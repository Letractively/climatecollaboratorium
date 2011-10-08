delete from ContestPhase where ContestPhasePK = 10;

INSERT INTO `ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('10', '4', '2011 finals', '2011.X Global finals', 'OPEN_FOR_EDIT', '2011-10-07 01:00:00', '2011-10-31 12:00', '', NOW(), NOW(), '10144', '1');
UPDATE ContestPhase set phaseActive = 0 WHERE ContestPhasePK = 8;


delete from ContestPhaseColumn WHERE ContestPhasePK = 10;
insert into ContestPhaseColumn Values(101, 10, 'CREATOR', 10, 0);
insert into ContestPhaseColumn Values(102, 10, 'ABSTRACT', 20, 0);
insert into ContestPhaseColumn Values(103, 10, 'SUPPORTERS', 30, 0);


delete from ContestPhase where ContestPhasePK = 11;

INSERT INTO `ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('11', '5', '2011.X Finals', '2011.X National finals', 'OPEN_FOR_EDIT', '2011-10-07 01:00:00', '2011-10-31 12:00', '', NOW(), NOW(), '10144', '1');
UPDATE ContestPhase set phaseActive = 0 WHERE ContestPhasePK = 9;

delete from ContestPhaseColumn WHERE ContestPhasePK = 11;
insert into ContestPhaseColumn Values(111, 11, 'CREATOR', 10, 0);
insert into ContestPhaseColumn Values(112, 11, 'ABSTRACT', 20, 0);
insert into ContestPhaseColumn Values(113, 11, 'SUPPORTERS', 30, 0);

delete from ContestPhaseColumn WHERE ColumnName = 'NAME';
