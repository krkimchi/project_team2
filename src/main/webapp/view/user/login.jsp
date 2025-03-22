<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/21/2025
  Time: 7:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Register</title>
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
            position: relative;
            width: 430px;
            height: 500px;
            background-color: var(--white-color);
            border-radius: 15px;
            padding: 120px 32px 64px;
            border: 1px solid var(--primary-color);
            box-shadow: 0 8px 15px var(--shadow-color);
            overflow: hidden;
            transition: var(--transition-3s);
        }

        .form-header {
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 140px;
            height: 70px;
            background-color: var(--primary-color);
            border-radius: 0 0 20px 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-header .titles {
            position: relative;
        }

        .title-login, .title-register {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: var(--white-color);
            font-size: 24px;
            transition: var(--transition-3s);
        }

        .title-register {
            top: 50px;
        }

        .login-form {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            width: 85%;
            transition: var(--transition-3s);
        }

        .icon {
            position: absolute;
            top: 50%;
            right: 20px;
            transform: translateY(-50%);
            font-size: 20px;
            color: var(--secondary-color);
        }

        .input-box {
            position: relative;
            display: flex;
            flex-direction: column;
            margin: 20px 0;
        }

        .input-field {
            width: 100%;
            height: 55px;
            font-size: 16px;
            background: transparent;
            color: var(--black-color);
            padding: 0 20px;
            border: 1px solid var(--input-border-color);
            border-radius: 30px;
            outline: none;
            transition: var(--transition-3s);
        }

        .input-field:focus {
            border: 1px solid var(--primary-color);
        }

        .label {
            position: absolute;
            top: 50%;
            left: 20px;
            transform: translateY(-50%);
            color: var(--secondary-color);
            transition: 0.2s;
            cursor: text;
        }

        .input-field:focus ~ .label,
        .input-field:valid ~ .label {
            top: 0;
            font-size: 14px;
            background-color: var(--white-color);
            color: var(--primary-color);
            padding: 0 10px;
        }

        .btn-submit {
            width: 100%;
            height: 50px;
            background-color: var(--primary-color);
            color: var(--white-color);
            font-size: 16px;
            font-weight: 500;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            transition: var(--transition-3s);
        }

        .btn-submit:hover {
            background-color: #08122b;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="form-header">
        <div class="titles">
            <div class="title-login">Login</div>
            <div class="title-register">Register</div>
        </div>
    </div>

    <form action="login" method="post" class="login-form" autocomplete="off">
        <div class="input-box">
            <input type="text" class="input-field" name="email" required>
            <label class="label">Email</label>
            <i class='bx bx-envelope icon'></i>
        </div>
        <div class="input-box">
            <input type="password" class="input-field" name="password" required>
            <label class="label">Password</label>
            <i class='bx bx-lock-alt icon'></i>
        </div>
        <div class="input-box">
            <button type="submit" class="btn-submit">Sign In</button>
        </div>
        <div class="switch-form">
            <span>Don't have an account? <a href="register">Register</a></span>
        </div>
    </form>
</div>

<script>
    function registerFunction() {
        document.querySelector(".login-form").style.left = "-50%";
        document.querySelector(".register-form").style.left = "50%";
        document.querySelector(".wrapper").style.height = "580px";
    }
</script>
</body>
</html>
