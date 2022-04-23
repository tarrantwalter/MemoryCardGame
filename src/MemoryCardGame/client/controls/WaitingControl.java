package MemoryCardGame.client.controls;

import MemoryCardGame.client.ClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class WaitingControl implements ActionListener {
	
	private final ClientGUI        client;
	private       Consumer<String> errorFunction;
	
	public WaitingControl(ClientGUI client) {
		this.client = client;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Cancel")) {
			client.switchToPanel(ClientGUI.INITIAL_PANEL);
		}
	}
	
}
