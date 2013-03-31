package pentago;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chain implements Iterable<Coordinate>, Comparable<Chain>{
	
	List<Coordinate> coords;
	Position position;
	Direction direction;

	public Chain(Position pos, Direction direction) {
		coords = new ArrayList<Coordinate>();
		position = pos;
		this.direction = direction;
	}
	
	public void add(Coordinate c) {
		coords.add(c);
	}
	
	public int getSize() {
		return coords.size();
	}
	
	public Direction getDirection() {
		return direction;
	}

	@Override
	public Iterator<Coordinate> iterator() {
		return coords.iterator();
	}

	@Override
	public int compareTo(Chain arg0) {
		return (new Integer(getSize()).compareTo(arg0.getSize())) * (-1);
	}

	public List<Coordinate> getCoordinates() {
		return coords;
	}
	
	public String toString() {
		return position + "/" + direction + ":" + coords;
	}

}
