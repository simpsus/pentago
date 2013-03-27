package pentago;

public class Tile {
	
	Position[] positions = new Position[9];
	
	public Tile() {
		for (int i=0;i<9;i++) {
			positions[i] = Position.EMPTY;
		}
	}
	
	private int coordToIndex(Coordinate c) {
		return c.getX() + 3* c.getY();
	}
	
	public void setPosition(Coordinate c, Position pos) {
		positions[coordToIndex(c)] = pos;
	}
	
	public void rotate(Rotation rot) {
		System.out.println("Implement rotation");
	}

	public String getLineRepresentation(int line) {
		String result = "";
		for (int i=0;i<3;i++) {
			result += positions[line*3 + i];
		}
		return result;
	}
	
	public Position getPosition(Coordinate coord) {
		return positions[coordToIndex(coord)];
	}

}
