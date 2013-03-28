package pentago;

public class InvalidCoordinateException extends Exception {
	
	int x;
	int y;
	
	public InvalidCoordinateException(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String getMessage() {
		return "Invalid Coordinates: (" + x + "," + y+ ")";
	}
	
	

}
