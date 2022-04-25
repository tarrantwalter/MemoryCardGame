package MemoryCardGame.client.panel;

import MemoryCardGame.client.CardData;
import MemoryCardGame.client.control.GameControl;
import MemoryCardGame.server.info.StartGameInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {
	
	private static final int CARD_WIDTH  = 50;
	private static final int CARD_HEIGHT = 100;
	
	private final Image                 cardBack;
	private final Map<Integer, Image>   cardImages;
	private final Map<JLabel, CardData> cardPositionsReverse;
	private final CardData[][]          cardPositions;
	private final GameControl           control;
	private final JPanel                cardPanel;
	
	public GamePanel(GameControl control) {
		super(new GridBagLayout());
		this.control = control;
		
		cardPanel = new JPanel(new GridLayout(6, 6, 5, 5));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(control);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(cancelButton);
		
		JPanel grid = new JPanel(new GridLayout(2, 1, 0, 0));
		grid.add(cardPanel);
		grid.add(buttonPanel);
		
		add(grid, new GridBagConstraints());
		
		cardImages = new HashMap<>();
		cardPositionsReverse = new HashMap<>();
		cardPositions = new CardData[6][6];
		
		try {
			cardBack = resizeImage(ImageIO.read(getClass().getResourceAsStream("/MemoryCardGame/client/card/back.jpg")));
			for (int i = 1; i <= 18; i++) {
				cardImages.put(i, resizeImage(ImageIO.read(getClass().getResourceAsStream("/MemoryCardGame/client/card/" + i + ".jpg"))));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void flipCard(int x, int y, int number) {
		System.out.println("flipCard: " + x + " " + y + " " + number);
		if (isCardFlipped(x, y)) {
			System.out.println("flipCard: already flipped");
			cardPositions[x][y].flip(null);
		} else {
			System.out.println("flipCard: flipping");
			cardPositions[x][y].flip(new ImageIcon(cardImages.get(number)));
		}
	}
	
	public boolean isCardFlipped(int x, int y) {
		return cardPositions[x][y].isFlipped();
	}
	
	public CardData getCardData(JLabel card) {
		return cardPositionsReverse.get(card);
	}
	
	private BufferedImage resizeImage(BufferedImage originalImage) throws IOException {
		BufferedImage resizedImage = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D    graphics2D   = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, CARD_WIDTH, CARD_HEIGHT, null);
		graphics2D.dispose();
		return resizedImage;
	}
	
	public void setStartGameInfo(StartGameInfo info) {
		
		for (int y = 0; y < info.getRows(); y++) {
			for (int x = 0; x < info.getColumns(); x++) {
				ImageIcon back  = new ImageIcon(cardBack);
				JLabel    label = new JLabel(back);
				CardData  data  = new CardData(label, x, y, back);
				cardPositions[x][y] = data;
				cardPositionsReverse.put(label, data);
				label.setMinimumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
				label.addMouseListener(control);
				cardPanel.add(label);
				label.setVisible(true);
			}
		}
		
	}
	
	public void setTurn(boolean turn) {
		control.setTurn(turn);
	}
	
	public void reset() {}
	
}
