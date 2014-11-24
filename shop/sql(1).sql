DROP DATABASE IF EXISTS `task_shop`;

CREATE DATABASE `task_shop`;

USE `task_shop`;

/*
CREATE TABLE `roles` (
	`id` int not null primary key auto_increment,
	`name` varchar(20) not null unique
);

INSERT INTO `roles`(`id`, `name`) VALUES(1, 'CUSTOMER'), (2, 'ADMIN');
*/

CREATE TABLE `users` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null,
    `lastName` varchar(50) not null,
    `password` varchar(50) not null,
    `login` varchar(50) not null unique,
	`receiveLetters` boolean not null,
	`avatarFile` varchar(50),
    `role` enum ('CUSTOMER', 'ADMIN') not null
    /*`role_id` int not null,
	constraint `user_role_fk` foreign key(role_id) references `roles`(`id`) 
					on delete cascade on update cascade
    */
);

