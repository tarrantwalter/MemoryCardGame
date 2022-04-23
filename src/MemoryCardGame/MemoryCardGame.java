package MemoryCardGame;

import MemoryCardGame.client.ClientGUI;

public class MemoryCardGame {
	
	public static void main(String[] args) {
		if (args.length == 1) {
			if (args[0].equals("-server")) {
				System.out.println("not implemented yet");
				System.out.println("Hello");
			} else if (args[0].equals("-client")) {
				ClientGUI client = new ClientGUI();
				client.setVisible(true);
			}
		}
		
	}
	
}
