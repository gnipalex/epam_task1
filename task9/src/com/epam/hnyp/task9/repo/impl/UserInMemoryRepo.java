package com.epam.hnyp.task9.repo.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.repo.UserRepo;

public class UserInMemoryRepo implements UserRepo {
	private long lastId = -1;
	private List<User> users = new ArrayList<>();
	
	@Override
	public boolean add(User user) {
		User u = getByLogin(user.getLogin());
		if (u != null) {
			return false;
		}
		if (user.getId() == 0) {
			user.setId(++lastId);
		} else if (user.getId() > lastId) {
			lastId = user.getId();
		} else {
			return false;
		}
		users.add(user);
		return true;
	}

	@Override
	public boolean remove(long id) {
		int index = -1;
		for (int i=0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				index = i;
				break;
			}
		}
		if (index > 0) {
			users.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public User get(long id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	@Override
	public User getByLogin(String login) {
		for (User u : users) {
			if (u.getLogin().equals(login)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public Collection<User> getAll() {
		return Collections.unmodifiableList(users);
	}
}
