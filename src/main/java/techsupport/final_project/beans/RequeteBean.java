package techsupport.final_project.beans;

import java.util.Date;

public class RequeteBean {
    private int id;
    private String description;
    private StatutRequete statut; // "Nouvelle", "En cours", "Terminée"
    private Date dateCreation;

    // Constructeur par défaut
    public RequeteBean() {}

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatutRequete getStatut() {
        return statut;
    }

    public void setStatut(StatutRequete statut) {
        this.statut = statut;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
