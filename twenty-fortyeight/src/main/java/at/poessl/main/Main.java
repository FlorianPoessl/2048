package at.poessl.main;

import at.poessl.examples.FloStrategy;
import at.poessl.game.Game;
import at.poessl.strategy.Move;

public class Main {
	public static void main( String[] args ) {
		Game game = new Game();
		FloStrategy strategy = new FloStrategy();
		
		boolean previousResult = false;
		while(!game.isFinished()) {
			Move move = strategy.calculateMove(game, previousResult);
			
			previousResult = game.executeMove(move);
			
			if (game.isFinished()) {
				for (int i = 0; i < game.getGrid().getField().length; i++) {
					System.out.println(java.util.Arrays.toString(game.getGrid().getField()[i]));
				}
				System.out.println(game.getPoints());
			}
		}
		
	}
}
