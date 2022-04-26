package MemoryCardGame.client;

import MemoryCardGame.server.info.EndGameInfo;
import MemoryCardGame.server.info.FlipCardInfo;
import MemoryCardGame.server.info.StartGameInfo;
import MemoryCardGame.server.info.TurnInfo;
import MemoryCardGame.server.response.CreateAccountResponse;
import MemoryCardGame.server.response.JoinResponse;
import MemoryCardGame.server.response.LoginResponse;
import ocsf.client.AbstractClient;

import java.io.IOException;
import java.util.logging.Logger;

public class MemoryCardGameClient extends AbstractClient {
	
	private final Logger                  logger;
	private final MemoryCardGameClientGUI gui;
	
	public MemoryCardGameClient(MemoryCardGameClientGUI gui, String host) {
		super(host, 8301);
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
			}
			
		} else if (object instanceof String response) {
			
			logger.info("String response: " + response);
			
		} else if (object instanceof StartGameInfo info) {
			
			logger.info("StartGameInfo: " + info);
			
			gui.getGamePanel().setStartGameInfo(info);
			gui.switchToPanel(MemoryCardGameClientGUI.GAME_PANEL);
			
		} else if (object instanceof EndGameInfo info) {
			
			logger.info("EndGameInfo: " + info);
			
			gui.getGamePanel().reset();
			gui.getGameOverPanel().setEndGameInfo(info);
			gui.switchToPanel(MemoryCardGameClientGUI.GAME_OVER_PANEL);
			
		} else if (object instanceof TurnInfo info) {
			
			logger.info("TurnInfo: " + info);
			
			gui.getGamePanel().setTurn(info.isTurn());
			gui.getGamePanel().updateScores(info.getMyScore(), info.getOpponentScore());
			
		} else if (object instanceof FlipCardInfo info) {
			
			logger.info("FlipCardInfo: " + info);
			
			gui.getGamePanel().flipCard(info.getX(), info.getY(), info.getNumber());
			
		} else if (object instanceof CreateAccountResponse response) {
			
			logger.info("CreateAccountResponse: " + response);
			
			if (response.wasSuccessful()) {
				logger.info("Create account successful.");
				gui.switchToPanel(MemoryCardGameClientGUI.PRE_WAITING_PANEL);
			} else {
				logger.info("Create account failed, resson: " + response.getFailureMessage());
				gui.getCreateAccountPanel().setErrorMessage(response.getFailureMessage());
			}
			
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
