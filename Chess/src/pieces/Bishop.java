package pieces;

import java.util.LinkedHashSet;
import java.util.Set;

public class Bishop extends Piece {
	Set<Move> moves=new LinkedHashSet<Move>();

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
		return "Invalid Move. Try Again.\n";
	}
	
	
  return null;
}

	@Override
	public Set<Move> getAllMoves(Piece[][] newboard) {
		int d=1;
		int e=1;
		int srcrow=location[0];
		int srccol=location[1];
		
		for(int r=srcrow+1; r<8; r++){ 
			int c=srccol;
        	if(c+d<=7){
        		//System.out.println("Bottom Right: "+newboard[r][c+d]);
        		if(newboard[r][c+d] instanceof BoardNull){
        			moves.add(new Move(location, ID, r,c+d));
        		}
        		else if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color!=color)){
        			moves.add(new Move(location, ID, r,c+d));
        			break;
        		}
        		else if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color==color)){
        			break;
        		}
        		d++;
        	}
		}
		for(int r=srcrow+1; r<8; r++){ 
			int c=srccol;
        	if(c-e>=0){
        		//System.out.println("Bottom Left: "+newboard[r][c-e]);
        		if(newboard[r][c-e] instanceof BoardNull){
        			moves.add(new Move(location, ID, r,c-e));
        		}
        		else if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color!=color)){
        			moves.add(new Move(location, ID, r,c-e));
        			break;
        		}
        		else if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color==color)){
        			break;
        		}
        		e++;
        	}
        }
		d=1;
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
			if(c+d<=7){
        		//System.out.println("Top Right: "+newboard[r][c+d]);
				if(newboard[r][c+d] instanceof BoardNull){
        			moves.add(new Move(location, ID, r,c+d));
        		}
        		else if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color!=color)){
        			moves.add(new Move(location, ID, r,c+d));
        			break;
        		}
        		else if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color==color)){
        			break;
        		}
        		d++;
        	}
		}
		e=1;
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
        	if(c-e>=0){
        		//System.out.println("Top Left: "+newboard[r][c-e]);
        		if(newboard[r][c-e] instanceof BoardNull){
        			moves.add(new Move(location, ID, r,c-e));
        		}
        		else if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color!=color)){
        			moves.add(new Move(location, ID, r,c-e));
        			break;
        		}
        		else if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color==color)){
        			break;
        		}
        		e++;
        	}
        }
		return moves;
	}
}
