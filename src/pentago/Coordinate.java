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
	
	public Coordinate left() throws InvalidCoordinateException {
		if (x == 0) {
			throw new InvalidCoordinateException(x-1, y);
		}
		return translate(-1,0);
	}
	
	public Coordinate right() throws InvalidCoordinateException {
		if (x == 5) {
			throw new InvalidCoordinateException(x+1, y);
		}
		return translate(1,0);
	}
	
	public Coordinate up() throws InvalidCoordinateException {
		if (y == 0) {
			throw new InvalidCoordinateException(x, y-1);
		}
		return translate(0,-1);
	}
	
	public Coordinate down() throws InvalidCoordinateException {
		if (y == 5) {
			throw new InvalidCoordinateException(x, y+1);
		}
		return translate(0,1);
	}
	
	public Coordinate leftUp() throws InvalidCoordinateException {
		if (y == 0 || x == 0) {
			throw new InvalidCoordinateException(x-1, y-1);
		}
		return translate(-1,-1);
	}
	
	public Coordinate leftDown() throws InvalidCoordinateException {
		if (y == 5 || x == 0) {
			throw new InvalidCoordinateException(x-1, y+1);
		}
		return translate(-1,1);
	}
	
	public Coordinate rightUp() throws InvalidCoordinateException {
		if (y == 0 || x == 5) {
			throw new InvalidCoordinateException(x+1, y-1);
		}
		return translate(1,-1);
	}
	
	public Coordinate rightDown() throws InvalidCoordinateException {
		if (y == 5 || x == 5) {
			throw new InvalidCoordinateException(x+1, y+1);
		}
		return translate(1,1);
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}

}
