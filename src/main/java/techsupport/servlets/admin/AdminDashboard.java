package techsupport.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.daos.MessageDAO;
import techsupport.entity.Requete;
import techsupport.entity.Utilisateur;
import techsupport.daos.RequeteDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/jsp/adminDashboard")
public class AdminDashboard extends HttpServlet {

    private RequeteDAO requeteDAO;

    @Override
    public void init() throws ServletException {
        requeteDAO = new RequeteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // Récupérer l'administrateur connecté
        Utilisateur admin = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (admin == null || !"ADMIN".equals(admin.getRole().toString())) {
            response.sendRedirect("login.jsp");
            return;
        }

        // l'admin doit aussi récupérer une requête par id

        List<Requete> requetes = requeteDAO.recupererToutesLesRequetes();
        request.setAttribute("requetes", requetes);
        request.getRequestDispatcher("/gestionRequetesAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'administrateur connecté
        Utilisateur admin = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (admin == null || !"admin".equals(admin.getRole().toString())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("updateStatut".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nouveauStatut = request.getParameter("nouveauStatut");
                requeteDAO.mettreAJourStatut(id, Requete.Statut.valueOf(nouveauStatut));
                request.setAttribute("successMessage", "Statut mis à jour avec succès !");
            } else if ("repondre".equals(action)) {
                int requeteId = Integer.parseInt(request.getParameter("requeteId"));
                String contenu = request.getParameter("message");
                MessageDAO messageDAO = new MessageDAO();
                messageDAO.ajouterMessage(requeteId, "ADMIN", contenu);
                request.setAttribute("successMessage", "Réponse envoyée avec succès !");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Une erreur est survenue : " + e.getMessage());
        }

        doGet(request, response);
    }
}

