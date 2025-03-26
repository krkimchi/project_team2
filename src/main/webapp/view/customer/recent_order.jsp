<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Đơn Hàng Vừa Đặt</title>
    <!-- Bootstrap CSS -->
    <c:import url="../../view/layout/header.jsp"/>
    <c:import url="../../view/layout/library.jsp"/>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }
        .order-container {
            margin: 50px auto;
            max-width: 800px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .order-info {
            margin-bottom: 15px;
        }
        .order-info label {
            font-weight: bold;
            width: 150px;
            display: inline-block;
        }
        .order-items table {
            width: 100%;
            margin-top: 20px;
        }
        .order-items th, .order-items td {
            padding: 10px;
            text-align: left;
        }
        .order-items th {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<!-- Include header -->

<div class="order-container">
    <h2 class="text-center mb-4">Đơn Hàng Vừa Đặt</h2>

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

    <!-- Hiển thị thông tin đơn hàng -->
    <c:if test="${not empty requestScope.recentOrder}">
        <div class="order-info">
            <label>Mã đơn hàng:</label>
            <span>${requestScope.recentOrder.id}</span>
        </div>
        <div class="order-info">
            <label>Trạng thái:</label>
            <span>${requestScope.recentOrder.status}</span>
        </div>
        <div class="order-info">
            <label>Thời gian đặt:</label>
            <span><fmt:formatDate value="${requestScope.recentOrder.createdAtAsDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
        </div>
        <div class="order-info">
            <label>Ghi chú:</label>
            <span>${requestScope.recentOrder.customerNote != null ? requestScope.recentOrder.customerNote : 'Không có'}</span>
        </div>
        <div class="order-info">
            <label>Nhà hàng:</label>
            <span>Nhà hàng ID: ${requestScope.recentOrder.restaurantId}</span>
        </div>
        <div class="order-info">
            <label>Shipper:</label>
            <span>
                    <c:choose>
                        <c:when test="${requestScope.recentOrder.shipperId == null || requestScope.recentOrder.shipperId == 0}">
                            Đang tìm shipper...
                        </c:when>
                        <c:otherwise>
                            Shipper ID: ${requestScope.recentOrder.shipperId}
                        </c:otherwise>
                    </c:choose>
                </span>
        </div>

        <!-- Hiển thị danh sách món trong đơn hàng (nếu có) -->
        <c:if test="${not empty requestScope.recentOrder.items}">
            <div class="order-items">
                <h5>Danh sách món</h5>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Tên món</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Tổng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${requestScope.recentOrder.items}">
                        <tr>
                            <td>${item.food.name}</td>
                            <td><fmt:formatNumber value="${item.food.price}" type="currency" currencySymbol="₫"/></td>
                            <td>${item.quantity}</td>
                            <td><fmt:formatNumber value="${item.food.price * item.quantity}" type="currency" currencySymbol="₫"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <div class="text-center mt-4">
            <a href="/customer" class="btn btn-primary">Quay lại</a>
        </div>
    </c:if>

    <!-- Nếu không có đơn hàng -->
    <c:if test="${empty requestScope.recentOrder}">
        <div class="alert alert-warning text-center">
            Không có đơn hàng nào vừa đặt! <a href="/customer">Quay lại</a>
        </div>
    </c:if>
</div>

<!-- Bootstrap JS -->
</body>
</html>