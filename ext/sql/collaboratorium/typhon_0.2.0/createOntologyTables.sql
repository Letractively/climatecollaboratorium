
create table OntologyTerm (
    id_ BIGINT not null primary key,
    parentId BIGINT,
    name VARCHAR(75) null
);

create table OntologyTermEntity (
    id_ BIGINT not null primary key,
    termId BIGINT,
    classNameId BIGINT,
    classPK BIGINT
);


create index IX_D17A1280 on OntologyTerm (name);
create index IX_F229DF5A on OntologyTerm (parentId);

create index IX_222A254A on OntologyTermEntity (classNameId);
create index IX_E20301D9 on OntologyTermEntity (classNameId, classPK);
create index IX_8E77BEDF on OntologyTermEntity (termId);
