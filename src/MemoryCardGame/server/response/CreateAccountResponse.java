package MemoryCardGame.server.response;

import MemoryCardGame.client.request.CreateAccountRequest;

import java.io.Serializable;

public class CreateAccountResponse implements Serializable {
	
	private final CreateAccountRequest source;
	private final boolean              success;
	private final String       failureMessage;
	
	public CreateAccountResponse(CreateAccountRequest source, boolean success, String failureMessage) {
		this.source = source;
		this.success = success;
		this.failureMessage = failureMessage;
	}
	
	public CreateAccountResponse(CreateAccountRequest source, boolean success) {
		this(source, success, null);
	}
	
	public CreateAccountRequest getSource() {
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
		return "CreateAccountResponse[source=" + source + ",success=" + success + ",failureMessage=" + failureMessage + "]";
	}
	
}
