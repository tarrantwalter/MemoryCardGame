package MemoryCardGame.server;

import MemoryCardGame.client.request.CardSelectRequest;
import MemoryCardGame.client.request.LoginRequest;
import MemoryCardGame.server.game.Game;
import MemoryCardGame.server.game.GameProvider;
import MemoryCardGame.server.game.Player;
import MemoryCardGame.server.game.PlayerManager;
import MemoryCardGame.server.response.JoinResponse;
import MemoryCardGame.server.response.LoginResponse;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.logging.Logger;

public class MemoryCardGameServer extends AbstractServer {
	
	private final Logger logger;
	
	private final GameProvider  gameProvider;
	private final PlayerManager playerManager;
	private final Database      database;
	
	public MemoryCardGameServer() {
		super(8301);
		gameProvider = new GameProvider(this);
		playerManager = new PlayerManager();
		logger = Logger.getLogger("MemoryCardGameServer");
		database = new Database();
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	@Override
	protected void handleMessageFromClient(Object object, ConnectionToClient client) {
		if (object instanceof LoginRequest request) {
			
			logger.info("Server received LoginRequest: " + request);
			
			if ((request.getUsername().equals("test1") && request.getPassword().equals("1")) ||
					(request.getUsername().equals("test2") && request.getPassword().equals("2"))) {
				
				send(client, new LoginResponse(request, true));
				playerManager.createPlayer(request.getUsername(), client);
				
			} else {
				send(client, new LoginResponse(request, false, "Invalid username or password"));
			}
			
		} else if (object instanceof String request) {
			
			logger.info("Server received String: " + request);
			
			if (request.equals("JoinGame")) {
				send(client, new JoinResponse(true));
				gameProvider.playerJoined(playerManager.getPlayer(client));
			} else if (request.equals("LeaveGame")) {
				gameProvider.playerLeft(playerManager.getPlayer(client));
			}
			
		} else if (object instanceof CardSelectRequest request) {
			
			logger.info("Server received CardSelectRequest: " + request);
			
			Player player = playerManager.getPlayer(client);
			Game   game   = gameProvider.getPlayerGame(player);
			game.playerSelectedCard(player, request.getX(), request.getY());
			
		}
	}
	
	@Override
	protected void serverStarted() {
		logger.info("Server started");
	}
	
	@Override
	protected void serverStopped() {
		logger.info("Server stopped");
	}
	
	@Override
	protected void clientConnected(ConnectionToClient client) {
		logger.info("Client connected");
	}
	
	@Override
	protected void clientDisconnected(ConnectionToClient client) {
		logger.info("Client disconnected");
	}
	
	@Override
	protected void clientException(ConnectionToClient client, Throwable exception) {
		logger.info("Client exception");
		exception.printStackTrace();
	}
	
	public void send(ConnectionToClient client, Object message) {
		try {
			logger.info("Sending to client " + client.getId() + ": " + message);
			client.sendToClient(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
