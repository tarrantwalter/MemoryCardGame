package MemoryCardGame;

import MemoryCardGame.controls.CreateAccountControl;
import MemoryCardGame.controls.InitialControl;
import MemoryCardGame.controls.LoginControl;
import MemoryCardGame.panels.CreateAccountPanel;
import MemoryCardGame.panels.InitialPanel;
import MemoryCardGame.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class MemoryCardGame extends JFrame {
	
	public static void main(String[] args) {
		MemoryCardGame game = new MemoryCardGame();
		game.setVisible(true);
	}
	
	private final CardLayout layout    = new CardLayout();
	private final JPanel     container = new JPanel(layout);
	
	public MemoryCardGame() {
		setTitle("Memory Card Game");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		InitialControl       initialControl       = new InitialControl(this);
		LoginControl         loginControl         = new LoginControl(this);
		CreateAccountControl createAccountControl = new CreateAccountControl(this);
		
		JPanel initialPanel       = new InitialPanel(initialControl);
		JPanel loginPanel         = new LoginPanel(loginControl);
		JPanel createAccountPanel = new CreateAccountPanel(createAccountControl);
		
		// Add the views to the card layout container.
		container.add(initialPanel, "1");
		container.add(loginPanel, "2");
		container.add(createAccountPanel, "3");
		
		// Show the initial view in the card layout.
		layout.show(container, "1");
		
		// Add the card layout container to the JFrame.
		add(container, BorderLayout.CENTER);
		
	}
	
	public void switchToPanel(int panel) {
		layout.show(container, String.valueOf(panel));
	}
	
}
