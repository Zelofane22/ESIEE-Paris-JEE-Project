<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de Bord Admin</title>
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
    <header>
        <h1>Tableau de Bord Admin</h1>
    </header>
    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Sujet</th>
                    <th>Description</th>
                    <th>Statut</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%-- Boucle pour afficher les requêtes --%>
                <c:forEach var="requete" items="${requetes}">
                    <tr>
                        <td>${requete.id}</td>
                        <td>${requete.sujet}</td>
                        <td>${requete.description}</td>
                        <td>${requete.statut}</td>
                        <td>
                            <form action="GestionRequetesServlet" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${requete.id}" />
                                <select name="statut">
                                    <option value="NOUVELLE" ${requete.statut == 'NOUVELLE' ? 'selected' : ''}>Nouvelle</option>
                                    <option value="EN_COURS" ${requete.statut == 'EN_COURS' ? 'selected' : ''}>En cours</option>
                                    <option value="TERMINEE" ${requete.statut == 'TERMINEE' ? 'selected' : ''}>Terminée</option>
                                </select>
                                <button type="submit" name="action" value="update" class="action-button">Mettre à jour</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
