DELETE FROM `Simulation` WHERE `id` = '680';
DELETE FROM `Simulation` WHERE `id` = '589';
DELETE FROM `Simulation` WHERE `id` = '660';
UPDATE `Simulation` SET  `name`='IPCC AR4'  WHERE `id` = '681';
UPDATE `Simulation` SET  `name`='Tyndall Center' WHERE `id` = '588';
UPDATE `Simulation` SET `name`='SeaLevel (C-LEARN / Rahmstorf)' WHERE `id` = '621';
UPDATE `Simulation` SET `description`='The Climate Interactive Simulation is a lightweight GHG model, written in the Vensim simulation language, and developed by researchers from the Sustainability Institute and Rocky Mountain Institute. Although it is lightweight enough to be run on a personal computer, its results are consistent with much larger models, including the MiniCam and Merge models.  The web-based version of the model is hosted at the <a href=\'http://forio.com/simulation/international-climate-change-simulation/\'>Forio</a> website.
<h3>Further Information</h3>
Additional information may be found on our <a href="/web/guest/resources/-/wiki/Main/Essential+Background">models page</a>
<h3>Evaluation</h3>
This model has been evaluated by a review panel of respected scientists (see <a href=\'http://www.climateinteractive.org/simulations/C-ROADS/technical/scientific-review/C-ROADS%20Scientific%20Review %20Summary-1.pdf\'>Summary Statement from C-ROADS Scientific Review Panel</a>. From the summary statement: \"This very rapid simulation model reproduces the response properties of state-of- the- art three dimensional climate models very well – well within the uncertainties of the high resolution models—and with sufficient precision to provide useful information for its intended audience.\"' `name`='Collaboratorium Launch Version Composite Model'  WHERE `id` = '240';
UPDATE `Simulation` SET `name`='MIT Composite Model' WHERE `id` = '623';


