package pentago;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SniperPlayer extends AbstractPlayer implements Player {

	Board clone;
	
	public SniperPlayer(String name) {
		super(name);
	}

	@Override
	public Move move(Board board) {
		clone = board.copy();
		Move currentMove;
		Set<Move> backupMoves = new HashSet<Move>();
		for (Coordinate possible: clone.getFreeCoordinates()) {
			for (Rotation rot: Rotation.values()) {
				for (Integer rotTile = 0; rotTile < 4;rotTile++) {
					currentMove = new Move();
					currentMove.setCoords(possible);
					currentMove.setPlayer(this);
					currentMove.setRotation(rot);
					currentMove.setRotationTile(rotTile);
					clone.move(currentMove);
					if (clone.detectWin()) {
						if (clone.winnerPosition == getPosition()) {
							return currentMove;
						} else {
							// I loose, don't do THAT!
							continue;
						}
					}
					//I haven't won or lost, it can't be SO bad!
					backupMoves.add(currentMove);
				}
			}
		}
		int index = new Random().nextInt(backupMoves.size());
		int current = 0;
		for (Move m:backupMoves) {
			if (current++ == index) {
				return m;
			}
		}
		throw new RuntimeException("Error in SniperPlayer.move");
	}

	
}
