Créez manuellement la base de données et les tables avant d'exécuter le projet
```
CREATE DATABASE support_technique;
CREATE TABLE IF NOT EXISTS utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'UTILISATEUR') DEFAULT 'UTILISATEUR' NOT NULL
);
CREATE TABLE IF NOT EXISTS requetes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT NOT NULL,
    sujet VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    statut ENUM('NOUVELLE', 'EN_COURS', 'TERMINEE') DEFAULT 'NOUVELLE',
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);
INSERT INTO utilisateurs (nom, email, password, role)
VALUES ('Admin Test', 'admin@example.com', 'admin', 'ADMIN');
CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    requete_id INT NOT NULL,
    auteur VARCHAR(255) NOT NULL, -- 'ADMIN' ou l'email de l'utilisateur
    contenu TEXT NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (requete_id) REFERENCES requetes(id)
);

ALTER TABLE utilisateurs
MODIFY COLUMN role ENUM('ADMIN', 'UTILISATEUR') NOT NULL DEFAULT 'UTILISATEUR';

```
