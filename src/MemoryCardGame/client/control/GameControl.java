package MemoryCardGame.client.control;

import MemoryCardGame.client.CardData;
import MemoryCardGame.client.MemoryCardGameClientGUI;
import MemoryCardGame.client.request.CardSelectRequest;

import javax.swing.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class GameControl implements ActionListener, MouseListener, MouseMotionListener {
	
	private final MemoryCardGameClientGUI gui;
	private       Consumer<String>        errorFunction;
	private       boolean                 turn = false;
	
	public GameControl(MemoryCardGameClientGUI gui) {
		this.gui = gui;
	}
	
	public void setErrorFunction(Consumer<String> errorFunction) {
		this.errorFunction = errorFunction;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		if (buttonName.equals("Cancel")) {
			gui.getClient().send("LeaveGame");
		}
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (turn) {
			if (e.getComponent() instanceof JLabel label) {
				CardData cardData = gui.getGamePanel().getCardData(label);
				int      x        = cardData.getX();
				int      y        = cardData.getY();
				if (!gui.getGamePanel().isCardFlipped(x, y)) {
					gui.getClient().send(new CardSelectRequest(x, y));
				}
			}
		}
	}
	
	private long lastClick = 0L;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (System.currentTimeMillis() - lastClick > 500L) {
			lastClick = System.currentTimeMillis();
			mouseClicked(e);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	
	}
	
}
