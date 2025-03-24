<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Giỏ hàng</title>
</head>
<body>
<h1>Giỏ Hàng</h1>
<c:forEach var="item" items="${sessionScope.cart}">
    <div>
        <p>${item.food.name} - Số lượng: ${item.quantity} - Giá: ${item.food.price} VND</p>
    </div>
</c:forEach>

<!-- Tổng tiền -->
<p>Tổng tiền: ${total}</p>

<!-- Thêm các nút thao tác như đặt hàng, xóa món khỏi giỏ... -->

</body>
</html>
