package MemoryCardGame.client.control;

import MemoryCardGame.client.MemoryCardGameClientGUI;
import MemoryCardGame.client.request.CreateAccountRequest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class CreateAccountControl implements ActionListener {
	
	private final MemoryCardGameClientGUI gui;
	private       Consumer<String>        errorFunction;
	
	public CreateAccountControl(MemoryCardGameClientGUI gui) {
		this.gui = gui;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Create")) {
			CreateAccountRequest request = gui.getCreateAccountPanel().constructCreateAccountRequest();
			if (request == null) {
				setError("Passwords do not match!");
			} else {
				gui.getClient().send(request);
			}
			//gui.switchToPanel(MemoryCardGameClientGUI.PRE_WAITING_PANEL);
		} else if (buttonName.equals("Cancel")) {
			gui.switchToPanel(MemoryCardGameClientGUI.INITIAL_PANEL);
		}
	}
	
	public void setError(String message) {
		if (errorFunction != null) {
			errorFunction.accept(message);
		}
	}
	
}
