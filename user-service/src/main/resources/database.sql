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
                         country_id UUID PRIMARY KEY,
                         country_name VARCHAR(255) NOT NULL
);
-- Address Table
CREATE TABLE address (
                         address_id UUID PRIMARY KEY,
                         unit_number VARCHAR(20),
                         street_number VARCHAR(20),
                         address_line1 VARCHAR(255) NOT NULL,
                         address_line2 VARCHAR(255),
                         city VARCHAR(100) NOT NULL,
                         region VARCHAR(100),
                         postal_code VARCHAR(20),
                         country_id UUID,
                         FOREIGN KEY (country_id) REFERENCES country(country_id)
);
-- Site User Table
CREATE TABLE site_user (
                           site_user_id UUID PRIMARY KEY,
                           email_address VARCHAR(255) UNIQUE NOT NULL,
                           phone_number VARCHAR(20),
                           password VARCHAR(255) NOT NULL,
                           role VARCHAR(50) NOT NULL DEFAULT 'USER',
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

-- User Profile
CREATE TABLE user_profile(
    user_profile_id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    gender VARCHAR(10) CHECK ( gender IN ('MALE', 'FEMALE') ),
    profile_avatars TEXT,
    site_user_id UUID UNIQUE NOT NULL,
    CONSTRAINT fk_user_profile_user FOREIGN KEY (site_user_id) REFERENCES site_user(site_user_id) ON DELETE CASCADE
);

-- User Address Table
CREATE TABLE user_address (
                              user_id UUID,
                              address_id UUID,
                              is_default BOOLEAN DEFAULT FALSE,
                              PRIMARY KEY (user_id, address_id),
                              FOREIGN KEY (user_id) REFERENCES user_profile(user_profile_id) ON DELETE CASCADE,
                              FOREIGN KEY (address_id) REFERENCES address(address_id) ON DELETE CASCADE
);
-- Brand Table
CREATE TABLE brand (
                       brand_id UUID PRIMARY KEY,
                       brand_name VARCHAR(200) NOT NULL,
                       brand_description TEXT
);
-- Colour Table
CREATE TABLE colour (
                        colour_id UUID PRIMARY KEY,
                        colour_name VARCHAR(100) NOT NULL,
                        hex_code VARCHAR(7) NOT NULL UNIQUE
);
-- Product Category Table
-- CREATE TABLE product_category (
--                                   product_category_id UUID PRIMARY KEY,
--                                   category_name VARCHAR(100) NOT NULL,
--                                   category_image VARCHAR(400),
--                                   category_description TEXT,
--                                   parent_product_category_id UUID,
--                                   FOREIGN KEY (parent_product_category_id) REFERENCES product_category(product_category_id)
-- );
CREATE TABLE product_category (
                                  product_category_id UUID PRIMARY KEY,
                                  category_name VARCHAR(100) NOT NULL,
                                  category_image VARCHAR(400),
                                  category_description TEXT,
                                  parent_product_category_id UUID REFERENCES product_category(product_category_id)
);

-- Promotion Table
CREATE TABLE promotion (
                           promotion_id UUID PRIMARY KEY,
                           name VARCHAR(200) NOT NULL,
                           description TEXT,
                           discount_rate DECIMAL(5, 2) NOT NULL,
                           start_date TIMESTAMP NOT NULL,
                           end_date TIMESTAMP NOT NULL
);
-- Promotion Category Table
CREATE TABLE promotion_category (
                                    category_id UUID,
                                    promotion_id UUID,
                                    PRIMARY KEY (category_id, promotion_id),
                                    FOREIGN KEY (promotion_id) REFERENCES promotion(promotion_id) ON DELETE CASCADE
);
-- Product Table
CREATE TABLE product (
                         product_id UUID PRIMARY KEY,
                         product_name VARCHAR(500) NOT NULL,
                         product_category_id UUID,
                         product_description TEXT,
                         brand_id UUID,
                         model_height VARCHAR(50),
                         model_wearing VARCHAR(50),
                         care_instructions TEXT,
                         about TEXT,
                         FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id) ON DELETE CASCADE,
                         FOREIGN KEY (brand_id) REFERENCES brand(brand_id) ON DELETE CASCADE
);

-- Product Item Table
CREATE TABLE product_item (
                              product_item_id UUID PRIMARY KEY,
                              product_id UUID,
                              colour_id UUID,
                              original_price DECIMAL(10, 2),
                              sale_price DECIMAL(10, 2),
                              product_code VARCHAR(20) NOT NULL,
                              FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
                              FOREIGN KEY (colour_id) REFERENCES colour(colour_id) ON DELETE CASCADE
);

-- Product Image Table
CREATE TABLE product_image (
                               image_id UUID PRIMARY KEY,
                               product_id UUID,
                               image_filename VARCHAR(400) NOT NULL,
                               product_item_id UUID,
                               FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
                               FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id) ON DELETE CASCADE
);
-- Attribute Type Table
CREATE TABLE attribute_type (
                                attribute_type_id UUID PRIMARY KEY,
                                attribute_name VARCHAR(100) NOT NULL
);
-- Attribute Option Table
CREATE TABLE attribute_option (
                                  attribute_option_id UUID PRIMARY KEY,
                                  attribute_type_id UUID,
                                  attribute_option_name VARCHAR(100) NOT NULL,
                                  FOREIGN KEY (attribute_type_id) REFERENCES attribute_type(attribute_type_id) ON DELETE CASCADE
);
-- Product Attribute Table
CREATE TABLE size_category (
    category_id UUID PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);
-- -- Product Review Table
-- CREATE TABLE product_review (
--                                 review_id UUID PRIMARY KEY,
--                                 product_id UUID,
--                                 review_title VARCHAR(100),
--                                 review_rating UUID CHECK (review_rating BETWEEN 1 AND 5),
--                                 review_comment TEXT,
--                                 review_date DATE NOT NULL,
--                                 FOREIGN KEY (product_id) REFERENCES product(product_id)
-- );
-- Size Category Table
CREATE TABLE product_attribute (
    product_id UUID,
    attribute_option_id UUID,
    PRIMARY KEY (product_id, attribute_option_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    FOREIGN KEY (attribute_option_id) REFERENCES attribute_option(attribute_option_id) ON DELETE CASCADE
);
-- Size Option Table
CREATE TABLE size_option (
                             size_id UUID PRIMARY KEY,
                             size_name VARCHAR(100) NOT NULL,
                             sort_order UUID,
                             size_category_id UUID,
                             FOREIGN KEY (size_category_id) REFERENCES size_category(category_id) ON DELETE CASCADE
);
-- Product Variation Table
CREATE TABLE product_variation (
                                   variation_id UUID PRIMARY KEY,
                                   product_item_id UUID,
                                   size_id UUID,
                                   qty_in_stock UUID,
                                   FOREIGN KEY (product_item_id) REFERENCES product_item(product_item_id) ON DELETE CASCADE,
                                   FOREIGN KEY (size_id) REFERENCES size_option(size_id) ON DELETE CASCADE
);

-- Payment Type Table
CREATE TABLE payment_type (
                              id UUID PRIMARY KEY,
                              value VARCHAR(100) NOT NULL
);
-- User Payment Method Table
CREATE TABLE user_payment_method (
                                     id UUID PRIMARY KEY,
                                     user_id UUID NOT NULL ,
                                     payment_type_id UUID,
                                     provider VARCHAR(100),
                                     account_number VARCHAR(50),
                                     expiry_date DATE,
                                     is_default BOOLEAN DEFAULT FALSE,
                                     FOREIGN KEY (payment_type_id) REFERENCES payment_type(id) ON DELETE CASCADE
);
-- Shopping Cart Table
CREATE TABLE shopping_cart (
                               cart_id UUID PRIMARY KEY,
                               user_id UUID NOT NULL
);
-- Shopping Cart Item Table
CREATE TABLE shopping_cart_item (
                                    cart_id UUID,
                                    product_item_id UUID,
                                    quantity INTEGER NOT NULL CHECK (quantity >= 0),
                                    PRIMARY KEY (cart_id, product_item_id),
                                    FOREIGN KEY (cart_id) REFERENCES shopping_cart(cart_id) ON DELETE CASCADE
);
-- Shipping Method Table
CREATE TABLE shipping_method (
                                 id UUID PRIMARY KEY,
                                 method_name VARCHAR(100) NOT NULL
);
-- Order Status Table
CREATE TABLE order_status (
                              id UUID PRIMARY KEY,
                              status_name VARCHAR(100) NOT NULL
);
-- Shop Order Table
CREATE TABLE shop_order (
                            order_id UUID PRIMARY KEY,
                            user_id UUID,
                            order_date TIMESTAMP NOT NULL,
                            shipping_method_id UUID,
                            order_status_id UUID,
                            total_amount DECIMAL(10, 2),
                            FOREIGN KEY (shipping_method_id) REFERENCES shipping_method(id) ON DELETE CASCADE,
                            FOREIGN KEY (order_status_id) REFERENCES order_status(id) ON DELETE CASCADE
);
-- Order Line Table
CREATE TABLE order_line (
                            order_line_id UUID PRIMARY KEY,
                            order_id UUID,
                            product_item_id UUID,
                            quantity UUID,
                            unit_price DECIMAL(10, 2),
                            FOREIGN KEY (order_id) REFERENCES shop_order(order_id) ON DELETE CASCADE
);
-- -- User Review Table
-- CREATE TABLE user_review (
--                              review_id UUID PRIMARY KEY,
--                              review_title VARCHAR(100),
--                              review_comment TEXT,
--                              review_date TIMESTAMP NOT NULL,
--                              rating UUID CHECK (rating BETWEEN 1 AND 5),
--                              user_id UUID,
--                              FOREIGN KEY (user_id) REFERENCES site_user(id)
-- );

-- Product Review Table
CREATE TABLE product_review (
                                review_id UUID PRIMARY KEY,
                                product_id UUID,
                                user_id UUID,
                                review_title VARCHAR(100),
                                review_rating BIGINT CHECK (review_rating BETWEEN 1 AND 5),
                                review_comment TEXT,
                                review_date DATE NOT NULL
);

-- Product Review Image Table
CREATE TABLE product_review_image (
                                      image_id UUID PRIMARY KEY,
                                      review_id UUID,
                                      image_url TEXT NOT NULL,
                                      FOREIGN KEY (review_id) REFERENCES product_review(review_id) ON DELETE CASCADE
);

-- -- User Review Table (Modified to link with Product Review)
-- CREATE TABLE user_review (
--                              review_id UUID PRIMARY KEY,
--                              user_id UUID,
--                              product_review_id UUID,
--                              review_title VARCHAR(100),
--                              review_comment TEXT,
--                              review_date TIMESTAMP NOT NULL,
--                              rating UUID CHECK (rating BETWEEN 1 AND 5),
--                              FOREIGN KEY (user_id) REFERENCES site_user(id),
--                              FOREIGN KEY (product_review_id) REFERENCES product_review(review_id)
-- );

-- -- Rating Type Table
-- CREATE TABLE rating_type (
--                              rating_type_id UUID PRIMARY KEY,
--                              rating_name VARCHAR(20) NOT NULL,
--                              label_min VARCHAR(20),
--                              label_max VARCHAR(20)
-- );
-- -- Review Rating Type Table
-- CREATE TABLE review_rating_type (
--                                     review_id UUID,
--                                     rating_type_id UUID,
--                                     rating_value UUID,
--                                     PRIMARY KEY (review_id, rating_type_id),
--                                     FOREIGN KEY (rating_type_id) REFERENCES rating_type(rating_type_id),
--                                     FOREIGN KEY (review_id) REFERENCES product_review(review_id)
-- );