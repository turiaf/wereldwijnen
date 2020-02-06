insert into soorten (naam, landid)
VALUES ('test', (select id from landen where landen.naam = 'test'));