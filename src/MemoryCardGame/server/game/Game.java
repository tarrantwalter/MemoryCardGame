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
	private int        player1Score  = 0;
	private Player     player2;
	private int        player2Score  = 0;
	private boolean    isPlayer1Turn;
	private List<Card> selectedCards = new ArrayList<>(2);
	private int        matches       = 0;
	
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
		player1.send(new TurnInfo(isPlayer1Turn, player1Score, player2Score));
		player2.send(new TurnInfo(!isPlayer1Turn, player1Score, player2Score));
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
		if (selectedCards.get(0).getNumber() == selectedCards.get(1).getNumber()) {
			if (isPlayer1Turn) {
				player1Score++;
				matches++;
				if (player1Score > numberOfPairs / 2) {
					endGame(player1, player2);
					return;
				}
			} else {
				player2Score++;
				matches++;
				if (player2Score > numberOfPairs / 2) {
					endGame(player2, player1);
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
		gameProvider.removeGame(this);
		winner.send(new EndGameInfo("win"));
		loser.send(new EndGameInfo("lose"));
		// TODO score
		
	}
	
	public void endGameDraw() {
		gameProvider.removeGame(this);
		player1.send(new EndGameInfo("tie"));
		player2.send(new EndGameInfo("tie"));
		// TODO score
	}
	
}
