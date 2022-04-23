package MemoryCardGame.server.control;

import MemoryCardGame.server.MemoryCardGameServerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialControl implements ActionListener {
	
	private final MemoryCardGameServerGUI server;
	
	public InitialControl(MemoryCardGameServerGUI server) {
		this.server = server;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Exit")) {
			server.stop();
		}
	}
	
}
