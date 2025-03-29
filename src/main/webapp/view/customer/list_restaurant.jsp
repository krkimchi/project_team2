<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh Sách Nhà Hàng</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f1f1f1;
      color: #343a40;
      line-height: 1.6;
    }
    .container {
      margin-top: 30px;
    }
    .restaurant-card {
      margin-bottom: 20px;
      padding: 20px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      display: flex;
      align-items: center;
    }
    .restaurant-card img {
      width: 100px;
      height: 100px;
      object-fit: cover;
      border-radius: 5px;
      margin-right: 20px;
    }
    .restaurant-card h5 {
      margin-bottom: 10px;
    }
    .restaurant-card p {
      margin-bottom: 5px;
    }
  </style>
</head>
<body>
<!-- Include header -->
<c:import url="../layout/header.jsp"/>

<div class="container">
  <h2 class="text-center mb-4">Danh Sách Nhà Hàng</h2>

  <!-- Hiển thị thông báo nếu có -->
  <c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success text-center">
        ${sessionScope.message}
      <c:remove var="message" scope="session"/>
    </div>
  </c:if>
  <c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger text-center">
        ${sessionScope.error}
      <c:remove var="error" scope="session"/>
    </div>
  </c:if>

  <!-- Hiển thị danh sách nhà hàng -->
  <c:if test="${not empty requestScope.restaurants}">
    <div class="row">
      <c:forEach var="restaurant" items="${requestScope.restaurants}">
        <div class="col-md-6">
          <div class="restaurant-card">
            <c:choose>
              <c:when test="${not empty restaurant.restaurantLogo}">
                <img src="/resources/images/restaurant/${restaurant.restaurantLogo}" alt="${restaurant.restaurantName}">
              </c:when>
              <c:otherwise>
                <img src="https://via.placeholder.com/100" alt="Default Logo">
              </c:otherwise>
            </c:choose>
            <div>
              <h5>${restaurant.restaurantName}</h5>
              <p><strong>Địa chỉ:</strong> ${restaurant.restaurantAddress}</p>
              <p><strong>Số điện thoại:</strong> ${restaurant.restaurantPhone}</p>
              <p><strong>Trạng thái:</strong> ${restaurant.open ? 'Đang mở' : 'Đóng cửa'}</p>
              <a href="/customer?action=view_dishes&restaurantId=${restaurant.id}" class="btn btn-primary btn-sm">Xem món ăn</a>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>

  <c:if test="${empty requestScope.restaurants}">
    <div class="alert alert-warning text-center">
      Không có nhà hàng nào để hiển thị.
    </div>
  </c:if>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>