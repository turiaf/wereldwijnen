insert into wijnen (soortid, jaar, beoordeling, prijs, inBestelling)
VALUES ((select id from soorten where naam= 'test'), 2020, 1, 10, 1);
insert into wijnen (soortid, jaar, beoordeling, prijs, inBestelling)
VALUES ((select id from soorten where naam= 'test2'), 2022, 2, 10, 2);