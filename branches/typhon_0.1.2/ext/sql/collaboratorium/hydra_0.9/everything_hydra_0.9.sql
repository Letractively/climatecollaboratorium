drop table if exists DiscussionCategory;
create table DiscussionCategory (
	pk BIGINT not null primary key,
	categoryId BIGINT,
	authorId BIGINT,
	categoryGroupId BIGINT,
	name VARCHAR(512) null,
	description TEXT null,
	createDate DATE null,
	deleted TIMESTAMP null,

	threadsCount INT null,
	lastActivityDate TIMESTAMP null, 
	lastActivityAuthorId BIGINT null
);

drop table if exists DiscussionCategoryGroup;
create table DiscussionCategoryGroup (
	id_ BIGINT not null primary key,
	description VARCHAR(512) null
	url VARCHAR(512) null
);

drop table if exists DiscussionMessage;
create table DiscussionMessage (
	pk BIGINT not null primary key,
	messageId BIGINT,
	subject VARCHAR(512) null,
	body TEXT null,
	threadId BIGINT,
	categoryId BIGINT,
	categoryGroupId BIGINT,
	authorId BIGINT,
	createDate TIMESTAMP null,
	version LONG,
	deleted TIMESTAMP null,

	responsesCount INT null,
	lastActivityDate TIMESTAMP null, 
	lastActivityAuthorId BIGINT null
);


insert into DiscussionCategoryGroup VALUES (0, 'Test category group');

ALTER TABLE  `PlanMeta` ADD  `categoryGroupId` BIGINT NOT NULL;

create table PlanFan (
	id_ BIGINT not null primary key,
	userId BIGINT not null,
	planId BIGINT not null,
	created DATE not null,
	deleted DATE null
);

create table PlanTeamHistory (
	id_ BIGINT not null primary key,
	planId BIGINT not null,
	userId BIGINT not null,
	action VARCHAR(75) not null,
	payload VARCHAR(256) null,
	created DATE not null,
	updateAuthorId BIGINT not null
);

ALTER TABLE  `PlanMeta` ADD  `open` INT( 1 ) NOT NULL;
ALTER TABLE  `PlanMeta` ADD  `status` VARCHAR(128) NULL;
UPDATE PlanMeta SET status = 'UNDER_DEVELOPMENT';
UPDATE  `lportal`.`Counter` SET  `currentId` =  '33000' WHERE  `Counter`.`name` =  'com.ext.portlet.plans.model.PlanAttribute';

ALTER TABLE  `ModelGlobalPreference` ADD  `expertEvaluationPageId` BIGINT NULL;

drop table if exists ModelPosition;
create table ModelPosition (
	id_ BIGINT not null primary key,
	positionId BIGINT,
	modelId BIGINT
);

