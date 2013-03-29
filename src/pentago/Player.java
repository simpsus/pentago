package pentago;

public interface Player {
	
	public Move move (Board board);
	
	public boolean isWhite();
	
	public void setWhite(boolean white);
	
}
