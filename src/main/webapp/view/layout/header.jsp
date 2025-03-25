<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  body {
    font-family: 'Arial', sans-serif;
    background-color: #f1f1f1;
    color: #343a40;
    line-height: 1.6;
  }

  /* Navbar Styling */
  .navbar {
    padding: 15px;
    background-color: #343a40;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }

  .navbar-nav .nav-link {
    color: #fff !important;
    font-weight: bold;
    text-transform: uppercase;
    padding: 10px 20px;
  }

  .navbar-nav .nav-link:hover {
    color: #689ff2 !important;
  }
</style>
<!-- Header with Navigation -->
<header class="bg-dark text-white p-3">
  <nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="home.jsp">Hệ thống Đặt Món Ăn</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="/customer">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="/customer?action=cart">Carts</a></li>
        <li class="nav-item"><a class="nav-link" href="/customer?action=order_history">Oder History</a></li>
        <li class="nav-item"><a class="nav-link" href="/customer?action=profile">My Profile</a></li>
        <li class="nav-item"><a class="nav-link" href="/customer?action=logout">Logout</a></li>
      </ul>
    </div>
    <form action="/customer" method="get" class="d-flex justify-content-end align-items-center">
      <input hidden="hidden" name="action" value="search_dishes">
      <input value="${searchName}" class="form-control form-control-sm w-50" name="searchName"
             placeholder="Nhập tên món cần tìm kiếm" aria-label="Search"
             style="height: 38px; vertical-align: middle; margin-right: 8px;">
      <button class="btn btn-primary btn-sm ml-2"
              style="height: 38px; vertical-align: middle; line-height: 1.5;">Tìm kiếm</button>
    </form>
  </nav>
</header>