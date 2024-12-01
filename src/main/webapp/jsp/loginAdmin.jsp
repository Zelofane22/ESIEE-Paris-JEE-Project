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
        <form action="AuthenticationServlet" method="post">
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required>
            <label for="motDePasse">Mot de passe :</label>
            <input type="password" id="motDePasse" name="motDePasse" required>
            <button type="submit">Se connecter</button>
        </form>
        <p style="color: red;">${erreur}</p>
    </div>
</body>
</html>
