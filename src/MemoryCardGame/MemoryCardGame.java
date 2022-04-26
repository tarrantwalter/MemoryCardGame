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
				MemoryCardGameClientGUI client = new MemoryCardGameClientGUI("localhost");
				client.setVisible(true);
			} else {
				System.out.println("Usage: java MemoryCardGame.MemoryCardGame [-server|-client]");
			}
		} else if (args.length == 3) {
			if (args[0].equals("-client") && args[1].equals("-host")) {
				String host = args[2];
				MemoryCardGameClientGUI client = new MemoryCardGameClientGUI(host);
				client.setVisible(true);
			} else if (args[0].equals("-host") && args[2].equals("-client")) {
				String host = args[1];
				MemoryCardGameClientGUI client = new MemoryCardGameClientGUI(host);
				client.setVisible(true);
			} else {
				System.out.println("Usage: java MemoryCardGame.MemoryCardGame [-server|-client] {-host 127.0.0.1}");
			}
		} else {
			System.out.println("Usage: java MemoryCardGame.MemoryCardGame [-server|-client] {-host 127.0.0.1}");
		}
		
	}
	
}
