USE `task_shop`;

DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `vendors`;

CREATE TABLE `categories` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null unique
);

INSERT INTO `categories`(`name`) VALUES ('drink'), ('bakery'), ('alcohol'), ('sweets');

CREATE TABLE `vendors` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null unique
);

INSERT INTO `vendors`(`name`) VALUES ('roshen'), ('mechanized bakery in Kharkov'), ('russia matushka');

CREATE TABLE `products` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null,
    `vendor_id` int not null,
    `price` long not null check (`price` > 0),
    `category_id` int not null,
    `weight` double check (`weight` > 0),
    `description` varchar(500),
    `imgFile` varchar(20),
    `available` boolean not null,
    constraint `prod_category_fk` foreign key(`category_id`) references `categories`(`id`)
		on delete cascade on update cascade,
	constraint `prod_vendor_fk` foreign key(`vendor_id`) references `vendors`(`id`)
		on delete cascade on update cascade
);

INSERT INTO `products`(`name`, `vendor_id`, `price`, `category_id`, `weight`, `description`, `imgFile`, `available`) VALUES
						('artek vafles', 1, 250, 4, 0.5, 'taste from the childhood', null, true),
                        ('borodinskiy', 2, 100, 2, 0.7, 'the best bread ever', null, true),
                        ('stolichnaya', 3, 2500, 3, 1.0, 'natural russian vodka', null, true);