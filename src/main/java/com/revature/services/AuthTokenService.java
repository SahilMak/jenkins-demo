package com.revature.services;

import java.util.List;

import com.revature.dao.AuthTokenDAO;
import com.revature.dao.AuthTokenDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.AuthToken;
import com.revature.models.User;

public class AuthTokenService {

	private UserDAO userDAO = new UserDAOImpl();
	private AuthTokenDAO authTokenDAO = new AuthTokenDAOImpl();
	
	public List<AuthToken> getAllTokens() {
		return authTokenDAO.getAllTokens();
	}

	public AuthToken getAuthTokenByID(String tokenID) {
		return authTokenDAO.getTokenByID(tokenID);
	}

	public AuthToken addToken(AuthToken newToken) {
		return authTokenDAO.addToken(newToken);
	}
	
	public boolean removeTokenByID(String tokenID) {
		return authTokenDAO.removeTokenByID(tokenID);
	}

	public Long removedExpiredTokens() {
		return authTokenDAO.removeExpiredTokens();
	}

	public AuthToken loginUser(String username, String password) {

		AuthToken newToken;
		User authUser = userDAO.getUserByCredentials(username, password);

		if(authUser != null) {
			
			System.out.println("[LOG] - User with credentials " + username + "/" + password + " authenticated");
			newToken = new AuthToken();
			if(authUser.getRoleID() == 1) newToken.setAdmin(true);
			return addToken(newToken);
			
		} else {
			System.out.println("[ERROR] - No user found with credentials: " + username + "/" + password);
		}

		return null;
	}

}
