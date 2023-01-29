create view mtl.bibliography as
select distinct b.Title,b.ReleaseDate,a.lname
from mtl.book b join mtl.author a on b.fk_author = a.codauthor
order by b.releasedate desc;

create view mtl.history as
select distinct a.fname, a.lname, ar.title,ar.releasedate,ar.editor
from mtl.author a join mtl.article ar on a.codauthor = ar.fk_author
order by ar.releasedate desc;

create view mtl.digital_goods as
select distinct b.title from mtl.book b where accessmode = 'Digital'
union
select distinct a.title from mtl.article a where accessmode = 'Digital'
union
select distinct m.name_m  from mtl.magazine m where accessmode = 'Digital'
union
select distinct s.name_s from mtl.series s join mtl.book b on s.issn_s = b.fk_series where b.accessmode='Digital';

create view mtl.paper_goods as
select distinct b.title from mtl.book b where accessmode = 'Paper'
union
select distinct a.title from mtl.article a where accessmode = 'Paper'
union
select distinct m.name_m  from mtl.magazine m where accessmode = 'Paper'
union
select distinct s.name_s from mtl.series s join mtl.book b on s.issn_s = b.fk_series where b.accessmode='Digital';

create view mtl.audio_goods as
select distinct b.title from mtl.book b where accessmode = 'Audio'
union
select distinct a.title from mtl.article a where accessmode = 'Audio'
union
select distinct m.name_m  from mtl.magazine m where accessmode = 'Audio'
union
select distinct s.name_s from mtl.series s join mtl.book b on s.issn_s = b.fk_series where b.accessmode='Digital';

create view mtl.presentation as
select b.title,a.fname,a.lname, b.presentationname, b.releaselocation, b.releasedate
from mtl.book b join mtl.author a on a.codauthor = b.fk_author;

create view mtl.discussion as
select ar.title,a.fname,a.lname, ar.conferencename, ar.releaselocation, ar.releasedate
from mtl.article ar join mtl.author a on a.codauthor = ar.fk_author
order by a.lname;