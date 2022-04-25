package MemoryCardGame.server.info;

import java.io.Serializable;

public class EndGameInfo implements Serializable {
	
	private final String result;
	
	public EndGameInfo(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	@Override
	public String toString() {
		return "EndGameInfo[result=" + result + "]";
	}
	
}
