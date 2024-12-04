<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer une requête</title>
</head>
<body>
<h1>Créer une nouvelle requête</h1>
<form action="requetes" method="POST">
    <input type="hidden" name="action" value="create">
    <label for="sujet">Sujet</label><br>
    <input type="text" id="sujet" name="sujet" required><br><br>

    <label for="description">Description</label><br>
    <textarea id="description" name="description" rows="5" required></textarea><br><br>

    <input type="submit" value="Soumettre">
</form>
<br>
<a href="mes_requetes.jsp">Retour à mes requêtes</a>
</body>
</html>
