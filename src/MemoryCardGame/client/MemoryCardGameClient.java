package MemoryCardGame.client;

import MemoryCardGame.server.info.EndGameInfo;
import MemoryCardGame.server.info.StartGameInfo;
import MemoryCardGame.server.response.JoinResponse;
import MemoryCardGame.server.response.LoginResponse;
import ocsf.client.AbstractClient;

import java.io.IOException;
import java.util.logging.Logger;

public class MemoryCardGameClient extends AbstractClient {
	
	private final Logger                  logger;
	private final MemoryCardGameClientGUI gui;
	
	public MemoryCardGameClient(MemoryCardGameClientGUI gui) {
		super("localhost", 8301);
		this.logger = Logger.getLogger("MemoryCardGameClient");
		this.gui = gui;
	}
	
	@Override
	protected void handleMessageFromServer(Object object) {
		if (object instanceof LoginResponse response) {
			
			logger.info("Login response: " + response);
			
			if (response.wasSuccessful()) {
				logger.info("Login successful.");
				gui.switchToPanel(MemoryCardGameClientGUI.PRE_WAITING_PANEL);
			} else {
				logger.info("Login failed, resson: " + response.getFailureMessage());
				gui.getLoginPanel().setErrorMessage(response.getFailureMessage());
			}
			
		} else if (object instanceof JoinResponse response) {
			
			logger.info("Join response: " + response);
			
			if (response.wasSuccessful()) {
				logger.info("Join successful.");
				gui.switchToPanel(MemoryCardGameClientGUI.POST_WAITING_PANEL);
			} else {
				logger.info("Join failed, resson: " + response.getFailureMessage());
				//gui.getJoinPanel().setErrorMessage(response.getFailureMessage());
			}
			
		} else if (object instanceof String response) {
			
			logger.info("String response: " + response);
			
		} else if (object instanceof StartGameInfo info) {
			
			logger.info("StartGameInfo: " + info);
			
			gui.switchToPanel(MemoryCardGameClientGUI.GAME_PANEL);
			
		} else if (object instanceof EndGameInfo info) {
			
			logger.info("EndGameInfo: " + info);
			
			gui.getGameOverPanel().setEndGameInfo(info);
			gui.switchToPanel(MemoryCardGameClientGUI.GAME_OVER_PANEL);
			
		} else {
			logger.severe("Unknown response: " + object);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	@Override
	protected void connectionClosed() {
		logger.info("Connection closed.");
	}
	
	@Override
	protected void connectionException(Exception exception) {
		logger.info("Connection exception");
		exception.printStackTrace();
	}
	
	public void start() {
		try {
			openConnection();
			logger.info("Started connection.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			closeConnection();
			logger.info("Stopped connection.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Object message) {
		try {
			logger.info("Sending message: " + message);
			sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
