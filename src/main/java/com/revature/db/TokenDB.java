package com.revature.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.revature.models.AuthToken;

public class TokenDB extends HashMap<String, AuthToken> {

	private static final long serialVersionUID = 1L;

	public static TokenDB tokenDB = new TokenDB();
	
	public List<AuthToken> getAllTokens() {
		
		List<AuthToken> authTokens = new ArrayList<>();
		
		for (Map.Entry<String, AuthToken> entry : tokenDB.entrySet()) {
			authTokens.add(entry.getValue());
		}
		
		return authTokens;
	}

	public AuthToken getTokenByID(String tokenID) {

		for (Map.Entry<String, AuthToken> entry : tokenDB.entrySet()) {
			if (entry.getValue().getSessionID().equals(UUID.fromString(tokenID))) {
				return entry.getValue();
			}
		}

		return null;
	}

	public AuthToken addToken(AuthToken newToken) {
		tokenDB.put(newToken.getSessionID().toString(), newToken);
		return tokenDB.getTokenByID(newToken.getSessionID().toString());
	}

	public boolean deleteToken(String tokenID) {
		tokenDB.remove(tokenID);
		return (tokenDB.getTokenByID(tokenID) != null);
	}

	public boolean removeTokenByID(String tokenID) {

		for (Map.Entry<String, AuthToken> entry : tokenDB.entrySet()) {
			if (entry.getKey().equals(tokenID)) {
				tokenDB.remove(entry.getKey());
				return true;
			}
		}

		return false;
	}

	public Long removeExpiredTokens() {

		Long tokensRemoved = 0L;

		for (Map.Entry<String, AuthToken> entry : tokenDB.entrySet()) {
			if (entry.getValue().getExpiration().isAfter(LocalDateTime.now())) {
				tokenDB.remove(entry.getKey());
				tokensRemoved++;
			}
		}

		return tokensRemoved;
	}

}
