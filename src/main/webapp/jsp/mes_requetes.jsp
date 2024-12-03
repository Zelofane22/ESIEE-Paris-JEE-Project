<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Requêtes</title>
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
