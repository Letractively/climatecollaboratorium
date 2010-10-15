UPDATE `simulation3`.`MetaData` SET `units` = '%' WHERE `MetaData`.`id` IN (3583, 3582, 3504, 3505, 3518, 3519, 3520, 3596, 3597, 3598);

UPDATE `simulation3`.`MetaDataDefaults` SET `max` = '415',
`min` = '-99' WHERE `MetaDataDefaults`.`id` =227;

UPDATE `simulation3`.`MetaDataDefaults` SET `max` = '225',
`min` = '-99' WHERE `MetaDataDefaults`.`id` =230;

UPDATE `simulation3`.`MetaDataDefaults` SET `max` = '350',
`min` = '-99' WHERE `MetaDataDefaults`.`id` =232;

UPDATE `simulation3`.`MetaDataDefaults` SET `max` = '225',
`min` = '-99' WHERE `MetaDataDefaults`.`id` =3001;

UPDATE `simulation3`.`MetaDataDefaults` SET `max` = '350',
`min` = '-99' WHERE `MetaDataDefaults`.`id` =3008;

UPDATE `simulation3`.`MetaDataDefaults` SET `max` = '415',
`min` = '-99' WHERE `MetaDataDefaults`.`id` =3010;
