-- This script creates the schema for an e-commerce platform using InnoDB engine, 
-- UTF-8 MB4 charset for full Unicode support, and utf8mb4_0900_ai_ci collation 
-- for accurate sorting and comparison of multilingual data.
CREATE DATABASE ecommerce;

USE ecommerce;


-- Drop existing tables to avoid conflicts
DROP TABLE IF EXISTS user_review;
DROP TABLE IF EXISTS order_line;
DROP TABLE IF EXISTS shop_order;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS shipping_method;
DROP TABLE IF EXISTS shopping_cart_item;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS user_payment_method;
DROP TABLE IF EXISTS payment_type;
DROP TABLE IF EXISTS product_configuration;
DROP TABLE IF EXISTS variation_option;
DROP TABLE IF EXISTS variation;
DROP TABLE IF EXISTS review_rating_type;
DROP TABLE IF EXISTS rating_type;
DROP TABLE IF EXISTS product_variation;
DROP TABLE IF EXISTS size_option;
DROP TABLE IF EXISTS size_category;
DROP TABLE IF EXISTS product_review;
DROP TABLE IF EXISTS product_image;
DROP TABLE IF EXISTS product_item;
DROP TABLE IF EXISTS product_attribute;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS promotion_category;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS colour;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS attribute_option;
DROP TABLE IF EXISTS attribute_type;
DROP TABLE IF EXISTS promotion;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS user_address;
DROP TABLE IF EXISTS site_user;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS country;

-- Country Table
CREATE TABLE country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Address Table
CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    unit_number VARCHAR(20),
    street_number VARCHAR(20),
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    region VARCHAR(100),
    postal_code VARCHAR(20),
    country_id INT,
    FOREIGN KEY (country_id) REFERENCES country(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Site User Table
CREATE TABLE site_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email_address VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    password VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- User Address Table
CREATE TABLE user_address (
    user_id INT,
    address_id INT,
    is_default BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (user_id, address_id),
    FOREIGN KEY (user_id) REFERENCES site_user(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Brand Table
CREATE TABLE brand (
    brand_id INT AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(200) NOT NULL,
    brand_description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Colour Table
CREATE TABLE colour (
    colour_id INT AUTO_INCREMENT PRIMARY KEY,
    colour_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Category Table
CREATE TABLE product_category (
    product_category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    category_image VARCHAR(400),
    category_description TEXT,
    parent_product_category_id INT,
    FOREIGN KEY (parent_product_category_id) REFERENCES product_category(product_category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Promotion Table
CREATE TABLE promotion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    discount_rate DECIMAL(5, 2) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Promotion Category Table
CREATE TABLE promotion_category (
    category_id INT,
    promotion_id INT,
    PRIMARY KEY (category_id, promotion_id),
    FOREIGN KEY (category_id) REFERENCES product_category(product_category_id),
    FOREIGN KEY (promotion_id) REFERENCES promotion(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Table
CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(500) NOT NULL,
    product_category_id INT,
    product_description TEXT,
    brand_id INT,
    model_height VARCHAR(50),
    model_wearing VARCHAR(50),
    care_instructions TEXT,
    about TEXT,
    FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id),
    FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Image Table
CREATE TABLE product_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    image_filename VARCHAR(400) NOT NULL,
    product_item_id INT,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Item Table
CREATE TABLE product_item (
    product_item_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    colour_id INT,
    original_price DECIMAL(10, 2),
    sale_price DECIMAL(10, 2),
    product_code VARCHAR(20) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (colour_id) REFERENCES colour(colour_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Attribute Type Table
CREATE TABLE attribute_type (
    attribute_type_id INT AUTO_INCREMENT PRIMARY KEY,
    attribute_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Attribute Option Table
CREATE TABLE attribute_option (
    attribute_option_id INT AUTO_INCREMENT PRIMARY KEY,
    attribute_type_id INT,
    attribute_option_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (attribute_type_id) REFERENCES attribute_type(attribute_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Attribute Table
CREATE TABLE product_attribute (
    product_id INT,
    attribute_option_id INT,
    PRIMARY KEY (product_id, attribute_option_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (attribute_option_id) REFERENCES attribute_option(attribute_option_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Review Table
CREATE TABLE product_review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    review_title VARCHAR(100),
    review_rating INT CHECK (review_rating BETWEEN 1 AND 5),
    review_comment TEXT,
    review_date DATE NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Size Category Table
CREATE TABLE size_category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Size Option Table
CREATE TABLE size_option (
    size_id INT AUTO_INCREMENT PRIMARY KEY,
    size_name VARCHAR(100) NOT NULL,
    sort_order INT,
    size_category_id INT,
    FOREIGN KEY (size_category_id) REFERENCES size_category(category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Variation Table
CREATE TABLE product_variation (
    variation_id INT AUTO_INCREMENT PRIMARY KEY,
    product_item_id INT,
    size_id INT,
    qty_in_stock INT,
    FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id),
    FOREIGN KEY (size_id) REFERENCES size_option(size_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Rating Type Table
CREATE TABLE rating_type (
    rating_type_id INT AUTO_INCREMENT PRIMARY KEY,
    rating_name VARCHAR(20) NOT NULL,
    label_min VARCHAR(20),
    label_max VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Review Rating Type Table
CREATE TABLE review_rating_type (
    review_id INT,
    rating_type_id INT,
    rating_value INT,
    PRIMARY KEY (review_id, rating_type_id),
    FOREIGN KEY (rating_type_id) REFERENCES rating_type(rating_type_id),
    FOREIGN KEY (review_id) REFERENCES product_review(review_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Payment Type Table
CREATE TABLE payment_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    value VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- User Payment Method Table
CREATE TABLE user_payment_method (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    payment_type_id INT,
    provider VARCHAR(100),
    account_number VARCHAR(50),
    expiry_date DATE,
    is_default BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES site_user(id),
    FOREIGN KEY (payment_type_id) REFERENCES payment_type(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Shopping Cart Table
CREATE TABLE shopping_cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES site_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Shopping Cart Item Table
CREATE TABLE shopping_cart_item (
    cart_id INT,
    product_item_id INT,
    quantity INT,
    PRIMARY KEY (cart_id, product_item_id),
    FOREIGN KEY (cart_id) REFERENCES shopping_cart(cart_id),
    FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Shipping Method Table
CREATE TABLE shipping_method (
    id INT AUTO_INCREMENT PRIMARY KEY,
    method_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Order Status Table
CREATE TABLE order_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Shop Order Table
CREATE TABLE shop_order (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date DATETIME NOT NULL,
    shipping_method_id INT,
    order_status_id INT,
    total_amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES site_user(id),
    FOREIGN KEY (shipping_method_id) REFERENCES shipping_method(id),
    FOREIGN KEY (order_status_id) REFERENCES order_status(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Order Line Table
CREATE TABLE order_line (
    order_line_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_item_id INT,
    quantity INT,
    unit_price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES shop_order(order_id),
    FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- User Review Table
CREATE TABLE user_review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    review_title VARCHAR(100),
    review_comment TEXT,
    review_date DATETIME NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES site_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
