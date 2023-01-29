create or replace function mtl.function_1() returns trigger as
$$
declare
    stringa_in   varchar(13) = new.isbn_b;
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
        delete from mtl.book where doi_b = new.doi_b;
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
    raise notice 'stringa in: %', stringa_in;
    stringa_in := replace(stringa_in, '-', '');
    raise notice 'stringa in: %', stringa_in;
    for i in 1..8
        loop
        raise notice 'i: %', i;
            if substr(stringa_in, 8, 1) = 'X' then
                sum = sum + 10;
                raise notice 'sum: %', sum;
                var_appoggio = cast(substring(stringa_in from i for 1) as int);
                if (i = 8) then
                    sum = sum + 0;
                    raise notice 'sum: %', sum;
                else
                    sum := sum + var_appoggio * (9 - i);
                    raise notice 'sum: %', sum;
                end if;
            else
                var_appoggio = cast(substring(stringa_in from i for 1) as int);
                sum := sum + var_appoggio * (9 - i);
            end if;
        end loop;
    resto = sum % 11;
    raise notice 'resto: %', resto;
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
            end if;
            var_appoggio = cast(substring(stringa_in from i for 1) as int);
            if (i = 8) then
                sum = sum + 0;
            else
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


<