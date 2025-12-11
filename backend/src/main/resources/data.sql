-- Reset tables for idempotent seeding
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE menu_items;
TRUNCATE TABLE restaurants;
SET FOREIGN_KEY_CHECKS=1;

-- Insert restaurants
INSERT INTO restaurants (id, name, email, phone, address, active)
VALUES 
 (1, 'Pizza House', 'pizza@example.com', '12345678', 'City Center', true),
 (2, 'Burger World', 'burger@example.com', '87654321', 'Town Square', true);

-- Insert menu items for Restaurant 1
INSERT INTO menu_items (id, available, name, description, price, restaurant_id)
VALUES 
 (1, true, 'Margherita', 'Classic cheese pizza', 10.00, 1),
 (2, true, 'Pepperoni', 'Pepperoni pizza', 12.00, 1),
 (3, true, 'Hawaii', 'Ham and pineapple', 11.50, 1);

-- Insert menu items for Restaurant 2
INSERT INTO menu_items (id, available, name, description, price, restaurant_id)
VALUES
 (4, true, 'Classic Burger', 'Beef burger with cheese', 9.99, 2),
 (5, true, 'Chicken Burger', 'Grilled chicken burger', 8.99, 2),
 (6, true, 'Fries', 'Crispy fries', 3.50, 2);
