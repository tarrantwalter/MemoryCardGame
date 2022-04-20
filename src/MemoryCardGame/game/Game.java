package MemoryCardGame.game;

import java.util.Random;

public class Game {
	
	private static final int NUMBER_OF_COLUMNS = 6;
	
	private Random random;
	private Card[][] cards;
	private int numberOfRows;
	private int numberOfCards;
	private int numberOfPairs;
	
	private Player player1;
	private int player1Score;
	private Player player2;
	private int player2Score;
	private boolean isPlayer1Turn;
	
	public Game(int numberOfRows) {
		random = new Random();
		this.numberOfRows = numberOfRows;
		this.cards = new Card[NUMBER_OF_COLUMNS][numberOfRows];
		this.numberOfCards = numberOfRows * NUMBER_OF_COLUMNS;
		this.numberOfPairs = numberOfCards / 2;
		this.isPlayer1Turn = random.nextBoolean();
	}
	
	
}
