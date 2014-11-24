package com.epam.hnyp.shop.repo.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.epam.hnyp.shop.model.User;
import com.epam.hnyp.shop.repo.UserRepo;

public class UserInMemoryRepo implements UserRepo {
	private AtomicInteger lastId = new AtomicInteger(-1);
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	private Map<String, User> usersByLogin = new HashMap<String, User>();
	private Map<Integer, User> usersById = new HashMap<Integer, User>();

	@Override
	public int add(User user) {
		w.lock();
		try {
			User u = getUserByLogin(user.getLogin());
			if (u != null) {
				throw new IllegalArgumentException(
						"duplicate: user with login '" + user.getLogin()
								+ "' exists");
			}
			User copyUser = new User(user.getName(),
					user.getLastName(), user.getPassword(), user.getLogin(), user.isReceiveLetters(), user.getRole(), user.getAvatarFile());
			copyUser.setId(lastId.incrementAndGet());
			usersByLogin.put(copyUser.getLogin(), copyUser);
			usersById.put(copyUser.getId(), copyUser);
			return copyUser.getId();
		} finally {
			w.unlock();
		}
	}

	@Override
	public void remove(int id) {
		w.lock();
		try {
			User remUser = usersById.get(id);
			if (remUser == null) {
				throw new IllegalArgumentException("user not found");
			}
			usersById.remove(id);
			usersByLogin.remove(remUser.getLogin());

		} finally {
			w.unlock();
		}
	}

	@Override
	public User get(int id) {
		r.lock();
		try {
			return getUserById(id);
		} finally {
			r.unlock();
		}
	}

	private User getUserById(int id) {
		return usersById.get(id);
	}

	private User getUserByLogin(String login) {
		return usersByLogin.get(login);
	}

	@Override
	public User getByLogin(String login) {
		r.lock();
		try {
			return getUserByLogin(login);
		} finally {
			r.unlock();
		}
	}

	@Override
	public Collection<User> getAll() {
		r.lock();
		try {
			return Collections.unmodifiableCollection(usersById.values());
		} finally {
			r.unlock();
		}
	}

	@Override
	public void update(User user) {
		throw new UnsupportedOperationException();
	}

}