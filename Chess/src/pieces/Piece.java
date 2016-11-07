package pieces;

public abstract class Piece {
	public char BW;
	public String ID;
	public boolean moved=false;
	//public boolean inCheck=false;
	
	public abstract String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol);
}