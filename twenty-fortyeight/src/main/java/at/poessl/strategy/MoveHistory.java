package at.poessl.strategy;

public class MoveHistory {
	
	private Move move;
	
	private boolean valid;

	public MoveHistory(Move move, boolean valid) {
		this.move = move;
		this.valid = valid;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
