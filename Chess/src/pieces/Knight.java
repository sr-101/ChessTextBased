package pieces;

public class Knight extends Piece {

	public Knight(char BW){
		this.BW=BW;
		this.ID=" "+BW+"N"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){       
		if( 
			(srcrow-1==destrow && srccol-2==destcol) || (srcrow-1==destrow && srccol+2==destcol) ||
			(srcrow-2==destrow && srccol-1==destcol) || (srcrow-2==destrow && srccol+1==destcol) ||
			(srcrow+1==destrow && srccol-2==destcol) || (srcrow+1==destrow && srccol+2==destcol) ||
			(srcrow+2==destrow && srccol-1==destcol) || (srcrow+2==destrow && srccol+1==destcol)
			) {
					
				if(!(newboard[destrow][destcol] instanceof BoardNull)){ //actual destination is not empty
					if((newboard[destrow][destcol]).BW==BW){ //piece in destination is same color as piece of player
						return "Invalid Move. Try Again.\n";
					} else if((newboard[destrow][destcol]).BW!=BW){ //piece in destination is different color 
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol); //kill!!!
					return null;
					}
				} else if(newboard[destrow][destcol] instanceof BoardNull){ //destination is empty
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol); //move!!
					return null;
				}
		}//end first if
			       
		

		return "Invalid Move. Try Again.\n";
	}
}

