<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Lịch Sử Đơn Hàng</title>
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
        .order-card {
            margin-bottom: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .order-card h5 {
            margin-bottom: 10px;
        }
        .order-card p {
            margin-bottom: 5px;
        }
        .status-pending { color: #ffc107; }
        .status-shipping { color: #17a2b8; }
        .status-completed { color: #28a745; }
        .status-cancelled { color: #dc3545; }
    </style>
</head>
<body>
<!-- Include header -->
<c:import url="../layout/header.jsp"/>

<div class="container">
    <h2 class="text-center mb-4">Lịch Sử Đơn Hàng</h2>

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

    <!-- Hiển thị danh sách đơn hàng -->
    <c:if test="${not empty requestScope.orders}">
        <c:forEach var="order" items="${requestScope.orders}">
            <div class="order-card">
                <h5>Mã đơn hàng #${order.id} - <fmt:formatDate value="${order.getCreatedAtAsDate()}" pattern="dd/MM/yyyy HH:mm"/></h5>
                <p><strong>Nhà hàng:</strong> ${order.restaurantName}</p>
                <p><strong>Shipper:</strong> ${order.shipperId != null ? order.shipperName : 'Chưa có shipper'}</p>
                <p><strong>Trạng thái:</strong>
                    <span class="status-${order.status.toLowerCase()}">
                        <c:choose>
                            <c:when test="${order.status == 'PENDING'}">Đang xử lý</c:when>
                            <c:when test="${order.status == 'SHIPPING'}">Đang giao</c:when>
                            <c:when test="${order.status == 'COMPLETED'}">Hoàn thành</c:when>
                            <c:when test="${order.status == 'CANCELLED'}">Đã hủy</c:when>
                            <c:otherwise>${order.status}</c:otherwise>
                        </c:choose>
                    </span>
                </p>
                <p><strong>Chi tiết món ăn:</strong></p>
                <ul>
                    <c:forEach var="detail" items="${order.orderDetails}">
                        <li>${detail.dishName} - Số lượng: ${detail.dishQuantity}</li>
                    </c:forEach>
                </ul>
                <p><strong>Tổng giá:</strong> ${order.totalPrice}</p>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${empty requestScope.orders}">
        <div class="alert alert-warning text-center">
            Bạn chưa có đơn hàng nào.
        </div>
    </c:if>

    <div class="text-center mt-4">
        <a href="/customer" class="btn btn-primary">Quay lại trang chủ</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>