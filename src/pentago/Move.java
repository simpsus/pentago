package pentago;

public class Move {

	Coordinate coords;
	Integer rotationTile;
	Rotation rotation;
	Player player;
	
	public Coordinate getCoords() {
		return coords;
	}
	public void setCoords(Coordinate coords) {
		this.coords = coords;
	}
	public Integer getRotationTile() {
		return rotationTile;
	}
	public void setRotationTile(Integer rotationTile) {
		this.rotationTile = rotationTile;
	}
	public Rotation getRotation() {
		return rotation;
	}
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String toString() {
		return player + ": " + coords + ", " + rotationTile + "(" + rotation + ")";
	}
	
}
