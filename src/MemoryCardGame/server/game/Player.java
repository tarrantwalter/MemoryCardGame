package MemoryCardGame.server.game;

import ocsf.server.ConnectionToClient;

import java.io.IOException;

public class Player {
	
	private final String username;
	private final ConnectionToClient client;
	
	public Player(String username, ConnectionToClient client) {
		this.username = username;
		this.client = client;
	}
	
	public String getUsername() {
		return username;
	}
	
	public ConnectionToClient getClient() {
		return client;
	}
	
	public void send(Object message) {
		try {
			client.sendToClient(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Player[username=" + username + "]";
	}
	
}

