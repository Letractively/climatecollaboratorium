UPDATE `MetaData` SET units = "&deg;C" WHERE units = "C" or units = "Degrees C";
UPDATE `MetaData` SET units = "Gtons of Carbon" WHERE units = "10^9 tons of Carbon";
UPDATE `MetaData` SET units = "parts per million or ppm" WHERE units = "ppm";
