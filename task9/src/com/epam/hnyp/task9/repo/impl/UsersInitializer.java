package com.epam.hnyp.task9.repo.impl;

import com.epam.hnyp.task9.model.Roles;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.repo.UserRepo;

public class UsersInitializer {
	public static void initUsers(UserRepo userRepo) {
		userRepo.add(new User("Anton", "Petrovich", "111", "user1@epam.com", true, Roles.ADMIN));
		userRepo.add(new User("Alex", "Gordon", "222", "user2@epam.com", false, Roles.CUSTOMER));
		userRepo.add(new User("Max", "Payne", "333", "user3@epam.com", false, Roles.CUSTOMER));
	}
}
