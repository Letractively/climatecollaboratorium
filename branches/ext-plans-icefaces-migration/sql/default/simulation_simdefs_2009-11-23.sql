# Sequel Pro dump
# Version 1191
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.0.67)
# Database: simulation3
# Generation Time: 2009-11-23 18:07:17 -0500
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Simulation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Simulation`;

CREATE TABLE `Simulation` (
  `description` varchar(8192) default NULL,
  `id` varchar(64) NOT NULL,
  `name` varchar(64) default NULL,
  `url` varchar(64) default NULL,
  `state` varchar(64) default NULL,
  `creation` timestamp NULL default NULL,
  `compositeString` varchar(8192) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `Simulation` WRITE;
/*!40000 ALTER TABLE `Simulation` DISABLE KEYS */;
INSERT INTO `Simulation` (`description`,`id`,`name`,`url`,`state`,`creation`,`compositeString`)
VALUES
	('<p><a href=\'/web/guest/resources/-/wiki/Main/C-LEARN\'>C-LEARN</a> is a simplified, Web-accessible version of the Climate Rapid Overview and Decision Support (<a href=\' http://www.climateinteractive.org/simulations/C-ROADS\'>C-ROADS</a>), a lightweight climate simulator developed by the <a href=\' http://www.sustainer.org/\'>Sustainability Institute</a>, <a href=\' http://www.vensim.com\'>Ventana Systems</a>, and the <a href=\' http://sdg.scripts.mit.edu\'>System Dynamics Group</a> at the <a href=\' http://mitsloan.mit.edu\'>MIT Sloan School of Management</a>, as part of the <a href=\' http://climateinteractive.org/\'>Climate Interactive</a> effort.</p>\r<h4>Further information</h4>\rFor more, see <a href=\'/web/guest/resources/-/wiki/Main/C-LEARN\'>C-LEARN</a>.\r<h4>Evaluation</h4>\rThis model has been evaluated by a review panel of respected scientists, see <a href=\' http://www.climateinteractive.org/simulations/C-ROADS/technical/scientific-review/C-ROADS%20Scientific%20Review%20Summary-1.pdf\'>Summary Statement from C-ROADS Scientific Review Panel</a>. From the summary statement: \"This very rapid simulation model reproduces the response properties of state-of-the-art three dimensional climate models very well within the uncertainties of the high resolution models and with sufficient precision to provide useful information for its intended audience.\"\r','240','C-LEARN','http://localhost:8080/pangaea-servlet/rest/','PUBLIC','2009-08-30 19:02:33',NULL),
	('<p>Estimates of the <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Mitigation_costs\'>mitigation costs</a> associated with varying levels of emission reductions are generated through use of a <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Response_surface\'>response surface</a> derived from runs of <a href=\'http://globalchange.mit.edu/igsm\'>MIT&#39;s Integrated Global System Model (IGSM)</a>.</p>\r<h4>Further information</h4>\nFor more, see <a href=\' /web/guest/resources/-/wiki/Main/IGSM\'>IGSM response surface </a>.\r<h4>Evaluation</h4>\r\nThe study that served as the basis for development of the IGSM response surface was accepted by the <a href=\'http://www.er.doe.gov/ober/cpdac.html\'>Climate Change Science Program Product Development Advisory Committee (CPDAC)</a>, a panel of thirty scientists from universities, U.S. government laboratories and agencies, corporations, and non-profit organizations.\n<br/>\nThe response surface methodology used here has not yet been evaluated by scientific experts, but the Collaboratorium community hopes this will happen soon. The <a href=\'/web/guest/resources/-/wiki/Main/Project%20staff\'>MIT research team</a> that developed the response surfaces notes: \"The response surfaces are only intended to approximate the results from the original models. In some cases, the assumptions used in the original models differ from those used in the Collaboratorium models that are linked to the response surfaces, and this may lead to further inaccuracies. We believe, however, that the results derived through use of response surfaces are accurate enough to provide a general sense of likely impacts.\"\r','583','IGSM','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/961','PUBLIC','2009-09-09 20:47:27',NULL),
	('<p>Estimates of the <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Mitigation_costs\'>mitigation costs</a> associated with varying levels of emission reductions are generated through use of a <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Response_surface>response surface</a> derived from runs of <a href=\'http://www.stanford.edu/group/MERGE/\'>MERGE</a>, a model developed by the <a href=\'http://my.epri.com/portal/server.pt?\'>Electronic Power Research Institute (EPRI)</a> and <a href=\'http://www.stanford.edu/group/MERGE/\'>Stanford University</a>.</p> \r\r<h4>Further information</h4>\rFor more, see <a href=\'/web/guest/resources/-/wiki/Main/MERGE/\'>MERGE response surface</a>.\n\r<h4>Evaluation</h4>\rThe study that served as the basis for development of the MERGE response surface was accepted by the <a href=\'http://www.er.doe.gov/ober/cpdac.html\'>Climate Change Science Program Product Development Advisory Committee (CPDAC)</a>, a panel of thirty scientists from universities, U.S. government laboratories and agencies, corporations, and non-profit organizations.\n<br/>\nThe response surface methodology used here has not yet been evaluated by scientific experts, but the Collaboratorium community hopes this will happen soon. The <a href=\'/web/guest/resources/-/wiki/Main/Project%20staff\'>MIT research team</a> that developed the response surfaces notes: \"The response surfaces are only intended to approximate the results from the original models. In some cases, the assumptions used in the original models differ from those used in the Collaboratorium models that are linked to the response surfaces, and this may lead to further inaccuracies. We believe, however, that the results derived through use of response surfaces are accurate enough to provide a general sense of likely impacts.\"','584','MERGE','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/962','PUBLIC','2009-09-09 20:47:52',NULL),
	('<p><p>Estimates of the <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Mitigation_costs\'>mitigation costs</a> associated with varying levels of emission reductions are generated through use of a <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Response_surface>response surface</a> derived from runs of <a href=\'http://www.globalchange.umd.edu\'>MiniCAM</a>, a model developed by the <a href=\'http://www.pni.gov\'>Pacific Northwest National Laboratory</a> and the <a href=\'http://www.umresearch.umd.edu/\'>University of Maryland</a>.</p> \r<h4>Further information</h4>\rFor more, see <a href=\'/web/guest/resources/-/wiki/Main/MiniCAM\'>MiniCAM response surface</a>.\r<h4>Evaluation</h4>\rThe study that served as the basis for development of the MERGE response surface was accepted by the <a href=\'http://www.er.doe.gov/ober/cpdac.html\'>Climate Change Science Program Product Development Advisory Committee (CPDAC)</a>, a panel of thirty scientists from universities, U.S. government laboratories and agencies, corporations, and non-profit organizations.\n<br/>\nThe response surface methodology used here has not yet been evaluated by scientific experts, but the Collaboratorium community hopes this will happen soon. The <a href=\'/web/guest/resources/-/wiki/Main/Project%20staff\'>MIT research team</a> that developed the response surfaces notes: \"The response surfaces are only intended to approximate the results from the original models. In some cases, the assumptions used in the original models differ from those used in the Collaboratorium models that are linked to the response surfaces, and this may lead to further inaccuracies. We believe, however, that the results derived through use of response surfaces are accurate enough to provide a general sense of likely impacts.\"','585','MiniCAM','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/963','PUBLIC','2009-09-09 20:48:16',NULL),
	('<p>DICE is an integrated assessment model. The Collaboratorium uses a <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Response_surface\'>response surface</a> based on the damage function in DICE, which estimates the costs of damages caused by varying increases in global mean temperature (GMT), which estimates the costs of future damages caused by varying increases in global mean temperature (GMT).</p>\n\r<h4>Further information</h4>\rFor more, see <a href=\'web/guest/resources/-/wiki/Main/DICE\'>DICE damage function</a>.\r\r<h4>Evaluation</h4>\nDICE is among the first integrated assessment models and is perhaps the most widely cited. It was developed in the 1970s and has been updated regularly since.\n<br/>\rThe response surface methodology used here has not yet been evaluated by scientific experts, but the Collaboratorium community hopes this will happen soon. The <a href=\'/web/guest/resources/-/wiki/Main/Project%20staff\'>MIT research team</a> that developed the response surfaces notes: \"The response surfaces are only intended to approximate the results from the original models. In some cases, the assumptions used in the original models differ from those used in the Collaboratorium models that are linked to the response surfaces, and this may lead to further inaccuracies. We believe, however, that the results derived through use of response surfaces are accurate enough to provide a general sense of likely impacts.\"','586','DICE','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/964','PUBLIC','2009-09-09 20:48:40',NULL),
	('<p>PAGE is an integrated assessment model. The Collaboratorium uses a <a href=\'/web/guest/resources/-/wiki/Main/Glossary#Response_surface\'>response surface</a> based on PAGE?s damage function, which estimates the costs of future damages caused by varying increases in global mean temperature (GMT).</p>\r<h4>Further information</h4>\rFor more, see <a href=\'/web/guest/resources/-/wiki/Main/MiniCAM\'>PAGE damage function</a>.\r<br/>\r<h4>Evaluation</h4>\nPAGE was the model used in the <a href=\'http://webarchive.nationalarchives.gov.uk/+/http:/www.hm-treasury.gov.uk/independent_reviews/stern_review_economics_climate_change/stern_review_report.cfm\'>Stern Review</a> (2006), a wide ranging and highly influential study of climate change sponsored by the UK government.\n<br/>\rThe response surface methodology used here has not yet been evaluated by scientific experts, but the Collaboratorium community hopes this will happen soon. The <a href=\'/web/guest/resources/-/wiki/Main/Project%20staff\'>MIT research team</a> that developed the response surfaces notes: \"The response surfaces are only intended to approximate the results from the original models. In some cases, the assumptions used in the original models differ from those used in the Collaboratorium models that are linked to the response surfaces, and this may lead to further inaccuracies. We believe, however, that the results derived through use of response surfaces are accurate enough to provide a general sense of likely impacts.\"','587','PAGE','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/965','PUBLIC','2009-09-09 20:49:00',NULL),
	('<p>During the preparation of the Stern Review, the Tyndall Centre for Climate Change Research was charged with undertaking a study that projected the physical impacts of rising global temperatures.</p>\r<h4>Further information</h4>\rFor more, see  <a href=\'/web/guest/resources/-/wiki/Main/Tyndall+Center\'>Tyndall Centre</a>.\r<h4>Evaluation</h4>\rThe Tyndall Centre was the primary source on the physical impacts of climate change for the <a href=\'http://webarchive.nationalarchives.gov.uk/+/http:/www.hm-treasury.gov.uk/independent_reviews/stern_review_economics_climate_change/stern_review_report.cfm\'>Stern Review</a> (2006), a wide ranging and highly influential study of climate change undertaken by the UK government.','588','Tyndall Center','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/966','PUBLIC','2009-09-09 20:49:32',NULL),
	('<p>The Intergovernmental Panel on Climate Change (IPCC) issued its Fourth Assessment Report (AR4) in 2007. The <a href=\'http://www.ipcc.ch/pdf/assessment-report/ar4/wg2/ar4-wg2-ts.pdf\'>Technical Summary</a> of the report prepared by IPCC Working Group II summarizes the projected physical impacts of climate change.</p>\r<h4>Further information</h4>\rFor more, see <a href=\'/web/guest/resources/-/wiki/Main/IPCC+AR4\'>IPCC AR4</a>. \r<h4>Evaluation</h4>\rThe IPCC is a United Nations body charged with assessing the scientific, technical and socio-economic information relevant for the understanding of the risk of human-induced climate change. The IPCC was awarded the Nobel Peace Prize in 2007.','681','IPCC AR4','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1061','PUBLIC','2009-09-20 22:38:10',NULL),
	('<p>\nC-LEARN includes a module that projects the rise in sea level that will result from increases in global temperature. For computational reasons, this module is implemented as a separate model in the Collaboratorium, using identical equations. \n</p>\n<h4>Further information</h4>\nFor more, see <a href=\"/web/guest/resources/-/wiki/Main/Sea+level+rise\"> C-LEARN sea level module</a>.\n<h4>Evaluation</h4>\nC-LEARN has been evaluated by a review panel of respected scientists. For more details see <a href=\"/web/guest/resources/-/wiki/Main/C-LEARN\">C-LEARN page</a>.','621','C-LEARN sea level','http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1001','PUBLIC','2009-09-16 10:23:50',NULL),
	('<p>The MIT Composite Model incorporates all the other models included in the launch version of the Collaboratorium. Its foundation is <a href=\'/web/guest/models?p_p_id=models&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_models_struts_action=%2Fext%2Fmodels%2Frun_model&_models_modelId=240&_models_modelName=C-LEARN\'>C-LEARN</a>; all the other models rely on outputs from C-LEARN as their inputs. The other models provide additional projections on the economic and physical impacts of climate change.</p>\n<h4>Further information</h4>\rFor more, see <a href=\'/web/guest/resources/-/wiki/Main/MIT+Composite+Model\'>MIT Composite Model</a>.\r<h4>Evaluation</h4>\rEach model used in the MIT Composite model has been extensively evaluated through the scientific peer review process. For more details, click on the individual page for each model (these links are all readily accessible from the <a href=\'/web/guest/models\'>Model Index</a>).','623','MIT Composite model',NULL,'PUBLIC','2009-09-16 10:40:47','<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><inputs><simulation id=\"240\"/></inputs><step><simulation id=\"240\"/></step><step><simulation id=\"586\"><input internalname=\"Temperature\" map=\"all\" source=\"240.GlobalTempChange\"/></simulation><simulation id=\"587\"><input internalname=\"Temperature\" map=\"all\" source=\"240.GlobalTempChange\"/></simulation><simulation id=\"583\"><input internalname=\"Atmospheric_CO2_concentration\" map=\"all\" source=\"240.AtmosphericCO2Concentration\"/><input internalname=\"Time\" interval=\"10\" map=\"some\" source=\"240.Year\"/></simulation><simulation id=\"584\"><input internalname=\"Atmospheric_CO2_concentration\" map=\"all\" source=\"240.AtmosphericCO2Concentration\"/><input internalname=\"Time\" interval=\"10\" map=\"some\" source=\"240.Year\"/></simulation><simulation id=\"585\"><input internalname=\"Atmospheric_CO2_concentration\" map=\"all\" source=\"240.AtmosphericCO2Concentration\"/><input internalname=\"Time\" interval=\"10\" map=\"some\" source=\"240.Year\"/></simulation><simulation id=\"588\"><input internalname=\"Temperature_Change\" map=\"max\" source=\"240.GlobalTempChange\"/></simulation><simulation id=\"681\"><input internalname=\"Temperature_Change\" map=\"max\" source=\"240.GlobalTempChange\"/></simulation><simulation accumulate=\"true\" id=\"621\"><input internalname=\"Temperature\" map=\"all\" source=\"240.GlobalTempChange\"/></simulation></step><outputs><simulation id=\"240\"/><simulation id=\"621\"><remap from=\"2942\" to=\"2958\"/></simulation><simulation id=\"583\"><remap from=\"2875\" to=\"2955\"/></simulation><simulation id=\"584\"><remap from=\"2878\" to=\"2956\"/></simulation><simulation id=\"585\"><remap from=\"2881\" to=\"2957\"/></simulation><simulation id=\"586\"><remap from=\"2883\" to=\"2951\"/></simulation><simulation id=\"587\"><remap from=\"2885\" to=\"2952\"/><remap from=\"2886\" to=\"2953\"/><remap from=\"2887\" to=\"2954\"/></simulation><simulation id=\"681\"/><simulation id=\"588\"/></outputs></root>');

/*!40000 ALTER TABLE `Simulation` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
