package techsupport.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.entity.Utilisateur;
import techsupport.daos.UtilisateurDAO;

import java.io.IOException;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {

    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        utilisateurDAO = new UtilisateurDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'action (connexion ou enregistrement)
        String action = request.getParameter("action");

        if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else if ("login".equalsIgnoreCase(action)) {
            handleLogin(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non reconnue.");
        }
    }

    /**
     * Gère l'enregistrement d'un nouvel utilisateur.
     */
    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Vérifier si l'email existe déjà
            if (utilisateurDAO.emailExiste(email)) {
                request.setAttribute("errorMessage", "Cet email est déjà utilisé.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

            // Créer un nouvel utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);
            utilisateur.setPassword(password); // Remplacer par un hachage dans le futur

            // Enregistrer l'utilisateur dans la base
            utilisateurDAO.ajouterUtilisateur(utilisateur);

            // Rediriger avec un message de succès
            request.setAttribute("successMessage", "Inscription réussie ! Veuillez vous connecter.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Une erreur est survenue lors de l'inscription.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    /**
     * Gère la connexion d'un utilisateur.
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Vérifier les informations d'identification de l'utilisateur
            Utilisateur utilisateur = utilisateurDAO.verifierIdentifiants(email, password);

            if (utilisateur != null) {
                // Stocker les informations de l'utilisateur dans la session
                request.getSession().setAttribute("utilisateurConnecte", utilisateur);

                // Rediriger en fonction du rôle de l'utilisateur
                if ("ADMIN".equalsIgnoreCase(utilisateur.getRole().toString())) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("mes_requetes.jsp");
                }
            } else {
                // Identifiants incorrects
                request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'authentification de l'utilisateur", e);
        }
    }
}
