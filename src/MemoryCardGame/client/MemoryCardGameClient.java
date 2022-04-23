package MemoryCardGame.client;

import ocsf.client.AbstractClient;

import java.io.IOException;

public class MemoryCardGameClient extends AbstractClient {
	
	public MemoryCardGameClient() {
		super("localhost", 8301);
	}
	
	@Override
	protected void handleMessageFromServer(Object object) {
		System.out.println("Message from server: " + object);
		try {
			sendToServer("Thank you!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
