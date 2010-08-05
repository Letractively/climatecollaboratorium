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

ALTER TABLE  `ModelGlobalPreference` ADD  `expertEvaluationPageId` BIGINT NULL
