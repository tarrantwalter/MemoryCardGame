import MemoryCardGame.server.Database;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {
	
	private final String   testUser     = "testy";
	private final String   testPassword = "123";
	private       Database db;
	
	@Before
	public void setUp() {
		db = new Database();
	}
	
	@Test
	public void testQuery() {
		List<String> results = db.query(
				"SELECT aes_decrypt(password, 'definitelysecure') AS password FROM users WHERE username = '" + testUser + "'");
		assertNotNull(results);
		String actual = results.get(0);
		assertEquals("Check query", testPassword, actual);
	}
	
	@Test
	public void testExecuteDML() throws SQLException {
		db.executeDML("INSERT INTO users (username, password) VALUES ('testUser', aes_encrypt('Password1', 'definitelysecure'))");
	}
	
}
