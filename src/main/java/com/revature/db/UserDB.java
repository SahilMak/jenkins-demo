package com.revature.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.models.User;

public class UserDB extends HashMap<Integer, User> {

	private static final long serialVersionUID = 1L;

	public static UserDB userDB = new UserDB();
	public static int key = 0;
	
	static {
		userDB.put(++key, new User(key, "wsingleton", "p4ssw0rd", 1));
		userDB.put(++key, new User(key, "bkruppa", "password", 2));
		userDB.put(++key, new User(key, "jknighten", "dr0wss4p", 2));
		userDB.put(++key, new User(key, "njurczak", "drowssap", 2));
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		for (Map.Entry<Integer, User> entry : userDB.entrySet()) {
			users.add(entry.getValue());
		}

		return users;
	}
	
	public User getUserByID(int userID) {

		for (Map.Entry<Integer, User> entry : userDB.entrySet()) {
			if (entry.getValue().getUserID() == userID) {
				return entry.getValue();
			}
		}

		return null;
	}

	public User getUserByUsername(String username) {

		for (Map.Entry<Integer, User> entry : userDB.entrySet()) {
			if (entry.getValue().getUsername().equals(username)) {
				return entry.getValue();
			}
		}

		return null;
	}

	public User getUserByCredentials(String username, String password) {

		for (Map.Entry<Integer, User> entry : userDB.entrySet()) {
			if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)) {
				return entry.getValue();
			}
		}

		return null;
	}
	
	public User addUser(User newUser) {
		userDB.put(++key, newUser);
		newUser.setUserID(key);
		return userDB.getUserByID(key);
	}
	
	public boolean updateUser(User updatedUser) {
		userDB.put(key, updatedUser);
		return (userDB.getUserByID(updatedUser.getUserID()) != null);
	}
	
	public boolean deleteUser(int userID) {
		userDB.remove(userID);
		
		return (userDB.getUserByID(userID) != null);
		
	}

}
