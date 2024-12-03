package techsupport.servlets.users;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.entity.Utilisateur;
import techsupport.daos.UtilisateurDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/utilisateur")
public class GestionUtilisateurServlet extends HttpServlet {

    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        utilisateurDAO = new UtilisateurDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "update":
                    mettreAJourUtilisateur(request, response);
                    break;
                case "delete":
                    supprimerUtilisateur(request, response);
                    break;
                default:
                    response.sendRedirect("login.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la gestion de l'utilisateur", e);
        }
    }

    private void mettreAJourUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        // Récupérer l'utilisateur connecté
        Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (utilisateurConnecte == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Récupérer les nouvelles informations du formulaire
        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String nouveauMotDePasse = request.getParameter("nouveauPassword");

        if (email == null || nom == null || email.isEmpty() || nom.isEmpty()) {
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("/profil.jsp").forward(request, response);
            return;
        }

        utilisateurConnecte.setEmail(email);
        utilisateurConnecte.setNom(nom);

        if (nouveauMotDePasse != null && !nouveauMotDePasse.isEmpty()) {
            utilisateurConnecte.setPassword(nouveauMotDePasse);
        }

        // Mettre à jour l'utilisateur dans la base de données
        utilisateurDAO.mettreAJourUtilisateur(utilisateurConnecte);

        // Mise à jour réussie
        request.setAttribute("successMessage", "Vos informations ont été mises à jour avec succès.");
        request.getRequestDispatcher("/profil.jsp").forward(request, response);
    }

    private void supprimerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        // Récupérer l'utilisateur connecté
        Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (utilisateurConnecte == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Supprimer l'utilisateur de la base de données
        utilisateurDAO.supprimerUtilisateur(utilisateurConnecte.getId());

        // Invalider la session après la suppression
        request.getSession().invalidate();

        // Rediriger vers la page d'accueil avec un message de confirmation
        request.setAttribute("successMessage", "Votre compte a été supprimé avec succès.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
