ALTER TABLE  `MessagingUserPreferences` ADD  `emailOnActivity` TINYINT( 1 ) NULL DEFAULT 1;
UPDATE MessagingUserPreferences SET emailOnActivity = 1;