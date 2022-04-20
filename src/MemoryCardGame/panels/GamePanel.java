package MemoryCardGame.panels;

import MemoryCardGame.controls.LoginControl;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
	
	public GamePanel(LoginControl control) {
		super(new GridBagLayout());
		
		JLabel label = new JLabel("Login", JLabel.CENTER);
		JLabel errorLabel = new JLabel("", JLabel.CENTER);
		
		errorLabel.setForeground(Color.RED);
		control.setErrorFunction(errorLabel::setText);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		
		JLabel         userLabel       = new JLabel("Username: ");
		JLabel         passLabel       = new JLabel("Password: ");
		JTextField     userField       = new JTextField(10);
		JPasswordField passField       = new JPasswordField(10);
		
		JButton        loginButton     = new JButton("Login");
		JButton        cancelButton     = new JButton("Cancel");
		
		loginButton.addActionListener(control);
		cancelButton.addActionListener(control);
		
		inputPanel.add(userLabel);
		inputPanel.add(userField);
		inputPanel.add(passLabel);
		inputPanel.add(passField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
		grid.add(label);
		grid.add(errorLabel);
		grid.add(inputPanel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
		
	}
	
}
