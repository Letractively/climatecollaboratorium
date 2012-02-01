
create table DiscussionMessageFlag (
    pk BIGINT not null primary key,
    messageId BIGINT,
    flagType VARCHAR(75) null,
    data_ VARCHAR(75) null,
    created DATE null,
    userId BIGINT
);