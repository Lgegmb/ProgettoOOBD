insert into mtl.series(issn_s, curator, edition, code_s, name_s)
VALUES  ('0000-0051', 'curator', 2, 'code', 'name');

UPDATE mtl.series SET curator = 'curator', edition = 2, code_s = 'code', name_s = 'name' WHERE issn_s = '0000-0051';