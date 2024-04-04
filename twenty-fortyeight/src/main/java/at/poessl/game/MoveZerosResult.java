package at.poessl.game;

public class MoveZerosResult {
	
	private int[] result;
	
	private boolean moved;

	public MoveZerosResult(int[] result, boolean moved) {
		this.result = result;
		this.moved = moved;
	}

	public int[] getResult() {
		return result;
	}

	public void setResult(int[] result) {
		this.result = result;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
