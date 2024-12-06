<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="techsupport.entity.Requete" %>

<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>search</title>
</head>
<body>
<!-- Affichage des résultats de la recherche -->
<h2>Résultats de la recherche</h2>
<%
  // Récupérer les résultats de la recherche depuis l'attribut "requetes"
  List<Requete> resultats = (List<Requete>) request.getAttribute("requetes");

  if (resultats != null && !resultats.isEmpty()) {
%>
<table border="1">
  <thead>
  <tr>
    <th>Sujet</th>
    <th>Description</th>
    <th>Statut</th>
    <th>Date de création</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <%
    for (Requete requete : resultats) {
  %>
  <tr>
    <td><%= requete.getSujet() %></td>
    <td><%= requete.getDescription() %></td>
    <td><%= requete.getStatut() %></td>
    <td><%= requete.getDateCreation() %></td>
    <td>
      <a href="requetes?action=view&id=<%= requete.getId() %>">Voir</a> |
      <a href="requetes?action=delete&id=<%= requete.getId() %>"
         onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette requête ?');">Supprimer</a>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
<%
} else if (resultats != null) {
%>
<p>Aucun résultat trouvé pour votre recherche.</p>
<%
  }
%>
</body>
</html>
