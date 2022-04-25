package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.LoginControl;
import MemoryCardGame.client.request.LoginRequest;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
	
	private final JLabel errorLabel;
	private final JTextField     userField;
	private final JPasswordField passField;
	
	public LoginPanel(LoginControl control) {
		super(new GridBagLayout());
		
		JLabel label      = new JLabel("Login", JLabel.CENTER);
		errorLabel = new JLabel("", JLabel.CENTER);
		
		errorLabel.setForeground(Color.RED);
		control.setErrorFunction(errorLabel::setText);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		
		JLabel userLabel = new JLabel("Username: ");
		JLabel passLabel = new JLabel("Password: ");
		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		JButton loginButton  = new JButton("Login");
		JButton cancelButton = new JButton("Cancel");
		
		loginButton.addActionListener(control);
		cancelButton.addActionListener(control);
		
		inputPanel.add(userLabel);
		inputPanel.add(userField);
		inputPanel.add(passLabel);
		inputPanel.add(passField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(5, 1, 5, 5));
		grid.add(label);
		grid.add(errorLabel);
		grid.add(inputPanel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
		
	}
	
	public LoginRequest constructLoginRequest() {
		return new LoginRequest(userField.getText(), new String(passField.getPassword()));
	}
	
	public void setErrorMessage(String error) {
		errorLabel.setText(error);
	}
	
	public void clearErrorMessage() {
		errorLabel.setText("");
	}
	
	public void reset() {
		clearErrorMessage();
	}
	
}
