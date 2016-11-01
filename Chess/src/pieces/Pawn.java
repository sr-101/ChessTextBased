package pieces;

public class Pawn extends Piece{
	
	public Pawn(char BW){
		this.BW=BW;
		this.ID=" "+BW+"p"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
			if((BW=='w' && srcrow-1==destrow)||(BW=='b' && srcrow+1==destrow)){
				if(newboard[destrow][destcol] instanceof BoardNull && (srccol==destcol)){  
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					moved=true;
					return null;
				}
				else if(!(newboard[destrow][destcol] instanceof BoardNull) && (srccol+1==destcol||srccol-1==destcol)){  
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					moved=true;
					return null;
				}
			}
			else if(moved==false && ((srcrow-2==destrow && srccol==destcol) || (srcrow+2==destrow && srccol==destcol))){  
				if(BW=='w'){
					if(newboard[destrow][destcol] instanceof BoardNull && newboard[destrow+1][destcol] instanceof BoardNull){
						newboard[destrow][destcol]=newboard[srcrow][srccol];
						newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
						moved=true;
						return null;
					}
				}
				else if(BW=='b'){
					if(newboard[destrow][destcol] instanceof BoardNull && newboard[destrow-1][destcol] instanceof BoardNull){
						newboard[destrow][destcol]=newboard[srcrow][srccol];
						newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
						moved=true;
						return null;
					}
				}
			}
			
			return "Invalid Move. Try Again.\n";
	}
	
}