package techsupport.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.io.IOException;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private String email;
    private String password;
    private boolean isAdminLoggedIn;

    // Méthode pour la connexion
    public String login() {
        if ("admin@example.com".equals(email) && "admin123".equals(password)) {
            isAdminLoggedIn = true;
            return "adminDashboard.xhtml?faces-redirect=true"; // Redirection vers le tableau de bord
        } else {
            isAdminLoggedIn = false;
            return null; // Rester sur la page de connexion
        }
    }

    // Méthode pour vérifier l'accès
    public void checkAdminAccess() throws IOException {
        if (!isAdminLoggedIn) {
            jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().redirect("loginAdmin.xhtml");
        }
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }

    public void setAdminLoggedIn(boolean adminLoggedIn) {
        isAdminLoggedIn = adminLoggedIn;
    }
}
