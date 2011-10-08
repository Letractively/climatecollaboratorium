ALTER TABLE  `PlanItem` ADD INDEX (  `planId` ) ;
ALTER TABLE  `PlanItem` ADD INDEX (  `state` ) ;
ALTER TABLE  `PlanItem` ADD INDEX (  `planId` ,  `state` ) ;

ALTER TABLE  `PlanMeta` ADD INDEX (  `planTypeId` );
ALTER TABLE  `PlanMeta` ADD INDEX (  `ContestPhase` );
ALTER TABLE  `PlanMeta` ADD INDEX (  `planId` ,  `planVersion` ) ;
ALTER TABLE  `PlanMeta` ADD INDEX (  `planId` ,  `ContestPhase` ) ;

ALTER TABLE  `PlanModelRun` ADD INDEX (  `planId` );
ALTER TABLE  `PlanModelRun` ADD INDEX (  `planId` ,  `planVersion` ) ;


ALTER TABLE  `PlanPositions` ADD INDEX (  `planId` );
ALTER TABLE  `PlanPositions` ADD INDEX (  `planId` ,  `planVersion` ) ;

ALTER TABLE  `PlanTypeAttribute` ADD INDEX (  `planTypeId` );
ALTER TABLE  `PlanTypeColumn` ADD INDEX (  `planTypeId` );

ALTER TABLE  `PlanDescription` ADD INDEX (  `planId` );
ALTER TABLE  `PlanDescription` ADD INDEX (  `planId` ,  `planVersion` ) ;

ALTER TABLE  `PlanAttribute` ADD INDEX (  `planId` );
