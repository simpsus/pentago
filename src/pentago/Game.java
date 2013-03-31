package pentago;

import pentago.player.DummyPlayer;

public class Game {
	
	Player white;
	Player black;
	Board board;
	Player winner;
	boolean isTie = false;
	int verbosity = 0;
	
	public Game() {
		board = new Board();
	}
	
	public Game(int verbosity) {
		this();
		this.verbosity = verbosity;
	}
	
	void tell(String message, int importance) {
		if (importance <= verbosity) {
			System.out.println(message);
		}
	}
	
	public void setPlayer (Player player) {
		if (player.isWhite()) {
			white = player;
		} else {
			black = player;
		}
	}
	
	boolean playerMove(Player player) {
		Move move = null;
		boolean moveOK = false;
		while (!moveOK) {
			tell("Query move from " + player,2);
			move = player.move(board);
			moveOK = board.checkCoordinate(move.coords);
			if (!moveOK) {
				tell("Invalid Move from " + player + ": " + move,0);
			}
		}
		board.move(move);
		if (board.detectWin()) {
			tell("Win detected", 2);
			return true;
		}
		return false;
	}
	
	public void play() {
		tell("Starting Game.", 2);
		for (int i=0;i<18;i++) {
			if (playerMove(white)) {
				break;
			}
			if (playerMove(black)) {
				break;
			}
			tell("After current Move: ",2);
			tell(toString(),2);
		}
		if (board.isWon) {
			Position winnerPos = board.getWinnerPosition();
			tell("Winner is " + winnerPos, 2);
			tell(toString(),2);
			if (winnerPos == Position.BLACK) {
				winner = black;
			} else {
				winner = white;
			}
		} else {
			//We have a tie
			tell("There is no Winner in this game.",2);
			isTie = true;
		}
	}
	
	public String toString() {
		return board.toString();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		Player dummy = new DummyPlayer("Bob");
		dummy.setWhite(true);
		game.setPlayer(dummy);
		dummy = new DummyPlayer("Alice");
		dummy.setWhite(false);
		game.setPlayer(dummy);
		game.play();
	}

}
