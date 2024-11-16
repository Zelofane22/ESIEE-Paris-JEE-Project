package techsupport.servlets.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.entity.Requete;
import techsupport.entity.Utilisateur;
import techsupport.daos.RequeteDAO;

import java.io.IOException;
import java.util.Date;

@WebServlet("/mesRequetes")
public class GestionRequeteUtilisateurServlet extends HttpServlet {

    private RequeteDAO requeteDAO;

    @Override
    public void init() throws ServletException {
        requeteDAO = new RequeteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur connecté
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (utilisateur == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // l'utilisateur doit pouvoir afficher la liste de ses requetes

        Requete requete = requeteDAO.recupererRequeteParId(utilisateur.getId());
        request.setAttribute("requete", requete);
        request.getRequestDispatcher("/gestionRequetesUtilisateur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur connecté
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (utilisateur == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    String description = request.getParameter("description");
                    if (description != null && !description.trim().isEmpty()) {
                        Requete nouvelleRequete = new Requete();
                        nouvelleRequete.setDescription(description);
                        nouvelleRequete.setStatut(Requete.Statut.NOUVELLE);
                        nouvelleRequete.setDateCreation(new Date());
                        requeteDAO.ajouterRequete(nouvelleRequete);
                    }
                    break;
                case "delete":
                    int idToDelete = Integer.parseInt(request.getParameter("id"));
                    if (requeteDAO.verifierProprieteRequete(idToDelete, utilisateur.getId())) {
                        requeteDAO.supprimerRequete(idToDelete);
                    }
                    break;
                default:
                    break;
            }
            response.sendRedirect("requeteUtilisateur");
        } catch (IOException e) {
            throw new ServletException("Erreur lors de la gestion des requêtes", e);
        }
    }
}
