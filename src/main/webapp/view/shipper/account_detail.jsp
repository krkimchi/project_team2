<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 28/3/25
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account detail</title>
</head>
<body>
<main>
    <div class="user-profile">
        <div class="logo">
            <img src="elements/default.jpg">
            <h2>${sessionScope.user.getUsername()}</h2>
            <p>Fulltime shipper</p>
        </div>
    </div>
</main>
</body>
</html>
