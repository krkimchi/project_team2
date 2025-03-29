use food_ordering_system;

-- CUSTOMER --
-- 1. get_most_ordered_food --
DELIMITER //
CREATE PROCEDURE get_most_ordered_food(IN so_luong INT)
BEGIN
    SET @sql_query = CONCAT(
        'SELECT d.*, r.restaurant_name, SUM(od.dish_quantity) AS total_ordered
		FROM order_detail od
		JOIN dishes d ON od.dish_id = d.id
		JOIN orders o ON od.order_id = o.id
		JOIN restaurants r on d.restaurant_id = r.id
		GROUP BY od.dish_id
		ORDER BY total_ordered DESC
		LIMIT ', so_luong
    );
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //
DELIMITER ;

-- call get_most_ordered_food(5);

-- 2. search food --

DELIMITER //
CREATE PROCEDURE search_food(IN keyword VARCHAR(100))
BEGIN
    SELECT 
        d.*, 
        r.restaurant_name, 
        IFNULL(SUM(od.dish_quantity), 0) AS total_ordered 
    FROM dishes d
    LEFT JOIN order_detail od ON d.id = od.dish_id
    LEFT JOIN orders o ON od.order_id = o.id
    JOIN restaurants r ON d.restaurant_id = r.id
    WHERE d.dish_name LIKE CONCAT('%', keyword, '%')
    GROUP BY d.id, r.restaurant_name
    ORDER BY total_ordered DESC, d.dish_name asc;
END//
DELIMITER ;

-- call search_food("");

-- 3. get food by id ----
select * from dishes where dishes.id = 1;

-- 4. create order ---

-- insert into orders(customer_id, restaurant_id, customer_note, status, created_at) values(1,1,"test1","pending", now());

-- add order deatail --
-- insert into order_detail(order_id, dish_id, dish_quantity) values(?, ?, ?);

-- SHIPPER --
DELIMITER //
CREATE PROCEDURE `GetOrderDetails`(IN orderId INT)
BEGIN
    SELECT
        o.id AS OrderID,
        r.restaurant_name AS RestaurantName,
        u_customer.full_name AS CustomerName,
        GROUP_CONCAT(CONCAT(d.dish_name, ' (', od.dish_quantity, ')') SEPARATOR ', ') AS DishesWithQuantity,
        so.pickup_time AS PickupTime,
        so.delivered_time AS DeliveredTime,
        o.status AS OrderStatus, -- Changed to o.status
        SUM(d.dish_price * od.dish_quantity) AS TotalPrice
    FROM
        orders o
    LEFT JOIN
        shipper_orders so ON o.id = so.order_id
    JOIN
        restaurants r ON o.restaurant_id = r.id
    JOIN
        users u_customer ON o.customer_id = u_customer.id
    JOIN
        order_detail od ON o.id = od.order_id
    JOIN
        dishes d ON od.dish_id = d.id
    WHERE
        o.id = orderId
    GROUP BY
        o.id;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE `GetOrderDishesWithOptions`(IN orderId INT)
BEGIN
    SELECT
        od.id AS DishID, -- Unique ID for each dish in the order
        d.dish_name AS DishName,
        od.dish_quantity AS Quantity,
        GROUP_CONCAT(do.option_name SEPARATOR ', ') AS Options
    FROM
        orders o
    JOIN
        order_detail od ON o.id = od.order_id
    JOIN
        dishes d ON od.dish_id = d.id
    LEFT JOIN
        order_option_detail ood ON od.id = ood.order_detail_id
    LEFT JOIN
        dish_options do ON ood.dish_option_id = do.id
    WHERE
        o.id = orderId
    GROUP BY
        od.id, d.dish_name, od.dish_quantity; 
END//
DELIMITER ;

delimiter //
CREATE PROCEDURE `GetShipperDeliveryList`(IN shipperId INT)
BEGIN
    SELECT
        o.id AS OrderID,
        r.restaurant_name AS RestaurantName,
        u_customer.full_name AS CustomerName,
        GROUP_CONCAT(CONCAT(d.dish_name, ' (', od.dish_quantity, ')') SEPARATOR ', ') AS DishesWithQuantity,
        so.pickup_time AS PickupTime,
        so.delivered_time AS DeliveredTime,
        o.status AS OrderStatus, -- Changed to o.status
        SUM(d.dish_price * od.dish_quantity) AS TotalPrice
    FROM
        shipper_orders so
    JOIN
        orders o ON so.order_id = o.id
    JOIN
        restaurants r ON o.restaurant_id = r.id
    JOIN
        users u_customer ON o.customer_id = u_customer.id
    JOIN
        order_detail od ON o.id = od.order_id
    JOIN
        dishes d ON od.dish_id = d.id
    WHERE
        so.shipper_id = shipperId
    GROUP BY
        o.id;
END//
delimiter ;

DELIMITER //
CREATE PROCEDURE CountShipperOrders(IN shipperId INT)
BEGIN
    SELECT
        COUNT(o.id) AS NumberOfOrders
    FROM
        orders o
    JOIN
        shipper_orders so ON o.id = so.order_id
    WHERE
        so.shipper_id = shipperId;
END //
DELIMITER ;