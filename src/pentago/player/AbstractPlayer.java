package pentago.player;

import java.util.HashSet;
import java.util.Set;

import pentago.Board;
import pentago.Coordinate;
import pentago.Move;
import pentago.Player;
import pentago.Position;
import pentago.Rotation;

public abstract class AbstractPlayer implements Player {

	String name;
	boolean white;
	
	public AbstractPlayer(String name) {
		this.name = name;
	}
	
	public boolean isWhite() {
		return white;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}
	
	public String toString() {
		return name;
	}
	
	public Position getPosition() {
		if (isWhite()) {
			return Position.WHITE;
		}
		return Position.BLACK;
	}
	
	public Set<Move> getPossibleMoves(Board board) {
		Set<Move> result = new HashSet<Move>();
		for (Integer rotTile = 0; rotTile < 4;rotTile++) {
			for (Rotation rot: Rotation.values()) {
				for (Coordinate possible: board.getFreeCoordinates()) {
					Move current = new Move();
					current.setPlayer(this);
					current.setCoords(possible);
					current.setRotation(rot);
					current.setRotationTile(rotTile);
					result.add(current);
				}
			}
		}
		return result;
	}

}
