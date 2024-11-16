package techsupport.final_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import techsupport.final_project.beans.RequeteBean;
import techsupport.final_project.beans.UtilisateurBean;
import techsupport.final_project.daos.RequeteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/gestionRequetesAdmin")
public class GestionRequetesServlet extends HttpServlet {

    private RequeteDAO requeteDAO;

    @Override
    public void init() throws ServletException {
        requeteDAO = new RequeteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // Récupérer l'administrateur connecté
        UtilisateurBean admin = (UtilisateurBean) request.getSession().getAttribute("utilisateurConnecte");

        if (admin == null || !"admin".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<RequeteBean> requetes = requeteDAO.recupererToutesLesRequetes();
        request.setAttribute("requetes", requetes);
        request.getRequestDispatcher("/gestionRequetesAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'administrateur connecté
        UtilisateurBean admin = (UtilisateurBean) request.getSession().getAttribute("utilisateurConnecte");

        if (admin == null || !"admin".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                String nouveauStatut = request.getParameter("statut");
                if (nouveauStatut != null) {
                    requeteDAO.mettreAJourStatut(id, nouveauStatut);
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
                    RequeteBean requete = requeteDAO.recupererRequeteParId(idToRespond);
                    envoyerReponseParEmail(requete, reponse);
                }
                break;
            default:
                break;
        }
        response.sendRedirect("gestionRequetesAdmin");
    }

    private void envoyerReponseParEmail(RequeteBean requete, String reponse) {
        // Implémentation de l'envoi de mail ici (par exemple, utiliser JavaMail API)
    }
}
