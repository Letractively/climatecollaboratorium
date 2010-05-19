DROP TABLE IF EXISTS HelpUserSetting;
CREATE TABLE HelpUserSetting (
    HelpUserSettingId BIGINT PRIMARY KEY,
    userId BIGINT not null,
    messageId VARCHAR(512) not null,
    visible BOOLEAN not null
);
