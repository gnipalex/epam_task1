package com.epam.hnyp.shop.repo.impl;

import com.epam.hnyp.shop.model.Role;
import com.epam.hnyp.shop.model.User;
import com.epam.hnyp.shop.repo.UserRepo;

public class UsersInitializer {
	public static void initUsers(UserRepo userRepo) {
		userRepo.add(new User("Anton", "Petrovich", "111", "user1@epam.com", true, Role.ADMIN));
		userRepo.add(new User("Alex", "Gordon", "222", "user2@epam.com", false, Role.CUSTOMER));
		userRepo.add(new User("Max", "Payne", "333", "user3@epam.com", false, Role.CUSTOMER));
	}
}
