package at.poessl.examples;

import at.poessl.game.Game;
import at.poessl.strategy.Move;
import at.poessl.strategy.MoveHistory;
import at.poessl.strategy.Strategy;

public class SmartStrategy implements Strategy {
	@Override
	public Move calculateMove(Game game) {
		if (!history.isEmpty()) {
			MoveHistory lastMove = history.get(history.size()-1);
			if (!lastMove.isValid()) {
				switch (lastMove.getMove()) {
				case UP:
					return Move.LEFT;
				case LEFT:
					return Move.RIGHT;
				case RIGHT:
					return Move.DOWN;
				case DOWN:
					return Move.UP;
				}
			}
		}
		return Move.UP;
	}
}
