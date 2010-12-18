/* create database */
drop database if exists simulation3;
create database simulation3;

/* create user */
grant all on simulation3.* to 'climatedb'@'localhost' identified by 'cci08' with grant option;
grant all on simulation3.* to 'climatedb'@'%' identified by 'cci08' with grant option;


