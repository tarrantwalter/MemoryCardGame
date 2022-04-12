package MemoryCardGame;

public class Card {
	
	private int x;
	private int y;
	private boolean isFaceUp;
	
	private Card pair;
	
	public Card(int x, int y) {
		this.x = x;
		this.y = y;
		this.isFaceUp = false;
		this.pair = null;
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
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return "Card[x=" + x + ", y=" + y + ", isFaceUp=" + isFaceUp + "]";
	}
	
}
