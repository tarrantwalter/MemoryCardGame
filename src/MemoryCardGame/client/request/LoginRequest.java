package MemoryCardGame.client.request;

import java.io.Serializable;

public class LoginRequest implements Serializable {
	
	private final String username;
	private final String password;
	
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "LoginRequest[username=" + username + ",password=" + password + "]";
	}
	
}

