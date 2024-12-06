<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="techsupport.entity.Requete" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de la requête</title>
</head>
<body>
<h1>Détails de la requête</h1>

<%
    // Récupérer la requête passée par le servlet
    Requete requete = (Requete) request.getAttribute("requete");
    if (requete == null) {
%>
<p>Requête introuvable.</p>
<a href="requetes">Retour à mes requêtes</a>
<%
} else {
%>
<!-- Formulaire pour modifier la requête -->
<form action="requetes" method="POST">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= requete.getId()%>">

    <div>
        <label for="sujet">Sujet :</label><br>
        <input type="text" id="sujet" name="sujet" value="<%= requete.getSujet() %>" required>
    </div>
    <br>
    <div>
        <label for="description">Description :</label><br>
        <textarea id="description" name="description" rows="5" required><%= requete.getDescription() %></textarea>
    </div>
    <br>
    <button type="submit">Mettre à jour</button>
</form>

<h2>Statut actuel</h2>
<p><strong><%= requete.getStatut() %></strong></p>

<%--<h2>Messages associés</h2>--%>
<%--<%--%>
<%--    if (requete.getMessages() != null && !requete.getMessages().isEmpty()) {--%>
<%--%>--%>
<%--<ul>--%>
<%--    <%--%>
<%--        for (techsupport.entity.Message message : requete.getMessages()) {--%>
<%--    %>--%>
<%--    <li>--%>
<%--        <strong><%= message.getAuteur() %> :</strong>--%>
<%--        <%= message.getContenu() %>--%>
<%--        <em>(<%= message.getDateCreation() %>)</em>--%>
<%--    </li>--%>
<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--</ul>--%>
<%--<%--%>
<%--} else {--%>
<%--%>--%>
<%--<p>Aucun message trouvé pour cette requête.</p>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

<h3>Ajouter un message</h3>
<form action="AjouterMessageServlet" method="POST">
    <input type="hidden" name="requeteId" value="<%= requete.getId() %>">
    <textarea name="contenu" rows="5" cols="40" placeholder="Votre message..." required></textarea><br>
    <button type="submit">Envoyer</button>
</form>

<br>
<a href="requetes">Retour à mes requêtes</a>
<%
    }
%>
</body>
</html>
