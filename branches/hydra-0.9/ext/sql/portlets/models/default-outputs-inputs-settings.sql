
UPDATE ModelOutputItem SET itemType="CONF_INTERVAL_5", relatedOutputItem=3045 WHERE modelOutputItemId = 3046;
UPDATE ModelOutputItem SET itemType="CONF_INTERVAL_95", relatedOutputItem=3045 WHERE modelOutputItemId = 3047;


-- developed countries group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('1', '741', '243', null, null, '1');
UPDATE `ModelInputItem` SET `modelGroupId` = 1 WHERE `modelInputItemID` IN (3021, 3035, 3033) AND `modelId` = 741;

UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3021;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3035;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3033;

-- developing A group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('2', '741', '247', null, null, '2');
UPDATE `ModelInputItem` SET `modelGroupId` = 2 WHERE `modelInputItemID` IN (3022, 3034, 3032) AND `modelId` = 741;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3022;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3034;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3032;


-- developing B group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('3', '741', '243', null, null, '3');
UPDATE `ModelInputItem` SET `modelGroupId` = 3 WHERE `modelInputItemID` IN (3028, 3023, 3025) AND `modelId` = 741;

UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3028;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3023;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 3025;



--UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '4', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 244;
--UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '5', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 240;





-- developing B group
INSERT INTO `lportal`.`ModelInputGroup` (`modelInputGroupPK`, `modelId`, `nameAndDescriptionMetaDataId`, `name`, `description`, `displayItemOrder`) VALUES ('3', '741', '243', null, null, '3');
UPDATE `ModelInputItem` SET `modelGroupId` = 3 WHERE `modelInputItemID` IN (248, 266, 262) AND `modelId` = 741;

UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '1', `type_` = 'SLIDER' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 248;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '2' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 266;
UPDATE `lportal`.`ModelInputItem` SET `displayItemOrder` = '3' WHERE `ModelInputItem`.`modelId` =741 AND `ModelInputItem`.`modelInputItemID` = 262;


