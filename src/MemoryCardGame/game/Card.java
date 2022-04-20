package MemoryCardGame.game;

import javax.swing.*;

public class Card {
	
	private final int     x;
	private final int     y;
	private final int number;
	private final JLabel  face;
	private final JLabel  back;
	
	private boolean isFaceUp = false;
	private Card pair = null;
	
	public Card(int x, int y, int number, JLabel face, JLabel back) {
		this.x = x;
		this.y = y;
		this.number = number;
		this.face = face;
		this.back = back;
	}
	
	public Card getPair() {
		return this.pair;
	}
	
	public void setPair(Card pair) {
		this.pair = pair;
	}
	
	public boolean hasPair() {
		return this.pair != null;
	}
	
	public boolean equals(Card other) {
		return this.x == other.x && this.y == other.y;
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
		return "Card[x=" + x + ",y=" + y + ",isFaceUp=" + isFaceUp + "]";
	}
	
}
