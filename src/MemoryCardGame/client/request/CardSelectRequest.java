package MemoryCardGame.client.request;

import java.io.Serializable;

public class CardSelectRequest implements Serializable {
	
	private final int x;
	private final int y;
	
	public CardSelectRequest(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "CardSelectRequest[x=" + x + ",y=" + y + "]";
	}

}
