<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
<a href="creer_requete.jsp">Créer une nouvelle requête</a>
<hr>

<c:if test="${not empty requetes}">
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
        <c:forEach var="requete" items="${requetes}">
            <tr>
                <td>${requete.sujet}</td>
                <td>${requete.description}</td>
                <td>${requete.statut}</td>
                <td>${requete.dateCreation}</td>
                <td>
                    <a href="details_requete.jsp?id=${requete.id}">Voir</a> |
                    <a href="supprimer_requete?id=${requete.id}" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette requête ?');">Supprimer</a> |
                    <a href="mettre_a_jour_requete.jsp?id=${requete.id}">Mettre à jour</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty requetes}">
    <p>Aucune requête trouvée.</p>
</c:if>
</body>
</html>
