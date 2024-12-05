<%@ page import="techsupport.entity.Utilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="techsupport.entity.Requete" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Requêtes</title>
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
<h1>Mes Requêtes</h1>

<%
    HttpSession mysession = request.getSession(false);
    if (mysession == null || session.getAttribute("cookie") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("cookie");
%>
<h1>Bienvenue, <%= utilisateur.getNom() %> !</h1>
<a href="LogoutServlet">Se déconnecter</a>

<a href="creer_requete.jsp">Créer une nouvelle requête</a>
<hr>
<table border="1">
    <thead>
    <tr>
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
        <td><%= requete.getSujet() %></td>
        <td><%= requete.getDescription() %></td>
        <td><%= requete.getStatut() %></td>
        <td><%= requete.getDateCreation() %></td>
        <td>
            <a href="details_requete.jsp?id=<%= requete.getId() %>">Voir</a> |
            <a href="supprimer_requete?id=<%= requete.getId() %>"
               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette requête ?');">Supprimer</a> |
            <a href="mettre_a_jour_requete.jsp?id=<%= requete.getId() %>">Mettre à jour</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">Aucune requête trouvée.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
