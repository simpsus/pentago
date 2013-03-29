package pentago;

import java.util.Arrays;

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
		Position[] temp = Arrays.copyOf(positions, 9);
		if (rot == Rotation.CLOCKWISE) {
			positions[0] = temp[6];
			positions[1] = temp[3];
			positions[2] = temp[0];
			positions[3] = temp[7];
			positions[4] = temp[4];
			positions[5] = temp[1];
			positions[6] = temp[8];
			positions[7] = temp[5];
			positions[8] = temp[2];
		} else {
			positions[0] = temp[2];
			positions[1] = temp[5];
			positions[2] = temp[8];
			positions[3] = temp[1];
			positions[4] = temp[4];
			positions[5] = temp[7];
			positions[6] = temp[0];
			positions[7] = temp[3];
			positions[8] = temp[6];
		}
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
	
	public Tile copy() {
		Tile result = new Tile();
		for (int i=0;i<9;i++) {
			result.positions[i] = positions[i];
		}
		return result;
	}

}
