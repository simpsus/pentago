package pentago;

import java.util.HashMap;
import java.util.Map;

import pentago.player.ChainPlayer;
import pentago.player.DummyPlayer;

public class Tournament {

	int rounds;
	int currentRound;
	Player playerOne = null;
	Player playerTwo = null;
	Game currentGame;
	Map<Player,Integer> winCount;
	int ties = 0;
	int verbosity = 0;
	
	public Tournament(int rounds) {
		super();
		this.rounds = rounds;
		winCount = new HashMap<Player,Integer>();
	}
	
	public void setVerbosity(int v) {
		verbosity = v;
	}
	
	void tell(String message, int importance) {
		if (importance <= verbosity) {
			System.out.println(message);
		}
	}
	
	public void addPlayer(Player player) {
		if (playerOne == null) {
			tell("Adding Player 1 " + player, 3);
			playerOne = player;
		} else if (playerTwo == null) {
			tell("Adding Player 2 " + player, 3);
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
		tell("Starting the tournament.",2);
		while (currentRound <= rounds) {
			tell("Playing Round " + currentRound,2);
			currentGame = new Game(verbosity);
			playerOne.setWhite(flip);
			playerTwo.setWhite(!flip);
			currentGame.setPlayer(playerOne);
			currentGame.setPlayer(playerTwo);
			currentGame.play();
			if (currentGame.isTie) {
				tell("Game is a tie.", 2);
				ties++;
			} else {
				tell("Awarding Winner.",2);
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
		Tournament t = new Tournament(1);
		t.setVerbosity(2);
		t.addPlayer(new ChainPlayer("Alice"));
		t.addPlayer(new DummyPlayer("Bob"));
		t.play();
	}

}
