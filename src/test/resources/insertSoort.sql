insert into soorten (naam, landid)
VALUES ('test', (select id from landen where landen.naam = 'test'));
insert into soorten (naam, landid)
VALUES ('test2', (select id from landen where landen.naam = 'test'));