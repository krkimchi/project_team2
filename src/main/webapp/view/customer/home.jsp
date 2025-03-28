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
<%--                            <button class="btn btn-success mt-2" onclick="addToCart(${dish.id}, '${dish.name}')">--%>
<%--                                Thêm vào Giỏ Hàng--%>
<%--                            </button>--%>
                            <button onclick="addToCart(`${dish.getId()}`, `${dish.getName()}`)" type="button"
                                    class="btn btn-success mt-2" data-bs-toggle="modal" data-bs-target="#notifyModal">
                                Thêm vào giỏ hàng
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