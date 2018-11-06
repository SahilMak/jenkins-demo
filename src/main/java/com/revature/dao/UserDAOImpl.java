package com.revature.dao;

import java.util.List;

import com.revature.db.UserDB;
import com.revature.models.User;

public class UserDAOImpl implements UserDAO {
	
	private UserDB userDB = UserDB.userDB;

	public List<User> getAllUsers() {
		return userDB.getAllUsers();
	}

	public User getUserById(int userId) {
		return userDB.getUserByID(userId);
	}
	
	public User getUserByUsername(String username) {
		return userDB.getUserByUsername(username);
	}

	public User getUserByCredentials(String username, String password) {
		return userDB.getUserByCredentials(username, password);
	}

	public User addUser(User newUser) {
		return userDB.addUser(newUser);
	}

	public boolean updateUser(User updatedUser) {
		return userDB.updateUser(updatedUser);
	}

	public boolean deleteUser(int userId) {
		return userDB.deleteUser(userId);
		
	}

}
