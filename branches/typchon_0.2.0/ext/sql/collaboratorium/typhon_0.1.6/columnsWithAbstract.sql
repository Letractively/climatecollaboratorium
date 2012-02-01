delete from ContestPhase where ContestPhasePK = 10;

INSERT INTO `ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('10', '4', 'Second phase', 'Second phase', 'OPEN_FOR_EDIT', '2011-10-10 01:00:00', '2011-10-31 23:59', '', NOW(), NOW(), '10144', '1');
UPDATE ContestPhase set phaseActive = 0 WHERE ContestPhasePK = 8;


delete from ContestPhaseColumn WHERE ContestPhasePK = 10;
insert into ContestPhaseColumn Values(102, 10, 'ABSTRACT', 20, 0);
insert into ContestPhaseColumn Values(103, 10, 'SUPPORTERS', 30, 0);
insert into ContestPhaseColumn Values(104, 10, 'COMMENTS', 40, 0);
insert into ContestPhaseColumn Values(105, 10, 'UPDATE_DATE', 50, 0);


delete from ContestPhase where ContestPhasePK = 11;

INSERT INTO `ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('11', '5', 'Second phase', 'Second phase', 'OPEN_FOR_EDIT', '2011-10-10 01:00:00', '2011-10-31 23:59', '', NOW(), NOW(), '10144', '1');
UPDATE ContestPhase set phaseActive = 0 WHERE ContestPhasePK = 9;

delete from ContestPhaseColumn WHERE ContestPhasePK = 9 AND columnName = 'SUBREGION';
delete from ContestPhaseColumn WHERE ContestPhasePK = 11;

insert into ContestPhaseColumn Values(111, 11, 'REGION', 10, 0);
insert into ContestPhaseColumn Values(113, 11, 'ABSTRACT', 30, 0);no

insert into ContestPhaseColumn Values(114, 11, 'SUPPORTERS', 40, 0);
insert into ContestPhaseColumn Values(115, 11, 'COMMENTS', 50, 0);
insert into ContestPhaseColumn Values(116, 11, 'UPDATE_DATE', 60, 0);

update ContestPhaseColumn set defaultSort = 1 WHERE ContestPhasePK IN (8,9,10,11) AND columnName = 'COMMENTS';

