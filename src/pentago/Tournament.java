package pentago;

import java.util.HashMap;
import java.util.Map;

public class Tournament {

	int rounds;
	int currentRound;
	Player playerOne = null;
	Player playerTwo = null;
	Game currentGame;
	Map<Player,Integer> winCount;
	int ties = 0;
	
	public Tournament(int rounds) {
		super();
		this.rounds = rounds;
		winCount = new HashMap<Player,Integer>();
	}
	
	public void addPlayer(Player player) {
		if (playerOne == null) {
			playerOne = player;
		} else if (playerTwo == null) {
			playerTwo = player;
		} else {
			System.out.println("Already two players present, ditching " + player);
		}
	}

	public void play() {
		currentRound = 1;
		boolean flip = true;
		winCount.put(playerOne,0);
		winCount.put(playerTwo,0);
		int lastTick = 0;
		while (currentRound <= rounds) {
			currentGame = new Game();
			playerOne.setWhite(flip);
			playerTwo.setWhite(!flip);
			currentGame.setPlayer(playerOne);
			currentGame.setPlayer(playerTwo);
			currentGame.play();
			if (currentGame.isTie) {
				ties++;
			} else {
				winCount.put(currentGame.winner, winCount.get(currentGame.winner)+1);
			}
			flip = !flip;
			if ( (((float) (currentRound - lastTick) / rounds)) >= 0.01 ) {
				lastTick = currentRound;
				System.out.println("Played round " + currentRound + "(" + (((float) currentRound / rounds)) + ")");
			}
			currentRound++;
		}
		System.out.println("FINISH");
		System.out.println("Ties " + ties);
		System.out.println(playerOne + ": " + winCount.get(playerOne));
		System.out.println(playerTwo + ": " + winCount.get(playerTwo));
	}
	
	public static void main(String[] args) {
		Tournament t = new Tournament(1000);
		t.addPlayer(new DummyPlayer("Bob"));
		t.addPlayer(new DummyPlayer("Alice"));
		t.play();
	}

}
