package pentago;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputPlayer extends AbstractPlayer {
	
	BufferedReader br;

	public ConsoleInputPlayer(String name) {
		super(name);
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public Move move(Board board) {
		System.out.println("Move required. White? " + isWhite());
		boolean successful = false;
		int x = -1;
		int y = -1;
		int tile = -1;
		Rotation rot = null;
		while (!successful) {
			try{
				System.out.println(board);
				System.out.print("x: ");
	            x = Integer.parseInt(br.readLine());
	            System.out.print("y: ");
	            y = Integer.parseInt(br.readLine());
	            System.out.print("Rotation Tile: ");
	            tile = Integer.parseInt(br.readLine());
	            if (!(0<=x && 0<=y && 0<= tile && 5>=x && 5>= y && 4 >= tile)) {
	            	System.out.println("Invalid Inputs");
	            	continue;
	            }
	            System.out.print("Clockwise[0] or Counter[1]?");
	            int temp = Integer.parseInt(br.readLine());
	            if (temp == 0 || temp == 1) {
	            	successful = true;
	            	rot = temp == 0 ? Rotation.CLOCKWISE : Rotation.COUNTER;
	            }
			} catch(NumberFormatException | IOException nfe){
	            System.err.println("Invalid Input!");
	        }
		}
		System.out.println("Summary");
		System.out.println("(" + x + "," + y + "). Rotate " + tile + rot);
		Move move = new Move();
		move.setPlayer(this);
		move.setCoords(new Coordinate(x,y));
		move.setRotationTile(tile);
		move.setRotation(rot);
		return move;
	}

}
