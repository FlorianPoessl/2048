package at.poessl.game;

public class MoveResult {

	private boolean compacted;

	private boolean moved;
	
	private int points;

	public MoveResult(boolean compacted, boolean moved, int points) {
		this.compacted = compacted;
		this.moved = moved;
		this.points = points;
	}

	public boolean isCompacted() {
		return compacted;
	}

	public void setCompacted(boolean compacted) {
		this.compacted = compacted;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
