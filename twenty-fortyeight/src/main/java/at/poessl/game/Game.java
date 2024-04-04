package at.poessl.game;

import at.poessl.strategy.Move;

public class Game {
	
	private boolean finished;
	
	private Grid grid;
	
	private int points;
	
	private boolean log;
	
	public Game() {
		this.grid = new Grid();
		this.fillEmptyCell();
		this.finished = false;
	}

	public Game(int[][] fieldImnput) {
		this.grid = new Grid();
		this.grid.setField(fieldImnput);
		this.finished = false;
	}
	
	public boolean executeMove(Move move) {
		MoveResult result = this.grid.executeMove(move);
		boolean successfulMove = false;
		boolean gameIsFinished = this.gameIsFinished();
		
		if (result.isMoved() || result.isCompacted()) {
			this.setPoints(this.getPoints() + result.getPoints());
			if (!gameIsFinished) {
				this.fillEmptyCell();
			} 
			successfulMove = true;
		}
		
		if (gameIsFinished) {
			this.finished = true;
		}
		
		return successfulMove;
	}
	
	public void fillEmptyCell() {
		GridCoordinate coordinate = this.grid.getRandomEmptyCell();
		this.grid.fillGridCoordinate(coordinate);
	}
	
	public boolean gameIsFinished() {
		boolean gridIsFull = this.grid.getRandomEmptyCell() == null;
		boolean movePossible = this.movePossible();
		
		if (!gridIsFull) {
			return false;
		} else {
			return !movePossible;
		}
	}
	
	public boolean movePossible() {
		for (int row = 0; row < this.grid.getField().length; row++) {
			for (int col = 0; col < this.grid.getField()[row].length; col++) {
				int currentValue = this.grid.getField()[row][col];
				// Check if neighboring cells have the same value
				if (row > 0 && this.grid.getField()[row - 1][col] == currentValue) { // Check above
					return true;
				}
				if (row < this.grid.getField().length - 1 && this.grid.getField()[row + 1][col] == currentValue) { // Check below
					return true;
				}
				if (col > 0 && this.grid.getField()[row][col - 1] == currentValue) { // Check left
					return true;
				}
				if (col < this.grid.getField()[row].length - 1 && this.grid.getField()[row][col + 1] == currentValue) { // Check right
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public boolean isLog() {
		return log;
	}
}
