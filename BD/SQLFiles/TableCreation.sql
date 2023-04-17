drop schema if exists mtl cascade;
create schema mtl;

create domain mtl.issn as varchar(9)
    check ( value like '%-%'
        and value not similar to '%[a-z]+%'
        and value not similar to '%[A-Z]+%'
        and value not similar to '%[@!#$ˆ∗%&]+%');

create domain mtl.isbn as varchar(30)
    check ( value like '%-_-%-%-_');

create domain mtl.doi as varchar(30)
    check ( value like '10.%/%');

create domain mtl.access as varchar(20)
    check ( value <> '' and value not similar to '%[0-9]+%'
        and value not similar to '%[@!#$ˆ∗%&]+%');

create domain mtl.names as varchar(30)
    check ( value not similar to '%[@!#$ˆ∗%&]+%');

create domain mtl.location as varchar(50)
    check ( value like '%,%,%,%,%'
        and value not similar to '%[@!#$ˆ∗%&]+%');

create table mtl.author
(
    CodAuthor serial,
    FName     varchar(20),
    LName     varchar(20)
);

CREATE INDEX idx_name
    ON mtl.author (LName, FName);

ALTER TABLE IF EXISTS mtl.author
    ADD CONSTRAINT AuthorPK primary key (CodAuthor);

create table mtl.series
(
    ISSN_S  mtl.issn,
    Curator mtl.names,
    Edition int,
    Code_S  varchar(10),
    Name_S  mtl.names
);

ALTER TABLE IF EXISTS mtl.series
    ADD CONSTRAINT SeriesPK primary key (ISSN_S);

CREATE INDEX IF NOT EXISTS SeriesIndex ON mtl.series (Code_S);

create table mtl.magazine
(
    ISSN_M            mtl.issn,
    Name_M            mtl.names,
    Argument          mtl.names,
    Manager           mtl.names,
    YearRelease       timestamp,
    PublicationPeriod mtl.names,
    AccessMode        mtl.access,
    PublishingHouse   mtl.names
);

ALTER TABLE IF EXISTS mtl.magazine
    ADD CONSTRAINT MagazinePk primary key (ISSN_M);

create table mtl.book
(
    ISBN_B           mtl.isbn,
    DOI_B            mtl.doi,
    PublishingHouse  mtl.names,
    Language         mtl.names,
    AccessMode       mtl.access,
    Title            varchar(30),
    Argument         mtl.names,
    Reprint          boolean,
    Edition          int,
    ReleaseDate      timestamp,
    ReleaseLocation  mtl.location,
    PresentationName mtl.names,
    FK_Series        mtl.issn,

    constraint BookFK_3 foreign key (FK_Series) references mtl.Series (ISSN_S) on delete set null
);

ALTER TABLE IF EXISTS mtl.book
    ADD CONSTRAINT BookPk primary key (ISBN_B);

ALTER TABLE IF EXISTS mtl.book
    ADD CONSTRAINT BookUnique unique (DOI_B);


create table mtl.article
(
    Doi_A           mtl.doi,
    Title           varchar(40),
    AccessMode      mtl.access,
    Editor          mtl.names,
    Topic           mtl.names,
    ReleaseDate     timestamp,
    ReleaseLocation mtl.location,
    ConferenceName  varchar(50),
    FK_Magazine     mtl.issn,

    constraint ArticleFK_2 foreign key (FK_Magazine) references mtl.Magazine (ISSN_M) on delete set null,
    check ((FK_Magazine is not null and (ConferenceName is null and ReleaseLocation is null)) OR
           (FK_Magazine is null and (ConferenceName is not null and ReleaseLocation is not null)))
);

ALTER TABLE IF EXISTS mtl.article
    ADD CONSTRAINT ArticlePK primary key (Doi_A);

create table mtl.author_books
(

    AuthorsFK serial,
    BooksFK   mtl.isbn,

    constraint autori_libriFK_1 foreign key (AuthorsFK) references mtl.author (codauthor) on delete cascade,
    constraint autori_libriFK_2 foreign key (BooksFK) references mtl.book (ISBN_B) on delete cascade
);

create table mtl.author_article
(
    AuthorsFK  serial,
    ArticlesFK mtl.doi,

    constraint autori_libriFK_1 foreign key (AuthorsFK) references mtl.author (codauthor) on delete cascade,
    constraint autori_libriFK_2 foreign key (ArticlesFK) references mtl.article (Doi_A) on delete cascade
);

create or replace view mtl.presentation as
select b.title, a.fname, a.lname, b.presentationname, b.releaselocation, b.releasedate
from (mtl.book b join mtl.author_books au on au.BooksFK = b.ISBN_B)
         join mtl.author a on a.codauthor = au.AuthorsFK
where b.presentationname is not null
  and b.releaselocation is not null
order by lname;

create or replace view mtl.conference as
select a.title, ar.fname, ar.lname, a.conferencename, a.releaselocation, a.releasedate
from (mtl.article a join mtl.author_article au on au.ArticlesFK = a.doi_a)
         join mtl.author ar on au.AuthorsFK = ar.codauthor
where a.conferencename is not null
  and a.releaselocation is not null
order by lname;


create or replace function mtl.function_1() returns trigger as
$$
declare
    stringa_in   varchar(19) = new.isbn_b;
    sum          integer     := 0;
    var_appoggio integer;
    resto        integer;
begin
    stringa_in := replace(stringa_in, '-', '');
    for i in 1..13
        loop
            var_appoggio = cast(substring(stringa_in from i for 1) as int);
            if (i % 2 = 0) then
                sum := sum + var_appoggio * 3;
            else
                sum := sum + var_appoggio;
            end if;
        end loop;
    resto = sum % 10;
    if (resto != 0) then
        delete from mtl.book where isbn_b = new.isbn_b;
    end if;
    return new;
end
$$
    language plpgsql;

create trigger validity_isbn
    after insert
    on mtl.book
    for each row
execute procedure mtl.function_1();

create or replace function mtl.function_2() returns trigger as
$$
declare
    stringa_in   varchar(13) = new.issn_s;
    sum          integer     := 0;
    var_appoggio integer;
    resto        integer;
begin
    stringa_in := replace(stringa_in, '-', '');
    for i in 1..8
        loop
            if substr(stringa_in, 8, 1) = 'X' then
                sum = sum + 10;
                var_appoggio = cast(substring(stringa_in from i for 1) as int);
                if (i = 8) then
                    sum = sum + 0;
                else
                    sum := sum + var_appoggio * (9 - i);
                end if;
            else
                var_appoggio = cast(substring(stringa_in from i for 1) as int);
                sum := sum + var_appoggio * (9 - i);
            end if;
        end loop;
    resto = sum % 11;
    if (resto != 0) then
        delete from mtl.series where issn_s = new.issn_s;
    end if;
    return new;
end
$$
    language plpgsql;

create trigger validity_issn_s
    after insert
    on mtl.series
    for each row
execute procedure mtl.function_2();

create or replace function mtl.function_3() returns trigger as
$$
declare
    stringa_in   varchar(13) = new.issn_m;
    sum          integer     := 0;
    var_appoggio integer;
    resto        integer;
begin
    stringa_in := replace(stringa_in, '-', '');
    for i in 1..8
        loop
            if substr(stringa_in, 8, 1) = 'X' then
                sum = sum + 10;
                var_appoggio = cast(substring(stringa_in from i for 1) as int);
                if (i = 8) then
                    sum = sum + 0;
                else
                    sum := sum + var_appoggio * (9 - i);
                end if;
            else
                var_appoggio = cast(substring(stringa_in from i for 1) as int);
                sum := sum + var_appoggio * (9 - i);
            end if;
        end loop;
    resto = sum % 11;
    if (resto != 0) then
        delete from mtl.magazine where issn_m = new.issn_m;
    end if;
    return new;
end
$$
    language plpgsql;

create trigger validity_issn_m
    after insert
    on mtl.magazine
    for each row
execute procedure mtl.function_3();

CREATE OR REPLACE FUNCTION mtl.function_4() returns TRIGGER AS
$$
declare
    isbn_app       mtl.book.isbn_b%TYPE;
    autori_record  mtl.author%ROWTYPE;
    cod_author_app mtl.author.codauthor%TYPE;
    cod_author CURSOR FOR SELECT codauthor
                          FROM mtl.author;
begin
    isbn_app := new.isbn_b;
    for autori_record IN cod_author
        loop
            cod_author_app := autori_record.codauthor;
            INSERT INTO mtl.author_books(AuthorsFK, BooksFK) VALUES (cod_author_app, isbn_app);
        end loop;
    return NULL;
end;
$$
    LANGUAGE plpgsql;


create trigger insert_autori_libri
    after insert
    on mtl.book
    for each row
execute procedure mtl.function_4();


CREATE OR REPLACE FUNCTION mtl.function_5() returns TRIGGER AS
$$
declare
    doi_app        mtl.article.doi_a%TYPE;
    autori_record  mtl.author%ROWTYPE;
    cod_author_app mtl.author.codauthor%TYPE;
    cod_author CURSOR FOR SELECT codauthor
                          FROM mtl.author;
begin
    doi_app := new.doi_a;
    for autori_record in cod_author
        loop
            cod_author_app := autori_record.codauthor;
            raise notice 'cod_author_app: %', cod_author_app;
            insert into mtl.author_article(AuthorsFK, ArticlesFK) values (cod_author_app, doi_app);
        end loop;
    return NULL;
end;
$$
    LANGUAGE plpgsql;


create trigger insert_autori_articoli
    after insert
    on mtl.article
    for each row
execute procedure mtl.function_5();


create or replace function mtl.function_6() returns trigger as
$$
declare
    articoli_record record;
    articoli_cursore cursor for (select *
                                 from mtl.article a
                                 where a.fk_magazine = new.issn_m);
begin
    for articoli_record in articoli_cursore
        loop
            delete
            from mtl.article
            where new.yearrelease <> articoli_record.releasedate;
        end loop;
    return new;
end;
$$
    language plpgsql;


create trigger date_coerenti
    after insert
    on mtl.magazine
    for each row
execute procedure mtl.function_6();

INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0205', 'The Lancet', 'medicine', 'Denny Stevens', '2008-09-28 14:03:14.000000', 'Yearly', 'Paper',
        'Global Print');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-1236', 'IEEE Transactions on Electrica', 'elettric engineering', 'Eve Hobbs',
        '1994-04-22 11:23:33.000000', 'Every two months', 'Digital', 'Apple Inc.');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0329', 'To the Lighthouse', 'physics', 'Maia Vernon', '2009-12-11 06:32:50.000000', 'Every six months',
        'Paper', 'Global Print');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0337', 'ACM Transactions on Sensor Net', 'sensor networks', 'Peter Doherty', '2007-05-13 15:21:53.000000',
        'Monthly', 'Paper', 'UPC');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0078', 'Inorganic Chemistry', 'inorganic chemistry', 'Kamila Hopkins', '2008-04-14 06:23:34.000000',
        'Every three months', 'Paper', 'AECOM');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0086', 'The Astronomical Journal', 'astrophysics', 'Owen Rycroft', '2019-05-12 17:08:54.000000', 'Daily',
        'Audio', 'Metro Cash Carry');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0280', 'Chemical Reviews', 'chemistry', 'Chris Edley', '2000-09-17 21:07:24.000000', 'Every nine months',
        'Paper', 'UPC');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0094', 'The Plant Cell', 'biology', 'Johnny Mason', '2019-01-14 06:40:51.000000', 'Every five months',
        'Audio', 'Global Print');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-2372', 'Physical Review A', 'physics', 'Callie Underhill', '1981-01-10 06:00:22.000000', 'Weekly',
        'Audio', 'Apple Inc.');
INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-3247', 'PLOS ONE', 'science', 'Colleen Nobbs', '2008-12-25 04:18:28.000000', 'Yearly', 'Paper', 'AECOM');

INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('3252-5788', 'Ludovico Lanz', 3, '1', 'Introduction to physic theory');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('1233-3980', 'Andrea L. Lacaita', 1, '2', 'Applied electronic lessons');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('4213-8973', 'Remigio Ruggeri', 2, '3', 'Industrial logistic');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('7402-7883', 'Franco Cariati', 1, '4', 'Analityc chemistry lessons');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('0870-5429', 'National geographic channel', 1, '5', 'Grande Atlante');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('2494-9000', 'Mondadori', 1, '6', 'Piero Angela');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('2143-2139', 'J. K. Rowling', 1, '7', 'Harry Potter');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('0000-0361', 'Andrzej Sapkowski', 2, '8', 'The Witcher');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('0000-3336', 'Maya Jenkins', 4, '9', 'To the Lighthouse');
INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('0000-8362', 'Cassidy Glass', 3, '10', 'To the infinity and other');

INSERT INTO mtl.author (fname, lname)
VALUES ('Federico', 'Rossi');
INSERT INTO mtl.author (fname, lname)
VALUES ('Laura', 'Bianchi');
INSERT INTO mtl.author (fname, lname)
VALUES ('Marco', 'Neri');
INSERT INTO mtl.author (fname, lname)
VALUES ('Sara', 'Giordano');
INSERT INTO mtl.author (fname, lname)
VALUES ('Anna', 'Giordano');
INSERT INTO mtl.author (fname, lname)
VALUES ('Marco', 'Bianchi');
INSERT INTO mtl.author (fname, lname)
VALUES ('Laura', 'Ferrari');
INSERT INTO mtl.author (fname, lname)
VALUES ('Francesco', 'Verdi');
INSERT INTO mtl.author (fname, lname)
VALUES ('Chiara', 'Esposito');
INSERT INTO mtl.author (fname, lname)
VALUES ('Federica', 'Romano');
INSERT INTO mtl.author (fname, lname)
VALUES ('Joanne', 'Rowling ');
INSERT INTO mtl.author (fname, lname)
VALUES ('Andrzej ', 'Sapkowski');
INSERT INTO mtl.author (fname, lname)
VALUES ('Ludovico', 'Lanz');
INSERT INTO mtl.author (fname, lname)
VALUES ('Andrea', 'Lacaita');
INSERT INTO mtl.author (fname, lname)
VALUES ('National', 'Geographic');


INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.7528/978-7-4636-6250-1', '978-7-4636-6250-1', 'Salani', 'Spanish', 'Paper', 'La piedra filosofal',
        'fantasy novel', true, 6, '1998-12-17 12:26:36.000000', 'DynCorp, Bede  Grove, 308, San Diego, 1347',
        'La piedra filosofal', '2143-2139');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.2081/978-0-7135-9978-7', '978-0-7135-9978-7', 'Salani', 'French', 'Audio', 'La chambre des secrets',
        'fantasy novel', false, 8, '1999-02-08 14:45:52.000000', 'Zepter, Thomas  Tunnel, 4547, Milwaukee,  3883',
        'La chambre des secrets', '2143-2139');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1715/978-5-7830-7642-8', '978-5-7830-7642-8', 'Salani', 'German', 'Digital', 'Der Gefangene von Askaban',
        'fantasy novel', true, 5, '2000-08-24 03:10:40.000000', 'It Smart Group, Pine Avenue, 4492, Milwaukee, 8877',
        'Der Gefangene von Askaban', '2143-2139');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1831/978-6-1032-3199-2', '978-6-1032-3199-2', 'Nord', 'Italian', 'Paper', 'Il guardiano degli innocenti',
        'fantasy novel', true, 5, '1985-05-11 00:03:00.000000', 'BuzzFeed, Rivervalley Vale, 3636, Cincinnati, 7516',
        'The Witcher', '0000-0361');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1101/978-8-8678-5623-7', '978-8-8678-5623-7', 'Nord', 'Italian', 'Digital', 'La spada del destino',
        'fantasy novel', false, 6, '1988-07-29 22:13:05.000000', 'Erickson, Chester Crossroad, 4461, New Orleans,686',
        'The Witcher', '0000-0361');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.2125/978-7-0592-3314-6', '978-7-0592-3314-6', 'National Geographic', 'English', 'Paper',
        'The American Continent', 'geography', true, 9, '2003-11-27 14:21:48.000000',
        'Comodo, Tiptree   Rue, 9695, Minneapolis, 1578', 'World Geography', '0870-5429');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1830/978-1-3267-0869-6', '978-1-3267-0869-6', 'Hoepli', 'German', 'Paper', 'Introduction to circuits',
        'applied electronic', false, 8, '2018-04-04 17:48:03.000000', 'ExxonMobil, Bagford   Lane, 7740, Lincoln, 6618',
        'Electronic world', '1233-3980');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1631/317-8-028670-00-4', '317-8-028670-00-4', 'Hoepli', 'French', 'Digital', 'Power electronics',
        'applied electronic',
        false, 2, '2016-03-15 08:12:19.000000', 'Vodafone, Wakley   Drive, 1288, Denver, 2336', 'Electronic world',
        '1233-3980');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1364/978-5-3498-2779-2', '978-5-3498-2779-2', 'BuzzFeed', 'English', 'Digital', 'Fundamentals of mechanics',
        'physics', true, 2, '2001-04-20 01:07:51.000000', 'ExxonMobil, Tilson  Route, 8721, Hollywood, 1805',
        'Physics discussion', '3252-5788');
INSERT INTO mtl.book (doi_b, isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)
VALUES ('10.1570/010-7-624775-72-1', '010-7-624775-72-1', 'BuzzFeed', 'English', 'Digital',
        'Concepts of electromagnetism', 'physics', false, 7, '2000-07-22 18:10:00.000000',
        'BuzzFeed, Rivervalley Vale, 3636, Cincinnati, 7516', 'Physics discussion', '3252-5788');



INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.5915/96618', 'The structure of written graphene-based ', 'Paper', 'Facebook', 'physics',
        '2012-12-07 13:32:33.000000', 'Global Print, 5700,  Wager   Hill ,  5775, Cincinn', 'Graphene-based materials',
        null);
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.4339/03746', 'The effectiveness of flu vaccines', 'Digital', 'Zanichelli', 'medicine',
        '2016-12-10 10:08:28.000000', null, null, '0000-1236');
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.1232/361857', 'The identification of a new regulating m', 'Audio', 'Boeing', 'chemestry',
        '2018-06-02 02:05:17.000000', null, null, '0000-0086');
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.6923/55729', 'The results of a clinical trial of a new', 'Digital', 'Coca-Cola Company', 'medicine',
        '2015-04-21 20:29:05.000000', 'Demaco, 470, Liverpool  Drive ,  5520 ,Zurich', 'Type 2 diabetes', null);
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.1668/223239', 'Data analysis of a survey on lifestyles ', 'Digital', 'Team Guard SRL', 'science',
        '2014-05-15 20:49:52.000000', 'Danone, 9642, Fair Drive ,  2115 , Cincinnati', 'Mental health and lifestyle',
        null);
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.1546/561529', 'The conservation of biodiversity', 'Paper', 'AECOM', 'science', '2011-06-12 16:02:39.000000',
        null, null, '0000-0329');
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.2384/23093', 'The discovery of a new distant galaxy', 'Digital', 'UPC', 'astrophysics',
        '1981-06-22 01:36:43.000000', 'Danone, 1706, Vintners  Drive,  0117 , Anaheim', 'Next galaxy', null);
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.4489/65722', 'The formation of stars and galaxies ', 'Audio', 'Erickson', 'astrophysics',
        '2007-08-31 18:37:00.000000', null, null, '0000-0205');
INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename,
                         fk_magazine)
VALUES ('10.1442/420908', 'New technologies for renewable energy', 'Audio', 'DynCorp', 'physics',
        '2005-06-06 19:28:36.000000', null, null, '0000-1236');