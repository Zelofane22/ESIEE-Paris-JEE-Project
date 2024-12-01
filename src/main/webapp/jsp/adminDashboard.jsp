<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de Bord Admin</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
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
                            <form action="GestionRequetesServlet" method="post">
                                <input type="hidden" name="id" value="${requete.id}" />
                                <select name="statut">
                                    <option value="NOUVELLE">Nouvelle</option>
                                    <option value="EN_COURS">En cours</option>
                                    <option value="TERMINEE">Terminée</option>
                                </select>
                                <button type="submit" name="action" value="update">Mettre à jour</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
