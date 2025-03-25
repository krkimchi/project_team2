<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 23/3/25
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
    <link rel="stylesheet" href="style.css">
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
            <a href="#">
        <span class="material-symbols-outlined">
          dashboard
        </span>
                <h3>Overview</h3>
            </a>
            <a href="#">
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
    <!--  end of sidebar-->

    <main>
        <h1>Orders</h1>
        <div class="recent-orders">
            <table>
                <thead>
                <tr>
                    <th>Customer</th>
                    <th>Restaurant</th>
                    <th>Cost</th>
                    <th>Distance</th>
                    <th></th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </main>
    <div class="right-section">
        <div class="nav">
            <button id="menu-btn">
                    <span class="material-symbols-outlined">
                      menu
                    </span>
            </button>
            <div class="dark-mode">
                    <span class="material-symbols-outlined">
                      light_mode
                    </span>
                <span class="material-symbols-outlined">
          dark_mode
        </span>
            </div>

            <div class="profile">
                <div class="info">
                    <p>Hey, <b>Semmar</b></p>
                    <small class="text-muted">Shipper</small>
                </div>
                <div class="profile-photo">
                    <img src="elements/default.jpg" alt="">
                </div>
            </div>

        </div>
        <!-- End of Nav -->

        <div class="user-profile">
            <div class="logo">
                <img src="elements/default.jpg" alt="">
                <h2>Semmar</h2>
                <p>Fulltime shipper</p>
            </div>
        </div>

        <div class="reminders">
            <div class="header">
                <h2>Your orders</h2>
                <span class="material-symbols-outlined">
          notifications
        </span>
            </div>

            <div class="notification">
                <div class="icon">
          <span class="material-symbols-outlined">
            list_alt
          </span>
                </div>
                <div class="content">
                    <div class="info">
                        <h3>Name</h3>
                        <small class="text_muted">
                            Destination
                        </small>
                    </div>
                    <span class="material-symbols-outlined">
            more_vert
          </span>
                </div>
            </div>

            <div class="notification deactive">
                <div class="icon">
          <span class="material-symbols-outlined">
            list_alt
          </span>
                </div>
                <div class="content">
                    <div class="info">
                        <h3>Name</h3>
                        <small class="text_muted">
                            Destination
                        </small>
                    </div>
                    <span class="material-symbols-outlined">
            more_vert
          </span>
                </div>
            </div>

            <div class="notification add-reminder">
                <div>
          <span class="material-symbols-outlined">
            read_more
          </span>
                    <h3>More order</h3>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>