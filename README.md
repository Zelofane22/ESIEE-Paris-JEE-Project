Créez manuellement la base de données et les tables avant d'exécuter le projet
```
CREATE DATABASE support_technique;
CREATE TABLE IF NOT EXISTS utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
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
INSERT INTO utilisateurs (nom, email, mot_de_passe, role)
VALUES ('Admin Test', 'admin@example.com', 'hashed_password', 'ADMIN');
```
