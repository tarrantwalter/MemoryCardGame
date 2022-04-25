package MemoryCardGame.server.info;

import java.io.Serializable;

public class StartGameInfo implements Serializable {
	
	private final int test;
	
	public StartGameInfo() {
		test = 1;
	}
	
	public int getTest() {
		return test;
	}
	
	@Override
	public String toString() {
		return "StartGameInfo[test=" + test + "]";
	}

}
