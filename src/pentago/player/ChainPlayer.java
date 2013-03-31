package pentago.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pentago.Board;
import pentago.Chain;
import pentago.Move;

public class ChainPlayer extends AbstractPlayer {
	
	Map<Integer, Move> impactMoves; 

	public ChainPlayer(String name) {
		super(name);
	}
	
	private Map<Integer,Integer> analyzeChains(Set<Chain> chains) {
		Map<Integer,Integer> chainLengths = new HashMap<Integer,Integer>();
		for (Chain chain: chains) {
			if (!chainLengths.containsKey(chain.getSize())) {
				chainLengths.put(chain.getSize(),0);
			}
			chainLengths.put(chain.getSize(),chainLengths.get(chain.getSize())+1);
		}
		return chainLengths;
	}

	@Override
	public Move move(Board board) {
		Board clone = board.copy();
		Set<Chain> chains = board.getAllChains(getPosition());
		Map<Integer,Integer> beforeLengths = analyzeChains(chains);
		// Now lets try to expand them with any of the possible moves
		for (Move m: getPossibleMoves(board)) {
			clone.move(m);
			Map<Integer,Integer> afterLengths = analyzeChains(clone.getAllChains(getPosition()));
			for (Integer length : new Integer[]{5,4,3,2}) {
				if (afterLengths.containsKey(length)) {
					if (!beforeLengths.containsKey(length)) {
						return m;
					} else {
//						System.out.println("Before " + beforeLengths);
//						System.out.println("After " + afterLengths);
						if (afterLengths.get(length) > beforeLengths.get(length)) {
							return m;
						}
					}
				}
			}
			clone.undoMove(m);
		}
		return (Move) getPossibleMoves(board).toArray()[0];
	}

}
