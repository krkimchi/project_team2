<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Management</title>

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
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
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

        h2 {
            text-align: center;
            margin-top: 20px;
            color: var(--primary-color);
        }

        h3 {
            margin-left: 60px;
        }

        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            padding: 20px;
        }

        .search-container {
            display: flex;
            align-items: center;
        }

        #search {
            padding: 8px 12px;
            margin-right: 60px;
            border-radius: 5px;
            border: 1px solid var(--input-border-color);
            font-size: 16px;
            display: inline-block;
            height: 35px;
        }


        #search-btn {
            cursor: pointer;
            border: none;
            background-color: transparent;
            font-size: 20px;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: var(--primary-color);
            color: var(--white-color);
        }

        td {
            background-color: #fff;
        }

        .btn {
            padding: 8px 16px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            text-align: center;
            border-radius: 5px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }

        .form-group {
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        button[type="submit"] {
            padding: 10px 20px;
            background-color: #28a745;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        button[type="submit"]:hover {
            background-color: #218838;
        }

        .update-form {
            display: none;
        }

        .update-form.active {
            display: block;
        }

        .actions button {
            display: block;
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border-radius: 5px;
        }

        .icon {
            font-size: 18px;
            margin-right: 5px;
        }

        .actions {
            position: relative;
        }

    </style>

</head>
<body>

<div class="wrapper">
    <h2><i class="bx bx-building-house icon"></i>Restaurant Management</h2>

    <div class="header-container">
        <h3>List of Restaurants</h3>
        <div class="search-container">
            <button id="search-btn">
                <i class="bx bx-search icon"></i>
            </button>
            <input type="text" id="search" placeholder="Enter Restaurant ID or Name" />
        </div>
    </div>

    <table>
        <thead>
        <tr>
            <th>Restaurant ID</th>
            <th>Restaurant Name</th>
            <th>Restaurant Address</th>
            <th>Restaurant Phone</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="restaurant" items="${restaurants}">
            <tr>
                <td>${restaurant.id}</td>
                <td>${restaurant.restaurantName}</td>
                <td>${restaurant.restaurantAddress}</td>
                <td>${restaurant.restaurantPhone}</td>
                <td class="actions">
                    <button id="update-btn-${restaurant.id}" class="btn" onclick="showUpdateForm(${restaurant.id})">
                        <i class="bx bx-edit-alt icon"></i>Update Restaurant
                    </button>

                    <form id="update-form-${restaurant.id}" action="restaurant-management" method="post" class="update-form">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${restaurant.id}">
                        <div class="form-group">
                            <label for="restaurant_name">Restaurant Name:</label>
                            <input type="text" id="restaurant_name" name="restaurant_name" value="${restaurant.restaurantName}" required>
                        </div>

                        <div class="form-group">
                            <label for="restaurant_address">Restaurant Address:</label>
                            <input type="text" id="restaurant_address" name="restaurant_address" value="${restaurant.restaurantAddress}" required>
                        </div>

                        <div class="form-group">
                            <label for="restaurant_phone">Restaurant Phone:</label>
                            <input type="text" id="restaurant_phone" name="restaurant_phone" value="${restaurant.restaurantPhone}" required>
                        </div>

                        <button type="submit"><i class="bx bx-check icon"></i>Confirm Update</button>
                    </form>

                    <form id="delete-${restaurant.id}" action="restaurant-management" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${restaurant.id}">
                    </form>

                    <button class="btn btn-delete" onclick="confirmAction('delete', ${restaurant.id})">
                        <i class="bx bx-trash icon"></i>Confirm Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function confirmAction(action, id) {
        var message = '';
        if (action === 'update') {
            message = 'Are you sure you want to update this restaurant?';
        } else if (action === 'delete') {
            message = 'Are you sure you want to delete this restaurant?';
        }

        var confirmed = confirm(message);
        if (confirmed) {
            if (action === 'delete') {
                document.getElementById("delete-" + id).submit();
            } else if (action === 'update') {
                document.getElementById("update-form-" + id).submit();
            }
        } else {
            return false;
        }
    }

    function showUpdateForm(id) {
        document.getElementById("update-form-" + id).classList.add("active");
        document.getElementById("update-btn-" + id).style.display = "none";
    }
</script>

</body>
</html>
