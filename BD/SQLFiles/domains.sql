create domain issn as varchar(9)
check ( value like '[0-9]-[0-9]' );

create domain isbn as varchar(17)
check ( value like '%-_-%-%-_');

create domain doi as varchar(30)
check ( value like'10.%/%');

create domain access as varchar(20)
check ( value <> '' and value not similar to '%[0-9]+%'
        and value not similar to '%[@!#$ˆ∗%&]+%');

create domain names as varchar(30)
check ( value not similar to '%[@!#$ˆ∗%&]+%');

create domain location as varchar(50)
check ( value like '%,%,%,%,%');