drop table if exists MessagingMessage;
create table MessagingMessage (
    messageId bigint not null primary key,
    name VARCHAR(255) null,
    description TEXT null,
    subject VARCHAR(512) null,
    body LONGTEXT null,
    replyTo VARCHAR(256) null,
    redirectURL VARCHAR(256) null,
    sendToAll tinyint null,
    conversionCount bigint DEFAULT 0,
    creatorId bigint,
    createDate TIMESTAMP null,
    modifiedDate TIMESTAMP null
);

drop table if exists MessagingMessageConversion;
create table MessagingMessageConversion (
    conversionId bigint not null primary key,
    conversionTypeId bigint not null,
    messageId bigint not null,
    extraData VARCHAR(128) null,
    extraData2 VARCHAR(128) null,
    ipAddress VARCHAR(128) null,
    createDate TIMESTAMP null
);



drop table if exists MessagingMessageRecipient;
create table MessagingMessageRecipient (
    recipientId bigint not null primary key,
    messageId bigint not null,
    userId bigint,
    emailAddress VARCHAR(256) null
);

drop table if exists MessagingRedirectLink;
create table MessagingRedirectLink (
    redirectId bigint not null primary key,
    messageId bigint not null,
    link VARCHAR(256) not null,
    createDate TIMESTAMP null
);

drop table if exists MessagingMessageConversionType;
create table MessagingMessageConversionType (
    typeId bigint not null primary key,
    name VARCHAR(128) null,
    description VARCHAR(128) null
);

drop table if exists MessagingIgnoredRecipients;
create table MessagingIgnoredRecipients (
    ignoredRecipientId bigint not null primary key,
    email VARCHAR(128) null,
    name VARCHAR(128) null,
    userId bigint null,
    createDate TIMESTAMP null
);


INSERT INTO MessagingMessageConversionType (typeId, name, description) VALUES (1, 'email_opened', 'Email has been opened');
INSERT INTO MessagingMessageConversionType (typeId, name, description) VALUES (2, 'email_link_clicked', 'Email link has been clicked');
INSERT INTO MessagingMessageConversionType (typeId, name, description) VALUES (3, 'user_registered', 'User has been registered after comming from email');

