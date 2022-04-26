package MemoryCardGame.server.game;

import MemoryCardGame.server.info.EndGameInfo;
import MemoryCardGame.server.info.FlipCardInfo;
import MemoryCardGame.server.info.StartGameInfo;
import MemoryCardGame.server.info.TurnInfo;

import java.util.ArrayList;
import java.util.Collections;
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
		
		List<Integer> cardNumbers = new ArrayList<>(36);
		for (int i = 0; i < numberOfPairs; i++) {
			cardNumbers.add(i + 1);
			cardNumbers.add(i + 1);
		}
		Collections.shuffle(cardNumbers);
		
		for (int y = 0; y < numberOfRows; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				cards[x][y] = new Card(x, y, cardNumbers.remove(0));
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
		winner.send(new EndGameInfo("win", player1Won ? player1Score : player2Score,
				player1Won ? player1Matches * 100 / Math.max(player1Guesses, 1) : player2Matches * 100 / Math.max(player2Guesses, 1)));
		loser.send(new EndGameInfo("lose", player1Won ? player2Score : player1Score,
				player1Won ? player2Matches * 100 / Math.max(player2Guesses, 1) : player1Matches * 100 / Math.max(player1Guesses, 1)));
		
		player1.setGamesPlayed(player1.getGamesPlayed() + 1);
		player1.setGuesses(player1.getGuesses() + player1Guesses);
		if (player1Score > player1.getHighestScore()) {
			player1.setHighestScore(player1Score);
		}
		player1.setMatches(player1.getMatches() + player1Matches);
		player1.setTotalScore(player1.getTotalScore() + player1Score);
		if (player1Won) {
			player1.setWins(player1.getWins() + 1);
		}
		
		player2.setGamesPlayed(player2.getGamesPlayed() + 1);
		player2.setGuesses(player2.getGuesses() + player2Guesses);
		if (player2Score > player2.getHighestScore()) {
			player2.setHighestScore(player2Score);
		}
		player2.setMatches(player2.getMatches() + player2Matches);
		player2.setTotalScore(player2.getTotalScore() + player2Score);
		if (!player1Won) {
			player2.setWins(player2.getWins() + 1);
		}
		
		gameProvider.getServer().getDatabase()
				.updateUser(player1.getUsername(), player1.getTotalScore(), player1.getHighestScore(), player1.getWins(), player1.getGamesPlayed(),
						player1.getMatches(), player1.getGuesses());
		gameProvider.getServer().getDatabase()
				.updateUser(player2.getUsername(), player2.getTotalScore(), player2.getHighestScore(), player2.getWins(), player2.getGamesPlayed(),
						player2.getMatches(), player2.getGuesses());
		
		gameProvider.removeGame(this);
	}
	
	public void endGameDraw() {
		player1.send(new EndGameInfo("tie", player1Score, player1Matches * 100 / player1Guesses));
		player2.send(new EndGameInfo("tie", player2Score, player2Matches * 100 / player2Guesses));
		
		player1.setGamesPlayed(player1.getGamesPlayed() + 1);
		player1.setGuesses(player1.getGuesses() + player1Guesses);
		if (player1Score > player1.getHighestScore()) {
			player1.setHighestScore(player1Score);
		}
		player1.setMatches(player1.getMatches() + player1Matches);
		player1.setTotalScore(player1.getTotalScore() + player1Score);
		
		player2.setGamesPlayed(player2.getGamesPlayed() + 1);
		player2.setGuesses(player2.getGuesses() + player2Guesses);
		if (player2Score > player2.getHighestScore()) {
			player2.setHighestScore(player2Score);
		}
		player2.setMatches(player2.getMatches() + player2Matches);
		player2.setTotalScore(player2.getTotalScore() + player2Score);
		
		gameProvider.getServer().getDatabase()
				.updateUser(player1.getUsername(), player1.getTotalScore(), player1.getHighestScore(), player1.getWins(), player1.getGamesPlayed(),
						player1.getMatches(), player1.getGuesses());
		gameProvider.getServer().getDatabase()
				.updateUser(player2.getUsername(), player2.getTotalScore(), player2.getHighestScore(), player2.getWins(), player2.getGamesPlayed(),
						player2.getMatches(), player2.getGuesses());
		
		gameProvider.removeGame(this);
	}
	
}
