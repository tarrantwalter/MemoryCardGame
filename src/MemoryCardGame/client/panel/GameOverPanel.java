package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.GameOverControl;
import MemoryCardGame.server.info.EndGameInfo;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
	
	private final JLabel label;
	
	public GameOverPanel(GameOverControl control) {
		super(new GridBagLayout());
		
		label = new JLabel("", JLabel.CENTER);
		
		JButton joinButton = new JButton("Join Another Game");
		JButton backButton = new JButton("Back to Main Menu");
		
		joinButton.addActionListener(control);
		backButton.addActionListener(control);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(joinButton);
		buttonPanel.add(backButton);
		
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
	}
	
	public void setEndGameInfo(EndGameInfo info) {
		label.setText(info.won() ? "You won!" : "You lost!");
	}
	
	public void reset() {}
	
}
