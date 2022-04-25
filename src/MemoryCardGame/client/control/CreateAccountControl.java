package MemoryCardGame.client.control;

import MemoryCardGame.client.MemoryCardGameClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class CreateAccountControl implements ActionListener {
	
	private final MemoryCardGameClientGUI client;
	private       Consumer<String>        errorFunction;
	
	public CreateAccountControl(MemoryCardGameClientGUI client) {
		this.client = client;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Create")) {
			// TODO: Implement login
			client.switchToPanel(MemoryCardGameClientGUI.PRE_WAITING_PANEL);
		} else if (buttonName.equals("Cancel")) {
			client.switchToPanel(MemoryCardGameClientGUI.INITIAL_PANEL);
		}
	}
	
}
