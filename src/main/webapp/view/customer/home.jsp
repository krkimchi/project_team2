<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ - Hệ thống Đặt Món Ăn</title>

    <!-- Bootstrap CSS -->
    <c:import url="../../view/layout/header.jsp"/>
    <c:import url="../../view/layout/library.jsp"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/customer/home.css"/>
</head>
<body>

<!-- Main Content -->
<main class="container my-5">

    <h1 class="text-center mb-4"
        style="border: 1px solid #000; padding: 10px; background-color: #689ff2; border-radius: 5px;">Chào mừng đến với
        hệ thống đặt món ăn!</h1>

    <!-- Món Ăn Phổ Biến Section -->
    <section>
        <h2>Món Ăn Phổ Biến Nhất</h2>
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
                            <!-- Button to Add to Cart -->
                            <button class="btn btn-success mt-2" onclick="addToCart(${dish.id}, '${dish.name}')">
                                Thêm vào Giỏ Hàng
                            </button>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

    <!-- Nhà Hàng Gần Bạn Section -->
    <section>
        <h2>Nhà Hàng Gần Bạn</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="/resources/images/restaurant/ok.jpg" class="card-img-top" alt="Nhà Hàng 1">
                    <div class="card-body">
                        <h5 class="card-title">Nhà Hàng 1</h5>
                        <p class="card-text">Địa chỉ: 123 Main St, Hanoi</p>
                        <a href="restaurant_details.jsp?id=1" class="btn btn-primary">Xem chi tiết</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="/resources/images/restaurant/ok.jpg" class="card-img-top" alt="Nhà Hàng 2">
                    <div class="card-body">
                        <h5 class="card-title">Nhà Hàng 2</h5>
                        <p class="card-text">Địa chỉ: 456 Nguyen Thi, Hanoi</p>
                        <a href="restaurant_details.jsp?id=2" class="btn btn-primary">Xem chi tiết</a>
                    </div>
                </div>
            </div>

            <!-- Các nhà hàng khác sẽ được thêm vào đây -->
        </div>
    </section>

</main>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-3">
    <p>&copy; 2025 Hệ thống Đặt Món Ăn - Tất cả quyền được bảo vệ</p>
</footer>

<!-- Bootstrap JS and Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

<script>
    function addToCart(dishId, dishName) {
        console.log('Món ăn có ID ' + dishId + ' đã được thêm vào giỏ hàng.');
        alert('Món ăn ' + dishName + ' đã được thêm vào giỏ hàng.');

        // Tạo một form ẩn để gửi thông tin món ăn và số lượng vào giỏ hàng
        var form = document.createElement("form");
        form.method = "POST";
        form.action = "/customer"; // Địa chỉ xử lý giỏ hàng

        var dishIdInput = document.createElement("input");
        dishIdInput.type = "hidden";
        dishIdInput.name = "dish_id";
        dishIdInput.value = dishId;
        form.appendChild(dishIdInput);

        var quantityInput = document.createElement("input");
        quantityInput.type = "hidden";
        quantityInput.name = "quantity";
        quantityInput.value = 1; // Giả định rằng khách hàng chỉ thêm một món
        form.appendChild(quantityInput);

        // Thêm thông tin hành động vào form
        var actionInput = document.createElement("input");
        actionInput.type = "hidden";
        actionInput.name = "action";
        actionInput.value = "add_to_cart";
        form.appendChild(actionInput);

        // Thêm form vào body và submit
        document.body.appendChild(form);
        form.submit();
    }
</script>
</body>
</html>