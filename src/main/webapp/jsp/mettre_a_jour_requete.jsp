<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mettre à jour la requête</title>
</head>
<body>
<h1>Mettre à jour la requête</h1>

<c:if test="${not empty requete}">
    <form action="requetes" method="POST">
        <%--@declare id="statut"--%><input type="hidden" name="requeteId" value="${requete.id}">
            <input type="hidden" name="action" value="update">

        <label for="statut">Statut</label><br>
        <select name="statut">
            <option value="NOUVELLE" ${requete.statut == 'NOUVELLE' ? 'selected' : ''}>Nouvelle</option>
            <option value="EN_COURS" ${requete.statut == 'EN_COURS' ? 'selected' : ''}>En cours</option>
            <option value="TERMINEE" ${requete.statut == 'TERMINEE' ? 'selected' : ''}>Terminée</option>
        </select><br><br>

        <input type="submit" value="Mettre à jour">
    </form>
</c:if>

<c:if test="${empty requete}">
    <p>Requête non trouvée.</p>
</c:if>

<br>
<a href="mes_requetes.jsp">Retour à mes requêtes</a>
</body>
</html>
