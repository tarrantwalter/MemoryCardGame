package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.GameOverControl;
import MemoryCardGame.server.info.EndGameInfo;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
	
	private final JLabel status;
	private final JLabel score;
	private final JLabel accuracy;
	
	public GameOverPanel(GameOverControl control) {
		super(new GridBagLayout());
		
		status = new JLabel("", JLabel.CENTER);
		
		JPanel textPanel = new JPanel(new FlowLayout());
		textPanel.add(new JLabel("Score:"));
		score = new JLabel("");
		textPanel.add(score);
		textPanel.add(new JLabel("Accuracy:"));
		accuracy = new JLabel("");
		textPanel.add(accuracy);
		
		JButton joinButton = new JButton("Join Another Game");
		JButton backButton = new JButton("Back to Main Menu");
		
		joinButton.addActionListener(control);
		backButton.addActionListener(control);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(joinButton);
		buttonPanel.add(backButton);
		
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(status);
		grid.add(textPanel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
	}
	
	public void setEndGameInfo(EndGameInfo info) {
		score.setText(String.valueOf(info.getScore()));
		accuracy.setText(info.getAccuracy() + "%");
		switch (info.getResult()) {
			case "win" -> status.setText("You won!");
			case "lose" -> status.setText("You lost.");
			case "tie" -> status.setText("It's a draw!");
		}
	}
	
	public void reset() {}
	
}
