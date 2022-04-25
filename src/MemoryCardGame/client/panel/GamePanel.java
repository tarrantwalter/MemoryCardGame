package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.GameControl;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
	
	public GamePanel(GameControl control) {
		super(new GridBagLayout());
		
		JLabel label      = new JLabel("GAME", JLabel.CENTER);
		//JLabel errorLabel = new JLabel("", JLabel.CENTER);
		
		//errorLabel.setForeground(Color.RED);
		//control.setErrorFunction(errorLabel::setText);
		
		JButton cancelButton = new JButton("Cancel");
		
		cancelButton.addActionListener(control);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		//grid.add(errorLabel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
	}
	
	public void reset() {}
	
}
