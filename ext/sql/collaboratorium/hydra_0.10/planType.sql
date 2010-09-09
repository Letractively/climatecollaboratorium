ALTER TABLE PlanType ADD COLUMN defaultModelId bigint;
update PlanType set defaultModelId = 841 where planTypeId = 3;

ALTER TABLE ContestPhase DROP COLUMN active;
ALTER TABLE ContestPhase ADD COLUMN phaseActive tinyint(1);
UPDATE ContestPhase SET phaseActive = TRUE WHERE ContestPhasePK = 3;

create table ContestDebate (
	id_ BIGINT not null primary key,
	debateId BIGINT,
	ContestPK BIGINT
);
create index IX_19AE4974 on ContestDebate (ContestPK);

delete FROM ContestDebate;
insert into ContestDebate(id_, ContestPK, debateId) VALUES(0, 1, 191);
insert into ContestDebate(id_, ContestPK, debateId) VALUES(1, 1, 190);
insert into ContestDebate(id_, ContestPK, debateId) VALUES(2, 1, 192);


insert into ContestDebate(id_, ContestPK, debateId) VALUES(3, 2, 146);
insert into ContestDebate(id_, ContestPK, debateId) VALUES(4, 2, 424);


create table ContestPhaseColumn (
	id_ BIGINT not null primary key,
	ContestPhasePK BIGINT,
	columnName VARCHAR(75) null,
	columnWeight INTEGER
);


create index IX_1652B7B9 on ContestPhaseColumn (ContestPhasePK);

delete from ContestPhaseColumn;

insert into ContestPhaseColumn VALUES (0, 1, 'NAME', 0);
insert into ContestPhaseColumn VALUES (1, 1, 'SUPPORTERS', 0);
insert into ContestPhaseColumn VALUES (2, 1, 'SEEKING_ASSISTANCE', 0);

insert into ContestPhaseColumn VALUES (3, 2, 'NAME', 0);
insert into ContestPhaseColumn VALUES (4, 2, 'SUPPORTERS', 0);
insert into ContestPhaseColumn VALUES (5, 2, 'SEEKING_ASSISTANCE', 0);

insert into ContestPhaseColumn VALUES (6, 3, 'NAME', 0);
insert into ContestPhaseColumn VALUES (7, 3, 'SUPPORTERS', 0);
insert into ContestPhaseColumn VALUES (8, 3, 'SEEKING_ASSISTANCE', 0);

insert into ContestPhaseColumn VALUES (9, 4, 'NAME', 0);
insert into ContestPhaseColumn VALUES (10, 4, 'SUPPORTERS', 0);
insert into ContestPhaseColumn VALUES (11, 4, 'SEEKING_ASSISTANCE', 0);

insert into ContestPhaseColumn VALUES (12, 5, 'NAME', 0);
insert into ContestPhaseColumn VALUES (13, 5, 'SUPPORTERS', 0);
insert into ContestPhaseColumn VALUES (14, 5, 'SEEKING_ASSISTANCE', 0);

