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

CREATE OR REPLACE FUNCTION mtl.function_4()
    returnS TRIGGER AS
$$
declare
    isbn_app       mtl.book.isbn_b%TYPE;
    autori_record  mtl.author%ROWTYPE;
    cod_author_app mtl.author.codauthor%TYPE;
    cod_author CURSOR FOR SELECT codauthor
                          FROM mtl.author;
begin
    isbn_app := new.isbn_b;
    FOR autori_record IN cod_author
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


CREATE OR REPLACE FUNCTION mtl.function_5() returnS TRIGGER AS
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
            insert into mtl.author_article (AuthorsFK, ArticlesFK) values (cod_author_app, doi_app);
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
