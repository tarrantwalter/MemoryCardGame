package MemoryCardGame.server.info;

import java.io.Serializable;

public class EndGameInfo implements Serializable {
	
	private final boolean won;
	
	public EndGameInfo(boolean won) {
		this.won = won;
	}
	
	public boolean won() {
		return won;
	}
	
	@Override
	public String toString() {
		return "EndGameInfo[won=" + won + "]";
	}
	
}
