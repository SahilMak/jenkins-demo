package com.revature.dao;

import java.util.List;

import com.revature.db.TokenDB;
import com.revature.models.AuthToken;

public class AuthTokenDAOImpl implements AuthTokenDAO {
	
	private TokenDB tokenDB = TokenDB.tokenDB;

	@Override
	public List<AuthToken> getAllTokens() {
		return tokenDB.getAllTokens();
	}
	
	@Override
	public AuthToken getTokenByID(String tokenID) {
		return tokenDB.getTokenByID(tokenID);
	}

	@Override
	public AuthToken addToken(AuthToken newToken) {
		return tokenDB.addToken(newToken);
	}

	@Override
	public boolean removeTokenByID(String tokenID) {
		return tokenDB.removeTokenByID(tokenID);
	}
	
	@Override
	public Long removeExpiredTokens() {
		return tokenDB.removeExpiredTokens();
	}

}
