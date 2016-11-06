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
				moved=true;
				return null;
				}
			} else if(newboard[destrow][destcol] instanceof BoardNull){ //destination is empty
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				newboard[srcrow][srccol]=new BoardNull(srcrow,srccol); //move!!
				moved=true;
				return null;
			}
		}
		else if(srcrow==destrow &&( (Math.abs(srccol-destcol)==2) || (Math.abs(srccol-destcol)==3)) ){ //castling move
			if(
				(chess.Board.castling==false) && //there has been no other instance of castling in the game - once per side or game?
				(moved==false) && //king has not moved
				(newboard[destrow][destcol].ID=="R") && //theres a Rook
				(newboard[destrow][destcol].BW==BW) && //Rook is same color as King we want to move
				(newboard[destrow][destcol].moved==false) && //Rook has also not moved 
				(newboard[srcrow][srccol].inCheck==false) && //king is not in check
				(newboard[destrow][destcol].inCheck==false) //destination is not in check
				
				) {
				int c=srccol;
				for(;c<destcol; c++){
					if((!(newboard[srcrow][c] instanceof BoardNull)) || (newboard[srcrow][c].inCheck==true)){ //there is a piece b/w king and rook or king will pass through a space that is in check
						return "Invalid Move. Try Again.\n";
					}
				}
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				newboard[srcrow][srccol]=newboard[destrow][destcol]; //????
				
			}
		}

		return "Invalid Move. Try Again.\n";
	}
	}
