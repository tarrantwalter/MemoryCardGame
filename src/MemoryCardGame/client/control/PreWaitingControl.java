package MemoryCardGame.client.control;

import MemoryCardGame.client.MemoryCardGameClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class PreWaitingControl implements ActionListener {
	
	private final MemoryCardGameClientGUI gui;
	private       Consumer<String>        errorFunction;
	
	public PreWaitingControl(MemoryCardGameClientGUI gui) {
		this.gui = gui;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Join")) {
			gui.getClient().send("JoinGame");
		} else if (buttonName.equals("Cancel")) {
			gui.switchToPanel(MemoryCardGameClientGUI.INITIAL_PANEL);
		}
	}
	
}
