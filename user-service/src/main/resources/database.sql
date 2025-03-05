CREATE DATABASE ecommerce;
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
                         id BIGINT PRIMARY KEY,
                         country_name VARCHAR(255) NOT NULL
);
-- Address Table
CREATE TABLE address (
                         id BIGINT PRIMARY KEY,
                         unit_number VARCHAR(20),
                         street_number VARCHAR(20),
                         address_line1 VARCHAR(255) NOT NULL,
                         address_line2 VARCHAR(255),
                         city VARCHAR(100) NOT NULL,
                         region VARCHAR(100),
                         postal_code VARCHAR(20),
                         country_id BIGINT,
                         FOREIGN KEY (country_id) REFERENCES country(id)
);
-- Site User Table
CREATE TABLE site_user (
                           id BIGINT PRIMARY KEY,
                           email_address VARCHAR(255) UNIQUE NOT NULL,
                           phone_number VARCHAR(20),
                           password VARCHAR(255) NOT NULL
);
-- User Address Table
CREATE TABLE user_address (
                              user_id BIGINT,
                              address_id BIGINT,
                              is_default BOOLEAN DEFAULT FALSE,
                              PRIMARY KEY (user_id, address_id),
                              FOREIGN KEY (user_id) REFERENCES site_user(id),
                              FOREIGN KEY (address_id) REFERENCES address(id)
);
-- Brand Table
CREATE TABLE brand (
                       brand_id BIGINT PRIMARY KEY,
                       brand_name VARCHAR(200) NOT NULL,
                       brand_description TEXT
);
-- Colour Table
CREATE TABLE colour (
                        colour_id BIGINT PRIMARY KEY,
                        colour_name VARCHAR(100) NOT NULL,
                        hex_code VARCHAR(7) NOT NULL UNIQUE
);
-- Product Category Table
CREATE TABLE product_category (
                                  product_category_id BIGINT PRIMARY KEY,
                                  category_name VARCHAR(100) NOT NULL,
                                  category_image VARCHAR(400),
                                  category_description TEXT,
                                  parent_product_category_id BIGINT,
                                  FOREIGN KEY (parent_product_category_id) REFERENCES product_category(product_category_id)
);
-- Promotion Table
CREATE TABLE promotion (
                           id BIGINT PRIMARY KEY,
                           name VARCHAR(200) NOT NULL,
                           description TEXT,
                           discount_rate DECIMAL(5, 2) NOT NULL,
                           start_date TIMESTAMP NOT NULL,
                           end_date TIMESTAMP NOT NULL
);
-- Promotion Category Table
CREATE TABLE promotion_category (
                                    category_id BIGINT,
                                    promotion_id BIGINT,
                                    PRIMARY KEY (category_id, promotion_id),
                                    FOREIGN KEY (category_id) REFERENCES product_category(product_category_id),
                                    FOREIGN KEY (promotion_id) REFERENCES promotion(id)
);
-- Product Table
CREATE TABLE product (
                         product_id BIGINT PRIMARY KEY,
                         product_name VARCHAR(500) NOT NULL,
                         product_category_id BIGINT,
                         product_description TEXT,
                         brand_id BIGINT,
                         model_height VARCHAR(50),
                         model_wearing VARCHAR(50),
                         care_instructions TEXT,
                         about TEXT,
                         FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id),
                         FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
);
-- Product Image Table
CREATE TABLE product_image (
                               image_id BIGINT PRIMARY KEY,
                               product_id BIGINT,
                               image_filename VARCHAR(400) NOT NULL,
                               product_item_id BIGINT,
                               FOREIGN KEY (product_id) REFERENCES product(product_id),
                               FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id)
);
-- Product Item Table
CREATE TABLE product_item (
                              product_item_id BIGINT PRIMARY KEY,
                              product_id BIGINT,
                              colour_id BIGINT,
                              original_price DECIMAL(10, 2),
                              sale_price DECIMAL(10, 2),
                              product_code VARCHAR(20) NOT NULL,
                              FOREIGN KEY (product_id) REFERENCES product(product_id),
                              FOREIGN KEY (colour_id) REFERENCES colour(colour_id)
);
-- Attribute Type Table
CREATE TABLE attribute_type (
                                attribute_type_id BIGINT PRIMARY KEY,
                                attribute_name VARCHAR(100) NOT NULL
);
-- Attribute Option Table
CREATE TABLE attribute_option (
                                  attribute_option_id BIGINT PRIMARY KEY,
                                  attribute_type_id BIGINT,
                                  attribute_option_name VARCHAR(100) NOT NULL,
                                  FOREIGN KEY (attribute_type_id) REFERENCES attribute_type(attribute_type_id)
);
-- Product Attribute Table
CREATE TABLE product_attribute (
                                   product_id BIGINT,
                                   attribute_option_id BIGINT,
                                   PRIMARY KEY (product_id, attribute_option_id),
                                   FOREIGN KEY (product_id) REFERENCES product(product_id),
                                   FOREIGN KEY (attribute_option_id) REFERENCES attribute_option(attribute_option_id)
);
-- -- Product Review Table
-- CREATE TABLE product_review (
--                                 review_id BIGINT PRIMARY KEY,
--                                 product_id BIGINT,
--                                 review_title VARCHAR(100),
--                                 review_rating BIGINT CHECK (review_rating BETWEEN 1 AND 5),
--                                 review_comment TEXT,
--                                 review_date DATE NOT NULL,
--                                 FOREIGN KEY (product_id) REFERENCES product(product_id)
-- );
-- Size Category Table
CREATE TABLE size_category (
                               category_id BIGINT PRIMARY KEY,
                               category_name VARCHAR(100) NOT NULL
);
-- Size Option Table
CREATE TABLE size_option (
                             size_id BIGINT PRIMARY KEY,
                             size_name VARCHAR(100) NOT NULL,
                             sort_order BIGINT,
                             size_category_id BIGINT,
                             FOREIGN KEY (size_category_id) REFERENCES size_category(category_id)
);
-- Product Variation Table
CREATE TABLE product_variation (
                                   variation_id BIGINT PRIMARY KEY,
                                   product_item_id BIGINT,
                                   size_id BIGINT,
                                   qty_in_stock BIGINT,
                                   FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id),
                                   FOREIGN KEY (size_id) REFERENCES size_option(size_id)
);

-- Payment Type Table
CREATE TABLE payment_type (
                              id BIGINT PRIMARY KEY,
                              value VARCHAR(100) NOT NULL
);
-- User Payment Method Table
CREATE TABLE user_payment_method (
                                     id BIGINT PRIMARY KEY,
                                     user_id BIGINT,
                                     payment_type_id BIGINT,
                                     provider VARCHAR(100),
                                     account_number VARCHAR(50),
                                     expiry_date DATE,
                                     is_default BOOLEAN DEFAULT FALSE,
                                     FOREIGN KEY (user_id) REFERENCES site_user(id),
                                     FOREIGN KEY (payment_type_id) REFERENCES payment_type(id)
);
-- Shopping Cart Table
CREATE TABLE shopping_cart (
                               cart_id BIGINT PRIMARY KEY,
                               user_id BIGINT,
                               FOREIGN KEY (user_id) REFERENCES site_user(id)
);
-- Shopping Cart Item Table
CREATE TABLE shopping_cart_item (
                                    cart_id BIGINT,
                                    product_item_id BIGINT,
                                    quantity BIGINT,
                                    PRIMARY KEY (cart_id, product_item_id),
                                    FOREIGN KEY (cart_id) REFERENCES shopping_cart(cart_id),
                                    FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id)
);
-- Shipping Method Table
CREATE TABLE shipping_method (
                                 id BIGINT PRIMARY KEY,
                                 method_name VARCHAR(100) NOT NULL
);
-- Order Status Table
CREATE TABLE order_status (
                              id BIGINT PRIMARY KEY,
                              status_name VARCHAR(100) NOT NULL
);
-- Shop Order Table
CREATE TABLE shop_order (
                            order_id BIGINT PRIMARY KEY,
                            user_id BIGINT,
                            order_date TIMESTAMP NOT NULL,
                            shipping_method_id BIGINT,
                            order_status_id BIGINT,
                            total_amount DECIMAL(10, 2),
                            FOREIGN KEY (user_id) REFERENCES site_user(id),
                            FOREIGN KEY (shipping_method_id) REFERENCES shipping_method(id),
                            FOREIGN KEY (order_status_id) REFERENCES order_status(id)
);
-- Order Line Table
CREATE TABLE order_line (
                            order_line_id BIGINT PRIMARY KEY,
                            order_id BIGINT,
                            product_item_id BIGINT,
                            quantity BIGINT,
                            unit_price DECIMAL(10, 2),
                            FOREIGN KEY (order_id) REFERENCES shop_order(order_id),
                            FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id)
);
-- -- User Review Table
-- CREATE TABLE user_review (
--                              review_id BIGINT PRIMARY KEY,
--                              review_title VARCHAR(100),
--                              review_comment TEXT,
--                              review_date TIMESTAMP NOT NULL,
--                              rating BIGINT CHECK (rating BETWEEN 1 AND 5),
--                              user_id BIGINT,
--                              FOREIGN KEY (user_id) REFERENCES site_user(id)
-- );

-- Product Review Table
CREATE TABLE product_review (
                                review_id BIGINT PRIMARY KEY,
                                product_id BIGINT,
                                user_id BIGINT,
                                review_title VARCHAR(100),
                                review_rating BIGINT CHECK (review_rating BETWEEN 1 AND 5),
                                review_comment TEXT,
                                review_date DATE NOT NULL,
                                FOREIGN KEY (product_id) REFERENCES product(product_id),
                                FOREIGN KEY (user_id) REFERENCES site_user(id)
);

-- Product Review Image Table
CREATE TABLE product_review_image (
                              image_id BIGINT PRIMARY KEY,
                              review_id BIGINT,
                              image_url TEXT NOT NULL,
                              FOREIGN KEY (review_id) REFERENCES product_review(review_id) ON DELETE CASCADE
);

-- -- User Review Table (Modified to link with Product Review)
-- CREATE TABLE user_review (
--                              review_id BIGINT PRIMARY KEY,
--                              user_id BIGINT,
--                              product_review_id BIGINT,
--                              review_title VARCHAR(100),
--                              review_comment TEXT,
--                              review_date TIMESTAMP NOT NULL,
--                              rating BIGINT CHECK (rating BETWEEN 1 AND 5),
--                              FOREIGN KEY (user_id) REFERENCES site_user(id),
--                              FOREIGN KEY (product_review_id) REFERENCES product_review(review_id)
-- );

-- -- Rating Type Table
-- CREATE TABLE rating_type (
--                              rating_type_id BIGINT PRIMARY KEY,
--                              rating_name VARCHAR(20) NOT NULL,
--                              label_min VARCHAR(20),
--                              label_max VARCHAR(20)
-- );
-- -- Review Rating Type Table
-- CREATE TABLE review_rating_type (
--                                     review_id BIGINT,
--                                     rating_type_id BIGINT,
--                                     rating_value BIGINT,
--                                     PRIMARY KEY (review_id, rating_type_id),
--                                     FOREIGN KEY (rating_type_id) REFERENCES rating_type(rating_type_id),
--                                     FOREIGN KEY (review_id) REFERENCES product_review(review_id)
-- );