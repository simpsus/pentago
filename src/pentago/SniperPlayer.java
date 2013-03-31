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
//		System.out.println("CLONE BEFORE" + clone);
		Move currentMove;
		int examinedMoves = 0;
		Set<Move> backupMoves = new HashSet<Move>();
		for (Integer rotTile = 0; rotTile < 4;rotTile++) {
			for (Rotation rot: Rotation.values()) {
				for (Coordinate possible: clone.getFreeCoordinates()) {
					examinedMoves++;
					currentMove = new Move();
					currentMove.setCoords(possible);
					currentMove.setPlayer(this);
					currentMove.setRotation(rot);
					currentMove.setRotationTile(rotTile);
					clone.move(currentMove);
					if (clone.detectWin()) {
						if (clone.winnerPosition == getPosition()) {
							System.out.println(examinedMoves);
							tell(this + " wins with " + currentMove,1);
							tell("Winning CLONE " + clone,2);
							return currentMove;
						} else {
							tell("Losing Move! " + currentMove,1);
							// I loose, don't do THAT!
						}
					} else {
						//I haven't won or lost, it can't be too bad!
						backupMoves.add(currentMove);
					}
					clone.undoMove(currentMove);
				}
			}
		}
//		System.out.println("CLONE AFTER" + clone);
//		tell("Number of examined Moves: " + examinedMoves, 2);
//		tell("Undone Moves " + clone.undoCount,2);
//		tell("Number of moves to chose from: " + backupMoves.size(),2);
		int index = new Random().nextInt(backupMoves.size());
		int current = 0;
//		tell("No finisher found, radoming",2);
//		System.out.println("Still equal?" + clone.equals(board));
		for (Move m:backupMoves) {
			if (current++ == index) {
				tell("Moving randomly " + m,2);
				return m;
			}
		}
		throw new RuntimeException("Error in SniperPlayer.move");
	}

	
}
