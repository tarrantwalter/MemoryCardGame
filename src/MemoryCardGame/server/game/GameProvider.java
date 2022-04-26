package MemoryCardGame.server.game;

import MemoryCardGame.server.MemoryCardGameServer;

import java.util.HashSet;
import java.util.Set;

public class GameProvider {
	
	private final MemoryCardGameServer server;
	
	public GameProvider(MemoryCardGameServer server) {
		this.server = server;
	}
	
	private final Set<Game> activeGames = new HashSet<>();
	
	private Player waitingPlayer = null;
	
	public void playerJoined(Player player) {
		if (getPlayerGame(player) != null) {
			throw new IllegalStateException("Player '" + player.getUsername() + "' tried to join a game when they are already in one!");
		}
		if (waitingPlayer == null) {
			waitingPlayer = player;
			server.getLogger().info("Player '" + player.getUsername() + "' joined a game, waiting for another player to join.");
		} else {
			server.getLogger()
					.info("Player '" + player.getUsername() + "' joined a game, starting game with Player '" + waitingPlayer.getUsername() + "'.");
			Game game = new Game(this, 6, waitingPlayer, player);
			activeGames.add(game);
			waitingPlayer = null;
		}
	}
	
	public MemoryCardGameServer getServer() {
		return server;
	}
	
	public void playerLeft(Player player) {
		Game game = getPlayerGame(player);
		if (game != null) {
			game.playerLeft(player);
		} else {
			server.getLogger().warning("Player '" + player.getUsername() + "' tried to leave a game they are not in!");
		}
	}
	
	public Game getPlayerGame(Player player) {
		for (Game game : activeGames) {
			if (game.containsPlayer(player)) {
				return game;
			}
		}
		return null;
	}
	
	public void removeGame(Game game) {
		activeGames.remove(game);
	}
	
}
