package MemoryCardGame.server.game;

import javax.swing.*;

public class Card {
	
	private final int     x;
	private final int     y;
	private final int number;
	private final JLabel  face;
	private final JLabel  back;
	private boolean isFaceUp = false;
	
	public Card(int x, int y, int number, JLabel face, JLabel back) {
		this.x = x;
		this.y = y;
		this.number = number;
		this.face = face;
		this.back = back;
	}
	
	@Override
	public boolean equals(Object object) {
		return object instanceof Card && this.x == ((Card)object).x && this.y == ((Card)object).y;
	}
	
	public boolean match(Card card) {
		return number == card.number;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isFaceUp() {
		return isFaceUp;
	}
	
	public void flip() {
		isFaceUp = !isFaceUp;
		face.setVisible(isFaceUp);
		back.setVisible(!isFaceUp);
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return String.format("Card[x=%s,y=%s,number=%s,isFaceUp=%s]", x, y, number, isFaceUp);
	}
	
}
