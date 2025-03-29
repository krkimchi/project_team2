<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Món Ăn - <c:out value="${requestScope.restaurant != null ? requestScope.restaurant.restaurantName : 'Không xác định'}"/></title>
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
    .food-card {
      margin-bottom: 20px;
      padding: 20px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      display: flex;
      align-items: center;
    }
    .food-card img {
      width: 100px;
      height: 100px;
      object-fit: cover;
      border-radius: 5px;
      margin-right: 20px;
    }
    .food-card h5 {
      margin-bottom: 10px;
    }
    .food-card p {
      margin-bottom: 5px;
    }
    .quantity-input {
      width: 60px;
      display: inline-block;
      margin-right: 10px;
    }
  </style>
</head>
<body>
<!-- Include header -->
<c:import url="../layout/header.jsp"/>

<div class="container">
  <h2 class="text-center mb-4">Món Ăn - <c:out value="${requestScope.restaurant != null ? requestScope.restaurant.restaurantName : 'Không xác định'}"/></h2>

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

  <!-- Hiển thị danh sách món ăn -->
  <c:if test="${not empty requestScope.foods}">
    <div class="row">
      <c:forEach var="food" items="${requestScope.foods}">
        <div class="col-md-6">
          <div class="food-card">
            <c:choose>
              <c:when test="${not empty food.foodImg}">
                <img src="/resources/images/food/${food.foodImg}" alt="${food.name}">
              </c:when>
              <c:otherwise>
                <img src="https://via.placeholder.com/100" alt="Default Image">
              </c:otherwise>
            </c:choose>
            <div>
              <h5>${food.name}</h5>
              <p><strong>Giá:</strong> <fmt:formatNumber value="${food.price}" type="currency" currencySymbol="₫"/></p>
              <p><strong>Mô tả:</strong> ${food.description != null ? food.description : 'Không có mô tả'}</p>
              <button onclick="addToCart(`${food.getId()}`, `${food.getName()}`)" type="button"
                      class="btn btn-success btn-sm d-flex align-items-center" data-bs-toggle="modal" data-bs-target="#notifyModal">
                Thêm vào giỏ hàng
              </button>

<%--              <form action="/customer" method="post" class="d-flex align-items-center">--%>
<%--                <input type="hidden" name="action" value="addToCart">--%>
<%--                <input type="hidden" name="dish_id" value="${food.id}">--%>
<%--                <input type="number" name="quantity" value="1" min="1" class="form-control quantity-input">--%>
<%--                <button type="submit" class="btn btn-success btn-sm">Thêm vào giỏ hàng</button>--%>
<%--              </form>--%>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>

  <c:if test="${empty requestScope.foods}">
    <div class="alert alert-warning text-center">
      Nhà hàng này chưa có món ăn nào.
    </div>
  </c:if>

  <div class="text-center mt-4">
    <a href="/customer?action=list_restaurant" class="btn btn-primary">Quay lại danh sách nhà hàng</a>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<%--Modal thông báo--%>
<div class="modal fade" id="notifyModal" tabindex="-1" aria-labelledby="labelModal" aria-hidden="true">
  <div class="modal-dialog">
    <form action="/customer" method="post">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="labelModal">Thông báo</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <input hidden="hidden" name="action" value="add_to_cart">
          <input hidden="hidden" id="dishId" name="dish_id">
          <span>Bạn có muốn thêm món: </span><span id="dishName"> vào giỏ hàng ?</span>

          <!-- Trường nhập số lượng -->
          <div class="mt-3">
            <label for="quantity">Số lượng: </label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" class="form-control">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
          <button type="submit" class="btn btn-primary">Ok</button>
        </div>
      </div>
    </form>
  </div>
</div>

<!-- Bootstrap JS and Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

<script>
  function addToCart(dishId, dishName) {
    // Cập nhật thông tin món ăn vào modal
    document.getElementById("dishId").value = dishId;
    document.getElementById("dishName").innerText = dishName;

    // Hiển thị modal
    var myModal = new bootstrap.Modal(document.getElementById('notifyModal'));
    myModal.show();
  }
</script>
</body>
</html>