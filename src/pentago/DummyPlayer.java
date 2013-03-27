package pentago;

import java.util.Random;

public class DummyPlayer implements Player {
	
	boolean white;
	Random rand;
	String name;
	
	public DummyPlayer(String name, boolean white) {
		this.name = name;
		this.white = white;
		rand = new Random();
	}
	
	public boolean isWhite() {
		return white;
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
		move.setRotationTile(board.getTile(rand.nextInt(4)));
		move.setRotation(rand.nextInt(2) == 0 ? Rotation.CLOCKWISE : Rotation.COUNTER);
		move.setPlayer(this);
		return move;
	}

}
