
package pentago;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	Tile[] tiles = new Tile[4];
	String SEP = "|";
	String NL = "\n";
	String HORSEP = "-";
	private Position winnerPosition;
	boolean isWon = false;
	int undoCount = 0;
	
	public Board() {
		for (int i=0;i<4;i++) {
			tiles[i] = new Tile();
		}
	}
	
	public Tile getTile(int pos) {
		return tiles[pos];
	}
	
	public void move(Move move) {
		Coordinate c = move.getCoords();
		if (checkCoordinate(c)) {
			Tile moveTile = getTileAtCoord(c);
			Coordinate tileCoord = translateCoordToTile(c, moveTile);
			if (move.getPlayer().isWhite()) {
				moveTile.setPosition(tileCoord, Position.WHITE);
			} else {
				moveTile.setPosition(tileCoord, Position.BLACK);
			}
			Integer rotTile = move.getRotationTile();
			tiles[rotTile].rotate(move.getRotation());
		}
	}
	
	public void undoMove(Move move) {
		++undoCount;
		//Change rotation and position and rotate first so that the coordinates match again!
		move.setRotation(move.getRotation().opposite());
		Integer rotTile = move.getRotationTile();
		tiles[rotTile].rotate(move.getRotation());
		Coordinate c = move.getCoords();
		Tile moveTile = getTileAtCoord(c);
		Coordinate tileCoord = translateCoordToTile(c, moveTile);
		moveTile.setPosition(tileCoord, Position.EMPTY);
	}
	
	public boolean detectWin() {
		//a win is any straight line of 5 same color stones
		// Let's start at any coord and check if its the start of a winning line
		for (Coordinate c: getBoardCoordinates()) {
			Position p = getPositionAtCoord(c);
			if (p.isPlayer()) {
				// there are 8 possible ways to win from this position
				for (String direction : Coordinate.DIRECTIONS) {
					int count = 1;
					Coordinate nextC = c;
					while (count != 5) {
						try {
							nextC = nextC.getDirection(direction);
							if (getPositionAtCoord(nextC).equals(p)) {
								count++;
							} else {
								break;
							}
						} catch (InvalidCoordinateException ex) {
							break;
						}
					}
					if (count == 5) {
						// we have a bingo!
						setWinnerPosition(p);
						isWon = true;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Iterable<Coordinate> getBoardCoordinates() {
		List<Coordinate> temp = new ArrayList<Coordinate>();
		for (int i = 0;i<6;i++) {
			for (int j = 0;j<6;j++) {
				temp.add(new Coordinate(i,j));
			}
		}
		return temp;
	}
	
	public String toString() {
		String result = " ";
		for (int i=0;i<6;i++) {
			if (i==3) {
				result += SEP;
			}
			result += ""+i;
		}
		result += NL;
		for (int i=0;i<3;i++) {
			result += i + tiles[0].getLineRepresentation(i) + SEP + tiles[1].getLineRepresentation(i) + NL;
		}
		for (int i=0;i<7;i++) {
			result += HORSEP;
		}
		result += NL;
		for (int i=0;i<3;i++) {
			result += (3+i) + tiles[2].getLineRepresentation(i) + SEP + tiles[3].getLineRepresentation(i) + NL;
		}
		return result;
	}
	
	public boolean checkCoordinate(Coordinate coord) {
		return getPositionAtCoord(coord) == Position.EMPTY;
	}
	
	public Tile getTileAtCoord(Coordinate coord) {
		Tile tile;
		if (coord.getX() > 2) {
			if (coord.getY() > 2) {
				//bottom right
				tile = tiles[3];
			} else {
				//top right
				tile = tiles[1];
			}
		} else {
			if (coord.getY() > 2) {
				//bottom left
				tile = tiles[2];
			} else {
				//top left
				tile = tiles[0];
			}
		}
		return tile;
	}
	
	public Coordinate translateCoordToTile(Coordinate coord, Tile tile) {
		Coordinate c = null;
		if (tile == tiles[0]) {
			c = coord;
		} else if (tile == tiles[1]) {
			c = coord.translate(-3, 0);
		} else if (tile == tiles[2]) {
			c = coord.translate(0, -3);
		} else if (tile == tiles[3]) {
			c = coord.translate(-3, -3);
		} else {
			System.out.println("Unknown tile for Coordinate translation.");
		}
		return c;
	}
	
	public Position getPositionAtCoord(Coordinate coord) {
		Tile tile = getTileAtCoord(coord);
		Coordinate c = translateCoordToTile(coord, tile);
		//System.out.println(coord + ":" + c);
		return tile.getPosition(c);
	}
	
	public List<Coordinate> getFreeCoordinates() {
		List<Coordinate> result = new ArrayList<Coordinate>();
		for (Coordinate c: getBoardCoordinates()) {
			if (getPositionAtCoord(c) == Position.EMPTY) {
				result.add(c);
			}
		}
		return result;
	}
	
	public Board copy() {
		Board result = new Board();
		result.setWinnerPosition(winnerPosition);
		result.isWon = isWon;
		for (int i=0;i<4;i++) {
			result.tiles[i] = tiles[i].copy();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Board)) {
			return false;
		}
		Board other = (Board) obj;
		for (int i=0;i<4;i++) {
			if (!tiles[i].equals(other.tiles[i])) {
				System.out.println("Tile Nr" + i);
				return false;
			}
		}
		return true;
	}

	public Position getWinnerPosition() {
		return winnerPosition;
	}

	private void setWinnerPosition(Position winnerPosition) {
		this.winnerPosition = winnerPosition;
	}

	public int getLongestChain(Position position) {
		int result = 0;
		for (Coordinate c: getBoardCoordinates()) {
			Position p = getPositionAtCoord(c);
			if (p == position) {
				// there are 8 possible ways to win from this position
				for (String direction : Coordinate.DIRECTIONS) {
					int count = 1;
					//failsafing an empty board
					result = Math.max(result, count);
					Coordinate nextC = c;
					while (count != 5) {
						try {
							nextC = nextC.getDirection(direction);
							if (getPositionAtCoord(nextC).equals(p)) {
								count++;
								result = Math.max(result, count);
							} else {
								break;
							}
						} catch (InvalidCoordinateException ex) {
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
	

}
