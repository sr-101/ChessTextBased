package pieces;

import java.util.LinkedHashSet;
import java.util.Set;

public class Rook extends Piece {
	Set<Move> moves=new LinkedHashSet<Move>();
	
	public Rook(char BW){
		this.color=BW;
		this.ID=" "+BW+"R"+" ";
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
		
		int r=srcrow;
		int c=srccol;
		boolean isEmpty=true;
	   
		if((srcrow==destrow) || (srccol==destcol)){
			if(r<destrow && c==destcol){ //down
				r=r+1;
				for(;r<destrow; r++){
					if(!(newboard[r][c] instanceof BoardNull)){ // not empty at spot
							isEmpty=false;
							return "entered first if loop. Invalid Move. Try Again.\n";
					} 
				} 
			} 

			if(r>destrow && c==destcol){ //up
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
				if((newboard[destrow][destcol]).color==color){ //piece in destination is same color as piece of player
					return "Invalid Move. Try Again.\n";
				} else if((newboard[destrow][destcol]).color!=color){ //piece in destination is different color 
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

	@Override
	public Set<Move> getAllMoves(Piece[][] newboard) {
		int srcrow=location[0];
		int srccol=location[1];
		
		for(int c=srccol+1; c<8; c++){ 
			int r=srcrow;
    		//System.out.println("Right: "+newboard[r][c]);
    		if(newboard[r][c] instanceof BoardNull){
    			moves.add(new Move(location, ID, r,c));
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=color)){
    			moves.add(new Move(location, ID, r,c));
    			break;
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==color)){
    			break;
    		}     	
		}
		
		for(int c=srccol-1; c>=0; c--){ 
			int r=srcrow;
    		//System.out.println("Left: "+newboard[r][c]);
    		if(newboard[r][c] instanceof BoardNull){
    			moves.add(new Move(location, ID, r,c));
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=color)){
    			moves.add(new Move(location, ID, r,c));
    			break;
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==color)){
    			break;
    		}     	
		}
		
		for(int r=srcrow+1; r<8; r++){ 
			int c=srccol;
    		//System.out.println("Down: "+newboard[r][c]);
    		if(newboard[r][c] instanceof BoardNull){
    			moves.add(new Move(location, ID, r,c));
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=color)){
    			moves.add(new Move(location, ID, r,c));
    			break;
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==color)){
    			break;
    		}        	
		}
		
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
    		//System.out.println("Up: "+newboard[r][c]);
    		if(newboard[r][c] instanceof BoardNull){
    			moves.add(new Move(location, ID, r,c));
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=color)){
    			moves.add(new Move(location, ID, r,c));
    			break;
    		}
    		else if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==color)){
    			break;
    		}
		}
		return moves;
	}
}