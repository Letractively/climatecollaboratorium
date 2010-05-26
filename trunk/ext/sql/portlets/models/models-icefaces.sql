drop table if exists ModelInputGroup;
create table ModelInputGroup (
	modelInputGroupPK BIGINT not null primary key,
	modelId BIGINT,
	nameAndDescriptionMetaDataId BIGINT,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	displayItemOrder INT
);

drop table if exists ModelInputItem;
create table ModelInputItem (
	modelInputItemPK BIGINT not null primary key,
	modelId BIGINT,
	modelInputItemID BIGINT,
	modelGroupId BIGINT,
	displayItemOrder INT,
	type_ VARCHAR(75) null
);

drop table if exists ModelOutputChartOrder;
create table ModelOutputChartOrder (
	modelOutputChartOrderPK BIGINT not null primary key,
	modelId BIGINT,
	modelOutputLabel VARCHAR(75) null,
	modelOutputChartOrder INT
);

drop table if exists ModelOutputItem;
create table ModelOutputItem (
	modelOutputItemModifierPK BIGINT not null primary key,
	modelId BIGINT,
	modelOutputItemId BIGINT,
	modelOutputItemOrder INT,
	itemType VARCHAR(75) null,
	relatedOutputItem BIGINT
);

drop table if exists ModelOutputItemModifier;
create table ModelOutputItemModifier (
	modelOutputItemModifierPK BIGINT not null primary key,
	modelId BIGINT,
	modelOutputItemId BIGINT,
	sourceItemId BIGINT,
	type_ VARCHAR(75) null
);

drop table if exists ModelOutputItemOrder;
create table ModelOutputItemOrder (
	modelOutputItemModifierPK BIGINT not null primary key,
	modelId BIGINT,
	modelOutputItemId BIGINT,
	modelOutputItemOrder BIGINT,
	type_ VARCHAR(75) null
);

