

delete from `Contest` WHERE ContestPK = 8;
INSERT INTO `Contest` (`ContestPK`, `ContestName`, `ContestDescription`, `PlanTypeId`, `created`, `updated`, `authorId`, `contestActive`, `ContestShortName`, `ContestModelDescription`, `ContestPositionsDescription`) VALUES ('8', 'How can consumer behavior be changed to reduce emissions from personal transportation?', 'Sub-questions: How can average consumers get comfortable with the idea of purchasing alternative powered vehicles? Can consumers be convinced to buy carbon offsets or use modes of transportation other than driving?<br /><br />
 
 The initial proposals in this contest were created on April 27 in a workshop held during the <a href="http://sustainabilitysummit.mit.edu/">MIT Sustainability Summit 2012</a>. Workshop participants are invited to continue working on their proposals after the event, and members of the Climate CoLab community are also welcome to contribute.', '6', '2012-04-27 00:00:00', '2012-04-30 20:00:00', '10144', '1', 'MIT Sustainability Summit 2012 workshop: Personal transportation', 'Model description', 'Positions description');


delete from ContestPhase where ContestPhasePK = 80;
INSERT INTO `ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseName`, `ContestPhaseDescription`, `ContestPhaseStatus`, `PhaseStartDate`, `PhaseEndDate`, `nextStatus`, `created`, `updated`, `authorId`, `phaseActive`) VALUES ('80', '8', 'First phase', '', 'OPEN_FOR_SUBMISSION', '2012-04-27 00:00:00', '2012-09-30 01:00:00', NULL, '2012-04-27 00:00:00', '2012-04-27 00:00:00', '10144', '1');


delete from ContestPhaseColumn where ContestPhasePK = 80;
insert into ContestPhaseColumn VALUES (800, 80, 'NAME', 0, 1);
insert into ContestPhaseColumn VALUES (801, 80, 'ABSTRACT', 10, 0);
insert into ContestPhaseColumn VALUES (802, 80, 'SUPPORTERS', 20, 0);
Insert into ContestPhaseColumn values (803, 80, 'COMMENTS', 30, 0);
insert into ContestPhaseColumn VALUES (804, 80, 'UPDATE_DATE', 40, 0);

