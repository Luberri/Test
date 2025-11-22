CREATE DATABASE IF NOT EXISTS sprint;
USE sprint;
-- Création de la table etudiant
CREATE TABLE etudiant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    date_naissance DATE,
    numero_etudiant VARCHAR(20) UNIQUE,
    promotion VARCHAR(50),
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index pour améliorer les performances de recherche
CREATE INDEX idx_etudiant_nom ON etudiant(nom);
CREATE INDEX idx_etudiant_email ON etudiant(email);

-- Insertion de données de test
INSERT INTO etudiant (nom, prenom, email, date_naissance, numero_etudiant, promotion) VALUES
('Rakoto', 'Jean', 'jean.rakoto@example.com', '2000-05-15', 'ETU001', 'L3'),
('Rasoa', 'Marie', 'marie.rasoa@example.com', '2001-08-22', 'ETU002', 'L2'),
('Rabe', 'Paul', 'paul.rabe@example.com', '1999-12-10', 'ETU003', 'M1');

-- Affichage des données insérées
SELECT * FROM etudiant;