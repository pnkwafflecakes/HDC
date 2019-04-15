-- FKs should only exist on "many" side
-- Cant delete a table with FKs in another
DROP DATABASE if exists capstonedb;
CREATE DATABASE capstonedb DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE capstonedb;


Create table `AccountType`
(
    `account_type` int(1) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`account_type`)
);

Create table `User`
(
    `user_id` int(4) NOT NULL,
    `name` VARCHAR(90),
    `address` VARCHAR(99),
    `postal_code` VARCHAR(6),
    `email` VARCHAR(99),
    `phone_no` VARCHAR(12),
    --Former account properties
    `account_type` int(1) NOT NULL, -- Combined w/ Privileges, will add constraints, turned to int --1 is regular, 2 is admin, 3 is guest
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `account_status` boolean NOT NULL, -- Change from int
    -- Primary Key
    PRIMARY KEY (`user_id`),

    KEY `FK_USER_ACCOUNT_TYPE` (`account_type`), 
    CONSTRAINT `FK_USER_ACCOUNT_TYPE` FOREIGN KEY (`account_type`) REFERENCES `AccountType` (`account_type`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

Create table `Pickup`
(
    `pickup_id` int(4) NOT NULL AUTO_INCREMENT,
    `pickup_name` VARCHAR(150) NOT NULL,
    `pickup_address` VARCHAR(199) NOT NULL,
    PRIMARY KEY (`pickup_id`)
);


Create table `Delivery`
(
    `delivery_no` int(4) NOT NULL,
    `method` VARCHAR(100) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `phone_no` VARCHAR(12) NOT NULL,
    `notes` VARCHAR(99) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`delivery_no`)
);

Create table `Orders`
(
    `order_no` int(4) NOT NULL,
    `user_id` int(4) NOT NULL, -- user_id instead of customer_id
    `order_datetime` datetime NOT NULL,
    `due_datetime` datetime NOT NULL,
    `order_items` VARCHAR(99) NOT NULL, --Do we need this?
    `total_price` double(9,2) NOT NULL,
    `delivery_no` int(4) NOT NULL,
    `active` boolean NOT NULL,
    `confirmed` boolean NOT NULL,
    `paid` boolean NOT NULL,
    `delivered` boolean NOT NULL,
    `pickup_id` int(4) NULL,
    -- Primary Key
    PRIMARY KEY (`order_no`),
    -- Forign Key
    KEY `FK_ORDER_USER_ID` (`user_id`), 
    CONSTRAINT `FK_ORDER_USERID` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    KEY `FK_ORDER_Delivery_No` (`delivery_no`), 
    CONSTRAINT `FK_ORDER_Delivery_No` FOREIGN KEY (`delivery_no`) REFERENCES `Delivery` (`delivery_no`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    KEY `FK_ORDER_PICKUP_ID` (`pickup_id`), 
    CONSTRAINT `FK_ORDER_PICKUP_ID` FOREIGN KEY (`pickup_id`) REFERENCES `Pickup` (`pickup_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);




Create table `Cake` -- To add constraints
(
    `cake_id` int(4) NOT NULL,
    `name` VARCHAR(99) NOT NULL,
    `namecn` VARCHAR(99) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    -- Category variable is redundant
    `size` int(2), -- Change from char, will be in inches THE DI
    `price` double (6,2) NOT NULL,
    `description` VARCHAR(300) NOT NULL, -- it be here
    `descriptioncn` VARCHAR(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `image` VARCHAR(99) NOT NULL,
    `featured` boolean, --Added
    `special` boolean, --Added
    `active` boolean, 
    -- Primary Key
    PRIMARY KEY (`cake_id`)
);

Create table `CakeOrder` -- To add constraints
(
    `order_no` int(4) NOT NULL,
    `cake_id` int(4) NOT NULL,
    `quantity` int(2),
    -- Primary Key
    PRIMARY KEY (`order_no`, `cake_id`),
    -- Forign Key
    KEY `FK_CAKEORDER_ORDER_NO` (`order_no`), 
    CONSTRAINT `FK_CAKEORDER_ORDER_NO` FOREIGN KEY (`order_no`) REFERENCES `Orders` (`order_no`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    KEY `FK_CAKEORDER_CAKE_ID` (`cake_id`), 
    CONSTRAINT `FK_CAKEORDER_CAKE_ID` FOREIGN KEY (`cake_id`) REFERENCES `Cake` (`cake_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- Drop table CakeOrder;
-- Drop table Cake;
-- Drop table CakeCategory;
-- Drop table Order;
-- Drop table Delivery;
-- Drop table User;
-- Drop table Account;

insert into `AccountType` values(1, 'Regular User');
insert into `AccountType` values(2, 'Administrator');

--Possibly just get rid of Account_id and just use user_id
insert into `User` values(0001, 'Helen Xie', '16 Brenner Place NW', 'T2L1Z2', 'adam.schlinker@gmail.com', '403-874-0935', 1, 'helen', 'password', 1);
insert into `User` values(0002, 'Billy Joe', '1301 16 Ave NW', 'T2X2M2', 'billy.joe@google.com', '403-288-2888', 1, 'billyjoe', 'capstone2019', 1);
insert into `User` values(0003, 'Argis Fargis', '1302 16 Ave NW', 'A1A2B2', 'Argis.Fargis@google.ca', '403-288-2887',2, 'admin', 'password', 1);
insert into `User` values(0004, 'Arbichov Gopnik', '1303 16 Ave NW', 'T2X2M9', 'Arbichov.Gopnik@google.ru', '403-288-2886', 2, '1521', '1234', 1);
insert into `User` values(0005, 'Ada Wong', '1304 16 Ave NW', 'T2X2M1', 'Ada.wong@google.ru', '403-288-2885', 1, 'adaawong', 'capstone2019', 1);
--Might need further work, to allow certain extra details based off of things like 'Drop Off'


insert into `Cake` values(0001, 'Choco Cream Egg','可可脂蛋糕', 8, 55.99, 'Have a taste of Easter with this chocolatey delight. Made with chocolate cake batter, chocolate icing, mini eggs and full-size cream eggs!', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/cake1.jpg', 0, 1, 1);
insert into `Cake` values(0002, 'Oreo Dream',     '奥利奥之梦', 12, 57.99, 'Have you ever wondered what it would be like to eat an Oreo the size of a cake? Here it is. Made with chocolate cake batter, vanilla icing, and topped with real Oreos!', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干', '/images/cake2.jpg', 0, 1, 1);
insert into `Cake` values(0003, 'Fruity Delight', '水果狂想曲', 10, 59.99, 'Is chocolate not your thing? No worries! This cake is made with real assorted berries, whipped icing, and fluffy vanilla cake!', '巧克力不是你的菜？ 没关系！精选多种有机浆果，糖霜装饰的100%香草蛋糕', '/images/cake3.jpg', 0, 1, 1);
insert into `Cake` values(0004, 'Chocolate Fantasy','水彩星辰蛋糕', 8, 55.99, 'Dreamy colours in buttercream. Three layers of chocolate or vanilla cake, decorated with with vanilla buttercream and watercolour finish.', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。','/images/cake4.jpg', 0, 1, 1);
insert into `Cake` values(0005, 'Easter Special',     '复活节蛋糕', 12, 57.99, 'This hand decorated spring flower cake is available in chocolate or vanilla cake with vanilla buttercream. ', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干','/images/cake5.jpg', 0, 1, 1);
insert into `Cake` values(0006, 'Coconut Curl', '水果狂想曲', 10, 59.99, 'Vanilla cake with lemon curd filling, coconut buttercream and toasted coconut.', '巧克力不是你的菜？ 没关系！精选多种有机浆果，糖霜装饰的100%香草蛋糕', '/images/cake6.jpg', 0, 1, 1);
insert into `Cake` values(0007, 'Rosette Cake','罗赛特蛋糕', 8, 55.99, 'Turn your favourite cupcake into a cake! With this cake, you can choose any of our 13 cupcake flavours or create your own flavour from our current cake and icing choices!', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/cake7.jpg', 0, 0, 1);
insert into `Cake` values(0008, 'Buttercream Flower',     '奥利奥之梦', 12, 57.99, 'A beautiful cake hand decorated with buttercream flowers. This cake is shown with vanilla buttercream in Vava, Magenta and Teal.', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干','/images/cake8.jpg', 0, 0, 1);
insert into `Cake` values(0009, 'Viva Puff','可可脂蛋糕', 8, 55.99, 'The ultimate indulgence, inspired by one of our favourite childhood cookies. Three layers of rich chocolate cake with marshmallows, jam, dark chocolate ganache and shortbread crumb.', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/cake1.jpg', 0, 1, 1);
insert into `Cake` values(0010, 'Sunshine Confetti',     '奥利奥之梦', 12, 57.99, 'This cake will brighten any gathering! Three layers of vanilla cake baked with rainbow nonpareils and decorated with white vanilla buttercream. Available with a written message on top. ', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干', '/images/cake2.jpg', 0, 1, 1);
insert into `Cake` values(0011, 'Rose Signature Cake', '水果狂想曲', 10, 1.99, 'Beautiful buttercream texture! Three layers of fresh baked cake decorated with coloured vanilla buttercream. ', '巧克力不是你的菜？ 没关系！精选多种有机浆果，糖霜装饰的100%香草蛋糕', '/images/cake3.jpg', 0, 1, 1);
insert into `Cake` values(0012, 'Alpaca Cake','可可脂蛋糕', 8, 55.99, 'This cute-as-can-be alpaca is party ready! Choose three layers of chocolate or vanilla cake. Alpaca cake is decorated in our signature vanilla buttercream icing.', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。','/images/cake4.jpg', 0, 1, 1);
insert into `Cake` values(0013, 'Drip Cake',     '奥利奥之梦', 12, 57.99, 'Three layers of cake, decorated with buttercream icing and topped with milk chocolate ganache.', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干','/images/cake5.jpg', 0, 1, 1);
insert into `Cake` values(0014, 'Horizontal Ruffle', '水果狂想曲', 10, 59.99, 'Three layers of chocolate or vanilla cake decorated with delicate buttercream ruffles. ', '巧克力不是你的菜？ 没关系！精选多种有机浆果，糖霜装饰的100%香草蛋糕', '/images/cake6.jpg', 0, 1, 1);
insert into `Cake` values(0015, 'Strawberry Lemonade','可可脂蛋糕', 8, 55.99, 'Three layers of vanilla cake and a mix of strawberry and lemon buttercream, finished in a Watercolour Star design.', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/cake7.jpg', 0, 0, 1);
insert into `Cake` values(0016, 'Classic Crave',     '奥利奥之梦', 12, 57.99, 'Turn your favourite cupcake into a cake! With this cake, you can choose any of our 13 cupcake flavours or create your own flavour from our current cake and icing choices! ', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干','/images/cake8.jpg', 0, 0, 1);
insert into `Cake` values(0017, 'Wreath Cake','可可脂蛋糕', 8, 55.99, 'This hand decorated floral wreath cake is available in chocolate or vanilla cake with vanilla buttercream. ', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/cake7.jpg', 0, 0, 1);
insert into `Cake` values(0018, 'Double Rainbow',     '奥利奥之梦', 12, 57.99, 'The Double Rainbow is an extra tall six layer cake with a rainbow inside and out! Made with coloured vanilla cake and rainbow vanilla buttercream. ', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干','/images/cake8.jpg', 0, 0, 1);
insert into `Cake` values(0019, 'Classic Crave','可可脂蛋糕', 8, 55.99, 'Turn your favourite cupcake into a cake! With this cake, you can choose any of our 13 cupcake flavours or create your own flavour from our current cake and icing choices! ', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/cake1.jpg', 0, 1, 1);
insert into `Cake` values(0020, 'Oreo Dream',     '奥利奥之梦', 12, 57.99, '﻿Our gender reveal cake features white vanilla buttercream with a pink or blue cake inside.', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干', '/images/cake2.jpg', 0, 1, 1);

--Featured Cakes
insert into `Cake` values(0021, 'Classic Crave',     '经典雕刻蛋糕', 12, 57.99, 'Turn your favourite cupcake into a cake! With this cake, you can choose any of our 13 cupcake flavours or create your own flavour from our current cake and icing choices! ', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干','/images/home1.jpg', 1, 0, 1);
insert into `Cake` values(0022, 'Strawberry Lemonade','草莓柠檬蛋糕', 8, 55.99, 'Three layers of vanilla cake and a mix of strawberry and lemon buttercream, finished in a Watercolour Star design.', '体验由巧克力带来的复活节风情！由精选巧克力糖霜，迷你鸡蛋及全尺寸奶油蛋制成。', '/images/home2.jpg', 1, 0, 1);
insert into `Cake` values(0023, 'Chocolate Fantasy',     '水彩星辰蛋糕', 12, 57.99, 'Beautiful buttercream texture! Three layers of fresh baked cake decorated with coloured vanilla buttercream.', '曾经梦想过品尝一块和蛋糕一样大的奥利奥吗？不要错过！由精选香草糖霜制成，表面装装饰以真正的奥利奥饼干', '/images/home3.jpg', 1, 0, 1);



insert into `Pickup` values(null,"69 Street Park & Ride Surface","69 Street Park & Ride Surface");
insert into `Pickup` values(null,"Tom Baines School North Parking Lot","250 Edgepark Blvd NW, Calgary, AB T3A 3S2");
insert into `Pickup` values(null,"T&T North HSBC Bank Parking Lot","409-9650 Harvest Hills Blvd N, Calgary, AB T3K 0B3");
insert into `Pickup` values(null,"Nickle School Parking Lot","2500 Lake Bonavista Dr SE, Calgary, AB T2J 2Y6");
insert into `Pickup` values(null,"Somerset Station Parking Lot","288 Shawville Way SE Parking");




