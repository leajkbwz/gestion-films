INSERT INTO realisateurs (nom) VALUES ('Christopher Nolan');
INSERT INTO realisateurs (nom) VALUES ('Quentin Tarantino');
INSERT INTO films (titre, annee, duree_minutes, realisateur_id)
  VALUES ('Inception', 2010, 148, (SELECT id FROM realisateurs WHERE nom='Christopher Nolan'));
INSERT INTO films (titre, annee, duree_minutes, realisateur_id)
  VALUES ('Interstellar', 2014, 169, (SELECT id FROM realisateurs WHERE nom='Christopher Nolan'));
INSERT INTO films (titre, annee, duree_minutes, realisateur_id)
  VALUES ('Pulp Fiction', 1994, 154, (SELECT id FROM realisateurs WHERE nom='Quentin Tarantino'));
