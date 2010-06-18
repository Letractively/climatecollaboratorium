drop table if exists ModelGlobalPreference;# MySQL zwrócił pusty wynik (zero rekordów).


create table ModelGlobalPreference (
	modelGlobalPreferencePK bigint not null primary key,
	modelId bigint,
	visible int(1)
);# MySQL zwrócił pusty wynik (zero rekordów).

