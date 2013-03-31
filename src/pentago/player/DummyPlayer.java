package pentago.player;

import java.util.Random;

import pentago.Board;
import pentago.Coordinate;
import pentago.Move;
import pentago.Player;
import pentago.Rotation;

public class DummyPlayer extends AbstractPlayer implements Player {
	
	boolean white;
	Random rand;
	String name;
	
	public DummyPlayer(String name) {
		super(name);
		rand = new Random();
	}
	
	@Override
	public Move move(Board board) {
		Coordinate c;
		c = new Coordinate(rand.nextInt(6), rand.nextInt(6));
		while (!board.checkCoordinate(c)) {
			c = new Coordinate(rand.nextInt(6), rand.nextInt(6));
		}
		Move move = new Move();
		move.setCoords(c);
		move.setRotationTile(rand.nextInt(4));
		move.setRotation(rand.nextInt(2) == 0 ? Rotation.CLOCKWISE : Rotation.COUNTER);
		move.setPlayer(this);
		return move;
	}

}
