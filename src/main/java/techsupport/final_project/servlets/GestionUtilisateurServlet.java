package techsupport.final_project.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import techsupport.final_project.beans.UtilisateurBean;
import techsupport.final_project.daos.UtilisateurDAO;

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
                case "create":
                    creerUtilisateur(request, response);
                    break;
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

    private void creerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        // Récupérer les paramètres du formulaire de création de compte
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");

        // Validation des données du formulaire
        if (email == null || password == null || nom == null ||
                email.isEmpty() || password.isEmpty() || nom.isEmpty()) {
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("/inscription.jsp").forward(request, response);
            return;
        }

        // Hacher le mot de passe avant de le sauvegarder
        String hashedPassword = DigestUtils.sha256Hex(password);

        // Créer un nouvel utilisateur
        UtilisateurBean utilisateur = new UtilisateurBean();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(hashedPassword);
        utilisateur.setRole("utilisateur");

        // Enregistrer l'utilisateur dans la base de données
        utilisateurDAO.ajouterUtilisateur(utilisateur);

        // Rediriger vers la page de connexion avec un message de succès
        request.setAttribute("successMessage", "Votre compte a été créé avec succès. Veuillez vous connecter.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private void mettreAJourUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        // Récupérer l'utilisateur connecté
        UtilisateurBean utilisateurConnecte = (UtilisateurBean) request.getSession().getAttribute("utilisateurConnecte");

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
            String hashedPassword = DigestUtils.sha256Hex(nouveauMotDePasse);
            utilisateurConnecte.setPassword(hashedPassword);
        }

        // Mettre à jour l'utilisateur dans la base de données
        utilisateurDAO.mettreAJourUtilisateur(utilisateurConnecte);

        // Mise à jour réussie
        request.setAttribute("successMessage", "Vos informations ont été mises à jour avec succès.");
        request.getRequestDispatcher("/profil.jsp").forward(request, response);
    }

    private void supprimerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        // Récupérer l'utilisateur connecté
        UtilisateurBean utilisateurConnecte = (UtilisateurBean) request.getSession().getAttribute("utilisateurConnecte");

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
