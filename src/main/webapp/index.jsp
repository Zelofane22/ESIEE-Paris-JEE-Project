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
            background: #f9f9f9;
            color: #333;
            text-align: center;
        }

        header {
            background: #4CAF50;
            color: #fff;
            padding: 20px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            margin: 0;
            font-size: 2.5em;
        }

        .container {
            margin: 40px auto;
            padding: 20px;
            max-width: 700px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        a {
            display: inline-block;
            margin: 15px 10px;
            padding: 12px 25px;
            font-size: 1em;
            text-decoration: none;
            color: #fff;
            background: #4CAF50;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        a:hover {
            background: #388E3C;
        }

        .description {
            font-size: 1.2em;
            color: #555;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Accueil - Gestion des Requêtes</h1>
    </header>
    <div class="container">
        <p class="description">Bienvenue sur le portail de gestion. Accédez aux fonctionnalités selon vos besoins.</p>

        <!-- Version JSP -->
        <div>
            <h2>Version JSP</h2>
            <a href="jsp/loginAdmin.jsp">Connexion Administrateur (JSP)</a>
            <a href="jsp/soumissionRequete.jsp">Soumettre une Requête (JSP)</a>
        </div>

        <hr />

        <!-- Version JSF -->
        <div>
            <h2>Version JSF</h2>
            <a href="xhtml/loginAdmin.xhtml">Connexion Administrateur (JSF)</a>
            <a href="xhtml/soumissionRequete.xhtml">Soumettre une Requête (JSF)</a>
        </div>
    </div>
</body>
</html>
