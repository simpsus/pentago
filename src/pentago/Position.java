package pentago;

public enum Position {
	
	EMPTY(" "),
	BLACK("B"),
	WHITE("W");
	
	String repr;
	
	Position(String r) {
		repr = r;
	}
	
	public String toString() {
		return repr;
	}

}
