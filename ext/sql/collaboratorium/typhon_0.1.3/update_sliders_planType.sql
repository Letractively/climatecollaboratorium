update ModelInputItem set type_ = 'SLIDER' where modelInputItemID in (5, 6, 63, 72, 102);

update PlanType set modelId = 10, defaultModelId = 10;

create table UserSurvey (
	userId BIGINT not null,
	eventName VARCHAR(255) not null,
	createDate DATE null,
	primary key (userId, eventName)
);

create table Survey (
	eventName VARCHAR(255) not null primary key,
	description TEXT null,
	url VARCHAR(512) null,
	type_ VARCHAR(255) null
);

INSERT INTO Survey VALUES ("sampleEvent", "This is sample description", "http://www.surveymonkey.com/s.aspx?sm=eRg3EPb7rc9ajwp%2bHBFicg%3d%3d", "SURVEYMONKEY_EMBEDDED");

