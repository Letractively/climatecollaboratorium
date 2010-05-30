
UPDATE ModelOutputItem SET itemType="CONF_INTERVAL_5", relatedOutputItem=2952 WHERE modelOutputItemId = 2953;
UPDATE ModelOutputItem SET itemType="CONF_INTERVAL_95", relatedOutputItem=2952 WHERE modelOutputItemId = 2954;


-- developed countries group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('1', '623', '243', null, null, '1');
UPDATE `ModelInputItem` SET `modelGroupId` = 1 WHERE `modelInputItemID` IN (243, 265, 270) AND `modelId` = 623;

UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 243;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 265;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 270;

-- developing A group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('2', '623', '247', null, null, '2');
UPDATE `ModelInputItem` SET `modelGroupId` = 2 WHERE `modelInputItemID` IN (247, 264, 268) AND `modelId` = 623;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 247;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 264;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 268;


-- developing B group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('3', '623', '243', null, null, '3');
UPDATE `ModelInputItem` SET `modelGroupId` = 3 WHERE `modelInputItemID` IN (248, 266, 262) AND `modelId` = 623;

UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 248;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 266;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 262;



UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '4', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 244;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '5', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =623 AND `ModelInputItem`.`modelInputItemID` = 240;
