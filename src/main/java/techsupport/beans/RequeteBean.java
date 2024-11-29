package techsupport.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import techsupport.daos.RequeteDAO;
import techsupport.entity.Requete;

import java.util.List;

@Named("requeteBean")
@RequestScoped
public class RequeteBean {
    private final RequeteDAO requeteDAO = new RequeteDAO();
    private Requete requete = new Requete();

    public Requete getRequete() {
        return requete;
    }

    public void setRequete(Requete requete) {
        this.requete = requete;
    }

    public List<Requete> getToutesLesRequetes() {
        return requeteDAO.recupererToutesLesRequetes();
    }

    public void ajouterRequete() {
        requeteDAO.ajouterRequete(requete);
        requete = new Requete(); // Réinitialiser après ajout
    }
}
