package pentago;

public abstract class AbstractPlayer implements Player {

	String name;
	boolean white;
	
	public AbstractPlayer(String name) {
		this.name = name;
	}
	
	public boolean isWhite() {
		return white;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}
	
	public String toString() {
		return name;
	}
	
	public Position getPosition() {
		if (isWhite()) {
			return Position.WHITE;
		}
		return Position.BLACK;
	}

}
