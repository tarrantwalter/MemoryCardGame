package MemoryCardGame.server.game;

import ocsf.server.ConnectionToClient;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
	
	private final Map<ConnectionToClient, Player> players = new HashMap<>();
	
	public Player createPlayer(String username, ConnectionToClient client) {
		return players.computeIfAbsent(client, ignore -> new Player(username, client));
	}
	
	public Player getPlayer(ConnectionToClient client) {
		return players.get(client);
	}
	
}
