package MemoryCardGame.client.request;

import java.io.Serializable;

public class CreateAccountRequest implements Serializable {
	
	private final String username;
	private final String password;
	
	public CreateAccountRequest(String username, String password) {
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
		return "CreateAccountRequest[username=" + username + ",password=" + password + "]";
	}
	
}
