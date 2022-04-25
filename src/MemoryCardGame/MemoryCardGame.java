package MemoryCardGame;

import MemoryCardGame.client.MemoryCardGameClientGUI;
import MemoryCardGame.server.MemoryCardGameServerGUI;

public class MemoryCardGame {
	
	public static void main(String[] args) {
		if (args.length == 1) {
			if (args[0].equals("-server")) {
				MemoryCardGameServerGUI server = new MemoryCardGameServerGUI();
				server.setVisible(true);
			} else if (args[0].equals("-client")) {
				MemoryCardGameClientGUI client = new MemoryCardGameClientGUI();
				client.setVisible(true);
			} else {
				System.out.println("Usage: java MemoryCardGame.MemoryCardGame [-server|-client]");
			}
		} else {
			System.out.println("Usage: java MemoryCardGame.MemoryCardGame [-server|-client]");
		}
		
	}
	
}
