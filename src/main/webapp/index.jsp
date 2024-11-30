<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f4f4f9;
            color: #333;
            text-align: center;
        }

        header {
            background: #6200ea;
            color: #fff;
            padding: 20px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            margin: 0;
            font-size: 2.5em;
        }

        .container {
            margin: 20px auto;
            padding: 20px;
            max-width: 600px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        a, button {
            display: inline-block;
            margin: 10px 5px;
            padding: 10px 20px;
            font-size: 1em;
            text-decoration: none;
            color: #fff;
            background: #6200ea;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        a:hover, button:hover {
            background: #3700b3;
        }
    </style>
</head>
<body>
    <header>
        <h1>Hello World</h1>
    </header>
    <div class="container">
        <p>Gérez vos requêtes et vos utilisateurs efficacement avec notre système.</p>
        <div>
            <!-- Lien vers le servlet "Hello" -->
            <a href="hello-servlet">Hello Servlet</a>
        </div>
        <div>
            <!-- Bouton pour rediriger vers login.xhtml -->
            <a href="login.xhtml">Se connecter</a>
        </div>
    </div>
</body>
</html>
