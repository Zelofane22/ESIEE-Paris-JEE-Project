package techsupport.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "requetes")
public class Requete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private String sujet;

    @Column(nullable = false, length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation", nullable = false, updatable = false)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut;

    // Enumération pour le statut de la requête (Nouvelle, En cours, Terminée)
    public enum Statut {
        NOUVELLE,
        EN_COURS,
        TERMINEE
    }

    @OneToMany(mappedBy = "requete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    // Constructeur par défaut requis par JPA
    public Requete() {
        this.dateCreation = new Date();  // Initialise la date de création à l'instance courante
        this.statut = Statut.NOUVELLE;   // Par défaut, le statut est "Nouvelle"
    }

    // Constructeur avec paramètres
    public Requete(Utilisateur utilisateur, String sujet, String description) {
        this.utilisateur = utilisateur;
        this.sujet = sujet;
        this.description = description;
        this.dateCreation = new Date();  // Date de création au moment de la requête
        this.statut = Statut.NOUVELLE;   // Statut initial par défaut
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
