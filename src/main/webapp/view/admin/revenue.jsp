<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Top 20 Restaurants and Shippers</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap');
        :root {
            --primary-color: #0D1936;
            --secondary-color: #535354;
            --background-color: #EFEFEF;
            --shadow-color: rgba(0, 0, 0, 0.1);
            --white-color: #FFF;
            --black-color: #000;
            --input-border-color: #E3E4E6;
            --transition-3s: 0.3s;
        }
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: var(--background-color);
        }
        .wrapper {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 20px;
            width: 100%;
            max-width: 1300px;
            background-color: var(--white-color);
            border-radius: 15px;
            padding: 20px;
            border: 1px solid var(--primary-color);
            box-shadow: 0 8px 15px var(--shadow-color);
            overflow: hidden;
            transition: var(--transition-3s);
        }
        .chart-container {
            width: 48%;
        }

        h1 {
            text-align: center;
            color: var(--primary-color);
        }
        canvas {
            margin: 20px 0;
            width: 100%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: var(--primary-color);
            color: var(--white-color);
        }
        tr:hover { background-color: #f5f5f5; }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="chart-container">
        <h1><i class="bx bx-chart icon"></i>Top 20 Restaurants Revenue</h1>
        <canvas id="revenueChart" width="400" height="200"></canvas>
        <script>
            const ctx = document.getElementById('revenueChart').getContext('2d');
            const labels = [];
            const data = [];

            <c:forEach var="revenue" items="${topRestaurants}">
            labels.push('${revenue.restaurantName} (${revenue.month}/${revenue.year})');
            data.push(${revenue.totalRevenue});
            </c:forEach>

            const chartData = {
                labels: labels,
                datasets: [{
                    label: 'RestaurantRevenue (in USD)',
                    data: data,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderWidth: 1
                }]
            };

            const config = {
                type: 'bar',
                data: chartData,
                options: {
                    responsive: true,
                    plugins: {
                        title: {
                            display: true,
                            text: 'Top 20 Restaurants RestaurantRevenue by Month'
                        }
                    }
                }
            };

            const revenueChart = new Chart(ctx, config);
        </script>
    </div>

    <div class="chart-container">
        <h1><i class="bx bx-chart icon"></i>Top 20 Shippers Revenue</h1>
        <canvas id="shipperChart" width="400" height="200"></canvas>
        <script>
            const shipperCtx = document.getElementById('shipperChart').getContext('2d');
            const shipperLabels = [];
            const shipperData = [];

            <c:forEach var="shipper" items="${topShippers}">
            shipperLabels.push('${shipper.shipperName}');
            shipperData.push(${shipper.totalOrders});
            </c:forEach>

            const shipperChartData = {
                labels: shipperLabels,
                datasets: [{
                    label: 'Total Orders',
                    data: shipperData,
                    borderColor: 'rgba(153, 102, 255, 1)',
                    backgroundColor: 'rgba(153, 102, 255, 0.2)',
                    borderWidth: 1
                }]
            };

            const shipperConfig = {
                type: 'bar',
                data: shipperChartData,
                options: {
                    responsive: true,
                    plugins: {
                        title: {
                            display: true,
                            text: 'Top 20 Shippers by Total Orders'
                        }
                    }
                }
            };

            const shipperChart = new Chart(shipperCtx, shipperConfig);
        </script>
    </div>
</div>
</body>
</html>
