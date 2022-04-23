package MemoryCardGame.client;

import MemoryCardGame.client.control.CreateAccountControl;
import MemoryCardGame.client.control.InitialControl;
import MemoryCardGame.client.control.LoginControl;
import MemoryCardGame.client.control.WaitingControl;
import MemoryCardGame.client.panel.CreateAccountPanel;
import MemoryCardGame.client.panel.InitialPanel;
import MemoryCardGame.client.panel.LoginPanel;
import MemoryCardGame.client.panel.WaitingPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MemoryCardGameClientGUI extends JFrame {
	
	public static final int INITIAL_PANEL        = 1;
	public static final int LOGIN_PANEL          = 2;
	public static final int CREATE_ACCOUNT_PANEL = 3;
	public static final int WAITING_PANEL        = 4;
	
	private final CardLayout layout    = new CardLayout();
	private final JPanel     container = new JPanel(layout);
	
	private final MemoryCardGameClient client;
	
	public MemoryCardGameClientGUI() {
		setTitle("Memory Card Game");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		InitialControl       initialControl       = new InitialControl(this);
		LoginControl         loginControl         = new LoginControl(this);
		CreateAccountControl createAccountControl = new CreateAccountControl(this);
		WaitingControl       waitingControl       = new WaitingControl(this);
		
		JPanel initialPanel       = new InitialPanel(initialControl);
		JPanel loginPanel         = new LoginPanel(loginControl);
		JPanel createAccountPanel = new CreateAccountPanel(createAccountControl);
		JPanel waitingPanel       = new WaitingPanel(waitingControl);
		
		// Add the views to the card layout container.
		container.add(initialPanel, "1");
		container.add(loginPanel, "2");
		container.add(createAccountPanel, "3");
		container.add(waitingPanel, "4");
		
		// Show the initial view in the card layout.
		layout.show(container, "1");
		
		// Add the card layout container to the JFrame.
		add(container, BorderLayout.CENTER);
		
		client = new MemoryCardGameClient();
		try {
			client.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void switchToPanel(int panel) {
		layout.show(container, String.valueOf(panel));
	}
	
	public void stop() {
		try {
			client.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
}
