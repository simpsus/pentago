package pentago;

public class Coordinate {
	
	int x;
	int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coordinate translate(int dx, int dy) {
		return new Coordinate(x+dx,y+dy);
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}

}
