<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de bord - Admin</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .action-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .action-button:hover {
            background-color: #388E3C;
        }

        select {
            padding: 5px 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<h1>Gestion des Requêtes</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Utilisateur</th>
        <th>Sujet</th>
        <th>Description</th>
        <th>Statut</th>
        <th>Messages</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="requete" items="${requetes}">
        <tr>
            <td>${requete.id}</td>
            <td>${requete.utilisateur.nom} (${requete.utilisateur.email})</td>
            <td>${requete.sujet}</td>
            <td>${requete.description}</td>
            <td>${requete.statut}</td>
            <td>
                <c:forEach var="message" items="${requete.messages}">
                    <p>
                        <strong>${message.auteur} :</strong> ${message.contenu} <em>(${message.dateCreation})</em>
                    </p>
                </c:forEach>
            </td>
            <td>
                <!-- Formulaire pour répondre -->
                <form action="adminDashboard" method="post">
                    <input type="hidden" name="action" value="repondre">
                    <input type="hidden" name="requeteId" value="${requete.id}">
                    <textarea name="message" placeholder="Votre réponse" required></textarea>
                    <button type="submit">Envoyer</button>
                </form>

                <!-- Formulaire pour changer le statut -->
                <form action="adminDashboard" method="post">
                    <input type="hidden" name="action" value="updateStatut">
                    <input type="hidden" name="id" value="${requete.id}">
                    <select name="nouveauStatut" required>
                        <option value="NOUVELLE" ${requete.statut == 'NOUVELLE' ? 'selected' : ''}>Nouvelle</option>
                        <option value="EN_COURS" ${requete.statut == 'EN_COURS' ? 'selected' : ''}>En cours</option>
                        <option value="TERMINEE" ${requete.statut == 'TERMINEE' ? 'selected' : ''}>Terminée</option>
                    </select>
                    <button type="submit">Mettre à jour</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
