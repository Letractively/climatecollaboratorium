DROP TABLE IF EXISTS OntologyTerm;

create table OntologyTerm (
    id_ BIGINT not null primary key,
    parentId BIGINT,
    ontologySpaceId BIGINT not null,
    name VARCHAR(75) null
);

create table OntologyTermEntity (
    id_ BIGINT not null primary key,
    termId BIGINT,
    classNameId BIGINT,
    classPK BIGINT
);

create table OntologySpace (
    id_ BIGINT not null primary key,
    name VARCHAR(255) null,
    description VARCHAR(255) null
);


create table FocusArea (
    id_ BIGINT not null primary key,
    name VARCHAR(75) null
);

create table FocusAreaOntologyTerm (
    focusAreaId BIGINT not null,
    ontologyTermId BIGINT not null,
    primary key (focusAreaId, ontologyTermId)
);


create index IX_ABD7C0DC on OntologySpace (name);


create index IX_D17A1280 on OntologyTerm (name);
create index IX_F229DF5A on OntologyTerm (parentId);

create index IX_222A254A on OntologyTermEntity (classNameId);
create index IX_E20301D9 on OntologyTermEntity (classNameId, classPK);
create index IX_8E77BEDF on OntologyTermEntity (termId);



create index IX_CDFD3F6A on FocusArea (name);

create index IX_CDFD3F6A on FocusArea (name);

create index IX_33F53E13 on FocusAreaOntologyTerm (focusAreaId);
create index IX_36F557C5 on FocusAreaOntologyTerm (focusAreaIdId);
create index IX_36F557C5 on FocusAreaOntologyTerm (focusAreaIdId);
