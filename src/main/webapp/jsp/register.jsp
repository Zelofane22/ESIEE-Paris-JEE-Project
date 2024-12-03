<%--
  Created by IntelliJ IDEA.
  User: fouad
  Date: 03/12/2024
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="auth" method="post">
    <input type="hidden" name="action" value="register">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required>
    <br>
    <label for="email">Email :</label>
    <input type="email" id="email" name="email" required>
    <br>
    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">S'inscrire</button>
</form>



</body>
</html>
