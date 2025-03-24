<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/24/2025
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Ratings</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap');
        :root {
            --primary-color: #0D1936;
            --secondary-color: #535354;
            --background-color: #EFEFEF;
            --shadow-color: rgba(0, 0, 0, 0.1);
            --white-color: #FFF;
            --black-color: #000;
            --input-border-color: #E3E4E6;
            --transition-3s: 0.3s;
        }
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: var(--background-color);
        }
        .wrapper {
            width: 80%;
            max-width: 800px;
            background-color: var(--white-color);
            border-radius: 15px;
            padding: 20px;
            border: 1px solid var(--primary-color);
            box-shadow: 0 8px 15px var(--shadow-color);
            overflow: hidden;
            transition: var(--transition-3s);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: var(--primary-color);
            color: var(--white-color);
        }
        tr:hover { background-color: #f5f5f5; }
    </style>
</head>
<body>
<div class="wrapper">
    <h1>Restaurant Ratings</h1>
    <table>
        <tr>
            <th>Restaurant Name</th>
            <th>Average Rating</th>
            <th>Review Count</th>
        </tr>
        <c:forEach items="${ratings}" var="rating">
            <tr>
                <td>${rating.restaurantName}</td>
                <td>${rating.averageRating}</td>
                <td>${rating.reviewCount}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>



