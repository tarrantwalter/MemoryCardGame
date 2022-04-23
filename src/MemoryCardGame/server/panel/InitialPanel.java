package MemoryCardGame.server.panel;

import MemoryCardGame.server.control.InitialControl;

import javax.swing.*;
import java.awt.*;

public class InitialPanel extends JPanel {
	
	public InitialPanel(InitialControl control) {
		super(new GridBagLayout());
		JLabel label = new JLabel("Welcome!", JLabel.CENTER);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(control);
		JPanel exitButtonBuffer = new JPanel();
		exitButtonBuffer.add(exitButton);
		
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(exitButtonBuffer);
		
		add(grid, new GridBagConstraints());
	}
	
}
