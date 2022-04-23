package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.InitialControl;

import javax.swing.*;
import java.awt.*;

public class InitialPanel extends JPanel {
	
	public InitialPanel(InitialControl control) {
		super(new GridBagLayout());
		JLabel label = new JLabel("Welcome!", JLabel.CENTER);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(control);
		JPanel loginButtonBuffer = new JPanel();
		loginButtonBuffer.add(loginButton);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(control);
		JPanel createButtonBuffer = new JPanel();
		createButtonBuffer.add(createButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(control);
		JPanel exitButtonBuffer = new JPanel();
		exitButtonBuffer.add(exitButton);
		
		JPanel grid = new JPanel(new GridLayout(5, 1, 5, 5));
		grid.add(label);
		grid.add(loginButtonBuffer);
		grid.add(createButtonBuffer);
		grid.add(exitButtonBuffer);
		
		add(grid, new GridBagConstraints());
	}
	
}
