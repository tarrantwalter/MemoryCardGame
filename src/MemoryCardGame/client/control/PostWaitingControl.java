package MemoryCardGame.client.control;

import MemoryCardGame.client.MemoryCardGameClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class PostWaitingControl implements ActionListener {
	
	private final MemoryCardGameClientGUI gui;
	private       Consumer<String>        errorFunction;
	
	public PostWaitingControl(MemoryCardGameClientGUI gui) {
		this.gui = gui;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Cancel")) {
			gui.getClient().send("CancelJoin");
			gui.switchToPanel(MemoryCardGameClientGUI.PRE_WAITING_PANEL);
		}
	}
	
}
