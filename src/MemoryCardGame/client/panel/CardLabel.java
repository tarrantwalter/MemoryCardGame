package MemoryCardGame.client.panel;

import javax.swing.*;
import java.io.Serial;

public class CardLabel extends JLabel {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final int x;
	private final int y;
	private final ImageIcon back;
	
	public CardLabel(int x, int y, ImageIcon back) {
		super(back);
		this.x = x;
		this.y = y;
		this.back = back;
	}
	
	public void resetImage() {
		setIcon(back);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isFlipped() {
		return !back.equals(getIcon());
	}

	
}
