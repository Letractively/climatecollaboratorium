create table PlanSectionDefinition (
    id_ BIGINT not null primary key,
    title VARCHAR(512) null,
    defaultText VARCHAR(75) null,
    categoryId BIGINT
);


create table PlanTemplate (
    id_ BIGINT not null primary key,
    name VARCHAR(75) null
);

create table PlanTemplateSection (
    planTemplateId BIGINT not null,
    planSectionId BIGINT not null,
    weight int not null  default 0 ,
    primary key (planTemplateId, planSectionId)
);

ALTER TABLE  `Contest` ADD  `planTemplateId` BIGINT NOT NULL;

create index IX_910676FA on PlanTemplateSection (planTemplateId);



create table PlanSection (
    id_ BIGINT not null primary key,
    planSectionDefinitionId BIGINT,
    planId BIGINT,
    content VARCHAR(75) null,
    created DATE null,
    version BIGINT,
    planVersion BIGINT,
    updateAuthorId BIGINT
);


create index IX_918E76BA on PlanSection (planId);
create index IX_D6FF7AA0 on PlanSection (planId, planSectionDefinitionId);
