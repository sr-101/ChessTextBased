package pieces;

import java.util.LinkedHashSet;
import java.util.Set;

public class Queen extends Piece {

	Set<Move> moves=new LinkedHashSet<Move>();
	public Queen(char color){
		this.color=color;
		this.ID=" "+color+"Q"+" ";
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
		else if(v==h){ //moves like bishop
			
			if(srcrow>destrow && srccol<destcol){ // diagonal up to right
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

			if(srcrow>destrow && srccol>destcol){ // diagonal up to left
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
			
			if(srcrow<destrow && srccol<destcol){ // diagonal down to right
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

			if(srcrow<destrow && srccol>destcol){ // diagonal down to left
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
		
		return "Invalid Move. Try Again.\n";
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
