package com.epam.hnyp.task9.service.impl;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.repo.UserRepo;
import com.epam.hnyp.task9.service.UserService;

public class UserServiceImpl implements UserService{

	private UserRepo userRepo;
	
	public UserServiceImpl() {
	}
	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public boolean add(User user) {
		return userRepo.add(user);
	}

	@Override
	public boolean remove(long id) {
		return userRepo.remove(id);
	}

	@Override
	public User get(long id) {
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


	
}
