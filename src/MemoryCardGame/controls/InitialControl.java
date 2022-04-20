package MemoryCardGame.controls;

import MemoryCardGame.MemoryCardGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialControl implements ActionListener {
	
	private final MemoryCardGame game;
	
	public InitialControl(MemoryCardGame game) {
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Login")) {
			game.switchToPanel(2);
		} else if (buttonName.equals("Create")) {
			game.switchToPanel(3);
		}
	}
	
}
