package techsupport.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "requete_id", nullable = false)
    private Requete requete;

    @Column(nullable = false)
    private String auteur; // 'ADMIN' ou l'email de l'utilisateur

    @Column(nullable = false)
    private String contenu;

    @Column(name = "date_creation", nullable = false)
    private Timestamp dateCreation = new Timestamp(System.currentTimeMillis());

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Requete getRequete() {
        return requete;
    }

    public void setRequete(Requete requete) {
        this.requete = requete;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}

