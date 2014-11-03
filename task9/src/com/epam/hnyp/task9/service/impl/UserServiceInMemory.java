package com.epam.hnyp.task9.service.impl;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.repo.UserRepo;
import com.epam.hnyp.task9.service.UserService;

public class UserServiceInMemory implements UserService{

	private UserRepo userRepo;
	
	public UserServiceInMemory() {
	}
	
	public UserServiceInMemory(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	/**
	 * @throws IllegalArgumentException - if user with specified login already exists
	 */
	@Override
	public void add(User user) {
		userRepo.add(user);
	}

	/**
	 * @throws IllegalArgumentException - if user with specified id not found
	 */
	@Override
	public void remove(int id) {
		userRepo.remove(id);
	}

	@Override
	public User get(int id) {
		return userRepo.get(id);
	}

	@Override
	public User getByLogin(String email) {
		return userRepo.getByLogin(email);
	}

	@Override
	public boolean userExists(String login) {
		return userRepo.getByLogin(login) != null;
	}

	@Override
	public Collection<User> getAll() {
		return userRepo.getAll();
	}

	public UserRepo getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public void update(User user) {
		throw new UnsupportedOperationException();
	}


	
}