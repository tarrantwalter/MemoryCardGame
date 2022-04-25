package MemoryCardGame.server;

import MemoryCardGame.server.control.InitialControl;
import MemoryCardGame.server.panel.InitialPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MemoryCardGameServerGUI extends JFrame {
	
	public static final int INITIAL_PANEL        = 1;
	
	private final CardLayout layout    = new CardLayout();
	private final JPanel     container = new JPanel(layout);
	
	private final MemoryCardGameServer server;
	
	public MemoryCardGameServerGUI() {
		setTitle("MCG Server");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		InitialControl initialControl = new InitialControl(this);
		
		JPanel initialPanel       = new InitialPanel(initialControl);
		
		// Add the views to the card layout container.
		container.add(initialPanel, "1");
		
		// Show the initial view in the card layout.
		layout.show(container, "1");
		
		// Add the card layout container to the JFrame.
		add(container, BorderLayout.CENTER);
		
		server = new MemoryCardGameServer();
		try {
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void switchToPanel(int panel) {
		layout.show(container, String.valueOf(panel));
	}
	
	public void stop() {
		server.stopListening();
		System.exit(0);
	}
	
}
