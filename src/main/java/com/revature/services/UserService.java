package com.revature.services;

import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}
	
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	public User addUser(User newUser) {
		return userDAO.addUser(newUser);
	}
	
	public boolean updateUser(User updatedUser) {
		return userDAO.updateUser(updatedUser);
	}
	
	public boolean deleteUser(int userId) {
		return userDAO.deleteUser(userId);
	}

}
