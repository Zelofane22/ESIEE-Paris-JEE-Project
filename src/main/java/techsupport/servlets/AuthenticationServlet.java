package techsupport.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.entity.Utilisateur;
import techsupport.daos.UtilisateurDAO;

import java.io.IOException;

@WebServlet("/authentification")
public class AuthenticationServlet extends HttpServlet {

    UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        utilisateurDAO = new UtilisateurDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire de connexion
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Vérifier les informations d'identification de l'utilisateur
            Utilisateur utilisateur = utilisateurDAO.verifierIdentifiants(email, password);

            if (utilisateur != null) {
                // Stocker les informations de l'utilisateur dans la session
                request.getSession().setAttribute("utilisateurConnecte", utilisateur);

                // Rediriger en fonction du rôle de l'utilisateur
                if ("admin".equals(utilisateur.getRole())) {
                    response.sendRedirect("gestionRequetesAdmin");
                } else {
                    response.sendRedirect("mesRequetes");
                }
            } else {
                // Si les identifiants sont incorrects, renvoyer vers la page de connexion avec un message d'erreur
                request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } catch (IOException e) {
            throw new ServletException("Erreur lors de l'authentification de l'utilisateur", e);
        }
    }
}
