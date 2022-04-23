package MemoryCardGame.client.panels;

import MemoryCardGame.client.controls.CreateAccountControl;

import javax.swing.*;
import java.awt.*;

public class CreateAccountPanel extends JPanel {
	
	public CreateAccountPanel(CreateAccountControl control) {
		super(new GridBagLayout());
		
		JLabel label      = new JLabel("Create your User", JLabel.CENTER);
		JLabel errorLabel = new JLabel("", JLabel.CENTER);
		
		errorLabel.setForeground(Color.RED);
		control.setErrorFunction(errorLabel::setText);
		
		JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		
		JLabel         userLabel       = new JLabel("Username: ");
		JLabel         passLabel       = new JLabel("Password: ");
		JLabel         verifyPassLabel = new JLabel("Verify Password: ");
		JTextField     userField       = new JTextField(10);
		JPasswordField passField       = new JPasswordField(10);
		JPasswordField verifyPassField = new JPasswordField(10);
		
		JButton createButton = new JButton("Create");
		JButton cancelButton = new JButton("Cancel");
		
		createButton.addActionListener(control);
		cancelButton.addActionListener(control);
		
		inputPanel.add(userLabel);
		inputPanel.add(userField);
		inputPanel.add(passLabel);
		inputPanel.add(passField);
		inputPanel.add(verifyPassLabel);
		inputPanel.add(verifyPassField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(createButton);
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(5, 1, 5, 5));
		grid.add(label);
		grid.add(errorLabel);
		grid.add(inputPanel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
		
	}
	
}
