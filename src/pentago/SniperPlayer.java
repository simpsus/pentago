package pentago;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SniperPlayer extends AbstractPlayer implements Player {

	Board clone;
	int verbosity = 0;
	
	public SniperPlayer(String name) {
		super(name);
	}
	
	public SniperPlayer(String name, int verbosity) {
		this(name);
		this.verbosity = verbosity;
	}
	
	public void setVerbosity(int verb) {
		verbosity = verb;
	}
	
	void tell(String message, int importance) {
		if (importance <= verbosity) {
			System.out.println(message);
		}
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
							tell(this + " wins with " + currentMove,1);
							return currentMove;
						} else {
							tell("Losing Move! " + currentMove,1);
							// I loose, don't do THAT!
							clone.undoMove(currentMove);
							continue;
						}
					}
					//I haven't won or lost, it can't be SO bad!
					backupMoves.add(currentMove);
					clone.undoMove(currentMove);
				}
			}
		}
		int index = new Random().nextInt(backupMoves.size());
		int current = 0;
		tell("No finisher found, radoming",2);
		for (Move m:backupMoves) {
			if (current++ == index) {
				tell("Moving randomly " + m,3);
				return m;
			}
		}
		throw new RuntimeException("Error in SniperPlayer.move");
	}

	
}
