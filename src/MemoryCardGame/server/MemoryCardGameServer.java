package MemoryCardGame.server;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.io.IOException;

public class MemoryCardGameServer extends AbstractServer {
	
	public MemoryCardGameServer() {
		super(8301);
	}
	
	@Override
	protected void handleMessageFromClient(Object object, ConnectionToClient client) {
		System.out.println("Server received: " + object);
	}
	
	@Override
	protected void serverStarted() {
	
	}
	
	@Override
	protected void serverStopped() {
	
	}
	
	@Override
	protected void clientConnected(ConnectionToClient client) {
		System.out.println("Client connected");
		try {
			client.sendToClient("Welcome to the Memory Card Game Server!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void clientDisconnected(ConnectionToClient client) {
	
	}
	
	@Override
	protected void clientException(ConnectionToClient client, Throwable exception) {
	
	}
	
}
