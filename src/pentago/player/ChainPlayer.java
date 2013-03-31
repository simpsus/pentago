package pentago.player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pentago.Board;
import pentago.Move;

public class ChainPlayer extends AbstractPlayer {
	
	Map<Integer, Move> impactMoves; 

	public ChainPlayer(String name) {
		super(name);
	}

	@Override
	public Move move(Board board) {
		// This player fails if no chain is longer than the currently longest
		// The key is finding out whether or not the current move contributes
		// to the longest chain or not
		Board clone = board.copy();
		impactMoves = new HashMap<Integer, Move>();
		int before, after;
		for (Move m: getPossibleMoves(board)) {
			before = clone.getLongestChain(getPosition());
			clone.move(m);
			if (clone.detectWin()) {
				if (clone.getWinnerPosition() == getPosition()) {
					return m;
				} 
			}
			after = clone.getLongestChain(getPosition());
//			System.out.println(before + " " + after);
			if (after > before) {
//				System.out.println("yes!");
				// the move has actually had an impact
				impactMoves.put(after,m);
			}
			clone.undoMove(m);
		}
		return impactMoves.get(Collections.max(impactMoves.keySet()));
	}

}
