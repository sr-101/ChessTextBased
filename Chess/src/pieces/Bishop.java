package pieces;

public class Bishop extends Piece {

	public Bishop(char BW){
		this.BW=BW;
		this.ID=" "+BW+"B"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
		
	int v=Math.abs((destrow-srcrow));
	int h=Math.abs((destcol-srccol));
	int r=srcrow;
	int c=srccol;
	boolean isEmpty=true;
  
    
	if(v==h){ //square

		if(r<destrow && c<destcol){ // diagonal up to right
			r=r+1;
			c=c+1;
			for(;r<destrow; r++){
				for(;c<destcol; c++){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}  
				}
			}  
		} 

		if(r<destrow && c>destcol){ // diagonal up to left
			r=r+1;
			c=c-1;
			for(;r<destrow; r++){
				for(;c>destcol; c--){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					} 
				}
			}  
		}
		
		if(r>destrow && c<destcol){ //diagonal down to right
			r=r-1;
			c=c+1;
			for(;r>destrow; r--){
				for(;c<destcol; c++){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}  
				}
			}  
		}

		if(r>destrow && c>destcol){ //diagonal down to left
			r=r-1;
			c=c-1;
			for(;r>destrow; r--){
				for(;c>destcol; c--){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}
				}
			}  
		}
		
		if(!(newboard[destrow][destcol] instanceof BoardNull)){ //actual destination is not empty
			if((newboard[destrow][destcol]).BW==BW){ //piece in destination is same color as piece of player
				return "Invalid Move. Try Again.\n";
			} else if((newboard[destrow][destcol]).BW!=BW){ //piece in destination is different color 
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
				return null;
			}
		}

		if(isEmpty==true){ // went through all for loops looking for a not-empty spot and found none
			newboard[destrow][destcol]=newboard[srcrow][srccol];
			newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
			return null;
		}
	
	} 

	else if(v!=h){
		return "Invalid Move. Try Again.\n";
	}
	
	
  return null;
}
}