<%@ page import="techsupport.entity.Requete" %>
<%@ page import="java.util.List" %>
<%@ page import="techsupport.entity.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de bord - Admin</title>
    <style>
        .description {
            font-size: 1.2em;
            color: #555;
            margin-bottom: 20px;
        }

        hr {
            border: 0;
            border-top: 1px solid #ddd;
            margin: 20px 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .action-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .action-button:hover {
            background-color: #388E3C;
        }

        select {
            padding: 5px 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<%
    // Vérification de la session et récupération de l'utilisateur
    HttpSession mysession = request.getSession(false);
    if (mysession == null || mysession.getAttribute("cookie") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<h1>Gestion des Requêtes</h1>

<table border="1">
    <thead>
    <tr>
        <th>Utilisateur</th>
        <th>Sujet</th>
        <th>Description</th>
        <th>Statut</th>
        <th>Date de création</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Récupérer les requêtes passées par le servlet
        List<Requete> requetes = (List<Requete>) request.getAttribute("requetes");
        if (requetes != null && !requetes.isEmpty()) {
            for (Requete requete : requetes) {
    %>
    <tr>
        <td><%= requete.getUtilisateur().getNom() %> (<%= requete.getUtilisateur().getEmail() %>)</td>
        <td><%= requete.getSujet() %></td>
        <td><%= requete.getDescription() %></td>
        <td><%= requete.getStatut() %></td>
        <td><%= requete.getDateCreation() %></td>
        <td>
            <!-- Formulaire pour répondre -->
            <form action="adminDashboard" method="post">
                <input type="hidden" name="action" value="repondre">
                <input type="hidden" name="requeteId" value="<%= requete.getId() %>">
                <textarea name="message" placeholder="Votre réponse" required></textarea>
                <button type="submit">Envoyer</button>
            </form>

            <!-- Formulaire pour changer le statut -->
            <form action="adminDashboard" method="post">
                <input type="hidden" name="action" value="updateStatut">
                <input type="hidden" name="requeteId" value="<%= requete.getId() %>">
                <select name="nouveauStatut" required>
                    <option value="NOUVELLE" <%= "NOUVELLE".equals(requete.getStatut().name()) ? "selected" : "" %>>Nouvelle</option>
                    <option value="EN_COURS" <%= "EN_COURS".equals(requete.getStatut().name()) ? "selected" : "" %>>En cours</option>
                    <option value="TERMINEE" <%= "TERMINEE".equals(requete.getStatut().name()) ? "selected" : "" %>>Terminée</option>
                </select>
                <button type="submit">Mettre à jour</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">Aucune requête trouvée.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
