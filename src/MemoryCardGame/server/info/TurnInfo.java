package MemoryCardGame.server.info;

import java.io.Serializable;

public class TurnInfo implements Serializable {
	
	private final boolean isTurn;
	private final int player1Score;
	private final int player2Score;
	
	public TurnInfo(boolean isTurn, int player1Score, int player2Score) {
		this.isTurn = isTurn;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
	}
	
	public boolean isTurn() {
		return isTurn;
	}
	
	public int getPlayer1Score() {
		return player1Score;
	}
	
	public int getPlayer2Score() {
		return player2Score;
	}
	
	@Override
	public String toString() {
		return "TurnInfo[isTurn=" + isTurn + ",player1Score=" + player1Score + ",player2Score=" + player2Score + "]";
	}
	
}
