package at.poessl.strategy;

import at.poessl.game.Game;
import java.util.ArrayList;
import java.util.List;

public interface Strategy {
	
	List<MoveHistory> history = new ArrayList<>();
	
	Move calculateMove(Game game);
	
	default Move calculateMove(Game game, boolean previousMoveValid) {
		logPreviousMove(previousMoveValid);
		Move move = calculateMove(game);
		logNextMove(move);
		return move;
	}
	
	default void logPreviousMove(boolean previousMoveValid) {
		if (!history.isEmpty() && previousMoveValid) {
			history.get(history.size()-1).setValid(previousMoveValid);
		}
	}

	default void logNextMove(Move move) {
		history.add(new MoveHistory(move, false));
	}
	
}
