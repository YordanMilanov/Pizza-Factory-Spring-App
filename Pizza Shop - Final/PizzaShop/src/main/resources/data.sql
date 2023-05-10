-- Roles
INSERT INTO roles (id, description, role)
VALUES(1,'The manager have all the rights in the application','MANAGER');

INSERT INTO roles (id, description, role)
VALUES(2,'The staff have limited functions compared to the manager','STAFF');

INSERT INTO roles (id, description, role)
VALUES(3,'The customer have the rights to make orders, and this role is set by default. It is with the lowest list of permission','CUSTOMER');

-- Addresses
INSERT INTO addresses (id,city, neighborhood, street, street_number)
VALUES(1,'City','Center','street', 1);

INSERT INTO addresses (id, city, neighborhood, street, street_number)
VALUES(2,'City','Center','street', 2);

INSERT INTO addresses (id, city, neighborhood, street, street_number)
VALUES(3,'City','Center','street', 3);

INSERT INTO addresses (id, city, neighborhood, street, street_number)
VALUES(4,'City','Center','street', 4);

INSERT INTO addresses (id, city, neighborhood, street, street_number)
VALUES(5,'City','Center','street', 5);

-- some test users
INSERT INTO users (id, username, password, full_name, email, level, `address_id`, phone)
VALUES (1, 'manager', 'manager', 'mr.Manager', 'manager@abv.bg', 'EMPLOYEE', 1, '+359-000-000-001');

INSERT INTO users (id, username, password, full_name, email, level, `address_id`, phone)
VALUES (2,'staff', 'staff', 'mr.Staff', 'staff@abv.bg', 'EMPLOYEE', 2, '+359-000-000-002');

INSERT INTO users (id, username, password, full_name, email, level, `address_id`, phone)
VALUES (3,'customer-new', 'customer-new', 'mr.Customer-new', 'customer-new@abv.bg', 'NEW', 3, '+359-000-000-003');

INSERT INTO users (id, username, password, full_name, email, level, `address_id`, phone)
VALUES (4,'customer-regular', 'customer-regular', 'mr.Customer-regular', 'customer-regular@abv.bg', 'REGULAR', 4, '+359-000-000-004');

INSERT INTO users (id, username, password, full_name, email, level, `address_id`, phone)
VALUES (5,'customer-vip', 'customer-vip', 'mr.Customer-vip', 'customer-vip@abv.bg', 'VIP', 5, '+359-000-000-005');

-- user roles

-- Manager
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (1, 3);

-- Staff
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (2, 2);
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (2, 3);

-- Customer - new
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (3, 3);

-- Customer - regular
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (4, 3);

-- Customer - vip
INSERT INTO users_roles (`user_id`, `roles_id`)
VALUES (5, 3);

-- Ingredients
INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (1,'pizza sauce', 10, 10, 5, 45, 0, 200, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (2,'mozzarella', 18, 5, 25, 6, 20, 304, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (3,'tuna', 35, 5, 25, 0, 1, 104, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (4,'red onions', 1.5, 10, 1, 9, 0, 40, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (5,'sweetcorn', 9, 5, 2, 18, 0, 80, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (6,'black olive', 10, 10, 1, 7, 6, 86, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (7,'ham', 15, 10, 13, 3, 27, 309, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (8,'bacon', 25, 10, 35, 1, 43, 531, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (9,'white cheese', 13, 10, 22, 16, 17, 264, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (10,'cheese', 19, 10, 25, 1, 30, 374, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (11,'tomato', 4, 15, 1, 4, 0, 20, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (12,'cucumber', 5, 20, 1, 2, 0, 12, 'MAIN');

INSERT INTO ingredients (id, name, price, stock_in_kg, protein, carbohydrates, fat, calories,ingredient_type)
VALUES (13,'dough', 8, 30, 8, 43, 1, 213, 'MAIN');

-- pictures

INSERT INTO pictures(id, title, url)
VALUES (1, 'Garden Party', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824301050/21235b76-f381-49fb-bdba-9488de76f54f.jpg');

INSERT INTO pictures(id, title, url)
VALUES (2, 'Garden Party', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824254644/6452aa16-2d94-451b-b69b-d912ebb6d0d2.jpg');

INSERT INTO pictures(id, title, url)
VALUES (3, 'Garden Party', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824196127/6145b5aa-1f4f-490b-ac74-c8e8e61a994f.jpg');

INSERT INTO pictures(id, title, url)
VALUES (4, 'The Works', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681823542445/379e1c96-34c0-45e8-81aa-028e3ea11681.jpg');

INSERT INTO pictures(id, title, url)
VALUES (5, 'Tuna Delight', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681823869991/a5d4ff05-431b-459c-a417-f886e7442446.jpg');

INSERT INTO pictures(id, title, url)
VALUES (6, 'Hawaiian', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824028201/4fb779eb-0ccf-4f49-a89c-7e04498d2ab5.jpg');

INSERT INTO pictures(id, title, url)
VALUES (7, 'PEPSI', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824522379/fb767431-84e2-48b3-b4b8-1f102d1eb807.jpg');

INSERT INTO pictures(id, title, url)
VALUES (8, '7UP', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824657551/aeed77e9-5225-4b18-a13c-bb9c79b359ab.jpg');

INSERT INTO pictures(id, title, url)
VALUES (9, 'LIPTON', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681824736955/6c5bc1ef-fd4a-4983-b5e5-7d67f1b893b0.jpg');

INSERT INTO pictures(id, title, url)
VALUES (10, 'CREAMY TOMATO', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681825024730/e2792f4c-669a-4661-9e3c-772b8139ca53.jpg');

INSERT INTO pictures(id, title, url)
VALUES (11, 'BOLOGNESE', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681825424531/8e1c023a-16a6-49de-90d8-b9766de7e99f.jpg');

INSERT INTO pictures(id, title, url)
VALUES (12, 'SALAD CAPRESE', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681825590869/a6aaa0cc-1198-44b5-8675-76fdc8d707ee.jpg');

INSERT INTO pictures(id, title, url)
VALUES (13, 'STRAWBERRY MASCARPONE', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681825887347/d5725607-5983-4895-b53a-621936d8a93a.jpg');

INSERT INTO pictures(id, title, url)
VALUES (14, 'OREO CAKE', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681826087649/a3d5fb85-818f-4778-a175-1cd151b62cf0.webp');

INSERT INTO pictures(id, title, url)
VALUES (15, 'ICE CREAM', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681826150123/d1662305-2855-4435-a1c5-b6fa9b979b4c.jpg');

INSERT INTO pictures(id, title, url)
VALUES (16, 'CHOCOLATE CAKE', 'https://res.cloudinary.com/duwdabwhr/image/upload/v1681826323163/70c3667b-b4e4-4bb8-8328-07385e49025a.jpg');


--Products

--Pizza
INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (1,'Cheese & Tomato', 10, 450, 'PIZZA', 1,'Pizza sauce and Mozzarella', 250);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (2,'Four Cheese', 11, 450, 'PIZZA', 2,'Pizza sauce, Mozzarella, Matured, Gouda',240);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (3,'Garden Party', 12, 450, 'PIZZA', 3,'Pizza sauce, Mozzarella, Mushrooms, Green Bell Pepper, Red Onions, Sweetcorn and Fresh Tomatoes',260);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (4,'The Works', 14, 450, 'PIZZA', 4,'Pizza Sauce, Mozzarella, Mushrooms, Green Bell Pepper, Ham, Italian Sausage, Pepperoni, Onions and Black Olives',270);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (5,'Hawaiian', 15, 450, 'PIZZA', 5,'Pizza sauce, mozzarella, tuna, red onions, sweet corn, black olives',280);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (6,'Tuna Delight', 9, 450, 'PIZZA', 6,'Pizza sauce, Mozzarella, Ham and Pineapple', 180);

--DRINK
INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (7,'Pepsi max', 2, 330, 'DRINK', 7,'Pepsi Max Zero Sugar 330', 0);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (8,'7UP', 2, 330, 'DRINK', 8,'7Up Zero Sugar 330', 0);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (9,'Lipton', 2, 330, 'DRINK', 9,'Lipton Zero Sugar 330', 0);

--PASTA
INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (10,'Creamy Tomato', 8, 400, 'PASTA', 10,'Creamy tomato riggatoni', 160);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (11,'Bolognese', 8, 400, 'PASTA', 11,'Spaghetti bolognese consists of spaghetti (long strings of pasta) with an Italian ragù (meat sauce) made with minced beef, bacon and tomatoes, served with Parmesan cheese.',180);

--salad

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (12,'Green Salad', 7, 400, 'SALAD', 12,'Cucumbers, Green Salad, Olive oil, Olives',23);

--dessert

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (13,'Strawberry Mascarpone', 5, 200, 'DESSERT', 13,'Mascarpone with strawberries', 280);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (14,'Oreo cake', 5, 200, 'DESSERT', 14,'filled with oreo taste', 380);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (15,'Ice cream', 5, 200, 'DESSERT', 15,'Vanilla Ice Cream',190);

INSERT INTO products (id, name, price, grams, product_type_enum, picture_id, description, calories_per100)
VALUES (16,'Chocolate cake', 5, 200, 'DESSERT', 16,'with mixed chocolate and nuts',420);

-- product ingredients receipts

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(1,1,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(1,2,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(1,13,200);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(2,1,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(2,2,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(2,13,200);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(3,1,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(3,2,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(3,13,200);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(4,1,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(4,2,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(4,13,200);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(5,1,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(5,2,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(5,13,200);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(6,1,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(6,2,100);

INSERT INTO required_ingredients(product_id, ingredient_id, grams)
VALUES(6,13,200);





