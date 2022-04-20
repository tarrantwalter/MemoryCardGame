package MemoryCardGame.controls;

import MemoryCardGame.MemoryCardGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class CreateAccountControl implements ActionListener {
	
	private final MemoryCardGame   game;
	private       Consumer<String> errorFunction;
	
	public CreateAccountControl(MemoryCardGame game) {
		this.game = game;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Create")) {
			// TODO: Implement login
			errorFunction.accept("Create Pressed");
		} else if (buttonName.equals("Cancel")) {
			game.switchToPanel(1);
		}
	}
	
}
