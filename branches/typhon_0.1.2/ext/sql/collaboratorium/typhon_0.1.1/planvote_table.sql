ALTER TABLE  `PlanVote` ADD  `contestId` BIGINT NOT NULL AFTER  `userId`;
ALTER TABLE  `PlanVote` DROP PRIMARY KEY;

ALTER TABLE  `PlanVote` ADD PRIMARY KEY (  `userId` ,  `contestId` ) ;

-- after doing migration from plans portlet preferences execute this line
-- DELETE FROM `PlanVote` WHERE contestId = 0;
