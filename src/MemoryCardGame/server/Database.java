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

