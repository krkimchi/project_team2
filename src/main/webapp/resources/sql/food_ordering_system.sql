create database if not exists food_ordering_system;
use food_ordering_system;

-- Bảng người dùng
create table users (
    id int primary key auto_increment,
    username varchar(50) unique not null,
    password varchar(255) not null, 
    email varchar(100) unique not null,
    phone varchar(15),
    full_name varchar(100),
    address text,
    avatar_url varchar(255),
    user_type enum('admin', 'owner', 'customer', 'shipper') not null,
    is_active boolean default true,
    created_at datetime default current_timestamp
);

-- Bảng mã xác thực OTP qua email
create table verification_codes (
    id int primary key auto_increment,
    user_id int not null,
    code varchar(6) not null,
    type enum('email') not null,
    expires_at datetime not null,
    foreign key (user_id) references users(id) on delete cascade
);

-- Bảng nhà hàng
create table restaurants (
    id int primary key auto_increment,
    owner_id int not null,
    restaurant_name varchar(100) not null,
    restaurant_address varchar(255) not null,
    restaurant_phone varchar(15) not null,
    restaurant_logo varchar(255),
    is_open boolean default true,
    created_at datetime default current_timestamp,
    foreign key (owner_id) references users(id) on delete cascade
);

-- Bảng món ăn
create table dishes (
    id int primary key auto_increment,
    restaurant_id int not null,
    dish_name varchar(100) not null,
    dish_price decimal(10,2) not null,
    dish_img varchar(255),
    description text,
    is_available boolean default true,
    created_at datetime default current_timestamp,
    foreign key (restaurant_id) references restaurants(id) on delete cascade
);

-- Bảng tùy chọn món ăn (ví dụ: ít đường, không đá)
create table dish_options (
    id int primary key auto_increment,
    dish_id int not null,
    option_name varchar(50) not null,
    option_price decimal(10,2) default 0,
    foreign key (dish_id) references dishes(id) on delete cascade
);

-- Bảng đơn hàng
create table orders (
    id int primary key auto_increment,
    customer_id int not null,
    restaurant_id int not null,
    shipper_id int,
    customer_note text,
    status enum('pending', 'confirmed', 'preparing', 'ready', 'delivering', 'completed', 'cancelled') not null default 'pending',
    created_at datetime default current_timestamp,
    foreign key (customer_id) references users(id) on delete cascade,
    foreign key (restaurant_id) references restaurants(id) on delete cascade,
    foreign key (shipper_id) references users(id) on delete set null
);

-- Bảng chi tiết đơn hàng (món ăn trong đơn)
create table order_detail (
    id int primary key auto_increment,
    order_id int not null,
    dish_id int not null,
    dish_quantity int not null check (dish_quantity > 0),
    foreign key (order_id) references orders(id) on delete cascade,
    foreign key (dish_id) references dishes(id) on delete cascade
);

-- Bảng tùy chọn của món ăn trong đơn hàng
create table order_option_detail (
    id int primary key auto_increment,
    order_detail_id int not null,
    dish_option_id int not null,
    foreign key (order_detail_id) references order_detail(id) on delete cascade,
    foreign key (dish_option_id) references dish_options(id) on delete cascade
);

-- Bảng thanh toán
create table payments (
    id int primary key auto_increment,
    order_id int not null,
    amount decimal(10,2) not null,
    method enum('wallet') not null,
    status enum('pending', 'completed', 'failed') not null default 'pending',
    transaction_id varchar(255),
    created_at datetime default current_timestamp,
    foreign key (order_id) references orders(id) on delete cascade
);

-- Bảng đánh giá nhà hàng
create table reviews (
    id int primary key auto_increment,
    user_id int not null,
    order_id int not null,
    target_type enum ('restaurant', 'shipper') not null,
    content text,
    rating tinyint check (rating between 1 and 5),
    created_at datetime default current_timestamp,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (order_id) references orders(id) on delete cascade
);

-- Bảng giao hàng của shipper
create table shipper_orders (
    id int primary key auto_increment,
    order_id int not null unique,
    shipper_id int not null,
    pickup_time datetime,
    delivered_time datetime,
    status enum('confirm_order', 'picking_up', 'delivering', 'delivered', 'cancelled') not null default 'confirm_order',
    foreign key (order_id) references orders(id) on delete cascade,
    foreign key (shipper_id) references users(id) on delete cascade
);

-- Bảng tích điểm khách hàng
create table customer_loyalty (
    user_id int primary key,
    points int default 0,
    tier enum('bronze', 'silver', 'gold', 'platinum', 'diamond') not null default 'bronze',
    foreign key (user_id) references users(id) on delete cascade
);

-- Bảng hỗ trợ đa ngôn ngữ
create table translations (
    id int primary key auto_increment,
    page varchar(50) not null,
    key_name varchar(50) not null,
    vi_vn text not null,
    en_us text not null,
    unique (page, key_name)
);
