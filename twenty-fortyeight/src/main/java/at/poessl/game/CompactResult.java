package at.poessl.game;

public class CompactResult {
	
	private int[] result;
	
	private boolean compacted;
	
	private boolean moved;
	
	private int points;

	public CompactResult(int[] result, boolean compacted, boolean moved, int points) {
		this.result = result;
		this.compacted = compacted;
		this.moved = moved;
		this.points = points;
	}

	public int[] getResult() {
		return result;
	}

	public void setResult(int[] result) {
		this.result = result;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setCompacted(boolean compacted) {
		this.compacted = compacted;
	}

	public boolean isCompacted() {
		return compacted;
	}
}
