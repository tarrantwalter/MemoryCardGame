package MemoryCardGame.client;

import MemoryCardGame.client.controls.CreateAccountControl;
import MemoryCardGame.client.controls.InitialControl;
import MemoryCardGame.client.controls.LoginControl;
import MemoryCardGame.client.controls.WaitingControl;
import MemoryCardGame.client.panels.CreateAccountPanel;
import MemoryCardGame.client.panels.InitialPanel;
import MemoryCardGame.client.panels.LoginPanel;
import MemoryCardGame.client.panels.WaitingPanel;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
	
	public static final int INITIAL_PANEL        = 1;
	public static final int LOGIN_PANEL          = 2;
	public static final int CREATE_ACCOUNT_PANEL = 3;
	public static final int WAITING_PANEL        = 4;
	
	private final CardLayout layout    = new CardLayout();
	private final JPanel     container = new JPanel(layout);
	
	public ClientGUI() {
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
		
	}
	
	public void switchToPanel(int panel) {
		layout.show(container, String.valueOf(panel));
	}
	
}
