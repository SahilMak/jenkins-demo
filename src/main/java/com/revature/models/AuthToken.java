package com.revature.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuthToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID sessionID;
	private LocalDateTime expiration;
	private boolean isAdmin;
	
	public AuthToken() {
		super();
		this.sessionID = UUID.randomUUID();
		this.expiration = LocalDateTime.now().plusHours(6);
	}
	
	public AuthToken(UUID sessionID, LocalDateTime expiration, boolean isAdmin) {
		super();
		this.sessionID = sessionID;
		this.expiration = expiration;
		this.isAdmin = false;
	}

	public UUID getSessionID() {
		return sessionID;
	}

	public void setSessionID(UUID sessionID) {
		this.sessionID = sessionID;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((sessionID == null) ? 0 : sessionID.hashCode());
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
		AuthToken other = (AuthToken) obj;
		if (expiration == null) {
			if (other.expiration != null)
				return false;
		} else if (!expiration.equals(other.expiration))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (sessionID == null) {
			if (other.sessionID != null)
				return false;
		} else if (!sessionID.equals(other.sessionID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuthToken [sessionID=" + sessionID + ", expiration=" + expiration + ", isAdmin=" + isAdmin + "]";
	}
}
