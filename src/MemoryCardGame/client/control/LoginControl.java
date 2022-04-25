package MemoryCardGame.client.control;

import MemoryCardGame.client.MemoryCardGameClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class LoginControl implements ActionListener {
	
	private final MemoryCardGameClientGUI client;
	private       Consumer<String>        errorFunction;
	
	public LoginControl(MemoryCardGameClientGUI client) {
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
			
			client.getClient().send(client.getLoginPanel().constructLoginRequest());
		} else if (buttonName.equals("Cancel")) {
			client.switchToPanel(MemoryCardGameClientGUI.INITIAL_PANEL);
		}
	}
	
}
