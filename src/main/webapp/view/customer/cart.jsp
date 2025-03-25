<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Giỏ Hàng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }
        .cart-container {
            margin: 50px auto;
            max-width: 800px;
        }
        .cart-item {
            border-bottom: 1px solid #ddd;
            padding: 15px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .cart-total {
            font-size: 1.2rem;
            font-weight: bold;
            margin-top: 20px;
        }
        .btn-action {
            margin-left: 10px;
        }
        .quantity-control {
            display: flex;
            align-items: center;
        }
        .quantity-control a {
            width: 30px;
            height: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
            text-decoration: none;
        }
        .quantity-control span {
            margin: 0 10px;
            width: 30px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="cart-container">
    <h1 class="text-center mb-4">Giỏ Hàng</h1>
    <c:if test="${empty sessionScope.customer.cart}">
        <div class="alert alert-warning text-center">
            Giỏ hàng của bạn đang trống! <a href="/customer">Tiếp tục mua sắm</a>
        </div>
    </c:if>

    <!-- Hiển thị danh sách món trong giỏ -->
    <c:if test="${not empty sessionScope.customer.cart}">
        <c:forEach var="item" items="${sessionScope.customer.cart}">
            <div class="cart-item">
                <div>
                    <h5>${item.food.name}</h5>
                    <p>Số lượng:
                    <div class="quantity-control">
                        <a href="/customer?action=update_cart&food_id=${item.food.id}&quantity=${item.quantity - 1}"
                           class="btn btn-outline-secondary" <c:if test="${item.quantity <= 1}">disabled</c:if>>-</a>
                        <span>${item.quantity}</span>
                        <a href="/customer?action=update_cart&food_id=${item.food.id}&quantity=${item.quantity + 1}"
                           class="btn btn-outline-secondary">+</a>
                    </div>
                    </p>
                    <p>Giá: <fmt:formatNumber value="${item.food.price}" type="number" groupingUsed="true"/> VND</p>
                    <p>Thành tiền: <fmt:formatNumber value="${item.food.price * item.quantity}" type="number" groupingUsed="true"/> VND</p>
                </div>
                <div>
                    <a href="/customer?action=remove_from_cart&food_id=${item.food.id}"
                       class="btn btn-danger btn-sm btn-action">Xóa</a>
                </div>
            </div>
        </c:forEach>

        <!-- Tổng tiền -->
        <div class="cart-total text-end">
            Tổng tiền: <fmt:formatNumber value="${total}" type="number" groupingUsed="true"/> VND
        </div>

        <!-- Nút đặt hàng -->
        <div class="text-end mt-3">
            <a href="/customer?action=place_order" class="btn btn-success">Đặt Hàng</a>
        </div>
    </c:if>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>