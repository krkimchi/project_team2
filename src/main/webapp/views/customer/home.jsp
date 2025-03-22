<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ - Hệ thống Đặt Món Ăn</title>

    <!-- Bootstrap CSS -->
    <c:import url="../layout/library.jsp"/>

    <!-- Tùy chỉnh CSS -->
    <style>
        /* General Styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
        }

        /* Navbar Styling */
        .navbar {
            padding: 15px;
        }

        .navbar-nav .nav-link {
            color: #fff !important;
        }

        .navbar-nav .nav-link:hover {
            color: #f8f9fa !important;
        }

        /* Food and Restaurant Card Styling */
        .card {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            transition: all 0.3s ease;
        }

        .card:hover {
            transform: scale(1.05);
        }

        .card-img-top {
            height: 200px;
            object-fit: cover;
        }

        /* Footer Styling */
        footer {
            background-color: #343a40;
            color: #f8f9fa;
            padding: 20px 0;
        }

        /* Food List Layout */
        .food-list {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
        }

        .food-item {
            width: 30%;
            margin: 15px 0;
            text-align: center;
        }

        /* Restaurant List Layout */
        .restaurant-list {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
        }

        .restaurant-item {
            width: 30%;
            margin: 15px 0;
            text-align: center;
        }

        /* Button Styling */
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }

        .btn-success:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }
        /* Card body for aligning buttons */
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 200px; /* Set a fixed height to prevent cards from expanding unevenly */
        }

        .card-body .btn {
            margin-top: 10px;
        }

        .card-body .btn-success {
            margin-top: auto; /* Push the "Add to Cart" button to the bottom */
        }

        /* Ensuring the buttons are aligned horizontally */
        .card-body .d-flex {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }


    </style>
</head>
<body>

<!-- Header with Navigation -->
<header class="bg-dark text-white p-3">
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="home.jsp">Hệ thống Đặt Món Ăn</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="home.jsp">Trang Chủ</a></li>
                <li class="nav-item"><a class="nav-link" href="/customer?action=search_dishes">Tìm Kiếm Món Ăn</a></li>
                <li class="nav-item"><a class="nav-link" href="/customer?action=cart">Giỏ Hàng</a></li>
                <li class="nav-item"><a class="nav-link" href="/customer?action=order_history">Lịch Sử Đơn Hàng</a></li>
                <li class="nav-item"><a class="nav-link" href="/customer?action=profile">Thông Tin Cá Nhân</a></li>
                <li class="nav-item"><a class="nav-link" href="/customer?action=logout">Đăng Xuất</a></li>
            </ul>
        </div>
    </nav>
</header>

<!-- Main Content -->
<main class="container my-5">

    <h1 class="text-center mb-4">Chào mừng đến với hệ thống đặt món ăn!</h1>

    <!-- Món Ăn Phổ Biến Section -->
    <section>
        <h2>Món Ăn Phổ Biến</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="path_to_image.jpg" class="card-img-top" alt="Pho Bo">
                    <div class="card-body">
                        <h5 class="card-title">Pho Bo</h5>
                        <p class="card-text">Giá: 50,000 VND</p>
                        <a href="dish_details.jsp?id=1" class="btn btn-primary">Xem chi tiết</a>
                        <button class="btn btn-success mt-2" onclick="addToCart(1)">Thêm vào Giỏ Hàng</button>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="path_to_image.jpg" class="card-img-top" alt="Bun Cha">
                    <div class="card-body">
                        <h5 class="card-title">Bun Cha</h5>
                        <p class="card-text">Giá: 40,000 VND</p>
                        <a href="dish_details.jsp?id=2" class="btn btn-primary">Xem chi tiết</a>
                        <button class="btn btn-success mt-2" onclick="addToCart(2)">Thêm vào Giỏ Hàng</button>
                    </div>
                </div>
            </div>

            <!-- Các món ăn khác sẽ được thêm vào đây -->
        </div>
    </section>

    <!-- Nhà Hàng Gần Bạn Section -->
    <section>
        <h2>Nhà Hàng Gần Bạn</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Nhà Hàng 1</h5>
                        <p class="card-text">Địa chỉ: 123 Main St, Hanoi</p>
                        <a href="restaurant_details.jsp?id=1" class="btn btn-primary">Xem chi tiết</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card">
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
    function addToCart(dishId) {
        // Giả lập việc thêm món vào giỏ hàng
        console.log('Món ăn có ID ' + dishId + ' đã được thêm vào giỏ hàng.');
        alert('Món ăn đã được thêm vào giỏ hàng!');
    }
</script>
</body>
</html>