<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 26/3/25
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${deliveryItem.getCustomerName()}</title>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
        :root{
            --color-primary: #6C9BCF;
            --color-danger: #FF0060;
            --color-success: #1B9C85;
            --color-warning: #F7D060;
            --color-white: #fff;
            --color-info-dark: #7d8da1;
            --color-dark: #363949;
            --color-light: rgba(132, 139, 200, 0.18);
            --color-dark-variant: #677483;
            --color-background: #f6f6f9;

            --card-border-radius: 2rem;
            --border-radius-1: 0.4rem;
            --border-radius-2: 1.2rem;

            --card-padding: 1.8rem;
            --padding-1: 1.2rem;

            --box-shadow: 0 2rem 3rem var(--color-light);
        }

        .dark-mode-variables{
            --color-background: #181a1e;
            --color-white: #202528;
            --color-dark: #edeffd;
            --color-dark-variant: #a3bdcc;
            --color-light: rgba(0, 0, 0, 0.4);
            --box-shadow: 0 2rem 3rem var(--color-light);
        }

        *{
            margin: 0;
            padding: 0;
            outline: 0;
            appearance: 0;
            border: 0;
            text-decoration: none;
            box-sizing: border-box;
        }

        html{
            font-size: 14px;
        }

        body{
            width: 100vw;
            height: 100vh;
            font-family: 'Poppins', sans-serif;
            font-size: 0.88rem;
            user-select: none;
            overflow-x: hidden;
            color: var(--color-dark);
            background-color: var(--color-background);
        }

        a{
            color: var(--color-dark);
        }

        img{
            display: block;
            width: 100%;
            object-fit: cover;
        }

        h1{
            font-weight: 800;
            font-size: 1.8rem;
        }

        h2{
            font-weight: 600;
            font-size: 1.4rem;
        }

        h3{
            font-weight: 500;
            font-size: 0.87rem;
        }

        small{
            font-size: 0.76rem;
        }

        p{
            color: var(--color-dark-variant);
        }

        b{
            color: var(--color-dark);
        }

        .text-muted{
            color: var(--color-info-dark);
        }

        .primary{
            color: var(--color-primary);
        }

        .danger{
            color: var(--color-danger);
        }

        .success{
            color: var(--color-success);
        }

        .warning{
            color: var(--color-warning);
        }

        .container{
            display: grid;
            width: 96%;
            margin: 0 auto;
            gap: 1.8rem;
            grid-template-columns: 12rem auto 23rem;
        }

        aside{
            height: 100vh;
        }

        aside .toggle{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 1.4rem;
        }

        aside .toggle .logo{
            display: flex;
            gap: 0.5rem;
        }

        aside .toggle .logo img{
            width: 2rem;
            height: 2rem;
        }

        aside .toggle .close{
            padding-right: 1rem;
            display: none;
        }

        aside .sidebar{
            display: flex;
            flex-direction: column;
            background-color: var(--color-white);
            box-shadow: var(--box-shadow);
            border-radius: 15px;
            height: 88vh;
            position: relative;
            top: 1.5rem;
            transition: all 0.3s ease;
        }

        aside .sidebar:hover{
            box-shadow: none;
        }

        aside .sidebar a{
            display: flex;
            align-items: center;
            color: var(--color-info-dark);
            height: 3.7rem;
            gap: 1rem;
            position: relative;
            margin-left: 2rem;
            transition: all 0.3s ease;
        }

        aside .sidebar a span{
            font-size: 1.6rem;
            transition: all 0.3s ease;
        }

        aside .sidebar a:last-child{
            position: absolute;
            bottom: 2rem;
            width: 100%;
        }

        aside .sidebar a.active{
            width: 100%;
            color: var(--color-primary);
            background-color: var(--color-light);
            margin-left: 0;
        }

        aside .sidebar a.active::before{
            content: '';
            width: 6px;
            height: 18px;
            background-color: var(--color-primary);
        }

        aside .sidebar a.active span{
            color: var(--color-primary);
            margin-left: calc(1rem - 3px);
        }

        aside .sidebar a:hover{
            color: var(--color-primary);
        }

        aside .sidebar a:hover span{
            margin-left: 0.6rem;
        }

        aside .sidebar .message-count{
            background-color: var(--color-danger);
            padding: 2px 6px;
            color: var(--color-white);
            font-size: 11px;
            border-radius: var(--border-radius-1);
        }
        main{
            margin-top: 1.4rem;
        }

        main .analyse{
            display:grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 1.6rem;
        }

        main .analyse > div{
            background-color: var(--color-white);
            padding: var(--card-padding);
            border-radius: var(--card-border-radius);
            margin-top: 1rem;
            box-shadow: var(--box-shadow);
            cursor: pointer;
            transition: all 0.3s ease;
        }

        main .analyse > div:hover{
            box-shadow: none;
        }

        main .analyse > div .status{
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        main .analyse h3{
            margin-left: 0.1rem;
            font-size: 1rem;
        }
        main .recent-orders{
            margin-top: 1.3rem;
        }

        main .recent-orders h2{
            margin-bottom: 0.8rem;
        }

        main .recent-orders table{
            background-color: var(--color-white);
            width: 100%;
            padding: var(--card-padding);
            text-align: center;
            box-shadow: var(--box-shadow);
            border-radius: var(--card-border-radius);
            transition: all 0.3s ease;
        }

        main .recent-orders table:hover{
            box-shadow: none;
        }

        main table tbody td{
            height: 2.8rem;
            border-bottom: 1px solid var(--color-light);
            color: var(--color-dark-variant);
        }

        main table tbody tr:last-child td{
            border: none;
        }

        main .recent-orders a{
            text-align: center;
            display: block;
            margin: 1rem auto;
            color: var(--color-primary);
        }
        .right-section{
            margin-top: 1.4rem;
        }

        .right-section .nav{
            display: flex;
            justify-content: end;
            gap: 2rem;
        }

        .right-section .nav button{
            display: none;
        }

        .right-section .dark-mode{
            background-color: var(--color-light);
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 1.6rem;
            width: 4.2rem;
            cursor: pointer;
            border-radius: var(--border-radius-1);
        }

        .right-section .dark-mode span{
            font-size: 1.2rem;
            width: 50%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .right-section .dark-mode span.active{
            background-color: var(--color-primary);
            color: white;
            border-radius: var(--border-radius-1);
        }

        .right-section .nav .profile{
            display: flex;
            gap: 2rem;
            text-align: right;
        }

        .right-section .nav .profile .profile-photo{
            width: 2.8rem;
            height: 2.8rem;
            border-radius: 50%;
            overflow: hidden;
        }

        .right-section .user-profile{
            display: flex;
            justify-content: center;
            text-align: center;
            margin-top: 1rem;
            background-color: var(--color-white);
            padding: var(--card-padding);
            border-radius: var(--card-border-radius);
            box-shadow: var(--box-shadow);
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .right-section .user-profile:hover{
            box-shadow: none;
        }

        .right-section .user-profile img{
            width: 11rem;
            height: auto;
            margin-bottom: 0.8rem;
            border-radius: 50%;
        }

        .right-section .user-profile h2{
            margin-bottom: 0.2rem;
        }

        .right-section .reminders{
            margin-top: 2rem;
        }

        .right-section .reminders .header{
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 0.8rem;
        }

        .right-section .reminders .header span{
            padding: 10px;
            box-shadow: var(--box-shadow);
            background-color: var(--color-white);
            border-radius: 50%;
        }

        .right-section .reminders .notification{
            background-color: var(--color-white);
            display: flex;
            align-items: center;
            gap: 1rem;
            margin-bottom: 0.7rem;
            padding: 1.4rem var(--card-padding);
            border-radius: var(--border-radius-2);
            box-shadow: var(--box-shadow);
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .right-section .reminders .notification:hover{
            box-shadow: none;
        }

        .right-section .reminders .notification .content{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 0;
            width: 100%;
        }

        .right-section .reminders .notification .icon{
            padding: 0.6rem;
            color: var(--color-white);
            background-color: var(--color-success);
            border-radius: 20%;
            display: flex;
        }

        .right-section .reminders .notification.deactive .icon{
            background-color: var(--color-danger);
        }

        .right-section .reminders .add-reminder{
            background-color: var(--color-white);
            border: 2px dashed var(--color-primary);
            color: var(--color-primary);
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
        }

        .right-section .reminders .add-reminder:hover{
            background-color: var(--color-primary);
            color: white;
        }

        .right-section .reminders .add-reminder div{
            display: flex;
            align-items: center;
            gap: 0.6rem;
        }

        @media screen and (max-width: 1200px) {
            .container{
                width: 95%;
                grid-template-columns: 7rem auto 23rem;
            }

            aside .logo h2{
                display: none;
            }

            aside .sidebar h3{
                display: none;
            }

            aside .sidebar a{
                width: 5.6rem;
            }

            aside .sidebar a:last-child{
                position: relative;
                margin-top: 1.8rem;
            }

            main .analyse{
                grid-template-columns: 1fr;
                gap: 0;
            }

            main .new-users .user-list .user{
                flex-basis: 40%;
            }

            main .recent-orders {
                width: 94%;
                position: absolute;
                left: 50%;
                transform: translateX(-50%);
                margin: 2rem 0 0 0.8rem;
            }

            main .recent-orders table{
                width: 83vw;
            }

            main table thead tr th:last-child,
            main table thead tr th:first-child{
                display: none;
            }

            main table tbody tr td:last-child,
            main table tbody tr td:first-child{
                display: none;
            }

        }

        @media screen and (max-width: 768px) {
            .container {
                width: 100%;
                grid-template-columns: 1fr;
                padding: 0 var(--padding-1);
            }

            aside {
                position: fixed;
                background-color: var(--color-white);
                width: 15rem;
                z-index: 3;
                box-shadow: 1rem 3rem 4rem var(--color-light);
                height: 100vh;
                left: -100%;
                display: none;
                animation: showMenu 0.4s ease forwards;
            }

            @keyframes showMenu {
                to {
                    left: 0;
                }
            }

            aside .logo {
                margin-left: 1rem;
            }

            aside .logo h2 {
                display: inline;
            }

            aside .sidebar h3 {
                display: inline;
            }

            aside .sidebar a {
                width: 100%;
                height: 3.4rem;
            }

            aside .sidebar a:last-child {
                position: absolute;
                bottom: 5rem;
            }

            aside .toggle .close {
                display: inline-block;
                cursor: pointer;
            }

            main {
                margin-top: 8rem;
                padding: 0 1rem;
            }

            main .new-users .user-list .user {
                flex-basis: 35%;
            }

            main .recent-orders {
                position: relative;
                margin: 3rem 0 0 0;
                width: 100%;
            }

            main .recent-orders table {
                width: 100%;
                margin: 0;
            }

            .right-section {
                width: 94%;
                margin: 0 auto 4rem;
            }

            .right-section .nav {
                position: fixed;
                top: 0;
                left: 0;
                align-items: center;
                background-color: var(--color-white);
                padding: 0 var(--padding-1);
                height: 4.6rem;
                width: 100%;
                z-index: 2;
                box-shadow: 0 1rem 1rem var(--color-light);
                margin: 0;
            }

            .right-section .nav .dark-mode {
                width: 4.4rem;
                position: absolute;
                left: 66%;
            }

            .right-section .profile .info {
                display: none;
            }

            .right-section .nav button {
                display: inline-block;
                background-color: transparent;
                cursor: pointer;
                color: var(--color-dark);
                position: absolute;
                left: 1rem;
            }

            .right-section .nav button span {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <!--  Sidebar-->
    <aside class="sidebar">
        <div class="toggle">
            <div class="logo">
                <img src="elements/Hamburger%20Icon.png" alt="">
                <h2>Food<span class="danger">Company</span></h2>
            </div>
            <div class="close" id="close-btn">
        <span class="material-symbols-outlined">
          close
        </span>
            </div>
        </div>
        <div class="sidebar">
            <a href="project/index.html">
        <span class="material-symbols-outlined">
          dashboard
        </span>
                <h3>Overview</h3>
            </a>
            <a href="orders/orders.html">
        <span class="material-symbols-outlined">
          list_alt
        </span>
                <h3>All orders</h3>
                <span class="message-count">69</span>
            </a>
            <a href="#">
        <span class="material-symbols-outlined">
          receipt_long
        </span>
                <h3>History</h3>
            </a>
            <a href="#">
        <span class="material-symbols-outlined">
          account_balance
        </span>
                <h3>Balance</h3>
            </a>
            <a href="#">
        <span class="material-symbols-outlined">
          settings
        </span>
                <h3>Settings</h3>
            </a>
            <a href="#">
        <span class="material-symbols-outlined">
          add
        </span>
                <h3>New logins</h3>
            </a>
            <a href="#">
        <span class="material-symbols-outlined">
          logout
        </span>
                <h3>Logout</h3>
            </a>
        </div>
    </aside>
    <main>
        <h1>${deliveryItem.getCustomerName()}'s order</h1>
        <div class="analyse">
            <div class="sales">
                <div class="status">
                    <div class="info">
                        <h3>Restaurant's name</h3>
                        <h1>${deliveryItem.getRestaurantName()}</h1>
                    </div>
                </div>
            </div>
            <div class="sales">
                <div class="status">
                    <div class="info">
                        <h3>Status</h3>
                        <h1>${deliveryItem.getDeliveryStatus()}</h1>

                    </div>
                </div>
            </div>
            <div class="sales">
                <div class="status">
                    <div class="info">
                        <h3>Total price</h3>
                        <h1>${deliveryItem.getDeliveryPrice()}</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="recent-orders">
            <h2>Details</h2>
            <table>
                <thead>
                <tr>
                    <th>Dish</th>
                    <th>Options</th>
                    <th>Quantity</th>
                </tr>
                </thead>
                <c:forEach var="option" items="${dishOption}">
                    <a>
                        <tr>
                            <td><c:out value="${option.getDishName()}"/></td>
                            <td><c:out value="${option.getOptions()}"/></td>
                            <td><c:out value="${option.getDishQuantity()}"/></td>
                        </tr>
                    </a>
                </c:forEach>
            </table>
        </div>
    </main>
</div>
</body>
</html>
