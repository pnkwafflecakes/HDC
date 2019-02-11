-- FKs should only exist on "many" side
-- Cant delete a table with FKs in another
DROP DATABASE if exists capstonedb;
CREATE DATABASE capstonedb;
USE capstonedb;


-- Proper Order
-- Drop table CakeOrder;
-- Drop table Cake;
-- Drop table CakeCategory;
-- Drop table Order;
-- Drop table Delivery;
-- Drop table User;
-- Drop table Account;

-- Proper Order

Create table `Account` -- To add constraints
(
    `account_no` int(4) NOT NULL,
    `account_type` int(1) NOT NULL, -- Combined w/ Privileges, will add constraints, turned to int --0 is regular, 1 is admin, 2 is guest
    `username` VARCHAR(30) NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    `account_status` boolean NOT NULL, -- Change to int
    -- Primary Key
    PRIMARY KEY (`account_no`)
);

Create table `User`
(
    `user_id` int(4) NOT NULL,
    `account_no` int(4) NOT NULL,
    `name` VARCHAR(90),
    `address` VARCHAR(99),
    `postal_code` VARCHAR(6),
    `email` VARCHAR(99),
    `phone_no` VARCHAR(12),
    -- Primary Key
    PRIMARY KEY (`user_id`),
    -- Forign Key
    KEY `FK_User_Account_No` (`account_no`), 
    CONSTRAINT `FK_User_Account_No` FOREIGN KEY (`account_no`) REFERENCES `Account` (`Account_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

Create table `Delivery`
(
    `delivery_no` int(4) NOT NULL,
    `method` VARCHAR(30) NOT NULL,
    `address` VARCHAR(80) NOT NULL,
    `phone_no` VARCHAR(12) NOT NULL,
    `notes` VARCHAR(99) NOT NULL,
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
    -- Primary Key
    PRIMARY KEY (`order_no`),
    -- Forign Key
    KEY `FK_ORDER_USER_ID` (`user_id`), 
    CONSTRAINT `FK_ORDER_USERID` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    KEY `FK_ORDER_Delivery_No` (`delivery_no`), 
    CONSTRAINT `FK_ORDER_Delivery_No` FOREIGN KEY (`delivery_no`) REFERENCES `Delivery` (`delivery_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

Create table `CakeCategory`
(
    `category_id` int(4) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(99) NOT NULL,
    PRIMARY KEY (`category_id`)
);

Create table `Cake` -- To add constraints
(
    `cake_id` int(4) NOT NULL,
    `category_id` int(4) NOT NULL,
    `name` VARCHAR(99) NOT NULL,
    -- Category variable is redundant
    `size` int(2), -- Change from char, will be in inches THE DI
    `price` double (6,2) NOT NULL,
    `description` VARCHAR(99) NOT NULL, -- it be here
    `image` VARCHAR(99) NOT NULL,
    -- Primary Key
    PRIMARY KEY (`cake_id`),
    -- Forign Key
    KEY `FK_Cake_Category_Id` (`category_id`), 
    CONSTRAINT `FK_Cake_Category_Id` FOREIGN KEY (`category_id`) REFERENCES `CakeCategory` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

Create table `CakeOrder` -- To add constraints
(
    `order_no` int(4) NOT NULL,
    `cake_id` int(4) NOT NULL,
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

insert into `Account` values(0000, 0, 'billyjoe', 'abc', 1);
insert into `Account` values(0001, 1, 'argv', 'abcd', 1);
insert into `Account` values(0002, 2, '1521', '1234', 1);

--Possibly just get rid of Account_id and just use user_id
insert into `User` values(0000, 0000, 'Billy Joe', '123 Sample St', 'T2X2M2', 'email@sample.com', '123-123-1234');
insert into `User` values(0001, 0001, 'Argis Fargis', '123 Sample Rd', 'a1a2b2', 'email@sample.ca', '123-123-1235');
insert into `User` values(0002, 0002, 'Arbichov Gopnik', '123 Sample Av', 'T2X2M9', 'email@sample.ru', '153-123-1236');

--Might need further work, to allow certain extra details based off of things like 'Drop Off'
insert into `Delivery` values(0000, 'Drop off', '211 Sample Road', '111-111-1111', 'Say happy Birthday to son');

insert into `Orders` values(0000, 0000, NOW(), NOW(), 'Awesome Cake', 60.00, 0000);

insert into `CakeCategory` values(0000, 'Cool Cakes', 'Cakes thats are cool');

insert into `Cake` values(0000, 0000, 'Cake1', 11, 55.99, 'Very Awesome Cake', '/WEB-INF/images/cake1.jpg');
insert into `Cake` values(0001, 0000, 'Cake2', 12, 57.99, 'Very Awesome Cake 2', '/WEB-INF/images/cake2.jpg');
insert into `Cake` values(0002, 0000, 'Cake3', 13, 59.99, 'Very Awesome Cake 3', '/WEB-INF/images/cake3.jpg');

insert into `CakeOrder` values(0000, 0000);

