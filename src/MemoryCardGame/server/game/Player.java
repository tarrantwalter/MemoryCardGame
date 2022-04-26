package MemoryCardGame.server.game;

import ocsf.server.ConnectionToClient;

import java.io.IOException;

public class Player {
	
	private final String username;
	private final ConnectionToClient client;
	
	private int totalScore = 0;
	private int highestScore = 0;
	private int wins = 0;
	private int gamesPlayed = 0;
	private int matches = 0;
	private int guesses = 0;
	
	public Player(String username, ConnectionToClient client) {
		this.username = username;
		this.client = client;
	}
	
	public String getUsername() {
		return username;
	}
	
	public ConnectionToClient getClient() {
		return client;
	}
	
	public int getTotalScore() {
		return this.totalScore;
	}
	
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	public int getHighestScore() {
		return this.highestScore;
	}
	
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getGamesPlayed() {
		return this.gamesPlayed;
	}
	
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getMatches() {
		return this.matches;
	}
	
	public void setMatches(int matches) {
		this.matches = matches;
	}
	
	public int getGuesses() {
		return this.guesses;
	}
	
	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}
	
	public void send(Object message) {
		try {
			client.sendToClient(message);
			System.out.println("Sent message to " + username + ": " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Player[username=" + username + "]";
	}
	
}

