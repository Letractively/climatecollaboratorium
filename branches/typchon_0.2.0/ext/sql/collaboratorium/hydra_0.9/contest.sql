create table Contest (
	ContestPK BIGINT not null primary key,
	ContestName VARCHAR(75) null,
	ContestDescription VARCHAR(75) null,
	PlanTypeId TIMESTAMP null,
	created TIMESTAMP null,
	upTIMESTAMPd TIMESTAMP null,
	authorId BIGINT
);

create table ContestPhase (
	ContestPhasePK BIGINT not null primary key,
	ContestPK BIGINT,
	ContestPhaseName BIGINT,
	ContestPhaseDescription VARCHAR(75) null,
	ContestPhaseStatus VARCHAR(75) null,
	PhaseStartTIMESTAMP TIMESTAMP null,
	PhaseEndTIMESTAMP BIGINT,
	nextStatus VARCHAR(75) null,
	created TIMESTAMP null,
	upTIMESTAMPd TIMESTAMP null,
	authorId BIGINT
);


ALTER TABLE  `PlanMeta` ADD  `ContestPhase` BIGINT NULL;
