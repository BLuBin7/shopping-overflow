-- Insert dữ liệu vào bảng brand
INSERT INTO brand (brand_id, brand_name, brand_description)
VALUES
    (1, 'Nike', 'A popular sportswear brand.'),
    (2, 'Adidas', 'A renowned sports brand specializing in athletic wear.');

-- Insert dữ liệu vào bảng colour
INSERT INTO colour (colour_id, colour_name)
VALUES
    (1, 'Red'),
    (2, 'Blue'),
    (3, 'Black');

-- Insert dữ liệu vào bảng product_category
INSERT INTO product_category (product_category_id, category_name, category_image, category_description)
VALUES
    (1, 'Footwear', 'footwear_image.jpg', 'A wide range of shoes, boots, and sneakers for all occasions.'),
    (2, 'Sportswear', 'sportswear_image.jpg', 'Apparel designed for physical activities like running, training, and sports.'),
    (3, 'Running Shoes', 'running_shoes_image.jpg', 'Running shoes designed for comfort and performance.'),
    (4, 'Casual Shoes', 'casual_shoes_image.jpg', 'Casual footwear for everyday wear.');

-- Insert dữ liệu vào bảng product
INSERT INTO product (product_id, product_name, product_category_id, product_description, brand_id, model_height, model_wearing, care_instructions, about)
VALUES
    (1, 'Nike Air Max', 1, 'A comfortable and stylish running shoe.', 1, '10cm', 'Men', 'Hand wash only.', 'The Nike Air Max series offers unbeatable comfort.'),
    (2, 'Adidas Ultraboost', 2, 'High-performance running shoes for athletes.', 2, '12cm', 'Women', 'Machine wash cold.', 'Boost your energy with the Adidas Ultraboost.');

-- Insert dữ liệu vào bảng product_image
INSERT INTO product_image (image_id, product_id, image_filename, product_item_id)
VALUES
    (1, 1, 'nike_air_max.jpg', 1),
    (2, 2, 'adidas_ultraboost.jpg', 2);

-- Insert dữ liệu vào bảng product_item
INSERT INTO product_item (product_item_id, product_id, colour_id, original_price, sale_price, product_code)
VALUES
    (1, 1, 1, 120.00, 100.00, 'AMX123'),
    (2, 2, 2, 150.00, 130.00, 'UB456');

-- Insert dữ liệu vào bảng attribute_type
INSERT INTO attribute_type (attribute_type_id, attribute_name)
VALUES
    (1, 'Material'),
    (2, 'Style');

-- Insert dữ liệu vào bảng attribute_option
INSERT INTO attribute_option (attribute_option_id, attribute_type_id, attribute_option_name)
VALUES
    (1, 1, 'Leather'),
    (2, 1, 'Mesh'),
    (3, 2, 'Sporty'),
    (4, 2, 'Casual');

-- Insert dữ liệu vào bảng product_attribute
INSERT INTO product_attribute (product_id, attribute_option_id)
VALUES
    (1, 1),
    (2, 2);

-- Insert dữ liệu vào bảng size_category
INSERT INTO size_category (category_id, category_name)
VALUES
    (1, 'Men Sizes'),
    (2, 'Women Sizes');

-- Insert dữ liệu vào bảng size_option
INSERT INTO size_option (size_id, size_name, sort_order, size_category_id)
VALUES
    (1, 'Small', 1, 1),
    (2, 'Medium', 2, 1),
    (3, 'Large', 3, 2);

-- Insert dữ liệu vào bảng product_variation
INSERT INTO product_variation (variation_id, product_item_id, size_id, qty_in_stock)
VALUES
    (1, 1, 1, 50),
    (2, 2, 3, 30);


