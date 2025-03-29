insert into users (username, password, email, phone, full_name, address, avatar_url, user_type)
values
('c', 'c', 'c@example.com', '0123456145', 'Nguyen Van C', 'Ho Chi Minh', 'avatar1.jpg', 'customer'),
('customer1', 'password1', 'customer1@example.com', '0123456789', 'Nguyen Van A', 'Ho Chi Minh', 'avatar1.jpg', 'customer'),
('customer2', 'password2', 'customer2@example.com', '0123456788', 'Nguyen Thi B', 'Ho Chi Minh', 'avatar2.jpg', 'customer'),
('customer3', 'password3', 'customer3@example.com', '0123456787', 'Le Minh C', 'Ho Chi Minh', 'avatar3.jpg', 'customer'),
('customer4', 'password4', 'customer4@example.com', '0123456786', 'Nguyen Thi D', 'Ho Chi Minh', 'avatar4.jpg', 'customer'),
('customer5', 'password5', 'customer5@example.com', '0123456785', 'Tran Minh E', 'Ho Chi Minh', 'avatar5.jpg', 'customer'),
('customer6', 'password6', 'customer6@example.com', '0123456784', 'Phan Thi F', 'Ho Chi Minh', 'avatar6.jpg', 'customer'),
('customer7', 'password7', 'customer7@example.com', '0123456783', 'Nguyen Thi G', 'Ho Chi Minh', 'avatar7.jpg', 'customer'),
('shipper1', 'shipper1', 'shipper1@example.com', '0123456782', 'Hoang Minh H', 'Ho Chi Minh', 'avatar8.jpg', 'shipper'),
('owner1', 'password3', 'owner1@example.com', '0123456787', 'Le Minh C', 'Ho Chi Minh', 'avatar3.jpg', 'owner'),
('admin', 'admin', 'admin@example.com', '0123456786', 'Admin User', 'Ho Chi Minh', 'avatar4.jpg', 'admin');

insert into restaurants (owner_id, restaurant_name, restaurant_address, restaurant_phone, restaurant_logo)
values
(3, 'Pizza Hut', 'Ho Chi Minh', '0901234567', 'restaurant_logo.jpg'),
(3, 'KFC', 'Ha Noi', '0907654321', 'restaurant_logo.jpg'),
(3, 'Burger King', 'Ho Chi Minh', '0908765432', 'restaurant_logo.jpg'),
(3, 'Sushi World', 'Ha Noi', '0905432167', 'restaurant_logo.jpg');

insert into dishes (restaurant_id, dish_name, dish_price, dish_img, description)
values
(1, 'Pizza Margherita', 12.99, 'pizza1.jpg', 'Classic pizza with mozzarella and tomato sauce'),
(1, 'Pizza Pepperoni', 14.99, 'pizza2.jpg', 'Pizza with pepperoni and mozzarella'),
(1, 'Pizza Veggie', 13.99, 'pizza3.jpg', 'Pizza with assorted vegetables'),
(1, 'Pizza BBQ Chicken', 15.99, 'pizza4.jpg', 'Pizza with BBQ chicken and mozzarella'),
(1, 'Pizza Hawaiian', 16.99, 'pizza5.jpg', 'Pizza with ham, pineapple, and mozzarella'),
(2, 'Chicken Bucket', 20.99, 'chicken1.jpg', 'Fried chicken bucket'),
(2, 'Zinger Burger', 5.99, 'burger1.jpg', 'Spicy chicken burger'),
(2, 'Spicy Wings', 7.99, 'wings1.jpg', 'Spicy fried chicken wings'),
(2, 'Chicken Sandwich', 6.49, 'sandwich1.jpg', 'Fried chicken sandwich with mayo'),
(2, 'Fries', 3.99, 'fries1.jpg', 'Crispy French fries'),
(3, 'Whopper', 8.99, 'whopper1.jpg', 'Classic beef burger with lettuce, tomato, and cheese'),
(3, 'Chicken Fries', 4.99, 'chicken_fries.jpg', 'Crispy chicken strips served with fries'),
(3, 'Cheese Fries', 5.49, 'cheese_fries.jpg', 'French fries topped with cheese and bacon'),
(3, 'Onion Rings', 3.49, 'onion_rings.jpg', 'Crispy fried onion rings'),
(3, 'Double Whopper', 11.99, 'double_whopper.jpg', 'Double beef patty burger with cheese and veggies'),
(4, 'Sushi Rolls', 9.99, 'sushi1.jpg', 'Assorted sushi rolls with fish and veggies'),
(4, 'Salmon Sashimi', 12.99, 'sashimi1.jpg', 'Fresh salmon sashimi'),
(4, 'Tempura Shrimp', 10.99, 'tempura_shrimp.jpg', 'Crispy fried shrimp with dipping sauce'),
(4, 'California Rolls', 8.99, 'california_rolls.jpg', 'California rolls with crab meat and avocado'),
(4, 'Dragon Roll', 14.99, 'dragon_roll.jpg', 'Sushi roll with eel, avocado, and spicy mayo');

insert into orders (customer_id, restaurant_id, shipper_id, customer_note, status)
values
(1, 1,1, 'No olives, please', 'confirmed'),
(2, 2, 1, 'Extra sauce', 'delivering'),
(3, 1, 2, 'Add more cheese', 'pending'),
(4, 2,2, 'Spicy, no onions', 'completed'),
(5, 3, 3, 'Light on the sauce', 'confirmed'),
(6, 4,3, 'Vegetarian, no meat', 'pending'),
(7, 1, 4, 'No cheese, extra mushrooms', 'delivering'),
(8, 3, 4, 'Double chicken', 'completed');

insert into order_detail (order_id, dish_id, dish_quantity)
values
(1, 1, 2),  -- 2 Pizza Margherita
(1, 2, 1),  -- 1 Pizza Pepperoni
(2, 3, 3),  -- 3 Chicken Bucket
(2, 4, 2),  -- 2 Zinger Burger
(3, 1, 1),  -- 1 Pizza Margherita
(3, 2, 2),  -- 2 Pizza Pepperoni
(4, 3, 4),  -- 4 Chicken Bucket
(4, 4, 1),  -- 1 Zinger Burger
(5, 5, 3),  -- 3 Whopper
(5, 6, 2),  -- 2 Chicken Fries
(6, 7, 2),  -- 2 Sushi Rolls
(6, 8, 1),  -- 1 California Roll
(7, 1, 2),  -- 2 Pizza Margherita
(7, 3, 1),  -- 1 Chicken Bucket
(8, 5, 4),  -- 4 Whopper
(8, 6, 2);  -- 2 Chicken Fries

insert into payments (order_id, amount, method, status, transaction_id)
values
(1, 40.97, 'wallet', 'completed', 'txn123'),
(2, 51.97, 'wallet', 'pending', 'txn124');

insert into reviews (user_id, order_id, target_type, content, rating)
values
(1, 1, 'restaurant', 'Pizza was great!', 5),
(2, 2, 'restaurant', 'The chicken was a bit too salty.', 3),
(3, 3, 'restaurant', 'Nice pizza, but could be hotter.', 4),
(4, 4, 'restaurant', 'Not spicy enough for my taste.', 2),
(5, 5, 'restaurant', 'Amazing burger, loved it!', 5),
(6, 6, 'restaurant', 'Sushi was fresh, would recommend!', 4),
(7, 7, 'restaurant', 'The pizza crust was too thick.', 3),
(8, 8, 'restaurant', 'Great fries, but the burger was average.', 4);

insert into shipper_orders (order_id, shipper_id, pickup_time, delivered_time, status)
values
(1, 4, '2025-03-22 18:00:00', '2025-03-22 18:30:00', 'delivered'),
(2, 4, '2025-03-22 19:00:00', NULL, 'confirm_order'),
(3, 5, '2025-03-23 12:00:00', '2025-03-23 12:45:00', 'delivered'),
(4, 5, '2025-03-23 13:30:00', NULL, 'picking_up'),
(5, 6, '2025-03-23 14:00:00', NULL, 'confirm_order'),
(6, 6, '2025-03-23 15:00:00', '2025-03-23 15:30:00', 'delivered'),
(7, 7, '2025-03-23 16:00:00', '2025-03-23 16:20:00', 'delivered'),
(8, 7, '2025-03-23 17:00:00', NULL, 'delivering');