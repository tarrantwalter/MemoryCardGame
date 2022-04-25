package MemoryCardGame.server.game;

import MemoryCardGame.server.info.EndGameInfo;
import MemoryCardGame.server.info.StartGameInfo;

import java.util.Random;

public class Game {
	
	// must be an even number
	private static final int NUMBER_OF_COLUMNS = 6;
	
	private final GameProvider gameProvider;
	private final Random       random;
	private final Card[][]     cards;
	private final int          numberOfRows;
	private final int          numberOfCards;
	private final int          numberOfPairs;
	
	private Player  player1;
	private int     player1Score = 0;
	private Player  player2;
	private int     player2Score = 0;
	private boolean isPlayer1Turn;
	
	public Game(GameProvider gameProvider, int numberOfRows, Player player1, Player player2) {
		this.gameProvider = gameProvider;
		this.random = new Random();
		this.numberOfRows = numberOfRows;
		this.player1 = player1;
		this.player2 = player2;
		this.cards = new Card[NUMBER_OF_COLUMNS][numberOfRows];
		this.numberOfCards = numberOfRows * NUMBER_OF_COLUMNS;
		this.numberOfPairs = numberOfCards / 2;
		this.isPlayer1Turn = random.nextBoolean();
		player1.send(new StartGameInfo());
		player2.send(new StartGameInfo());
	}
	
	public boolean containsPlayer(Player player) {
		return player1 == player || player2 == player;
	}
	
	public void playerLeft(Player loser) {
		Player winner = loser == player1 ? player2 : player1;
		endGame(winner, loser);
	}
	
	public void endGame(Player winner, Player loser) {
		gameProvider.removeGame(this);
		winner.send(new EndGameInfo(true));
		loser.send(new EndGameInfo(false));
		// TODO score
		
	}
	
}
