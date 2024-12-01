<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détail de la Requête</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <h1>Détail de la Requête</h1>
    </header>
    <div class="container">
        <p><strong>ID :</strong> ${requete.id}</p>
        <p><strong>Sujet :</strong> ${requete.sujet}</p>
        <p><strong>Description :</strong> ${requete.description}</p>
        <p><strong>Statut :</strong> ${requete.statut}</p>

        <form action="GestionRequetesServlet" method="post">
            <input type="hidden" name="id" value="${requete.id}" />
            <label for="reponse">Réponse :</label>
            <textarea id="reponse" name="reponse" required></textarea>
            <button type="submit" name="action" value="respond">Envoyer</button>
        </form>
    </div>
</body>
</html>
