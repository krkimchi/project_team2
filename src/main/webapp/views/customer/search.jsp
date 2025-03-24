<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Food</title>
    <c:import url="../layout/library.jsp"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/customer/home.css"/>
</head>
<body>
<h1>Search Result</h1>
<h2>Count: ${fn:length(foodList)}</h2>
<a href="/customer">Back to home page</a>

<main class="container my-5">
    <section>
        <div class="row">
            <c:forEach var="dish" items="${foodList}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="/resources/images/food/${dish.img}" class="card-img-top" alt="${dish.name}">
                        <div class="card-body">
                            <h5 class="card-title" style="font-weight: bold">${dish.name}</h5>
                            <p class="card-text">Price: ${dish.price}</p>
                            <p class="card-text">Restaurant: ${dish.restaurantName}</p>
                            <p class="card-text">Total Ordered: ${dish.totalOrdered}</p>
                            <a href="/customer?action=dish_details&id=${dish.id}" class="btn btn-primary">Xem chi tiết</a>
                            <button class="btn btn-success mt-2" onclick="addToCart('${dish.name}')">Thêm vào Giỏ Hàng</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</main>

<!-- Script để thêm món ăn vào giỏ hàng -->
<script>
    function addToCart(dishName) {
        console.log('Món ăn ' + dishName + ' đã được thêm vào giỏ hàng.');
        alert('Món ăn ' + dishName + ' đã được thêm vào giỏ hàng.');
    }
</script>
</body>
</html>