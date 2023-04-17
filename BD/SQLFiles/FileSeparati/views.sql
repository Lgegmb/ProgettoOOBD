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