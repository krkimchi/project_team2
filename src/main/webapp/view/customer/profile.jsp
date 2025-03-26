<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Thông Tin Người Dùng</title>
    <!-- Bootstrap CSS -->
    <c:import url="../../view/layout/library.jsp"/>
    <c:import url="../../view/layout/header.jsp"/>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }
        .profile-container {
            margin: 50px auto;
            max-width: 600px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .profile-avatar {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            margin-bottom: 20px;
        }
        .profile-info {
            margin-bottom: 15px;
        }
        .profile-info label {
            font-weight: bold;
            width: 150px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="profile-container">
    <h2 class="text-center mb-4">Thông Tin Người Dùng</h2>

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

    <!-- Hiển thị thông tin người dùng -->
    <c:if test="${not empty user}">
        <div class="text-center">
            <img src="${user.avatarUrl != null ? user.avatarUrl : '/resources/images/default-avatar.jpg'}"
                 alt="Avatar" class="profile-avatar">
        </div>
        <div class="profile-info">
            <label>Họ và tên:</label>
            <span>${user.fullName != null ? user.fullName : 'Chưa cập nhật'}</span>
        </div>
        <div class="profile-info">
            <label>Tên người dùng:</label>
            <span>${user.username}</span>
        </div>
        <div class="profile-info">
            <label>Email:</label>
            <span>${user.email}</span>
        </div>
        <div class="profile-info">
            <label>Số điện thoại:</label>
            <span>${user.phone != null ? user.phone : 'Chưa cập nhật'}</span>
        </div>
        <div class="profile-info">
            <label>Ngày tạo:</label>
            <span>${user.createdAt}</span>
        </div>
        <div class="text-center mt-4">
            <a href="/customer" class="btn btn-primary">Quay lại</a>
        </div>
    </c:if>

    <!-- Nếu không tìm thấy người dùng -->
    <c:if test="${empty user}">
        <div class="alert alert-warning text-center">
            Không tìm thấy thông tin người dùng! <a href="/customer">Quay lại</a>
        </div>
    </c:if>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>