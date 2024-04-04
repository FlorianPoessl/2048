package at.poessl.examples;

import at.poessl.game.Game;
import at.poessl.strategy.Move;
import at.poessl.strategy.Strategy;

public class RotatingStrategy implements Strategy {
	@Override
	public Move calculateMove(Game game) {
		switch (history.get(history.size()-1).getMove()) {
		case DOWN:
			return Move.LEFT;
		case UP:
			return Move.RIGHT;
		case LEFT:
			return Move.UP;
		case RIGHT:
			return Move.DOWN;
		}
		return Move.LEFT;
	}
}
