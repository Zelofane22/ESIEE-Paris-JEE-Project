package techsupport.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import techsupport.entity.Requete;
import techsupport.daos.RequeteDAO;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RequeteBean implements Serializable {
    private RequeteDAO requeteDAO = new RequeteDAO();
    private List<Requete> requetes;
    private Requete selectedRequete;
    private String reponse;

    public RequeteBean() {

        this.requetes = requeteDAO.recupererToutesLesRequetes();
    }


    public List<Requete> getRequetes() {
        return requetes;
    }


    public void voirDetails(Long id) {
        this.selectedRequete = requeteDAO.recupererRequeteParId(id.intValue());
    }

    public void repondreARequete() {
        if (selectedRequete != null) {
            selectedRequete.setStatut(Requete.Statut.EN_COURS);
            // Simuler l'ajout d'une réponse (par exemple, par un admin)
            // Cette méthode peut être enrichie pour envoyer un email
            requeteDAO.mettreAJourStatut(selectedRequete.getId().intValue(), Requete.Statut.EN_COURS);
            reponse = ""; // Réinitialiser le champ de réponse
        }
    }

    public void terminerRequete() {
        if (selectedRequete != null) {
            selectedRequete.setStatut(Requete.Statut.TERMINEE);
            requeteDAO.mettreAJourStatut(selectedRequete.getId().intValue(), Requete.Statut.TERMINEE);
        }
    }

    // Getters et Setters
    public Requete getSelectedRequete() {
        return selectedRequete;
    }

    public void setSelectedRequete(Requete selectedRequete) {
        this.selectedRequete = selectedRequete;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
