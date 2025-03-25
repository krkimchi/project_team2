<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/26/2025
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>My Menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <style>
        :root {
            --primary: #0070f3;
            --primary-foreground: #ffffff;
            --secondary: #f5f5f5;
            --secondary-foreground: #111111;
            --accent: #ffb800;
            --accent-foreground: #111111;
            --background: #ffffff;
            --foreground: #111111;
            --card: #ffffff;
            --card-foreground: #111111;
            --border: #e5e5e5;
            --input: #e5e5e5;
            --ring: #0070f3;
            --radius: 0.5rem;
            --shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1);
        }

        body {
            background-color: var(--background);
            color: var(--foreground);
        }

        .menu-item-img {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: var(--radius);
        }

        .menu-item-title {
            margin-bottom: 0.25rem;
            color: var(--card-foreground);
        }

        .menu-item-description {
            font-size: 0.875rem;
            color: var(--secondary-foreground);
            margin-bottom: 0.25rem;
        }

        .menu-item-price {
            font-weight: bold;
            color: var(--primary);
            margin-bottom: 0;
        }

        .dish-detail-img {
            width: 100%;
            max-width: 300px;
            height: auto;
            border-radius: var(--radius);
            margin-bottom: 1rem;
        }

        .dish-stats p {
            margin-bottom: 0.5rem;
        }

        @media (max-width: 768px) {
            .menu-item-img {
                width: 60px;
                height: 60px;
            }

            .menu-item-title {
                font-size: 1rem;
            }

            .menu-item-description {
                font-size: 0.8rem;
            }
        }
    </style>
</head>
<body>
<c:import url="../../view/layout/owner_header.jsp"/>
<div class="card shadow-sm">
    <div class="card-body">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="card-title">Menu Management</h3>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addDishModal">
                <i class="bi bi-plus-circle"></i> Add New Dish
            </button>
        </div>

        <div class="menu-list">
            <c:forEach items="${dishList}" var="dish">
                <div class="menu-item card mb-3">
                    <div class="card-body">
                        <div class="row align-items-center">
                            <div class="col-auto">
                                <img src="/resources/images/food/${dish.getDishImg()}" class="menu-item-img"
                                     alt="Menu Item">
                            </div>
                            <div class="col">
                                <h5 class="menu-item-title">${dish.getDishName()}</h5>
                                <p class="menu-item-description">${dish.getDescription()}</p>
                                <p class="menu-item-price">${dish.getDishPrice()} vnd</p>
                                <span class="badge bg-success">Available</span>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-sm btn-info me-2" data-bs-toggle="modal"
                                        data-bs-target="#dishDetailModal">
                                    <i class="bi bi-info-circle"></i>
                                </button>
                                <button class="btn btn-sm btn-danger">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<div class="modal fade" id="addDishModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add New Dish</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label class="form-label">Dish Name</label>
                        <input type="text" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <textarea class="form-control" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Price</label>
                        <input type="number" class="form-control" step="0.01">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Image</label>
                        <input type="file" class="form-control">
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="availabilityCheck" checked>
                        <label class="form-check-label" for="availabilityCheck">Available for Order</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary">Add Dish</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="dishDetailModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Dish Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="text-center mb-3">
                    <img src="https://images.unsplash.com/photo-1551183053-bf91a1d81141" class="dish-detail-img" alt="Dish">
                </div>
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="mb-0">Spaghetti Carbonara</h4>
                    <span class="badge bg-success">Available</span>
                </div>
                <p class="text-muted">Classic Italian pasta dish</p>
                <div class="dish-stats">
                    <p><strong>Price:</strong> $16.99</p>
                    <p><strong>Category:</strong> Pasta</p>
                    <p><strong>Preparation Time:</strong> 20 minutes</p>
                    <p><strong>Allergens:</strong> Eggs, Dairy, Wheat</p>
                </div>
                <div class="dish-description mt-3">
                    <h5>Description</h5>
                    <p>Classic Italian pasta with eggs, cheese, pancetta, and black pepper. Served with freshly grated Pecorino Romano cheese.</p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>`
</body>
</html>
