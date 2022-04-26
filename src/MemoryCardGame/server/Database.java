package MemoryCardGame.server;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
	
	private Connection conn;
	private Properties props;
	
	//Add any other data fields you like – at least a Connection object is mandatory
	public Database() {
		props = new Properties();
		try {
			props.load(new FileReader("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(props.getProperty("url"), props);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createTable();
	}
	
	public void createTable() {
		String query = "CREATE TABLE IF NOT EXISTS users (" +
				"username VARCHAR(24) NOT NULL," +
				"password VARBINARY(32) NOT NULL," +
				"total_score int," +
				"highest_score smallint," +
				"wins smallint," +
				"games_played smallint," +
				"matches smallint," +
				"guesses smallint," +
				"CONSTRAINT users_username_pk PRIMARY KEY (username)" +
				");";
		try {
			executeDML(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(String username, String password) {
		String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = aes_encrypt('" + password + "','definitelysecure');";
		List<String> list = query(query);
		return list != null;
	}
	
	public boolean createAccount(String username, String password) {
		String query = "SELECT * FROM users WHERE username = '" + username + "';";
		List<String> list = query(query);
		if (list != null) {
			return false;
		}
		String query2 = String.format("INSERT INTO users (username, password, total_score, highest_score, wins, games_played, matches, guesses)" +
				"VALUES ('%s', aes_encrypt('%s','definitelysecure'), '%s', '%s', '%s', '%s', '%s', '%s');", username, password, 0, 0, 0, 0, 0, 0);
		try {
			executeDML(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return true;
	}
	
	public void updateUser(String username, int totalScore, int highestScore, int wins, int gamesPlayed, int matches, int guesses) {
		String query = "UPDATE users SET total_score = ?, highest_score = ?, wins = ?, games_played = ?, matches = ?, guesses = ? WHERE username = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, totalScore);
			stmt.setInt(2, highestScore);
			stmt.setInt(3, wins);
			stmt.setInt(4, gamesPlayed);
			stmt.setInt(5, matches);
			stmt.setInt(6, guesses);
			stmt.setString(7, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> query(String query) {
		Statement statement;
		ResultSet resultSet;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
			List<String>      result   = new ArrayList<>();
			ResultSetMetaData metadata = resultSet.getMetaData();
			while (resultSet.next()) {
				List<String> row = new ArrayList<>();
				for (int i = 1; i <= metadata.getColumnCount(); i++) {
					row.add(resultSet.getString(i));
				}
				result.add(String.join(",", row));
			}
			return result.isEmpty() ? null : result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeDML(String dml) throws SQLException {
		Statement statement = conn.createStatement();
		statement.executeUpdate(dml);
	}
	
}

