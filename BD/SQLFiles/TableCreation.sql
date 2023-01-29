drop schema mtl cascade;
create schema mtl;

create table mtl.author
(
    CodAuthor serial
        primary key,
    FName     varchar(20),
    LName     varchar(20)
);

create table mtl.series
(
    ISSN_S  issn primary key,
    Curator names,
    Edition int,
    Code_S  varchar(10) unique,
    Name_S  names
);

create table mtl.magazine
(
    ISSN_M            issn primary key,
    Name_M            names,
    Argument          names,
    Manager           names,
    YearRelease       timestamp,
    PublicationPeriod names,
    AccessMode        access,
    PublishingHouse   names
);


create table mtl.book
(
    Doi_B            doi
        primary key,
    ISBN_B           isbn
        unique,
    PublishingHouse  names,
    Language         names,
    AccessMode       access,
    Title            varchar(30),
    Argument         names,
    Reprint          boolean,
    Edition          int,
    ReleaseDate      timestamp,
    ReleaseLocation  location,
    PresentationName names,
    FK_Author        serial,
    FK_Series        issn,

    constraint BookFK_2 foreign key (Fk_Author) references mtl.Author (CodAuthor) on delete cascade,
    constraint BookFK_3 foreign key (FK_Series) references mtl.Series (ISSN_S) on delete set null
);

create table mtl.article
(
    Doi_A           doi
        primary key,
    Title           varchar(40),
    AccessMode      access,
    Editor          names,
    Topic           names,
    ReleaseDate     timestamp,
    ReleaseLocation location,
    ConferenceName  varchar(50),
    FK_Author       serial,
    FK_Magazine     issn,

    constraint ArticleFK_1 foreign key (FK_Author) references mtl.Author (CodAuthor) on delete cascade,
    constraint ArticleFK_2 foreign key (FK_Magazine) references mtl.Magazine (ISSN_M) on delete set null
);