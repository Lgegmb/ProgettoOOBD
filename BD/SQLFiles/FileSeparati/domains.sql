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