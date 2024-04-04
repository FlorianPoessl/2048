package at.poessl.examples;

import at.poessl.game.Game;
import at.poessl.strategy.Move;
import at.poessl.strategy.Strategy;
import java.util.Random;

public class RandomStrategy implements Strategy {
	@Override
	public Move calculateMove(Game game) {
		Random random = new Random();
		int randomInt = random.nextInt(4);
		switch (randomInt) {
		case 0:
			return Move.LEFT;
		case 1:
			return Move.RIGHT;
		case 2:
			return Move.UP;
		case 3:
			return Move.DOWN;
		}
		return Move.UP
	}
}
