package MemoryCardGame.client.panels;

import MemoryCardGame.client.controls.WaitingControl;

import javax.swing.*;
import java.awt.*;

public class WaitingPanel extends JPanel {
	
	public WaitingPanel(WaitingControl control) {
		super(new GridBagLayout());
		
		JLabel label      = new JLabel("Waiting for another user...", JLabel.CENTER);
		JLabel errorLabel = new JLabel("", JLabel.CENTER);
		
		errorLabel.setForeground(Color.RED);
		control.setErrorFunction(errorLabel::setText);
		
		JButton cancelButton = new JButton("Cancel");
		
		cancelButton.addActionListener(control);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
		grid.add(label);
		grid.add(errorLabel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
	}
	
}
