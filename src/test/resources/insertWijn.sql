insert into wijnen (soortid, jaar, beoordeling, prijs, inBestelling)
VALUES ((select id from soorten where naam= 'test'), 2020, 1, 10, 1);