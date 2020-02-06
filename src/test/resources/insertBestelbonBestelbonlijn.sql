insert into bestelbonlijnen (bonid, wijnid, aantal, prijs)
VALUES ((select id from bestelbonnen where naam= 'test'), (select id from wijnen where jaar= 2020),
        1, 10);