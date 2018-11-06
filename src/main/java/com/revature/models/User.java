package com.revature.models;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private Integer userID;
	private String username;
	private String password;
	private Integer roleID;
	
	public User() {
		super();
		this.roleID = 2;
	}

	public User(Integer userID, String username, String password, Integer roleID) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.roleID = roleID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleID == null) {
			if (other.roleID != null)
				return false;
		} else if (!roleID.equals(other.roleID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", roleID=" + roleID + "]";
	}

}
