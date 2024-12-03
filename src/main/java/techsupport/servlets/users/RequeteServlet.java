package techsupport.servlets.users;

import techsupport.daos.RequeteDAO;
import techsupport.entity.Requete;
import techsupport.entity.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/jsp/requetes")
public class RequeteServlet extends HttpServlet {

    private RequeteDAO requeteDAO;

    @Override
    public void init() throws ServletException {
        requeteDAO = new RequeteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if (utilisateur == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            switch (action != null ? action : "") {
                case "list":
                    afficherRequetesUtilisateur(request, response, utilisateur);
                    break;
                case "view":
                    afficherDetailsRequete(request, response, utilisateur);
                    break;
                default:
                    response.sendRedirect("dashboard.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if (utilisateur == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            switch (action != null ? action : "") {
                case "create":
                    creerRequete(request, response, utilisateur);
                    break;
                case "update":
                    mettreAJourRequete(request, response, utilisateur);
                    break;
                case "delete":
                    supprimerRequete(request, response, utilisateur);
                    break;
                default:
                    response.sendRedirect("dashboard.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void afficherRequetesUtilisateur(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws ServletException, IOException {
        List<Requete> requetes = requeteDAO.getRequetesParUtilisateur(utilisateur.getId());
        request.setAttribute("requetes", requetes);
        request.getRequestDispatcher("mes_requetes.jsp").forward(request, response);
    }

    private void afficherDetailsRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws ServletException, IOException {
        int idRequete = (int) Long.parseLong(request.getParameter("id"));
        Requete requete = requeteDAO.getRequeteParId(idRequete);

        if (requete == null || requete.getUtilisateur().getId() == utilisateur.getId()) {
            response.sendRedirect("error.jsp");
            return;
        }

        request.setAttribute("requete", requete);
        request.getRequestDispatcher("details_requete.jsp").forward(request, response);
    }

    private void creerRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws IOException {
        String sujet = request.getParameter("sujet");
        String description = request.getParameter("description");

        if (sujet == null || sujet.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            response.sendRedirect("mes_requetes.jsp?error=empty_fields");
            return;
        }

        Requete requete = new Requete(utilisateur, sujet, description);
        requeteDAO.creerRequete(requete);
        response.sendRedirect("requetes?action=list");
    }

    private void mettreAJourRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws IOException {
        int idRequete = (int) Long.parseLong(request.getParameter("id"));
        String statutParam = request.getParameter("statut");

        Requete requete = requeteDAO.getRequeteParId(idRequete);

        if (requete == null ) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            Requete.Statut statut = Requete.Statut.valueOf(statutParam);
            requete.setStatut(statut);
            requeteDAO.mettreAJourRequete(requete);
            response.sendRedirect("requetes?action=view&id=" + idRequete);
        } catch (IllegalArgumentException e) {
            response.sendRedirect("error.jsp");
        }
    }

    private void supprimerRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws IOException {
        int idRequete = (int) Long.parseLong(request.getParameter("id"));

        Requete requete = requeteDAO.getRequeteParId(idRequete);

        if (requete == null ) {
            response.sendRedirect("error.jsp");
            return;
        }

        requeteDAO.supprimerRequete(idRequete);
        response.sendRedirect("requetes?action=list");
    }
}
