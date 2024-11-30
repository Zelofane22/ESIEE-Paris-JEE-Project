package techsupport.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Date;

@Named
@RequestScoped
public class RequeteBean {
    private Long id;
    private String description;
    private Date dateCreation;
    private StatutRequete statut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StatutRequete getStatut() {
        return statut;
    }

    public void setStatut(StatutRequete statut) {
        this.statut = statut;
    }
}
