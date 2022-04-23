package MemoryCardGame.client.controls;

import MemoryCardGame.client.ClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialControl implements ActionListener {
	
	private final ClientGUI client;
	
	public InitialControl(ClientGUI client) {
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Login")) {
			client.switchToPanel(ClientGUI.LOGIN_PANEL);
		} else if (buttonName.equals("Create")) {
			client.switchToPanel(ClientGUI.CREATE_ACCOUNT_PANEL);
		} else if (buttonName.equals("Exit")) {
			System.exit(0);
		}
	}
	
}
