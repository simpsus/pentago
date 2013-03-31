package pentago;

public enum Direction {
	
	L,R,U,D,LU,LD,RU,RD;
	
	public Direction opposite() {
		if (this == L) return R;
		if (this == R) return L;
		if (this == U) return D;
		if (this == D) return U;
		if (this == LU) return RD;
		if (this == LD) return RU;
		if (this == RU) return LD;
		if (this == RD) return LU;
		throw new RuntimeException("Unknown Direction " + this);
	}
	
	public Coordinate walk(Coordinate c) throws InvalidCoordinateException {
		if (this == L) return c.left();
		if (this == R) return c.right();
		if (this == U) return c.up();
		if (this == D) return c.down();
		if (this == LU) return c.leftUp();
		if (this == LD) return c.leftDown();
		if (this == RU) return c.rightUp();
		if (this == RD) return c.rightDown();
		throw new RuntimeException("Unknown Direction " + this);
	}

}
