package MemoryCardGame.server.game;

import MemoryCardGame.server.info.EndGameInfo;
import MemoryCardGame.server.info.FlipCardInfo;
import MemoryCardGame.server.info.StartGameInfo;
import MemoryCardGame.server.info.TurnInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
	
	// must be an even number
	public static final int COLUMNS = 6;
	
	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	private final GameProvider gameProvider;
	private final Random       random;
	private final Card[][]     cards;
	private final int          numberOfRows;
	private final int          numberOfCards;
	private final int          numberOfPairs;
	
	private Player     player1;
	private int        player1ScoreIncrement = 10;
	private int        player1Matches        = 0;
	private int        player1Guesses        = 0;
	private int        player1Score          = 0;
	private Player     player2;
	private int        player2ScoreIncrement = 10;
	private int        player2Matches        = 0;
	private int        player2Guesses        = 0;
	private int        player2Score          = 0;
	private boolean    isPlayer1Turn;
	private List<Card> selectedCards         = new ArrayList<>(2);
	private int        matches               = 0;
	
	public Game(GameProvider gameProvider, int numberOfRows, Player player1, Player player2) {
		this.gameProvider = gameProvider;
		this.random = new Random();
		this.numberOfRows = numberOfRows;
		this.player1 = player1;
		this.player2 = player2;
		this.cards = new Card[COLUMNS][numberOfRows];
		this.numberOfCards = numberOfRows * COLUMNS;
		this.numberOfPairs = numberOfCards / 2;
		this.isPlayer1Turn = random.nextBoolean();
		StartGameInfo startGameInfo = new StartGameInfo(numberOfRows);
		player1.send(startGameInfo);
		player2.send(startGameInfo);
		double i = 1;
		for (int y = 0; y < numberOfRows; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				cards[x][y] = new Card(x, y, (int)i);
				i += 0.5;
			}
		}
		startTurn();
	}
	
	public void startTurn() {
		player1.send(new TurnInfo(isPlayer1Turn, player1Matches, player2Matches));
		player2.send(new TurnInfo(!isPlayer1Turn, player2Matches, player1Matches));
	}
	
	public void playerSelectedCard(Player player, int x, int y) {
		if (selectedCards.size() >= 2 || cards[x][y].isRevealed()) {
			return;
		}
		if ((player == player1 && isPlayer1Turn) || (player == player2 && !isPlayer1Turn)) {
			revealCard(x, y);
			if (selectedCards.size() == 1) {
				selectedCards.add(cards[x][y]);
				endTurn();
			} else {
				selectedCards.add(cards[x][y]);
			}
		}
	}
	
	public void revealCard(int x, int y) {
		Card card = cards[x][y];
		card.setRevealed(true);
		FlipCardInfo info = new FlipCardInfo(x, y, card.getNumber());
		player1.send(info);
		player2.send(info);
	}
	
	public void endTurn() {
		if (isPlayer1Turn) {
			player1Guesses++;
		} else {
			player2Guesses++;
		}
		if (selectedCards.get(0).getNumber() == selectedCards.get(1).getNumber()) {
			if (isPlayer1Turn) {
				player1Score += player1ScoreIncrement;
				player1Matches++;
				matches++;
				if (player1Matches > numberOfPairs / 2) {
					executor.schedule(() -> endGame(player1, player2), 2, TimeUnit.SECONDS);
					return;
				}
			} else {
				player2Score += player2ScoreIncrement;
				player2Matches++;
				matches++;
				if (player2Matches > numberOfPairs / 2) {
					executor.schedule(() -> endGame(player2, player1), 2, TimeUnit.SECONDS);
					return;
				}
			}
			selectedCards.clear();
			if (matches >= numberOfPairs) {
				endGameDraw();
				return;
			}
			//selectedCards.clear();
			//isPlayer1Turn = !isPlayer1Turn;
			startTurn();
		} else {
			executor.schedule(() -> {
				selectedCards.forEach(card -> {
					card.setRevealed(false);
					FlipCardInfo info = new FlipCardInfo(card.getX(), card.getY(), card.getNumber());
					player1.send(info);
					player2.send(info);
				});
				if (isPlayer1Turn) {
					player1ScoreIncrement--;
					player1ScoreIncrement = Math.max(player1ScoreIncrement, 1);
				} else {
					player2ScoreIncrement--;
					player2ScoreIncrement = Math.max(player2ScoreIncrement, 1);
				}
				isPlayer1Turn = !isPlayer1Turn;
				selectedCards.clear();
				if (matches >= numberOfPairs) {
					endGameDraw();
					return;
				}
				//selectedCards.clear();
				//isPlayer1Turn = !isPlayer1Turn;
				startTurn();
			}, 2, TimeUnit.SECONDS);
		}
	}
	
	public boolean containsPlayer(Player player) {
		return player1 == player || player2 == player;
	}
	
	public void playerLeft(Player loser) {
		Player winner = loser == player1 ? player2 : player1;
		endGame(winner, loser);
	}
	
	public void endGame(Player winner, Player loser) {
		boolean player1Won = winner == player1;
		//System.out.println("1");
		winner.send(new EndGameInfo("win", player1Won ? player1Score : player2Score,
				player1Won ? player1Matches * 100 / Math.max(player1Guesses, 1) : player2Matches * 100 / Math.max(player2Guesses, 1)));
		//System.out.println("2");
		loser.send(new EndGameInfo("lose", player1Won ? player2Score : player1Score,
				player1Won ? player2Matches * 100 / Math.max(player2Guesses, 1) : player1Matches * 100 / Math.max(player1Guesses, 1)));
		//System.out.println("3");
		gameProvider.removeGame(this);
	}
	
	public void endGameDraw() {
		gameProvider.removeGame(this);
		player1.send(new EndGameInfo("tie", player1Score, player1Matches * 100 / player1Guesses));
		player2.send(new EndGameInfo("tie", player2Score, player2Matches * 100 / player2Guesses));
	}
	
}
