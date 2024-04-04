package at.poessl.main;

import at.poessl.examples.RandomStrategy;
import at.poessl.examples.RotatingStrategy;
import at.poessl.game.Game;
import at.poessl.strategy.Move;
import at.poessl.strategy.Strategy;

public class Main {
	
	public static final int TEST_SIZE = 1000;
	
	public static void main( String[] args ) {
		int averagePointsSmart = 0;
		int averagePointsRotating = 0;
		for (int i = 0; i<TEST_SIZE; i++) {
			if(i%100 == 0) {
				System.out.println(i);
			}
			averagePointsSmart += executeStrategy(new RandomStrategy());
			averagePointsRotating += executeStrategy(new RotatingStrategy());
		}
		System.out.println("Smart: " + averagePointsSmart/TEST_SIZE);
		System.out.println("Rotating: " + averagePointsRotating/TEST_SIZE);
	}
	
	public static int executeStrategy(Strategy strategy) {
		Game game = new Game();
		game.setLog(false);

		boolean previousResult = false;
		while(!game.isFinished()) {
			Move move = strategy.calculateMove(game, previousResult);

			previousResult = game.executeMove(move);

			if (game.isFinished() && game.isLog()) {
				for (int i = 0; i < game.getGrid().getField().length; i++) {
					System.out.println(java.util.Arrays.toString(game.getGrid().getField()[i]));
				}
				System.out.println(game.getPoints());
			}
		}
		return game.getPoints();
	}
}
