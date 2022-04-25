package MemoryCardGame.server.info;

import MemoryCardGame.server.game.Game;

import java.io.Serializable;

public class StartGameInfo implements Serializable {
	
	private final int columns = Game.COLUMNS;
	private final int rows;
	
	public StartGameInfo(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	@Override
	public String toString() {
		return "StartGameInfo[columns=" + columns + ",rows=" + rows + "]";
	}

}
