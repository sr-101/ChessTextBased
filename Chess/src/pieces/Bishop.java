package pieces;

public class Bishop extends Piece {

	public Bishop(char BW){
		this.color=BW;
		this.ID=" "+BW+"B"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
		
	int v=Math.abs((destrow-srcrow));
	int h=Math.abs((destcol-srccol));
	boolean isEmpty=true;
  
    
	if(v==h){ //square
		
		if(srcrow>destrow && srccol<destcol){ // diagonal up to right
			int d=0; //check up right
			for(int r=srcrow+1;r<destrow;r++){
				int c=srccol;
				if(c+d<destcol){
					if(!(newboard[r][c+d] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}  
				}
				d++;
			}  
		} 

		if(srcrow>destrow && srccol>destcol){ // diagonal up to left
			int d=0; //check up right
			for(int r=srcrow+1;r<destrow;r++){
				int c=srccol;
				if(c+d<destcol){
					if(!(newboard[r][c-d] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}  
				}
				d++;
			}
		}
		
		if(srcrow<destrow && srccol<destcol){ // diagonal down to right
			int d=0; //check up right
			for(int r=srcrow-1;r>destrow;r--){
				int c=srccol;
				if(c+d<destcol){
					if(!(newboard[r][c+d] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}  
				}
				d++;
			}  
		} 

		if(srcrow<destrow && srccol>destcol){ // diagonal down to left
			int d=0; 
			for(int r=srcrow-1;r>destrow;r--){
				int c=srccol;
				if(c+d<destcol){
					if(!(newboard[r][c-d] instanceof BoardNull)){ // not empty at spot
						isEmpty=false;
						return "Invalid Move. Try Again.\n";
					}  
				}
				d++;
			}
		}
		
		if(!(newboard[destrow][destcol] instanceof BoardNull)){ //actual destination is not empty
			if((newboard[destrow][destcol]).color==color){ //piece in destination is same color as piece of player
				return "Invalid Move. Try Again.\n";
			} else if((newboard[destrow][destcol]).color!=color){ //piece in destination is different color 
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
		return "V not equal to H \n";
	}
	
	
  return null;
}
}