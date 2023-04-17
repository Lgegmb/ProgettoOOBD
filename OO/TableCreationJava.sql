drop schema if exists mtl cascade;
create schema mtl;

create domain mtl.issn as varchar(9)
    check ( value like '%-%'
        and value not similar to '%[a-z]+%'
        and value not similar to '%[A-Z]+%'
        and value not similar to '%[@!#$ˆ∗%&]+%');

create domain mtl.isbn as varchar(17)
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
    LName     varchar(20),

    constraint AuthorPK primary key (CodAuthor)
);

create table mtl.series
(
    ISSN_S  mtl.issn,
    Curator mtl.names,
    Edition int,
    Code_S  varchar(10) unique,
    Name_S  mtl.names,

    constraint SeriesPK primary key (ISSN_S)
);

create table mtl.magazine
(
    ISSN_M            mtl.issn,
    Name_M            mtl.names,
    Argument          mtl.names,
    Manager           mtl.names,
    YearRelease       timestamp,
    PublicationPeriod mtl.names,
    AccessMode        mtl.access,
    PublishingHouse   mtl.names,

    constraint MagazinePk primary key (ISSN_M)
);


create table mtl.book
(
    ISBN_B           mtl.isbn
        unique,
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

    constraint BookPk primary key (ISBN_B),
    constraint BookFK_3 foreign key (FK_Series) references mtl.Series (ISSN_S) on delete set null
);

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

    constraint ArticlePK primary key (Doi_A),
    constraint ArticleFK_2 foreign key (FK_Magazine) references mtl.Magazine (ISSN_M) on delete set null,
    check ((FK_Magazine is not null and (ConferenceName is null and ReleaseLocation is null)) OR
           (FK_Magazine is null and (ConferenceName is not null and ReleaseLocation is not null)))
);

create table mtl.author_books
(
    AuthorsFK serial,
    BooksFK  mtl.isbn,

    constraint autori_libriFK_1 foreign key (AuthorsFK) references mtl.author (codauthor) on delete cascade,
    constraint autori_libriFK_2 foreign key (BooksFK) references mtl.book (ISBN_B) on delete cascade
);

create table mtl.author_article
(
    AuthorsFK   serial,
    ArticlesFK mtl.doi,

    constraint autori_libriFK_1 foreign key (AuthorsFK) references mtl.author (codauthor) on delete cascade,
    constraint autori_libriFK_2 foreign key (ArticlesFK) references mtl.article (Doi_A) on delete cascade
);

create view mtl.presentation as
select b.title, a.fname, a.lname, b.presentationname, b.releaselocation, b.releasedate
from (mtl.book b join mtl.author_books au on au.BooksFK = b.ISBN_B) join mtl.author a on a.codauthor=au.AuthorsFK
where presentationname is not null and releaselocation is not null
order by lname;

create view mtl.conference as
select a.title, ar.fname, ar.lname, a.conferencename, a.releaselocation, a.releasedate
from (mtl.article a join mtl.author_article au on  au.ArticlesFK = a.doi_a) join mtl.author ar on au.AuthorsFK = ar.codauthor
where a.conferencename is not null and releaselocation is not null
order by lname;


INSERT INTO mtl.author (fname, lname)
VALUES ('Federico', 'Rossi'),
       ('Laura', 'Bianchi'),
       ('Marco', 'Neri'),
       ('Sara', 'Giordano'),
       ('Anna', 'Giordano'),
       ('Marco', 'Bianchi'),
       ('Laura', 'Ferrari'),
       ('Francesco', 'Verdi'),
       ('Chiara', 'Esposito'),
       ('Federica', 'Romano'),
       ('Joanne', 'Rowling '),
       ('Andrzej ', 'Sapkowski'),
       ('Ludovico', 'Lanz'),
       ('Andrea', 'Lacaita'),
       ('National', 'Geographic');


INSERT INTO mtl.magazine (issn_m, name_m, argument, manager, yearrelease, publicationperiod, accessmode,
                          publishinghouse)
VALUES ('0000-0205', 'The Lancet', 'medicine', 'Denny Stevens', '2008-09-28 14:03:14.000000', 'Yearly', 'Paper',
        'Global Print'),
       ('0000-1236', 'IEEE Transactions on Electrica', 'elettric engineering', 'Eve Hobbs',
        '1994-04-22 11:23:33.000000', 'Every two months', 'Digital', 'Apple Inc.'),
       ('0000-0329', 'To the Lighthouse', 'physics', 'Maia Vernon', '2009-12-11 06:32:50.000000', 'Every six months',
        'Paper', 'Global Print'),
       ('0000-0337', 'ACM Transactions on Sensor Net', 'sensor networks', 'Peter Doherty', '2007-05-13 15:21:53.000000',
        'Monthly', 'Paper', 'UPC'),
       ('0000-0078', 'Inorganic Chemistry', 'inorganic chemistry', 'Kamila Hopkins', '2008-04-14 06:23:34.000000',
        'Every three months', 'Paper', 'AECOM'),
       ('0000-0086', 'The Astronomical Journal', 'astrophysics', 'Owen Rycroft', '2019-05-12 17:08:54.000000', 'Daily',
        'Audio', 'Metro Cash Carry'),
       ('0000-0280', 'Chemical Reviews', 'chemistry', 'Chris Edley', '2000-09-17 21:07:24.000000', 'Every nine months',
        'Paper', 'UPC'),
       ('0000-0094', 'The Plant Cell', 'biology', 'Johnny Mason', '2019-01-14 06:40:51.000000', 'Every five months',
        'Audio', 'Global Print'),
       ('0000-2372', 'Physical Review A', 'physics', 'Callie Underhill', '1981-01-10 06:00:22.000000', 'Weekly',
        'Audio', 'Apple Inc.'),
       ('0000-3247', 'PLOS ONE', 'science', 'Colleen Nobbs', '2008-12-25 04:18:28.000000', 'Yearly', 'Paper', 'AECOM');


INSERT INTO mtl.series (issn_s, curator, edition, code_s, name_s)
VALUES ('3252-5788', 'Ludovico Lanz', 3, '1', 'Introduction to physic theory'),
       ('1233-3980', 'Andrea L. Lacaita', 1, '2', 'Applied electronic lessons'),
       ('4213-8973', 'Remigio Ruggeri', 2, '3', 'Industrial logistic'),
       ('7402-7883', 'Franco Cariati', 1, '4', 'Analityc chemistry lessons'),
       ('0870-5429', 'National geographic channel', 1, '5', 'Grande Atlante'),
       ('2494-9000', 'Mondadori', 1, '6', 'Piero Angela'),
       ('2143-2139', 'J. K. Rowling', 1, '7', 'Harry Potter'),
       ('0000-0361', 'Andrzej Sapkowski', 2, '8', 'The Witcher'),
       ('0000-3336', 'Maya Jenkins', 4, '9', 'To the Lighthouse'),
       ('0000-8362', 'Cassidy Glass', 3, '10', 'To the infinity and other');


INSERT INTO mtl.book (isbn_b, publishinghouse, language, accessmode, title, argument, reprint, edition,
                      releasedate, releaselocation, presentationname, fk_series)VALUES
       ( '322-4-267037-81-7', 'Salani', 'Spanish', 'Digital', 'La piedra filosofal', 'fantasy novel',true, 6, '1998-12-17 12:26:36.000000', 'DynCorp, Bede  Grove, 308, San Diego, 1347', 'La piedra filosofal','2143-2139'),
       ( '832-5-458306-85-3', 'Salani', 'French', 'Audio', 'La chambre des secrets', 'fantasy novel',false, 8, '1999-02-08 14:45:52.000000', 'Zepter, Thomas  Tunnel, 4547, Milwaukee,  3883','La chambre des secrets','2143-2139'),
       ( '566-5-456840-10-8', 'Salani', 'German', 'Digital', 'Der Gefangene von Askaban','fantasy novel', true, 3, '2000-08-24 03:10:40.000000', 'It Smart Group, Pine Avenue, 4492, Milwaukee, 8877','Der Gefangene von Askaban','2143-2139'),
       ( '183-1-315581-30-7', 'Nord', 'Italian', 'Paper', 'Il guardiano degli innocenti','fantasy novel', true, 3, '1985-05-11 00:03:00.000000', 'BuzzFeed, Rivervalley Vale, 3636, Cincinnati, 7516','The Witcher','0000-0361'),
       ( '257-1-281344-71-5', 'Nord', 'Italian', 'Digital', 'La spada del destino', 'fantasy novel',false, 6, '1988-07-29 22:13:05.000000', 'Erickson, Chester Crossroad, 4461, New Orleans,686', 'The Witcher','0000-0361'),
       ( '556-8-261575-08-1', 'National Geographic', 'English', 'Paper', 'The American Continent','geography', true, 9, '2003-11-27 14:21:48.000000', 'Comodo, Tiptree   Rue, 9695, Minneapolis, 1578','World Geography','0870-5429'),
       ( '731-4-067801-02-0', 'Hoepli', 'German', 'Paper', 'Introduction to circuits','applied electronic', false, 8, '2018-04-04 17:48:03.000000', 'ExxonMobil, Bagford   Lane, 7740, Lincoln, 6618','Electronic world','1233-3980'),
       ( '317-8-028670-00-4', 'Hoepli', 'French', 'Digital', 'Power electronics', 'applied electronic',false, 2, '2016-03-15 08:12:19.000000', 'Vodafone, Wakley   Drive, 1288, Denver, 2336', 'Electronic world','1233-3980'),
       ( '764-1-124064-70-1', 'BuzzFeed', 'English', 'Digital', 'Fundamentals of mechanics', 'physics',true, 2, '2001-04-20 01:07:51.000000', 'ExxonMobil, Tilson  Route, 8721, Hollywood, 1805', 'Physics discussion','3252-5788'),
       ( '010-7-624775-72-1', 'BuzzFeed', 'English', 'Digital', 'Concepts of electromagnetism','physics', false, 7, '2000-07-22 18:10:00.000000', 'BuzzFeed, Rivervalley Vale, 3636, Cincinnati, 7516','Physics discussion','3252-5788');

INSERT INTO mtl.article (doi_a, title, accessmode, editor, topic, releasedate, releaselocation, conferencename, fk_magazine)VALUES
       ('10.5915/96618', 'The structure of written graphene-based ', 'Paper', 'Facebook', 'physics','2012-12-07 13:32:33.000000',null,null,'0000-3247'),
       ('10.4339/03746', 'The effectiveness of flu vaccines', 'Digital', 'Zanichelli', 'medicine','2016-12-10 10:08:28.000000', null,null,'0000-1236'),
       ('10.1232/361857', 'The identification of a new regulating m', 'Audio', 'Boeing', 'chemestry','2018-06-02 02:05:17.000000', 'Danone, 7704,Meadow Crossroad ,  0613 ,Madrid', 'The cell growth',null),
       ('10.6923/55729', 'The results of a clinical trial of a new', 'Digital', 'Coca-Cola Company', 'medicine','2015-04-21 20:29:05.000000', 'Demaco, 470, Liverpool  Drive ,  5520 ,Zurich', 'Type 2 diabetes',null),
       ('10.1668/223239', 'Data analysis of a survey on lifestyles ', 'Digital', 'Team Guard SRL', 'science','2014-05-15 20:49:52.000000', null,null,'0000-0337'),
       ('10.1546/561529', 'The conservation of biodiversity', 'Paper', 'AECOM', 'science', '2011-06-12 16:02:39.000000',null, null,'0000-0329'),
       ('10.2384/23093', 'The discovery of a new distant galaxy', 'Digital', 'UPC', 'astrophysics','1981-06-22 01:36:43.000000', 'Danone, 1706, Vintners  Drive,  0117 , Anaheim', 'Next galaxy',null),
       ('10.4489/65722', 'The formation of stars and galaxies ', 'Audio', 'Erickson', 'astrophysics','2007-08-31 18:37:00.000000', 'Vodafone, 3228,Cingworth  Drive , 5753 ,Nashville', 'Stars and galxies',null),
       ('10.1442/420908', 'New technologies for renewable energy', 'Audio', 'DynCorp', 'physics','2005-06-06 19:28:36.000000', null , null,'0000-1236');


INSERT INTO mtl.author_books(authorsfk, booksfk) VALUES (1, '317-8-028670-00-4'),
(2, '317-8-028670-00-4'),
(3, '832-5-458306-85-3'),
(4, '317-8-028670-00-4'),
(5, '257-1-281344-71-5'),
(6, '317-8-028670-00-4'),
(7, '317-8-028670-00-4'),
(8, '731-4-067801-02-0'),
(9, '764-1-124064-70-1'),
(10, '010-7-624775-72-1');


INSERT INTO mtl.author_article(authorsfk, articlesfk) VALUES (2, '10.5915/96618'),
(3, '10.5915/96618'),
(4, '10.1442/420908'),
(5, '10.5915/96618'),
(6, '10.5915/96618'),
(7, '10.5915/96618'),
(8, '10.5915/96618'),
(9, '10.5915/96618'),
(10, '10.5915/96618'),
(11, '10.4489/65722'),
(12, '10.1668/223239');


