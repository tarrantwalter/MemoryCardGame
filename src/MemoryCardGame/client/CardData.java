package MemoryCardGame.client;

import javax.swing.*;

public class CardData {
	
	private final JLabel card;
	private final ImageIcon back;
	private final int x;
	private final int y;
	
	private boolean isFlipped = false;
	
	public CardData(JLabel card, int x, int y, ImageIcon back) {
		this.card = card;
		this.x = x;
		this.y = y;
		this.back = back;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isFlipped() {
		return isFlipped;
	}
	
	public void flip(ImageIcon front) {
		isFlipped = !isFlipped;
		if (isFlipped) {
			card.setIcon(front);
		} else {
			card.setIcon(back);
		}
	}
}
