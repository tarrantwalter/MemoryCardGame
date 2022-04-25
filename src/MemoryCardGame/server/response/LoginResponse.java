package MemoryCardGame.server.response;

import MemoryCardGame.client.request.LoginRequest;

import java.io.Serializable;

public class LoginResponse implements Serializable {
	
	private final LoginRequest source;
	private final boolean      success;
	private final String       failureMessage;
	
	public LoginResponse(LoginRequest source, boolean success, String failureMessage) {
		this.source = source;
		this.success = success;
		this.failureMessage = failureMessage;
	}
	
	public LoginResponse(LoginRequest source, boolean success) {
		this(source, success, null);
	}
	
	public LoginRequest getSource() {
		return source;
	}
	
	public boolean wasSuccessful() {
		return success;
	}
	
	public String getFailureMessage() {
		return failureMessage;
	}
	
	@Override
	public String toString() {
		return "LoginResponse[source=" + source + ",success=" + success + ",failureMessage=" + failureMessage + "]";
	}
	
}

