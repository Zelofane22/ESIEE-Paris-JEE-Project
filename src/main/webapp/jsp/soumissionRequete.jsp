<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Soumettre une Requête</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background: #f4f4f9; }
        .container { max-width: 600px; margin: 50px auto; padding: 20px; background: #fff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }
        h1 { text-align: center; color: #6200ea; }
        label, textarea, input, button { display: block; width: 100%; margin-bottom: 15px; }
        button { background: #6200ea; color: white; border: none; padding: 10px; border-radius: 5px; cursor: pointer; }
        button:hover { background: #3700b3; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Soumettre une Requête</h1>
        <form action="GestionRequeteUtilisateurServlet" method="post">
            <label for="sujet">Sujet :</label>
            <input type="text" id="sujet" name="sujet" required />

            <label for="description">Description :</label>
            <textarea id="description" name="description" rows="4" required></textarea>

            <button type="submit">Envoyer la Requête</button>
        </form>
    </div>
</body>
</html>
