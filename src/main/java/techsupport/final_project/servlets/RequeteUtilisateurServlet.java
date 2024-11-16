package techsupport.final_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.final_project.beans.RequeteBean;
import techsupport.final_project.beans.StatutRequete;
import techsupport.final_project.beans.UtilisateurBean;
import techsupport.final_project.daos.RequeteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/requeteUtilisateur")
public class RequeteUtilisateurServlet extends HttpServlet {

    private RequeteDAO requeteDAO;

    @Override
    public void init() throws ServletException {
        requeteDAO = new RequeteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur connecté
        UtilisateurBean utilisateur = (UtilisateurBean) request.getSession().getAttribute("utilisateurConnecte");

        if (utilisateur == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<RequeteBean> requetes = requeteDAO.trouverRequeteParId(utilisateur.getId());
        request.setAttribute("requetes", requetes);
        request.getRequestDispatcher("/gestionRequetesUtilisateur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur connecté
        UtilisateurBean utilisateur = (UtilisateurBean) request.getSession().getAttribute("utilisateurConnecte");

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
                        RequeteBean nouvelleRequete = new RequeteBean();
                        nouvelleRequete.setDescription(description);
                        nouvelleRequete.setStatut(StatutRequete.NOUVELLE);
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
