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
	modelOutputChartOrder INT,
	modelIndexRangePolicy VARCHAR(75),
	modelIndexRangeMessage LONGTEXT,
	modelIndexErrorPolicy VARCHAR(75),
	modelIndexErrorMessage LONGTEXT,
	modelChartIsVisible binary(1) null default '1'
);

drop table if exists ModelOutputItem;
create table ModelOutputItem (
	modelOutputItemModifierPK BIGINT not null primary key,
	modelId BIGINT,
	modelOutputItemId BIGINT,
	modelOutputItemOrder INT,
	itemType VARCHAR(75) null,
	modelItemRangePolicy VARCHAR(75) null,
	modelItemRangeMessage LONGTEXT,
	modelItemErrorPolicy VARCHAR(75) null,
	modelItemErrorMessage LONGTEXT,
	modelItemIsVisible binary(1) null default '1',
	modelItemLabelFormat VARCHAR(255) null,
	relatedOutputItem BIGINT
);


drop table if exists ModelOutputItemOrder;
create table ModelOutputItemOrder (
	modelOutputItemModifierPK BIGINT not null primary key,
	modelId BIGINT,
	modelOutputItemId BIGINT,
	modelOutputItemOrder BIGINT,
	type_ VARCHAR(75) null
);

