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

ALTER TABLE  `PlanMeta` ADD  `categoryGroupId` BIGINT NOT NULL
