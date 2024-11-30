<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object admin = session.getAttribute("adminLoggedIn");
    if (admin == null || !((Boolean) admin)) {
        response.sendRedirect("loginAdmin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de Bord Admin</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background: #f4f4f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #6200ea; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        button { background: #6200ea; color: white; border: none; padding: 5px 10px; cursor: pointer; }
        button:hover { background: #3700b3; }
    </style>
</head>
<body>
    <h1>Tableau de Bord Admin</h1>
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
</body>
</html>
