package MemoryCardGame;

import javax.swing.*;

public class MemoryCardGame extends JFrame {
	
	public static void main(String[] args) {
		MemoryCardGame game = new MemoryCardGame();
		game.setVisible(true);
	}
	
	public MemoryCardGame() {
		setTitle("Memory Card Game");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
}
