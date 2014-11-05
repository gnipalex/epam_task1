package com.epam.hnyp.task9.service;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserService {
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	void add(User user);
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	void remove(int id);
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	void update(User user);
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	User get(int id);
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	User getByLogin(String email);
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	boolean userExists(String login);
	/**
	 * 
	 * @param user
	 * @throws ServiceLayerException if error occured at data access level
	 */
	Collection<User> getAll();
}
