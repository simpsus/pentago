package pentago;

public class Game {
	
	Player white;
	Player black;
	Board board;
	
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
			Move blackMove = black.move(board);
			board.move(blackMove);
			System.out.println(i);
			System.out.println(this);
			if (board.detectWin()) {
				System.out.println("We have a Bingo!");
				break;
			}
		}
	}
	
	public String toString() {
		return board.toString();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPlayer(new DummyPlayer("Bob", true));
		game.setPlayer(new DummyPlayer("Alice", false));
		game.play();
	}

}
