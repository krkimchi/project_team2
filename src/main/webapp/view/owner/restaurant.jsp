<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/24/2025
  Time: 1:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>My Restaurant</title>
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

        .restaurant-logo {
            width: 150px;
            height: 150px;
            object-fit: cover;
            box-shadow: var(--shadow);
        }

        .card {
            background: var(--card);
            border-radius: var(--radius);
            border-color: var(--border);
        }

        .card-title {
            color: var(--card-foreground);
            font-size: 1.75rem;
            font-weight: 600;
        }

        .restaurant-details p {
            margin-bottom: 0.75rem;
            color: var(--card-foreground);
        }

        .btn-primary {
            background-color: var(--primary);
            border-color: var(--primary);
            color: var(--primary-foreground);
        }

        .btn-secondary {
            background-color: var(--secondary);
            border-color: var(--secondary);
            color: var(--secondary-foreground);
        }

        .modal-content {
            background-color: var(--background);
            border-radius: var(--radius);
        }

        .form-control {
            border-color: var(--input);
            border-radius: var(--radius);
        }

        .form-control:focus {
            border-color: var(--ring);
            box-shadow: 0 0 0 2px rgba(0, 112, 243, 0.2);
        }

        @media (max-width: 768px) {
            .restaurant-logo {
                width: 120px;
                height: 120px;
            }

            .card-title {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
<div class="container-fluid p-4">
    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
            <div class="text-center mb-4">
                <img src="/resources/images/restaurant/${restaurant.getRestaurantLogo()}"
                     class="restaurant-logo rounded-circle" alt="Restaurant Logo">
            </div>
            <div class="card shadow-sm">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h2 class="card-title">${restaurant.getRestaurantName()}</h2>
                        <c:choose>
                            <c:when test="${restaurant.isOpen()}">
                                <span class="badge bg-success fs-6">Open Now</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger fs-6">Closed</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="restaurant-details">
                        <p><strong>Owner:</strong> ${restaurant.getOwnerName()}</p>
                        <p><strong>Contact:</strong> ${restaurant.getRestaurantPhone()}</p>
                        <p><strong>Address:</strong> ${restaurant.getRestaurantAddress()}</p>
                    </div>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-start mt-4">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#editModal">
                            <i class="bi bi-pencil-square"></i> Edit Information
                        </button>
                        <button type="button" class="btn btn-secondary">
                            <i class="bi bi-menu-button-wide"></i> View Menu
                        </button>
                        <c:choose>
                            <c:when test="${restaurant.isOpen()}">
                                <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                        data-bs-target="#closeRestaurantModal">
                                    <i class="bi bi-x-circle">Close Restaurant</i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-success" data-bs-toggle="modal"
                                        data-bs-target="#openRestaurantModal">
                                    <i class="bi bi-x-circle">Open Restaurant</i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit Restaurant Information</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="restaurantName" class="form-label">Restaurant Name</label>
                        <input type="text" class="form-control" id="restaurantName" value="Italian Delight">
                    </div>
                    <div class="mb-3">
                        <label for="ownerName" class="form-label">Owner Name</label>
                        <input type="text" class="form-control" id="ownerName" value="Maria Romano">
                    </div>
                    <div class="mb-3">
                        <label for="contact" class="form-label">Contact Number</label>
                        <input type="tel" class="form-control" id="contact" value="+1 (555) 123-4567">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <textarea class="form-control" id="address"
                                  rows="3">123 Pasta Street, Italian Quarter, NY 10001</textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="closeRestaurantModal" tabindex="-1" aria-labelledby="closeRestaurantModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="closeRestaurantModalLabel">Close Restaurant</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to close the restaurant? This will mark the restaurant as closed.</p>
            </div>
            <div class="modal-footer">
                <form method="get" action="/restaurants">
                    <input type="hidden" name="action" value="closeRestaurant">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Confirm Close</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="openRestaurantModal" tabindex="-1" aria-labelledby="openRestaurantModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="openRestaurantModalLabel">Open Restaurant</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Are you ready to open the restaurant? This will mark the restaurant as open now.</p>
            </div>
            <div class="modal-footer">
                <form method="get" action="/restaurants">
                    <input type="hidden" name="action" value="openRestaurant">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-success">Confirm Open</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
