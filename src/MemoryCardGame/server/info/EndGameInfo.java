package MemoryCardGame.server.info;

import java.io.Serializable;

public class EndGameInfo implements Serializable {
	
	private final String result;
	private final int score;
	private final int accuracy;
	
	public EndGameInfo(String result, int score, int accuracy) {
		this.result = result;
		this.score = score;
		this.accuracy = accuracy;
	}
	
	public String getResult() {
		return result;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	@Override
	public String toString() {
		return "EndGameInfo[result=" + result + ",score=" + score + ",accuracy=" + accuracy + "]";
	}
	
}
