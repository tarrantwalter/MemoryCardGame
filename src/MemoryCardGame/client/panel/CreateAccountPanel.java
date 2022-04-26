package MemoryCardGame.client.panel;

import MemoryCardGame.client.control.CreateAccountControl;
import MemoryCardGame.client.request.CreateAccountRequest;

import javax.swing.*;
import java.awt.*;

public class CreateAccountPanel extends JPanel {
	
	private final JLabel         errorLabel;
	private final JTextField     userField;
	private final JPasswordField passField;
	private final JPasswordField verifyPassField;
	
	public CreateAccountPanel(CreateAccountControl control) {
		super(new GridBagLayout());
		
		JLabel label = new JLabel("Create your User", JLabel.CENTER);
		errorLabel = new JLabel("", JLabel.CENTER);
		
		errorLabel.setForeground(Color.RED);
		control.setErrorFunction(errorLabel::setText);
		
		JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		
		JLabel userLabel       = new JLabel("Username: ");
		JLabel passLabel       = new JLabel("Password: ");
		JLabel verifyPassLabel = new JLabel("Verify Password: ");
		userField = new JTextField(24);
		passField = new JPasswordField(24);
		verifyPassField = new JPasswordField(24);
		
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
	
	public CreateAccountRequest constructCreateAccountRequest() {
		if (new String(passField.getPassword()).equals(new String(verifyPassField.getPassword()))) {
			return new CreateAccountRequest(userField.getText(), new String(passField.getPassword()));
		} else {
			return null;
		}
	}
	
	public void setErrorMessage(String errorMessage) {
		errorLabel.setText(errorMessage);
	}
	
	public void reset() {
		userField.setText("");
		passField.setText("");
		verifyPassField.setText("");
		errorLabel.setText("");
	}
	
}
