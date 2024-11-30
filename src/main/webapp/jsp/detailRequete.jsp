<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détail de la Requête</title>
</head>
<body>
    <h1>Détail de la Requête</h1>
    <p><strong>ID :</strong> ${requete.id}</p>
    <p><strong>Sujet :</strong> ${requete.sujet}</p>
    <p><strong>Description :</strong> ${requete.description}</p>
    <p><strong>Statut :</strong> ${requete.statut}</p>

    <h2>Répondre</h2>
    <form action="GestionRequetesServlet" method="post">
        <input type="hidden" name="id" value="${requete.id}" />
        <textarea name="reponse" required></textarea>
        <button type="submit" name="action" value="respond">Envoyer</button>
    </form>
</body>
</html>
