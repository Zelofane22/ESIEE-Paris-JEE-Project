<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion Administrateur</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <h1>Connexion Administrateur</h1>
    </header>
    <div class="container">
        <form action="auth" method="post">
            <input type="hidden" name="action" value="login">
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required>
            <br>
            <label for="password">Mot de passe :</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit">Se connecter</button>
        </form>
        <!-- Affichage du message d'erreur -->
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
    </div>
</body>
</html>
