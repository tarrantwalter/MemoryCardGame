import MemoryCardGame.client.MemoryCardGameClientGUI;
import MemoryCardGame.client.control.GameControl;
import MemoryCardGame.client.panel.GamePanel;
import MemoryCardGame.server.MemoryCardGameServer;
import MemoryCardGame.server.info.StartGameInfo;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ClientTest {
	
	private MemoryCardGameServer    server;
	private MemoryCardGameClientGUI gui;
	private GameControl             gameControl;
	private GamePanel               gamePanel;
	private StartGameInfo           startGameInfo;
	
	@Before
	public void setUp() throws IOException {
		server = new MemoryCardGameServer();
		server.listen();
		gui = new MemoryCardGameClientGUI();
		gameControl = new GameControl(gui);
		gamePanel = new GamePanel(gameControl);
		startGameInfo = new StartGameInfo(6);
	}
	
	@Test
	public void testAction() {
		gamePanel.setStartGameInfo(startGameInfo);
		for (Component component : gamePanel.getComponents()) {
			if (component instanceof JPanel panel && panel.getLayout() instanceof GridLayout) {
				assertEquals(panel.getComponentCount(), 6 * 6);
			}
		}
	}
	
}
