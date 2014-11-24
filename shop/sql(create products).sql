USE `task_shop`;

DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `manufacturers`;

CREATE TABLE `categories` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null unique
);

INSERT INTO `categories`(`name`) VALUES ('drink'), ('bakery'), ('alcohol'), ('sweets'), ('milk product'), ('fish');

CREATE TABLE `manufacturers` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null unique
);

INSERT INTO `manufacturers`(`name`) VALUES ('roshen'), ('mechanized bakery in Kharkov'), ('russia matushka'), ('romol');

CREATE TABLE `products` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null,
    `manufacturer_id` int not null,
    `price` long not null check (`price` > 0),
    `category_id` int not null,
    `weight` double check (`weight` > 0),
    `description` varchar(500),
    `imgFile` varchar(50),
    `available` boolean not null,
    constraint `prod_category_fk` foreign key(`category_id`) references `categories`(`id`)
		on delete cascade on update cascade,
	constraint `prod_manufacturer_fk` foreign key(`manufacturer_id`) references `manufacturers`(`id`)
		on delete cascade on update cascade
);

INSERT INTO `products`(`name`, `manufacturer_id`, `price`, `category_id`, `weight`, `description`, `imgFile`, `available`) VALUES
						('artek vafles', 1, 250, 4, 0.5, 'taste from the childhood', null, true),
                        ('borodinskiy', 2, 100, 2, 0.7, 'the best bread ever', 'borodinskiy.jpg', true),
                        ('stolichnaya', 3, 2500, 3, 1.0, 'natural russian vodka', 't_stolichnaya_vodka_113.jpg', true),
                        ('milk', 4, 250, 5, 0.5, 'taste from the childhood', null, true),
                        ('butter', 4, 100, 5, 0.7, 'from cow', null, true),
                        ('fanta', 3, 2500, 1, 1.0, 'with antural juice', 'fanta.jpg', true),
                        ('sardina', 3, 250, 6, 0.5, 'taste from sea', 'sardina.jpg', true),
                        ('cheeze', 3, 100, 5, 0.7, 'the best cheeze from russia', null, true),
                        ('fdgdfgdfg', 3, 2500, 3, 1.0, 'ggdfgdgdg', null, true);