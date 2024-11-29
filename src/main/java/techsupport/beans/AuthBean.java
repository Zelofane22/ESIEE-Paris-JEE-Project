package techsupport.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import techsupport.entity.Utilisateur;

import java.io.Serializable;

@Named("authBean") // Permet à JSF de trouver le bean par "#{authBean}"
@SessionScoped    // Garde les données dans la session utilisateur
public class AuthBean implements Serializable {
    private Utilisateur utilisateur = new Utilisateur();

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String login() {
        // Logique de connexion
        return "home.xhtml";
    }
}
