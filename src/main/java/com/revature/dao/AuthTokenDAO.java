package com.revature.dao;

import java.util.List;

import com.revature.models.AuthToken;

public interface AuthTokenDAO {

	List<AuthToken> getAllTokens();
	AuthToken getTokenByID(String tokenID);
	AuthToken addToken(AuthToken newToken);
	boolean removeTokenByID(String tokenID);
	Long removeExpiredTokens();
	
}
