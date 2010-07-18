# Sequel Pro dump
# Version 1191
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.0.67)
# Database: simulation3
# Generation Time: 2009-11-12 03:40:00 -0500
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table MetaData
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MetaData`;

CREATE TABLE `MetaData` (
  `description` varchar(1028) default NULL,
  `id` varchar(64) NOT NULL,
  `internalname` varchar(64) default NULL,
  `name` varchar(64) default NULL,
  `profile` varchar(64) default NULL,
  `vartype` varchar(64) default NULL,
  `units` varchar(64) default NULL,
  `labels` varchar(128) default NULL,
  `external` varchar(128) default NULL,
  `varcontext` varchar(64) default NULL,
  `indexingid` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `MetaData` WRITE;
/*!40000 ALTER TABLE `MetaData` DISABLE KEYS */;
INSERT INTO `MetaData` (`description`,`id`,`internalname`,`name`,`profile`,`vartype`,`units`,`labels`,`external`,`varcontext`,`indexingid`)
VALUES
	('Percentage value is a comparison of 2050 emission target vs. actual 2005 emissions. Other developing countries includes smaller developing nations in the Middle East, Latin America, Africa, and Asia.','248','D_Pct change in Developing B FF emissions','Other developing countries emissions change','java.lang.Double','RANGE','Percentage of 2005 emissions','Fossil Fuel Emissions Change',NULL,'SCALAR',NULL),
	('Percentage value is a comparison of 2050 emission target vs. actual 2005 emissions. Developed countries includes many of the most developed nations: US, EU (27 countries), Norway and Sweden, Russia and the former Soviet States, Japan, Canada, South Korea, New Zealand, and Australia.','243','D_Pct change in Developed FF emissions','Developed countries emissions change','java.lang.Double','RANGE','Percentage of 2005 emissions','Fossil Fuel Emissions Change',NULL,'SCALAR',NULL),
	('Amount of emissions per year for developed countries including US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.','242','DevelopedFossilFuelEmissions','Developed countries Fossil Fuel Emissions','java.lang.Double','RANGE','10^9 tons of Carbon','Fossil Fuel Emissions',NULL,'INDEXED','271'),
	('This value is a 0-to-1 index for the annual removal of CO2 from the atmosphere due to the creation of new forests, starting in 2009 and continuing until 2050. A value of 1 delivers the IPCC estimated maximum of 1.6 billion tons of carbon removed per year, while 0.5 delivers half of the maximum, and 0 means no increase.','244','D_Target Sequestration','Sequestration due to Treegrowth','java.lang.Double','RANGE','Fraction of max removal','Afforestation',NULL,'SCALAR',NULL),
	('This value is a measure of deforestation and resulting CO2 land use emissions. It value is a 0-to-1 index where a value of 1 yields constant emissions through 2050, 0.5 yields a drop of 45% by 2050, and 0 yields a 90% drop by 2050.','240','D_Global land use emissions change','Emissions from Deforestation','java.lang.Double','RANGE','Indexed fractional reduction','Deforestation',NULL,'SCALAR',NULL),
	('Amount of emissions per year for non-developed and non-developing countries.','246','DevelopingBFossilFuelEmissions','Other developing countries Fossil Fuel Emissions','java.lang.Double','RANGE','10^9 tons of Carbon','Fossil Fuel Emissions',NULL,'INDEXED','271'),
	('Amount of emissions per year for developing countries including China, India, South Africa, Mexico, Brazil, and Indonesia.','245','DevelopingAFossilFuelEmissions','Rapidly developing countries Fossil Fuel Emissions','java.lang.Double','RANGE','10^9 tons of Carbon','Fossil Fuel Emissions',NULL,'INDEXED','271'),
	('Percentage value is a comparison of 2050 emission target vs. actual 2005 emissions. Rapidly developing countries includes many of the fastest developing and larger nations: China, India, South Africa, Mexico, Brazil, Indonesia, and other large developing Asian countries.','247','D_Pct change in Developing A FF emissions','Rapidly developing countries emissions change','java.lang.Double','RANGE','Percentage of 2005 emissions','Fossil Fuel Emissions Change',NULL,'SCALAR',NULL),
	('Concentration of CO2 in the atmosphere each year.','241','AtmosphericCO2Concentration','Atmospheric CO2 Concentration','java.lang.Double','RANGE','ppm','CO2 Concentration',NULL,'INDEXED','271'),
	('Change in mean global temperature (Celsius) each year, relative to pre-industrial temperatures.','263','GlobalTempChange','Global mean temperature change above preindustrial values','java.lang.Double','RANGE','C','Temperature Change (Degrees C)',NULL,'INDEXED','271'),
	('Simulation Year','271','Year','Simulation Year','java.lang.Integer','RANGE','Year','Year',NULL,'INDEX','271'),
	('Year at which other countries fossil fuel emissions reach target levels.','262','D_Developing B target year','Other developing countries target year','java.lang.Integer','RANGE','Year','Target Year',NULL,'SCALAR',NULL),
	('Year at which other countries fossil fuel emissions begins to decrease.','266','D_Developing B start year','Other developing countries start reduction year','java.lang.Integer','RANGE','Year','Start Year',NULL,'SCALAR',NULL),
	('Year at which fossil fuel emissions reach target levels. US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.','270','D_Developed target year','Developed countries target year','java.lang.Integer','RANGE','Year','Target Year',NULL,'SCALAR',NULL),
	('Year at which fossil fuel emissions reach target levels. China, India, South Africa, Mexico, Brazil, and Indonesia.','268','D_Developing A target year','Rapidly developing countries target year','java.lang.Integer','RANGE','Year','Target Year',NULL,'SCALAR',NULL),
	('Year at which fossil fuel emissions begins to decrease. US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.','265','D_Developed start year','Developed countries start reduction year','java.lang.Integer','RANGE','Year','Start Year',NULL,'SCALAR',NULL),
	('Year at which fossil fuel emissions begins to decrease. China, India, South Africa, Mexico, Brazil, and Indonesia.','264','D_Developing A start year','Rapidly developing countries start reduction year','java.lang.Integer','RANGE','Year','Start Year',NULL,'SCALAR',NULL),
	('Year','2873','Time','Time','java.lang.Integer','RANGE','Year','Year','','SCALAR',NULL),
	('Atmospheric CO2 concentration','2874','Atmospheric_CO2_concentration','Atmospheric CO2 concentration','java.lang.Integer','RANGE','ppm','CO2','','SCALAR',NULL),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2875','_Change_in_GDP_vs__baseline_igsm_output','%Change in GDP vs. baseline.igsm','java.lang.Double','RANGE','%','','','SCALAR',NULL),
	('Year','2876','Time','Time','java.lang.Integer','RANGE','Year','Year','','SCALAR',NULL),
	('Atmospheric CO2 concentration','2877','Atmospheric_CO2_concentration','Atmospheric CO2 concentration','java.lang.Integer','RANGE','ppm','CO2','','SCALAR',NULL),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2878','_Change_in_GDP_vs__baseline_merge_output','%Change in GDP vs. baseline.merge','java.lang.Double','RANGE','%','','','SCALAR',NULL),
	('Year','2879','Time','Time','java.lang.Integer','RANGE','Year','Year','','SCALAR',NULL),
	('Atmospheric CO2 concentration','2880','Atmospheric_CO2_concentration','Atmospheric CO2 concentration','java.lang.Integer','RANGE','ppm','CO2','','SCALAR',NULL),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2881','_Change_in_GDP_vs__baseline_minicam_output','%Change in GDP vs. baseline.minicam','java.lang.Double','RANGE','%','','','SCALAR',NULL),
	('Degrees Temperature Change','2882','Temperature','Temperature','java.lang.Integer','RANGE','Degrees C','Temp. Change','','SCALAR',NULL),
	('%Change (GDP)','2883','Percent_Change_GDP_vs__Basline_output','Percent Change GDP vs. Basline','java.lang.Double','RANGE','%','','Percent Change GDP vs. Basline','SCALAR',NULL),
	('Degrees Temperature Change','2884','Temperature','Temperature','java.lang.Integer','RANGE','Degrees C','Temp. Change','','SCALAR',NULL),
	('%Change (Mean)','2885','Mean_Percent_Change_GDP_vs__Basline_output','Mean Percent Change GDP vs. Basline','java.lang.Double','RANGE','%','','','SCALAR',NULL),
	('%Change (5%)','2886','PercentChange5_output','PercentChange5','java.lang.Double','RANGE','%','','','SCALAR',NULL),
	('%Change (95%)','2887','PercentChange95_output','PercentChange95','java.lang.Double','RANGE','%','','','SCALAR',NULL),
	('Degrees Temperature Change - not dependant on time','2888','Temperature_Change','Temperature Change','java.lang.Integer','RANGE','Degrees C','Temp. Change.','','SCALAR',NULL),
	('Temperature Change','2889','Temperature_Change_output','Temperature Change','java.lang.String','FREE','Degrees C','','','INDEX','2889'),
	('Water Impacts','2890','Water_Impacts_output','Water','java.lang.String','FREE','Degrees C','Tyndall Center','','INDEXED','2889'),
	('Food Impacts','2891','Food_Impacts_output','Food/Agriculture','java.lang.String','FREE','Degrees C','Tyndall Center','','INDEXED','2889'),
	('Health Impacts','2892','Health_Impacts_output','Health','java.lang.String','FREE','Degrees C','Tyndall Center','','INDEXED','2889'),
	('Land Impacts','2893','Land_Impacts_output','Land/Coastal','java.lang.String','FREE','Degrees C','Tyndall Center','','INDEXED','2889'),
	('Environment Impacts','2894','Environment_Impacts_output','Environment/Ecosystems','java.lang.String','FREE','Degrees C','Tyndall Center','','INDEXED','2889'),
	('Abrupt and Large-Scale Impacts','2895','Abrupt_and_Large_Scale_Impacts_output','Abrupt/Singular Events','java.lang.String','FREE','Degrees C','Tyndall Center','','INDEXED','2889'),
	('%Change (GDP)','2896','Percent_Change_GDP_vs__Basline_output','DICE Damage Cost','java.lang.Double','RANGE','% GDP','Damage Cost (% GDP vs. Basline)','Percent Change GDP vs. Basline','INDEXED','271'),
	('%Change (Mean)','2897','Mean_Percent_Change_GDP_vs__Basline_output','PAGE Damage Cost','java.lang.Double','RANGE','% GDP','Damage Cost (% GDP vs. Basline)','','INDEXED','271'),
	('%Change (5%)','2898','PercentChange5_output','PercentChange5','java.lang.Double','RANGE','% GDP','','','INDEXED','271'),
	('%Change (95%)','2899','PercentChange95_output','PercentChange95','java.lang.Double','RANGE','% GDP','','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2900','_Change_in_GDP_vs__baseline_igsm_output','IGSM Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Basline)','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2901','_Change_in_GDP_vs__baseline_merge_output','MERGE Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Basline)','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2902','_Change_in_GDP_vs__baseline_minicam_output','MiniCam Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Basline)','','INDEXED','271'),
	('Degrees Temperature Change','2920','Temperature','Temperature','java.lang.Integer','RANGE','Degrees C','Temp. Change','','SCALAR',NULL),
	('%Change (GDP)','2921','Percent_Change_GDP_vs__Basline_output','Percent Change GDP vs. Basline','java.lang.Double','RANGE','% GDP','','Percent Change GDP vs. Basline','SCALAR',NULL),
	('Year','2922','Time','Time','java.lang.Integer','RANGE','Year','Year','','SCALAR',NULL),
	('Atmospheric CO2 concentration','2923','Atmospheric_CO2_concentration','Atmospheric CO2 concentration','java.lang.Integer','RANGE','ppm','CO2','','SCALAR',NULL),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2924','_Change_in_GDP_vs__baseline_igsm_output','%Change in GDP vs. baseline.igsm','java.lang.Double','RANGE','% GDP','','','SCALAR',NULL),
	('Year','2925','Time','Time','java.lang.Integer','RANGE','Year','Year','','SCALAR',NULL),
	('Atmospheric CO2 concentration','2926','Atmospheric_CO2_concentration','Atmospheric CO2 concentration','java.lang.Integer','RANGE','ppm','CO2','','SCALAR',NULL),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2927','_Change_in_GDP_vs__baseline_merge_output','%Change in GDP vs. baseline.merge','java.lang.Double','RANGE','% GDP','','','SCALAR',NULL),
	('mm','2940','Sea_Level_Rise_output','Sea Level Rise','java.lang.Double','RANGE','mm','','Expected Sea Level Rise','SCALAR',NULL),
	('Degrees Temperature Change','2941','Temperature','Temperature','java.lang.Integer','RANGE','Degrees C','Temp. Change','','SCALAR',NULL),
	('mm','2942','Sea_Level_Rise_output','Sea Level Rise','java.lang.Double','RANGE','mm','','Expected Sea Level Rise','SCALAR',NULL),
	('%Change (GDP)','2943','Percent_Change_GDP_vs__Basline_output','DICE Damage Cost','java.lang.Double','RANGE','% GDP','Damage Cost (% GDP vs. Baseline)','Percent Change GDP vs. Basline','INDEXED','271'),
	('%Change (Mean)','2944','Mean_Percent_Change_GDP_vs__Basline_output','Mean Percent Change GDP vs. Basline','java.lang.Double','RANGE','% GDP','Damage Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('%Change (5%)','2945','PercentChange5_output','PercentChange5','java.lang.Double','RANGE','% GDP','','','INDEXED','271'),
	('%Change (95%)','2946','PercentChange95_output','PercentChange95','java.lang.Double','RANGE','% GDP','','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2947','_Change_in_GDP_vs__baseline_igsm_output','IGSM Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2948','_Change_in_GDP_vs__baseline_merge_output','MERGE Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2949','_Change_in_GDP_vs__baseline_minicam_output','MiniCam Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('mm','2950','Sea_Level_Rise_output','Sea Level Rise (from 2000)','java.lang.Double','RANGE','mm','Sea Level Rise (from 2000)','Expected Sea Level Rise','INDEXED','2889'),
	('%Change (GDP)','2951','Percent_Change_GDP_vs__Basline_output','DICE Damage Cost','java.lang.Double','RANGE','% GDP','Damage Cost (% GDP vs. Baseline)','Percent Change GDP vs. Basline','INDEXED','271'),
	('%Change (Mean)','2952','Mean_Percent_Change_GDP_vs__Basline_output','PAGE Damage Cost','java.lang.Double','RANGE','% GDP','Damage Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('%Change (5%)','2953','PercentChange5_output','PercentChange5','java.lang.Double','RANGE','% GDP','','','INDEXED','271'),
	('%Change (95%)','2954','PercentChange95_output','PercentChange95','java.lang.Double','RANGE','% GDP','','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2955','_Change_in_GDP_vs__baseline_igsm_output','IGSM Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2956','_Change_in_GDP_vs__baseline_merge_output','MERGE Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('This parameter reflects the expected deviation from an implied baseline.  This baseline is based on assumptions derived from the IPCC A2 (high climate) scenario.','2957','_Change_in_GDP_vs__baseline_minicam_output','MiniCam Mitigation Cost','java.lang.Double','RANGE','% GDP','Mitigation Cost (% GDP vs. Baseline)','','INDEXED','271'),
	('mm','2958','Sea_Level_Rise_output','Sea Level Rise','java.lang.Double','RANGE','mm','Sea Level Rise (from 2000)','Expected Sea Level Rise','INDEXED','271'),
	('Degrees Temperature Change - not dependant on time','2960','Temperature_Change','Temperature Change','java.lang.Integer','RANGE','Degrees C','Temp. Change.','','SCALAR',NULL),
	('Temperature Change','2961','Temperature_Change_output','Temperature Change','java.lang.Integer','RANGE','Degrees C','','','INDEX','2961'),
	('Water Impacts','2962','Water_output','Water','java.lang.String','FREE','Degrees C','','','INDEXED','2961'),
	('Food Impacts','2963','Food_Agriculture_output','Food/Agriculture','java.lang.String','FREE','Degrees C','','','INDEXED','2961'),
	('Health Impacts','2964','Health_output','Health','java.lang.String','FREE','Degrees C','','','INDEXED','2961'),
	('Land Impacts','2965','Land_Coastal_output','Land/Coastal','java.lang.String','FREE','Degrees C','','','INDEXED','2961'),
	('Environment Impacts','2966','Environment_Ecosystems_output','Environment/Ecosystems','java.lang.String','FREE','Degrees C','','','INDEXED','2961'),
	('Abrupt and Large-Scale Impacts','2967','Abrupt_Singular_Events_output','Abrupt/Singular Events','java.lang.String','FREE','Degrees C','','','INDEXED','2961'),
	('Degrees Temperature Change - not dependant on time','2980','Temperature_Change','Temperature Change','java.lang.Integer','RANGE','Degrees C','Temp. Change.','','SCALAR',NULL),
	('Temperature Change','2981','Temperature_Change_output','Temperature Change','java.lang.Integer','RANGE','Degrees C','','','INDEX','2981'),
	('Water Impacts','2982','Water_output','Water','java.lang.String','FREE','Degrees C','','','INDEXED','2981'),
	('Food Impacts','2983','Food_Agriculture_output','Food/Agriculture','java.lang.String','FREE','Degrees C','','','INDEXED','2981'),
	('Health Impacts','2984','Health_output','Health','java.lang.String','FREE','Degrees C','','','INDEXED','2981'),
	('Land Impacts','2985','Land_Coastal_output','Land/Coastal','java.lang.String','FREE','Degrees C','','','INDEXED','2981'),
	('Environment Impacts','2986','Environment_Ecosystems_output','Environment/Ecosystems','java.lang.String','FREE','Degrees C','','','INDEXED','2981'),
	('Abrupt and Large-Scale Impacts','2987','Abrupt_Singular_Events_output','Abrupt/Singular Events','java.lang.String','FREE','Degrees C','','','INDEXED','2981'),
	('Degrees Temperature Change - not dependant on time','3000','Temperature_Change','Temperature Change','java.lang.Integer','RANGE','Degrees C','Temp. Change.','','SCALAR',NULL),
	('Temperature Change','3001','Temperature_Change1_output','Temperature Change1','java.lang.Integer','RANGE','Degrees C','','','INDEX','3001'),
	('Water Impacts','3002','Water1_output','Water1','java.lang.String','FREE','Degrees C','','','INDEXED','3001'),
	('Food Impacts','3003','Food_Agriculture1_output','Food/Agriculture1','java.lang.String','FREE','Degrees C','','','INDEXED','3001'),
	('Health Impacts','3004','Health1_output','Health1','java.lang.String','FREE','Degrees C','','','INDEXED','3001'),
	('Land Impacts','3005','Land_Coastal1_output','Land/Coastal1','java.lang.String','FREE','Degrees C','','','INDEXED','3001'),
	('Environment Impacts','3006','Environment_Ecosystems1_output','Environment/Ecosystems1','java.lang.String','FREE','Degrees C','','','INDEXED','3001'),
	('Abrupt and Large-Scale Impacts','3007','Abrupt_Singular_Events1_output','Abrupt/Singular Events1','java.lang.String','FREE','Degrees C','','','INDEXED','3001'),
	('Degrees Temperature Change - not dependant on time','3008','Temperature_Change','Temperature Change','java.lang.Integer','RANGE','Degrees C','Temp. Change.','','SCALAR',NULL),
	('Temperature Change','3009','Temperature_Change1_output','Temperature Change','java.lang.String','FREE','Degrees C','','','INDEX','3009'),
	('Water Impacts','3010','Water1_output','Water','java.lang.String','FREE','Degrees C','IPCC','','INDEXED','3009'),
	('Food Impacts','3011','Food_Agriculture1_output','Food/Agriculture','java.lang.String','FREE','Degrees C','IPCC','','INDEXED','3009'),
	('Health Impacts','3012','Health1_output','Health','java.lang.String','FREE','Degrees C','IPCC','','INDEXED','3009'),
	('Land Impacts','3013','Land_Coastal1_output','Land/Coastal','java.lang.String','FREE','Degrees C','IPCC','','INDEXED','3009'),
	('Environment Impacts','3014','Environment_Ecosystems1_output','Environment/Ecosystems','java.lang.String','FREE','Degrees C','IPCC','','INDEXED','3009'),
	('Abrupt and Large-Scale Impacts','3015','Abrupt_Singular_Events1_output','Abrupt/Singular Events','java.lang.String','FREE','Degrees C','IPCC','','INDEXED','3009');

/*!40000 ALTER TABLE `MetaData` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
