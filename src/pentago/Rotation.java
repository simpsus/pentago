package pentago;

public enum Rotation {
	
	CLOCKWISE, COUNTER;
	
	public Rotation opposite() {
		if (this == CLOCKWISE) {
			return COUNTER;
		}
		return CLOCKWISE;
	}
	
}
