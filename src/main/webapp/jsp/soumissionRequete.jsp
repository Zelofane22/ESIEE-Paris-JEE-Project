<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Soumission de Requête</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <h1>Soumission de Requête</h1>
    </header>
    <div class="container">
        <form action="GestionRequetesServlet" method="post">
            <label for="sujet">Sujet :</label>
            <input type="text" id="sujet" name="sujet" required>
            <label for="description">Description :</label>
            <textarea id="description" name="description" required></textarea>
            <button type="submit">Soumettre</button>
        </form>
    </div>
</body>
</html>
