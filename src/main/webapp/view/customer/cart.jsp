<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Giỏ Hàng</title>
    <!-- Bootstrap CSS -->
    <c:import url="../../view/layout/library.jsp"/>
    <c:import url="../../view/layout/header.jsp"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/customer/cart.css">
</head>
<body>
<div class="cart-container">
    <h1 class="text-center mb-4">Giỏ Hàng</h1>

    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-success text-center">
                ${sessionScope.message}
            <c:remove var="message" scope="session"/> <!-- Xóa thông báo sau khi hiển thị -->
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-danger text-center">
                ${sessionScope.error}
            <c:remove var="error" scope="session"/> <!-- Xóa thông báo lỗi sau khi hiển thị -->
        </div>
    </c:if>

    <!-- Kiểm tra giỏ hàng rỗng -->
    <c:if test="${empty sessionScope.customer.cart}">
        <div class="alert alert-warning text-center">
            Giỏ hàng của bạn đang trống! <a href="/customer">Tiếp tục mua sắm</a>
        </div>
    </c:if>

    <!-- Hiển thị danh sách món trong giỏ -->
    <c:if test="${not empty sessionScope.customer.cart}">
        <c:forEach var="item" items="${sessionScope.customer.cart}">
            <div class="cart-item">
                <!-- Ảnh nhỏ bên trái -->
                <img src="/resources/images/food/${item.food.foodImg}" alt="${item.food.name}">

                <!-- Thông tin bên phải -->
                <div class="cart-item-details">
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
            </div>
        </c:forEach>

        <!-- Tổng tiền -->
        <div class="cart-total text-end">
            Tổng tiền: <fmt:formatNumber value="${total}" type="number" groupingUsed="true"/> VND
        </div>

        <!-- Trường nhập ghi chú và nút đặt hàng -->
        <form action="/customer" method="post" accept-charset="UTF-8">
            <input type="hidden" name="action" value="place_order">
            <div class="order-note">
                <label for="orderNote" class="form-label">Ghi chú cho đơn hàng:</label>
                <textarea class="form-control" id="orderNote" name="note" rows="3" placeholder="Nhập ghi chú (nếu có)"></textarea>
            </div>
            <div class="text-end mt-3">
                <button type="submit" class="btn btn-success">Đặt Hàng</button>
            </div>
        </form>
    </c:if>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>