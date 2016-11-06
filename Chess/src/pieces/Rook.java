package pieces;

public class Rook extends Piece {

	public Rook(char BW){
		this.BW=BW;
		this.ID=" "+BW+"R"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
		
		int r=srcrow;
		int c=srccol;
		boolean isEmpty=true;
	   
		if((srcrow==destrow) || (srccol==destcol)){
			if(r<destrow && c==destcol){ //up
				r=r+1;
				for(;r<destrow; r++){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "entered first if loop. Invalid Move. Try Again.\n";
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
					moved=true;
					return null;
				}
			}

			if(isEmpty==true){ // went through all for loops looking for a not-empty spot and found none
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
				moved=true;
				return null;
			}
			
		}

			return "Invalid Move. Try Again.\n";
		

	}
		
		
	  
	

}