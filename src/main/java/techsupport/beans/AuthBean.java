package techsupport.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named("authBean")
@SessionScoped
public class AuthBean implements Serializable {
    private UtilisateurBean utilisateur = new UtilisateurBean(); // Utilisateur connecté
    private boolean loggedIn = false;


    private static final Map<String, UtilisateurBean> utilisateurs = new HashMap<>();

    static {
        // Ajoute des utilisateurs pour les tests
        UtilisateurBean admin = new UtilisateurBean();
        admin.setId(1L);
        admin.setNom("Admin");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setRole("admin");

        UtilisateurBean user = new UtilisateurBean();
        user.setId(2L);
        user.setNom("User");
        user.setEmail("user@example.com");
        user.setPassword("user123");
        user.setRole("utilisateur");

        utilisateurs.put(admin.getEmail(), admin);
        utilisateurs.put(user.getEmail(), user);
    }

    public UtilisateurBean getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBean utilisateur) {
        this.utilisateur = utilisateur;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String login() {
        // Valider les identifiants
        UtilisateurBean utilisateurTrouve = utilisateurs.get(utilisateur.getEmail());
        if (utilisateurTrouve != null && utilisateurTrouve.getPassword().equals(utilisateur.getPassword())) {
            this.utilisateur = utilisateurTrouve;
            this.loggedIn = true;

            // Redirection basée sur le rôle
            if ("admin".equals(utilisateurTrouve.getRole())) {
                return "admin.xhtml?faces-redirect=true";
            } else {
                return "user.xhtml?faces-redirect=true";
            }
        }

        // message d'erreur si échec de connexion
        jakarta.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                new jakarta.faces.application.FacesMessage(jakarta.faces.application.FacesMessage.SEVERITY_ERROR, "Erreur", "Identifiants incorrects."));
        return null;
    }

    public String logout() {
        // Réinitialiser la session
        jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
}
