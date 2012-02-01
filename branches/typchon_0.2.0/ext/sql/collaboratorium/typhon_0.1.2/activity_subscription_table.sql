drop table ActivitySubscription;

create table ActivitySubscription (
    pk BIGINT not null primary key,
    classNameId BIGINT not null,
    classPK BIGINT not null,
    type_ INT null,
    extraData VARCHAR(128) null,
    receiverId BIGINT,
    createDate DATE null,
    modifiedDate DATE null
);

create index IX_46601065 on ActivitySubscription (classNameId, classPK, receiverId);
create index IX_44E51D3A on ActivitySubscription (classNameId, classPK, type_, extraData, receiverId);
create index IX_A9BC42CC on ActivitySubscription (classNameId, classPK, type_, receiverId);
create index IX_43DD2710 on ActivitySubscription (receiverId);