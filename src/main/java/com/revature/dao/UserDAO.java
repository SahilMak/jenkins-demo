package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	List<User> getAllUsers();
	User getUserById(int userId);
	User getUserByUsername(String username);
	User getUserByCredentials(String username, String password);
	User addUser(User newUser);
	boolean updateUser(User updatedUser);
	boolean deleteUser(int userId);

}
