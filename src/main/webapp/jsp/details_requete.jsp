<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de la requête</title>
</head>
<body>
<h1>Détails de la requête</h1>

<c:if test="${not empty requete}">
    <p><strong>Sujet :</strong> ${requete.sujet}</p>
    <p><strong>Description :</strong> ${requete.description}</p>
    <p><strong>Statut :</strong> ${requete.statut}</p>
    <p><strong>Date de création :</strong> ${requete.dateCreation}</p>

    <h2>Messages :</h2>
    <c:forEach var="message" items="${requete.messages}">
        <p><strong>${message.utilisateur.nom} :</strong> ${message.contenu} <br><small>${message.dateCreation}</small></p>
    </c:forEach>

    <h3>Ajouter un message :</h3>
    <form action="AjouterMessageServlet" method="POST">
        <input type="hidden" name="requeteId" value="${requete.id}">
        <textarea name="contenu" rows="5" required></textarea><br><br>
        <input type="submit" value="Envoyer">
    </form>

    <br>
    <a href="mes_requetes.jsp">Retour à mes requêtes</a>
</c:if>

<c:if test="${empty requete}">
    <p>Requête non trouvée.</p>
</c:if>
</body>
</html>
