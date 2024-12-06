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

@WebServlet("/requetes")
public class RequeteServlet extends HttpServlet {

    private RequeteDAO requeteDAO;

    @Override
    public void init() throws ServletException {
        requeteDAO = new RequeteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("------------------action: " + action +" ----------------------");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("cookie");

        if (utilisateur == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            switch (action != null ? action : "") {
                case "view":
                    afficherDetailsRequete(request, response, utilisateur);
                    break;
                case "search":
                    searchRequete(request, response, utilisateur);
                case "delete":
                    supprimerRequete(request, response, utilisateur);
                    break;
                default:
                    afficherRequetesUtilisateur(request, response, utilisateur);
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
        System.out.println("------------------action: " + action +" ----------------------");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("cookie");

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
                default:
                    response.sendRedirect("mes_requetes.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void searchRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws ServletException, IOException {
        try {
            // Récupérer le mot-clé de recherche depuis les paramètres
            String keyword = request.getParameter("keyword");

            // Appeler la méthode de recherche dans le DAO
            List<Requete> requetes = requeteDAO.rechercherRequetes(keyword, utilisateur.getId());

            // Ajouter les résultats comme attribut de requête
            request.setAttribute("requetes", requetes);

            // Transférer vers la JSP
            request.getRequestDispatcher("mes_requetes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("mes_requetes.jsp?error=RechercheImpossible");
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

        if (requete == null || requete.getUtilisateur().getId() != utilisateur.getId()) {
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

    private void mettreAJourRequeteStatut(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws IOException {
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

    private void mettreAJourRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws IOException {
        try {
            // Récupérer les paramètres du formulaire
            int requeteId = Integer.parseInt(request.getParameter("id"));
            String sujet = request.getParameter("sujet");
            String description = request.getParameter("description");

            // Récupérer la requête depuis la base de données
            Requete requete = requeteDAO.getRequeteParId(requeteId);

            // Mettre à jour les champs de la requête
            requete.setSujet(sujet);
            requete.setDescription(description);
            requeteDAO.mettreAJourRequete(requete);

            // Rediriger vers les détails de la requête
            response.sendRedirect("requetes?action=view&id=" + requeteId);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("requetes");
        }
    }



    private void supprimerRequete(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur) throws IOException {
        int idRequete = Integer.parseInt(request.getParameter("id"));

        Requete requete = requeteDAO.getRequeteParId(idRequete);

        if (requete == null ) {
            response.sendRedirect("error.jsp");
            return;
        }

        requeteDAO.supprimerRequete(idRequete);
        response.sendRedirect("requetes?action=list");
    }
}
