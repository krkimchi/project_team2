<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/21/2025
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="register" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email">
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone">
    <label for="fullName">Full Name:</label>
    <input type="text" id="fullName" name="fullName">
    <button type="submit">Register</button>
</form>
<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
<p style="color:red;"><%= errorMessage %>
</p>
<% } %>

</body>
</html>
