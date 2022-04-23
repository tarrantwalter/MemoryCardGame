package MemoryCardGame.client.control;

import MemoryCardGame.client.MemoryCardGameClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialControl implements ActionListener {
	
	private final MemoryCardGameClientGUI client;
	
	public InitialControl(MemoryCardGameClientGUI client) {
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Login")) {
			client.switchToPanel(MemoryCardGameClientGUI.LOGIN_PANEL);
		} else if (buttonName.equals("Create")) {
			client.switchToPanel(MemoryCardGameClientGUI.CREATE_ACCOUNT_PANEL);
		} else if (buttonName.equals("Exit")) {
			client.stop();
		}
	}
	
}
