-- add default model to plan type
ALTER TABLE PlanType ADD COLUMN defaultModelId bigint;
update PlanType set defaultModelId = 862 where planTypeId = 3;
update PlanType set defaultModelId = 760 where planTypeId = 2;
update PlanType set defaultModelId = 760 where planTypeId = 1;

-- add active columns to contest phase 
ALTER TABLE ContestPhase ADD COLUMN phaseActive tinyint(1);
-- activate phase first for Cancun (3) and last for Copenhagen (1)
UPDATE ContestPhase SET phaseActive = FALSE;
UPDATE ContestPhase SET phaseActive = TRUE WHERE ContestPhasePK = 3;
UPDATE ContestPhase SET phaseActive = TRUE WHERE ContestPhasePK = 1;


-- create association between debates and contests
create table ContestDebate (
	id_ BIGINT not null primary key,
	debateId BIGINT,
	ContestPK BIGINT
);
create index IX_19AE4974 on ContestDebate (ContestPK);

-- add some issues for debates
delete FROM ContestDebate;
insert into ContestDebate(id_, ContestPK, debateId) VALUES(0, 1, 191);
insert into ContestDebate(id_, ContestPK, debateId) VALUES(1, 1, 190);
insert into ContestDebate(id_, ContestPK, debateId) VALUES(2, 1, 192);


insert into ContestDebate(id_, ContestPK, debateId) VALUES(3, 2, 146);
insert into ContestDebate(id_, ContestPK, debateId) VALUES(4, 2, 424);

-- create table with associations between phases and columns
create table ContestPhaseColumn (
	id_ BIGINT not null primary key,
	ContestPhasePK BIGINT,
	columnName VARCHAR(75) null,
	columnWeight INTEGER
);


create index IX_1652B7B9 on ContestPhaseColumn (ContestPhasePK);

-- add association between certain phases and columns
delete from ContestPhaseColumn;

insert into ContestPhaseColumn VALUES (0, 1, 'NAME', 0);
insert into ContestPhaseColumn VALUES (1, 1, 'VOTES', 10);
insert into ContestPhaseColumn VALUES (2, 1, 'SUPPORTERS', 20);
insert into ContestPhaseColumn VALUES (3, 1, 'CO2_CONCENTRATION', 30);
insert into ContestPhaseColumn VALUES (4, 1, 'MITIGATION_COST', 40);
insert into ContestPhaseColumn VALUES (5, 1, 'DAMAGE_COST', 50);

insert into ContestPhaseColumn VALUES (10, 2, 'NAME', 0);
insert into ContestPhaseColumn VALUES (11, 2, 'SUPPORTERS', 10);
insert into ContestPhaseColumn VALUES (12, 2, 'CO2_CONCENTRATION', 20);
insert into ContestPhaseColumn VALUES (13, 2, 'MITIGATION_COST', 30);
insert into ContestPhaseColumn VALUES (14, 2, 'DAMAGE_COST', 40);

insert into ContestPhaseColumn VALUES (20, 3, 'NAME', 0);
insert into ContestPhaseColumn VALUES (21, 3, 'SUPPORTERS', 10);
insert into ContestPhaseColumn VALUES (22, 3, 'CO2_CONCENTRATION', 20);
insert into ContestPhaseColumn VALUES (23, 3, 'MITIGATION_COST', 30);
insert into ContestPhaseColumn VALUES (24, 3, 'DAMAGE_COST', 40);
Insert into ContestPhaseColumn values (25, 3, 'IS_PLAN_OPEN', 50);
insert into ContestPhaseColumn VALUES (26, 3, 'SEEKING_ASSISTANCE', 60);

insert into ContestPhaseColumn VALUES (30, 4, 'NAME', 0);
insert into ContestPhaseColumn VALUES (31, 4, 'SUPPORTERS', 10);
insert into ContestPhaseColumn VALUES (32, 4, 'SEEKING_ASSISTANCE', 20);

insert into ContestPhaseColumn VALUES (40, 5, 'NAME', 0);
insert into ContestPhaseColumn VALUES (41, 5, 'SUPPORTERS', 10);
insert into ContestPhaseColumn VALUES (42, 5, 'SEEKING_ASSISTANCE', 20);


-- fix phases set up
update ContestPhase SET PhaseStartDate = '2009-10-20 00:00:00', PhaseEndDate = "2009-10-27 00:00:00"  WHERE ContestPhasePk = 2;
update ContestPhase SET ContestPhaseStatus = 'FINISHED' WHERE ContestPhasePK IN (1,2);



-- this I had to add, but I suppose that it will be available on all available envirnoments now
-- ALTER TABLE ModelInputItem ADD COLUMN properties VARCHAR(512);

-- set Cancun contest active
update Contest set contestActive = 0 WHERE ContestPK = 1;
update Contest set contestActive = 1 WHERE ContestPK = 2;

