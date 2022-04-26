package MemoryCardGame.server.info;

import java.io.Serializable;

public class TurnInfo implements Serializable {
	
	private final boolean isTurn;
	private final int myScore;
	private final int opponentScore;
	
	public TurnInfo(boolean isTurn, int myScore, int opponentScore) {
		this.isTurn = isTurn;
		this.myScore = myScore;
		this.opponentScore = opponentScore;
	}
	
	public boolean isTurn() {
		return isTurn;
	}
	
	public int getMyScore() {
		return myScore;
	}
	
	public int getOpponentScore() {
		return opponentScore;
	}
	
	@Override
	public String toString() {
		return "TurnInfo[isTurn=" + isTurn + ",myScore=" + myScore + ",opponentScore=" + opponentScore + "]";
	}
	
}
