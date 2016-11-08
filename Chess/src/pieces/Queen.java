package pieces;

public class Queen extends Piece {

	public Queen(char BW){
		this.color=BW;
		this.ID=" "+BW+"Q"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
		int v=Math.abs((destrow-srcrow));
		int h=Math.abs((destcol-srccol));
		int r=srcrow;
		int c=srccol;
		boolean isEmpty=true;
		
		if((srcrow==destrow) || (srccol==destcol)){ //moves like rook
			if(r<destrow && c==destcol){ //up
				r=r+1;
				for(;r<destrow; r++){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
					} 
				} 
			} 

			if(r>destrow && c==destcol){ //down
				r=r-1;
				for(;r>destrow; r--){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
					} 
				} 
			}

			if(r==destrow && c>destcol){ //left
				c=c-1;
				for(;c>destcol; c--){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
					} 
				} 
			}

			if(r==destrow && c<destcol){ //right
				c=c+1;
				for(;c<destcol; c++){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
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
		else if(v==h){ //moves like bishop
			
			if(srcrow<destrow && srccol<destcol){ // diagonal up to right
				int d=0; //check up right
				for(int row=srcrow+1;row<destrow;row++){
					int col=srccol;
					if(col+d<destcol){
						if(!(newboard[row][col+d] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
						}  
					}
					d++;
				}  
			} 

			if(srcrow<destrow && srccol>destcol){ // diagonal up to left
				int d=0; //check up right
				for(int row=srcrow+1;row<destrow;row++){
					int col=srccol;
					if(col+d<destcol){
						if(!(newboard[row][col-d] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
						}  
					}
					d++;
				}
			}
			
			if(srcrow>destrow && srccol<destcol){ // diagonal down to right
				int d=0; //check up right
				for(int row=srcrow-1;row>destrow;row--){
					int col=srccol;
					if(col+d<destcol){
						if(!(newboard[row][col+d] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
						}  
					}
					d++;
				}  
			} 

			if(srcrow>destrow && srccol>destcol){ // diagonal down to left
				int d=0; 
				for(int row=srcrow-1;row>destrow;row--){
					int col=srccol;
					if(col+d<destcol){
						if(!(newboard[row][col-d] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "Invalid Move. Try Again.\n";
						}  
					}
					d++;
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
		
		return "Invalid Move. Try Again.\n";
	}
}