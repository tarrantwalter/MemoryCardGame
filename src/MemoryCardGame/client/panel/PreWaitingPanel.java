package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.PreWaitingControl;

import javax.swing.*;
import java.awt.*;

public class PreWaitingPanel extends JPanel {
	
	public PreWaitingPanel(PreWaitingControl control) {
		super(new GridBagLayout());
		
		JLabel label      = new JLabel("Click 'Join' to join a game!", JLabel.CENTER);
		JLabel errorLabel = new JLabel("", JLabel.CENTER);
		
		errorLabel.setForeground(Color.RED);
		control.setErrorFunction(errorLabel::setText);
		
		JButton joinButton   = new JButton("Join");
		JButton cancelButton = new JButton("Cancel");
		
		joinButton.addActionListener(control);
		cancelButton.addActionListener(control);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(joinButton);
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
		grid.add(label);
		grid.add(errorLabel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
	}
	
	public void reset() {}
	
}
