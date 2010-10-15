/* create database */
drop database if exists exceldb;
create database exceldb;

/* create user */
grant all on exceldb.* to 'climatedb'@'localhost' identified by 'cci08' with grant option;
grant all on exceldb.* to 'climatedb'@'%' identified by 'cci08' with grant option;


