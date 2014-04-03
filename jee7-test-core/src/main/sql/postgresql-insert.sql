BEGIN TRANSACTION;
--Clean up table data(truncate didn't work due to foreign key constraints)
DELETE FROM users_consoles;
DELETE FROM games_consoles;
DELETE FROM users_games;
DELETE FROM games_tags;
DELETE FROM users;
DELETE FROM consoles;
DELETE FROM games;
DELETE FROM developers;
DELETE FROM companies;
DELETE FROM tags;

--Insets to populate the tables with sample data
INSERT INTO users
     VALUES(DEFAULT, 'Bruce', 'Wayne', '17-AUG-85');

INSERT INTO developers
     VALUES(DEFAULT, 'KONAMI', 'Japan', 'http://www.konami.com');

INSERT INTO games
     VALUES('BLUS30109', 'Metal Gear Solid 4', 'Lead legendary hero Solid Snake in this final chapter of the Metal Gear Solid saga', 'BluRay', 2008, 849.00, 1, '4600 MB', 'M', 94, lastval());

INSERT INTO developers
     VALUES(DEFAULT, 'Sony Santa Monica Studio', 'United States', 'http://www.worldwidestudios.net/santamonica');

INSERT INTO games
     VALUES('BCUS98111', 'God  Of War III', 'In the end,  there will be only chaos', 'BluRay', 2010, 989.00, 1, '5 MB', 'M', 93, lastval());

INSERT INTO developers
     VALUES(DEFAULT, 'CAPCOM', 'Japan', 'http://capcom.com');

INSERT INTO games
     VALUES('SLUS21115', 'Okami', 'You possess the power of a god,  but face the world in the form of the wolf.', 'DVD', 2006, 232.60, 1, '170 KB', 'T', 93, lastval());

INSERT INTO users_games
     VALUES(currval('users_id_seq'), 'BLUS30109', '01-JUN-08', 'NEW', 'OPENED', false);

INSERT INTO users_games
     VALUES(currval('users_id_seq'), 'BCUS98111', '19-MAR-10', 'NEW', 'OPENED', false);

INSERT INTO users_games
     VALUES(currval('users_id_seq'), 'SLUS21115', '15-APR-10', 'NEW', 'BRANDNEW', true);

INSERT INTO tags
     VALUES(DEFAULT, 'exclusive');

INSERT INTO games_tags (game_code, tag_id)
     VALUES ('BLUS30109', lastval());

INSERT INTO games_tags (game_code, tag_id)
     VALUES ('BCUS98111', lastval());

INSERT INTO tags
     VALUES(DEFAULT, '1080p');

INSERT INTO games_tags (game_code, tag_id)
     VALUES ('BLUS30109', lastval());

INSERT INTO tags
     VALUES(DEFAULT, '720p');

INSERT INTO games_tags (game_code, tag_id)
     VALUES ('BCUS98111', lastval());

INSERT INTO companies
     VALUES (DEFAULT, 'Sony', 'Sony Computer Entertainment SCE', 'Japan', 'http://www.playstation.com');

INSERT INTO consoles
     VALUES (DEFAULT, 'PS2', 'PlayStation 2', NULL, 4000, 4, false, false, false, 'DVD', currval('companies_id_seq'));

INSERT INTO users_consoles
     VALUES (currval('users_id_seq'), currval('consoles_id_seq'), '1-MAY-01', 'NEW');

INSERT INTO games_consoles
     VALUES ('SLUS21115', currval('consoles_id_seq'));

INSERT INTO consoles
     VALUES (DEFAULT, 'PS3', 'PlayStation 3', NULL, 8000, 7, true, true, true, 'BluRay', currval('companies_id_seq'));

INSERT INTO users_consoles
     VALUES (currval('users_id_seq'), currval('consoles_id_seq'), '1-AUG-07', 'NEW');

INSERT INTO games_consoles
     VALUES ('BLUS30109', currval('consoles_id_seq'));

INSERT INTO games_consoles
     VALUES ('BCUS98111', currval('consoles_id_seq'));

COMMIT;
