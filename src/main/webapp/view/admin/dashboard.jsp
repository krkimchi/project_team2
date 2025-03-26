<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet">
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

        .actions button {
            display: block;
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border-radius: 5px;
        }

        .pagination button {
            padding: 8px 16px;
            margin: 0 5px;
            background-color: var(--primary-color);
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .pagination button:hover {
            background-color: #0056b3;
        }

        nav ul li {
            display: flex;
            align-items: center;
            margin: 10px 0;
        }

        nav ul li a {
            display: flex;
            align-items: center;
            color: var(--primary-color);
            text-decoration: none;
            font-size: 16px;
        }

        nav ul li a i {
            font-size: 20px;
            margin-right: 10px;
        }

        nav ul li a::before {
            content: "";
            width: 20px;
            height: 20px;
            background-color: var(--primary-color);
            margin-right: 10px;
            border-radius: 4px; /* Thêm góc bo tròn cho các ô vuông */
        }

    </style>
</head>
<body>
<div class="wrapper">
    <h1>Admin Dashboard</h1>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/user-management"><i class="bx bx-user"></i> User Management</a></li>
            <li><a href="${pageContext.request.contextPath}/restaurant-management"><i class="bx bx-store"></i> Restaurant Management</a></li>
            <li><a href="${pageContext.request.contextPath}/revenue"><i class="bx bx-dollar-circle"></i> Revenue</a></li>
            <li><a href="${pageContext.request.contextPath}/restaurant-rating"><i class="bx bx-star"></i> Restaurant Ratings</a></li>
        </ul>
    </nav>
</div>
</body>

</html>
