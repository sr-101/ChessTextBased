package pieces;

import java.util.LinkedHashSet;
import java.util.Set;

public class Knight extends Piece {
	Set<Move> moves=new LinkedHashSet<Move>();

	public Knight(char BW){
		this.color=BW;
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
					if((newboard[destrow][destcol]).color==color){ //piece in destination is same color as piece of player
						return "Invalid Move. Try Again.\n";
					} else if((newboard[destrow][destcol]).color!=color){ //piece in destination is different color 
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

	@Override
	public Set<Move> getAllMoves(Piece[][] newboard) {
		int srcrow=location[0];
		int srccol=location[1];
		int r=srcrow; int c=srccol;
		if(r+2<=7 && c-1>=0){
			//System.out.println("Down Left: "+newboard[r+2][c-1]);
			if((!(newboard[r+2][c-1] instanceof BoardNull) && (newboard[r+2][c-1].color!=color))||(newboard[r+2][c-1] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r+2,c-1));
			}
		}
		if(r+2<=7 && c+1<=7){
			//System.out.println("Down Right: "+newboard[r+2][c+1]);
			if((!(newboard[r+2][c+1] instanceof BoardNull) && (newboard[r+2][c+1].color!=color))||(newboard[r+2][c+1] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r+2,c+1));
			}
		}
		if(r-2>=0 && c-1>=0){
			//System.out.println("Up Left: "+newboard[r-2][c-1]);
			if((!(newboard[r-2][c-1] instanceof BoardNull) && (newboard[r-2][c-1].color!=color))||(newboard[r-2][c-1] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r-2,c-1));
			}		
		}
		if(r-2>=0 && c+1<=7){
			//System.out.println("Up Right: "+newboard[r-2][c+1]);
			if((!(newboard[r-2][c+1] instanceof BoardNull) && (newboard[r-2][c+1].color!=color))||(newboard[r-2][c+1] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r-2,c+1));
			}
		}
		if(r+1<=7 && c-2>=0){
			//System.out.println("Left Down: "+newboard[r+1][c-2]);
			if((!(newboard[r+1][c-2] instanceof BoardNull) && (newboard[r+1][c-2].color!=color))||(newboard[r+1][c-2] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r+1,c-2));
			}
		}
		if(r+1<=7 && c+2<=7){ 
			//System.out.println("Right Down: "+newboard[r+1][c+2]);
			if((!(newboard[r+1][c+2] instanceof BoardNull) && (newboard[r+1][c+2].color!=color))||(newboard[r+1][c+2] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r+1,c+2));
			}
		}
		if(r-1>=0 && c-2>=0){
			//System.out.println("Left Up: "+newboard[r-1][c-2]);
			if((!(newboard[r-1][c-2] instanceof BoardNull) && (newboard[r-1][c-2].color!=color))||(newboard[r-1][c-2] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r-1,c-2));
			}	
		}
		if(r-1>=0 && c+2<=7){
			//System.out.println("Right Up: "+newboard[r-1][c+2]);
			if((!(newboard[r-1][c+2] instanceof BoardNull) && (newboard[r-1][c+2].color!=color))||(newboard[r-1][c+2] instanceof BoardNull)){ //there is a knight there
				moves.add(new Move(location, ID, r-1,c+2));
			}
		}
		return moves;
	}
}

