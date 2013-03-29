package pentago;

public class Game {
	
	Player white;
	Player black;
	Board board;
	Player winner;
	boolean isTie = false;
	
	public Game() {
		board = new Board();
	}
	
	public void setPlayer (Player player) {
		if (player.isWhite()) {
			white = player;
		} else {
			black = player;
		}
	}
	
	public void play() {
		for (int i=0;i<18;i++) {
			Move whiteMove = white.move(board);
			board.move(whiteMove);
			if (board.detectWin()) {
				break;
			}
			Move blackMove = black.move(board);
			board.move(blackMove);
			if (board.detectWin()) {
				break;
			}
		}
		if (board.isWon) {
			Position winnerPos = board.winnerPosition;
			if (winnerPos == Position.BLACK) {
				winner = black;
			} else {
				winner = white;
			}
		} else {
			//We have a tie
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
