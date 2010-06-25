drop table if exists PlanDescription;
create table PlanDescription (
	id_ BIGINT not null primary key,
	planId BIGINT,
	name VARCHAR(255) null,
	description LONGTEXT null,
	version BIGINT,
	planVersion BIGINT,
	updateAuthorId BIGINT,
	created TIMESTAMP null
);

drop table if exists PlanItem;
create table PlanItem (
	id_ BIGINT not null primary key,
	planId BIGINT,
	state VARCHAR(75) null,
	updated TIMESTAMP null,
	updateAuthorId BIGINT,
	updateType VARCHAR(75) null,
	version BIGINT
);

drop table if exists PlanMeta;
create table PlanMeta (
	id_ BIGINT not null primary key,
	planId BIGINT,
	authorId BIGINT,
	planTypeId BIGINT,
	planCreated BIGINT,
	planGroupId BIGINT,
	mbCategoryId BIGINT,
	version BIGINT,
	planVersion BIGINT,
	updateAuthorId BIGINT,
	created TIMESTAMP null
);

drop table if exists PlanModelRun;
create table PlanModelRun (
	id_ BIGINT not null primary key,
	planId BIGINT,
	scenarioId BIGINT,
	planVersion BIGINT,
	version BIGINT,
	updateAuthorId BIGINT,
	created TIMESTAMP null
);

drop table if exists PlanPositionItem;
create table PlanPositionItem (
	planPositionsId BIGINT not null,
	positionId BIGINT not null,
	primary key (planPositionsId, positionId)
);

drop table if exists PlanPositions;
create table PlanPositions (
	id_ BIGINT not null primary key,
	planId BIGINT,
	planVersion BIGINT,
	version BIGINT,
	created DATE null,
	updateAuthorId BIGINT
);
