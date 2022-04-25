package MemoryCardGame.server.response;

import java.io.Serializable;

public class JoinResponse implements Serializable {
	
	private final boolean      success;
	private final String       failureMessage;
	
	public JoinResponse(boolean success, String failureMessage) {
		this.success = success;
		this.failureMessage = failureMessage;
	}
	
	public JoinResponse(boolean success) {
		this(success, null);
	}
	
	public boolean wasSuccessful() {
		return success;
	}
	
	public String getFailureMessage() {
		return failureMessage;
	}
	
	@Override
	public String toString() {
		return "LoginResponse[success=" + success + ",failureMessage=" + failureMessage + "]";
	}
	
}
