package pieces;

import java.util.Set;

public abstract class Piece {
	public char color;
	public String ID;
	public boolean moved=false;
	//public boolean inCheck=false;
	public int[] location={-1,-1};
	
	public abstract String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol);
	
	@Override
	public String toString() {
		return "Piece: "+ID+" at Row: "+location[0]+", Column: "+location[1];
	}
	
	public abstract Set<Move> getAllMoves(Piece[][] newboard);
}