package MemoryCardGame.server.info;

import java.io.Serializable;

public class FlipCardInfo implements Serializable {
	
	private final int x;
	private final int y;
	private final int number;
	
	public FlipCardInfo(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
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
	
	@Override
	public String toString() {
		return "FlipCardInfo[x=" + x + ",y=" + y + ",number=" + number + "]";
	}
	
}
