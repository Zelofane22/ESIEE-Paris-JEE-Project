<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion Administrateur</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background: #f4f4f9; }
        .container { max-width: 400px; margin: 50px auto; padding: 20px; background: #fff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }
        h1 { text-align: center; color: #6200ea; }
        label, input, button { display: block; width: 100%; margin-bottom: 15px; }
        button { background: #6200ea; color: white; border: none; padding: 10px; border-radius: 5px; cursor: pointer; }
        button:hover { background: #3700b3; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Connexion Administrateur</h1>
        <form action="AuthenticationServlet" method="post">
            <label for="identifiant">Identifiant :</label>
            <input type="text" id="identifiant" name="identifiant" required />

            <label for="motdepasse">Mot de passe :</label>
            <input type="password" id="motdepasse" name="motdepasse" required />

            <button type="submit">Se connecter</button>
        </form>
    </div>
</body>
</html>
