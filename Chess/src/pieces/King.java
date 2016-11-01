package pieces;

public class King extends Piece {

	public King(char BW){
		this.BW=BW;
		this.ID=" "+BW+"K"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){ //is empty space, so move up, down, left or right one space?
		if(
			(srcrow-1==destrow && srccol==destcol) || (srcrow+1==destrow && srccol==destcol) ||
			(srcrow==destrow && srccol-1==destcol) || (srcrow==destrow && srccol+1==destcol) )
		{
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
		}

		return "Invalid Move. Try Again.\n";
	}
	}
