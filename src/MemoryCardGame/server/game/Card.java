package MemoryCardGame.server.game;

public class Card {
	
	private final int     x;
	private final int     y;
	private final int     number;
	private       boolean isRevealed = false;
	
	public Card(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}
	
	@Override
	public boolean equals(Object object) {
		return object instanceof Card && this.x == ((Card)object).x && this.y == ((Card)object).y;
	}
	
	public boolean matches(Card card) {
		return number == card.number;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setRevealed(boolean revealed) {
		isRevealed = revealed;
	}
	
	public boolean isRevealed() {
		return isRevealed;
	}
	
	public String toString() {
		return String.format("Card[x=%s,y=%s,number=%s,isRevealed=%s]", x, y, number, isRevealed);
	}
	
}
