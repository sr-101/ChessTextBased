package pieces;

public class King extends Piece {

	public King(char BW){
		this.BW=BW;
		this.ID=" "+BW+"K"+" ";
	}
	
	public boolean isInCheck(Piece[][] newboard, int srcrow, int srccol){
		boolean isInCheck=false;
		
        //isInCheck by Bishop or Queen?
		for(int r=srcrow+1; r<9; r++){ //bishop up to right
        	for (int c=srccol+1; c<9; c++){
        		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].BW!=BW) && ( (newboard[r][c].ID=="B") || (newboard[r][c].ID=="Q"))){ //there is nothing between a bishop of another color and the king
        			isInCheck=true;
        		}
        	}
        }
        
        for(int r=srcrow+1; r<9; r++){ //bishop up to left 
        	for (int c=srccol-1; c>0; c--){
        		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].BW!=BW) && ( (newboard[r][c].ID=="B") || (newboard[r][c].ID=="Q"))){ //there is nothing between a bishop of another color and the king
        			isInCheck=true;
        		}
        	}
        }
        
        for(int r=srcrow-1; r>0; r--){ //bishop down to right 
        	for (int c=srccol+1; c<9; c++){
        		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].BW!=BW) && ( (newboard[r][c].ID=="B") || (newboard[r][c].ID=="Q"))){ //there is nothing between a bishop of another color and the king
        			isInCheck=true;
        		}
        	}
        }
        
        for(int r=srcrow-1; r>0; r--){ //bishop down to left 
        	for (int c=srccol-1; c>0; c--){
        		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].BW!=BW) && ( (newboard[r][c].ID=="B") || (newboard[r][c].ID=="Q"))){ //there is nothing between a bishop of another color and the king
        			isInCheck=true;
        		}
        	}
        }
        
        //isInCheck by Rook or Queen?
        for(int r=srcrow+1; r<9; r++){ //up
        	if((!(newboard[r][srccol] instanceof BoardNull)) && (newboard[r][srccol].BW!=BW) && ( (newboard[r][srccol].ID=="R") || (newboard[r][srccol].ID=="Q"))){ //there is nothing between a rook of another color and the king
    			isInCheck=true;
        	}
        }
        
        for(int r=srcrow-1; r>0; r--){ //down
        	if((!(newboard[r][srccol] instanceof BoardNull)) && (newboard[r][srccol].BW!=BW) && ( (newboard[r][srccol].ID=="R") || (newboard[r][srccol].ID=="Q"))){ //there is nothing between a rook of another color and the king
    			isInCheck=true;
        	}
        }
        
        for(int c=srccol+1; c<9; c++){ //right
        	if((!(newboard[srcrow][c] instanceof BoardNull)) && (newboard[srcrow][c].BW!=BW) && ( (newboard[srcrow][c].ID=="R") || (newboard[srcrow][c].ID=="Q"))){ //there is nothing between a rook of another color and the king
    			isInCheck=true;
        	}
        }
		
        for(int c=srccol-1; c>0; c--){ //left
        	if((!(newboard[srcrow][c] instanceof BoardNull)) && (newboard[srcrow][c].BW!=BW) && ( (newboard[srcrow][c].ID=="R") || (newboard[srcrow][c].ID=="Q"))){ //there is nothing between a rook of another color and the king
    			isInCheck=true;
        	}
        }
        
        //isInCheck by Pawn?
        if(BW=='w'){ //king is white so only check up left and right
             if((!(newboard[srcrow+1][srccol+1] instanceof BoardNull)) && (newboard[srcrow+1][srccol+1].BW!=BW) &&  (newboard[srcrow+1][srccol+1].ID=="P") ){ //there is nothing between a pawn of another color and the king
         			isInCheck=true;	
             	}
             if((!(newboard[srcrow+1][srccol-1] instanceof BoardNull)) && (newboard[srcrow+1][srccol-1].BW!=BW) &&  (newboard[srcrow+1][srccol-1].ID=="P") ){ //there is nothing between a pawn of another color and the king
      			isInCheck=true;	
          	} 
        }
        
        if(BW=='b'){
        	if((!(newboard[srcrow-1][srccol+1] instanceof BoardNull)) && (newboard[srcrow-1][srccol+1].BW!=BW) &&  (newboard[srcrow-1][srccol+1].ID=="P") ){ //there is nothing between a pawn of another color and the king
     			isInCheck=true;	
         	}
        	
        	if((!(newboard[srcrow-1][srccol-1] instanceof BoardNull)) && (newboard[srcrow-1][srccol-1].BW!=BW) &&  (newboard[srcrow-1][srccol-1].ID=="P") ){ //there is nothing between a pawn of another color and the king
     			isInCheck=true;	
         	}
        }
       
        //isInCheck by King?
        //right
        if((!(newboard[srcrow][srccol+1] instanceof BoardNull)) && (newboard[srcrow][srccol+1].BW!=BW) &&  (newboard[srcrow][srccol+1].ID=="K") ){ //there is nothing between a king of another color and the king
 			isInCheck=true;	
     	}
        //left
        if((!(newboard[srcrow][srccol-1] instanceof BoardNull)) && (newboard[srcrow][srccol-1].BW!=BW) &&  (newboard[srcrow][srccol-1].ID=="K") ){ //there is nothing between a king of another color and the king
 			isInCheck=true;	
     	}
        //up
        if((!(newboard[srcrow+1][srccol] instanceof BoardNull)) && (newboard[srcrow+1][srccol].BW!=BW) &&  (newboard[srcrow+1][srccol].ID=="K") ){ //there is nothing between a king of another color and the king
 			isInCheck=true;	
     	}
        //down
        if((!(newboard[srcrow-1][srccol] instanceof BoardNull)) && (newboard[srcrow-1][srccol].BW!=BW) &&  (newboard[srcrow-1][srccol].ID=="K") ){ //there is nothing between a king of another color and the king
 			isInCheck=true;	
     	}
        
        //isInCheck by Knight?
        if((!(newboard[srcrow-1][srccol-2] instanceof BoardNull)) && (newboard[srcrow-1][srccol-2].BW!=BW) &&  (newboard[srcrow-1][srccol-2].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow-1][srccol+2] instanceof BoardNull)) && (newboard[srcrow-1][srccol+2].BW!=BW) &&  (newboard[srcrow-1][srccol+2].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow-2][srccol-1] instanceof BoardNull)) && (newboard[srcrow-2][srccol-1].BW!=BW) &&  (newboard[srcrow-2][srccol-1].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow-2][srccol+1] instanceof BoardNull)) && (newboard[srcrow-2][srccol+1].BW!=BW) &&  (newboard[srcrow-2][srccol+1].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow+1][srccol-2] instanceof BoardNull)) && (newboard[srcrow+1][srccol-2].BW!=BW) &&  (newboard[srcrow+1][srccol-2].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow+1][srccol+2] instanceof BoardNull)) && (newboard[srcrow+1][srccol+2].BW!=BW) &&  (newboard[srcrow+1][srccol+2].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow+2][srccol-1] instanceof BoardNull)) && (newboard[srcrow+2][srccol-1].BW!=BW) &&  (newboard[srcrow+2][srccol-1].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        if((!(newboard[srcrow+2][srccol+1] instanceof BoardNull)) && (newboard[srcrow+2][srccol+1].BW!=BW) &&  (newboard[srcrow+2][srccol+1].ID=="N") ){ //there is nothing between a knight of another color and the king
 			isInCheck=true;	
     	}
        
        
		return isInCheck;
	}
	
	//for checkmate - the places around the king are also in check, and the piece(s) that put the king in check are not in check
	//and no piece of same color can come between king and piece in check
	
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
				(newboard[destrow][destcol].ID=="R") && //there's a Rook
				(newboard[destrow][destcol].BW==BW) && //Rook is same color as King we want to move
				(newboard[destrow][destcol].moved==false) && //Rook has also not moved 
				(isInCheck(newboard,srcrow,srccol)==false) && //king is not in check
				(isInCheck(newboard,srcrow,srccol)==false) //destination is not in check
				
				) {
				int c=srccol;
				for(;c<destcol; c++){
					if((!(newboard[srcrow][c] instanceof BoardNull)) || (isInCheck(newboard,srcrow,c)==true)){ //there is a piece b/w king and rook or king will pass through a space that is in check
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
