package MemoryCardGame.client.controls;

import MemoryCardGame.client.ClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class LoginControl implements ActionListener {
	
	private final ClientGUI        client;
	private       Consumer<String> errorFunction;
	
	public LoginControl(ClientGUI client) {
		this.client = client;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Login")) {
			// TODO: Implement login
			client.switchToPanel(ClientGUI.WAITING_PANEL);
		} else if (buttonName.equals("Cancel")) {
			client.switchToPanel(ClientGUI.INITIAL_PANEL);
		}
	}
	
}
