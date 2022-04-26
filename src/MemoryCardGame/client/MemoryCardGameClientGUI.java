package MemoryCardGame.client;

import MemoryCardGame.client.control.*;
import MemoryCardGame.client.panel.*;

import javax.swing.*;
import java.awt.*;

public class MemoryCardGameClientGUI extends JFrame {
	
	public static final int INITIAL_PANEL        = 1;
	public static final int LOGIN_PANEL          = 2;
	public static final int CREATE_ACCOUNT_PANEL = 3;
	public static final int PRE_WAITING_PANEL    = 4;
	public static final int POST_WAITING_PANEL   = 5;
	public static final int GAME_PANEL           = 6;
	public static final int GAME_OVER_PANEL      = 7;
	
	private final CardLayout layout    = new CardLayout();
	private final JPanel     container = new JPanel(layout);
	
	private final MemoryCardGameClient client;
	
	private final InitialPanel       initialPanel;
	private final LoginPanel         loginPanel;
	private final CreateAccountPanel createAccountPanel;
	private final PreWaitingPanel    preWaitingPanel;
	private final PostWaitingPanel   postWaitingPanel;
	private final GamePanel          gamePanel;
	private final GameOverPanel      gameOverPanel;
	
	public MemoryCardGameClientGUI() {
		setTitle("Memory Card Game");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		InitialControl       initialControl       = new InitialControl(this);
		LoginControl         loginControl         = new LoginControl(this);
		CreateAccountControl createAccountControl = new CreateAccountControl(this);
		PreWaitingControl    preWaitingControl    = new PreWaitingControl(this);
		PostWaitingControl   postWaitingControl   = new PostWaitingControl(this);
		GameControl          gameControl          = new GameControl(this);
		GameOverControl      gameOverControl      = new GameOverControl(this);
		
		initialPanel = new InitialPanel(initialControl);
		loginPanel = new LoginPanel(loginControl);
		createAccountPanel = new CreateAccountPanel(createAccountControl);
		preWaitingPanel = new PreWaitingPanel(preWaitingControl);
		postWaitingPanel = new PostWaitingPanel(postWaitingControl);
		gamePanel = new GamePanel(gameControl);
		gameOverPanel = new GameOverPanel(gameOverControl);
		
		// Add the views to the card layout container.
		container.add(initialPanel, "1");
		container.add(loginPanel, "2");
		container.add(createAccountPanel, "3");
		container.add(preWaitingPanel, "4");
		container.add(postWaitingPanel, "5");
		container.add(gamePanel, "6");
		container.add(gameOverPanel, "7");
		
		// Show the initial view in the card layout.
		layout.show(container, "1");
		
		// Add the card layout container to the JFrame.
		add(container, BorderLayout.CENTER);
		
		client = new MemoryCardGameClient(this);
		client.start();
		
	}
	
	public void switchToPanel(int panel) {
		switch (panel) {
			case INITIAL_PANEL:
				initialPanel.reset();
				break;
			case LOGIN_PANEL:
				loginPanel.reset();
				break;
			case CREATE_ACCOUNT_PANEL:
				createAccountPanel.reset();
				break;
			case PRE_WAITING_PANEL:
				preWaitingPanel.reset();
				break;
			case POST_WAITING_PANEL:
				postWaitingPanel.reset();
				break;
			case GAME_PANEL:
				//gamePanel.reset();
				break;
			case GAME_OVER_PANEL:
				gameOverPanel.reset();
				break;
			default:
				client.getLogger().warning("Invalid panel number: " + panel);
				break;
		}
		layout.show(container, String.valueOf(panel));
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public CreateAccountPanel getCreateAccountPanel() {
		return createAccountPanel;
	}
	
	public PreWaitingPanel getPreWaitingPanel() {
		return preWaitingPanel;
	}
	
	public PostWaitingPanel getPostWaitingPanel() {
		return postWaitingPanel;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public GameOverPanel getGameOverPanel() {
		return gameOverPanel;
	}
	
	public MemoryCardGameClient getClient() {
		return client;
	}
	
	public void stop() {
		client.stop();
		System.exit(0);
	}
	
}
