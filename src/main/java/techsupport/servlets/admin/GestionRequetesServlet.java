package techsupport.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.entity.Requete;
import techsupport.entity.Utilisateur;
import techsupport.daos.RequeteDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/jsp/adminDashboard")
public class GestionRequetesServlet extends HttpServlet {

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

        if (admin == null || !"admin".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
            case "update":
                try {
                    // Récupérer l'ID de la requête
                    int id = Integer.parseInt(request.getParameter("id"));

                    // Récupérer le statut sous forme de chaîne
                    String nouveauStatutStr = request.getParameter("statut");

                    if (nouveauStatutStr != null) {
                        // Convertir la chaîne en enum
                        try {
                            Requete.Statut nouveauStatut = Requete.Statut.valueOf(nouveauStatutStr.toUpperCase());
                            requeteDAO.mettreAJourStatut(id, nouveauStatut);
                        } catch (IllegalArgumentException e) {
                            // Gestion des statuts invalides
                            System.err.println("Statut invalide : " + nouveauStatutStr);
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Statut invalide.");
                        }
                    }
                } catch (NumberFormatException e) {
                    // Gestion des ID non valides
                    System.err.println("ID non valide : " + request.getParameter("id"));
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valide.");
                }
                break;
            case "delete":
                int idToDelete = Integer.parseInt(request.getParameter("id"));
                requeteDAO.supprimerRequete(idToDelete);
                break;
            case "respond":
                int idToRespond = Integer.parseInt(request.getParameter("id"));
                String reponse = request.getParameter("reponse");
                if (reponse != null) {
                    // Envoyer une réponse à l'utilisateur (par exemple, via email)
                    Requete requete = requeteDAO.recupererRequeteParId(idToRespond);
                    envoyerReponseParEmail(requete, reponse);
                }
                break;
            default:
                break;
        }
        response.sendRedirect("gestionRequetesAdmin");
    }

    private void envoyerReponseParEmail(Requete requete, String reponse) {
        // Implémentation de l'envoi de mail ici (par exemple, utiliser JavaMail API)
    }
}
