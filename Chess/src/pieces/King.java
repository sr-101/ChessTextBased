package pieces;

import java.util.LinkedHashSet;
import java.util.Set;

import chess.Board;

public class King extends Piece {
	Set<Move> allmoves=new LinkedHashSet<Move>();

	public King(char color){
		this.color=color;
		this.ID=" "+color+"K"+" ";
	}
		
	//for checkmate - the places around the king are also in check, and the piece(s) that put the king in check are not in check
	//and no piece of same color can come between king and piece in check
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){ //is empty space, so move up, down, left or right one space?
		if(
			(srcrow-1==destrow && srccol==destcol) || (srcrow+1==destrow && srccol==destcol) ||
			(srcrow==destrow && srccol-1==destcol) || (srcrow==destrow && srccol+1==destcol) ||
			(srcrow-1==destrow && srccol-1==destcol) || (srcrow-1==destrow && srccol+1==destcol) ||
			(srcrow+1==destrow && srccol-1==destcol) || (srcrow+1==destrow && srccol+1==destcol))
		{
			if(!(newboard[destrow][destcol] instanceof BoardNull)){ //actual destination is not empty
				if((newboard[destrow][destcol]).color==color){ //piece in destination is same color as piece of player
					return "Invalid Move. Try Again.\n";
				} else if((newboard[destrow][destcol]).color!=color){ //piece in destination is different color 
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				newboard[srcrow][srccol]=new BoardNull(srcrow,srccol); //kill!!!
				moved=true;
				if(color=='b'){
					Board.bKing[0]=destrow;
					Board.bKing[1]=destcol;
				}
				else if(color=='w'){
					Board.wKing[0]=destrow;
					Board.wKing[1]=destcol;
				}
				return null;
				}
			} else if(newboard[destrow][destcol] instanceof BoardNull){ //destination is empty
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				newboard[srcrow][srccol]=new BoardNull(srcrow,srccol); //move!!
				moved=true;
				if(color=='b'){
					Board.bKing[0]=destrow;
					Board.bKing[1]=destcol;
				}
				else if(color=='w'){
					Board.wKing[0]=destrow;
					Board.wKing[1]=destcol;
				}
				return null;
			}
		
		}// moving 1 ended
			else if(srcrow==destrow && (Math.abs(srccol-destcol)==2) ){ //castling move
			
			if(color=='w'){
				if((chess.Board.castlingw==false) && (moved==false) ){ //king has not moved and white had no castling
					
					if(srccol+2==destcol){ //moving right
						if(
							(newboard[destrow][destcol+1] instanceof Rook) &&
							(newboard[destrow][destcol+1].color==color) &&
							(newboard[destrow][destcol+1].moved==false)  
								){
							
							int c=srccol+1;
							for(;c<destcol; c++){
								if((!(newboard[srcrow][c] instanceof BoardNull))){ //there is a piece b/w king and rook or king will pass through a space that is in check
									return "Invalid castling Move. Try Again.\n";
								}
							}
							newboard[destrow][destcol]=newboard[srcrow][srccol];
							newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
							newboard[destrow][destcol-1]=newboard[srcrow][destcol+1];
							newboard[srcrow][destcol+1]=new BoardNull(srcrow,srccol+1);
							chess.Board.castlingw=true;
							moved=true; //do i have to mark moved true for rook too?
							return null;
							
						}//end if
						return "You went into moving right loop";
					} //end move right
					
					else if(srccol-2==destcol){//moving left
						if(
								(newboard[destrow][destcol-2].ID.contains("R")) &&
								(newboard[destrow][destcol-2].color==color) &&
								(newboard[destrow][destcol-2].moved==false)
									){
							
								int c=srccol-1;
								for(;c>destcol; c--){
									if((!(newboard[srcrow][c] instanceof BoardNull))){ //there is a piece b/w king and rook or king will pass through a space that is in check
										return "Invalid castling Move. Try Again.\n";
									}
								}
								newboard[destrow][destcol]=newboard[srcrow][srccol];
								newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
								newboard[destrow][destcol+1]=newboard[srcrow][destcol-2];
								newboard[srcrow][destcol-2]=new BoardNull(srcrow,srccol-2);
								chess.Board.castlingw=true;
								moved=true; //do i have to mark moved true for rook too?
								return null;
								
							}
						}
				}
				}
			
			if(color=='b'){
				if((chess.Board.castlingb==false) && (moved==false) ){ //king has not moved and white had no castling
					
					if(srccol+2==destcol){ //moving right
						if(
							(newboard[destrow][destcol+1].ID.contains("R")) &&
							(newboard[destrow][destcol+1].color==color) &&
							(newboard[destrow][destcol+1].moved==false)
								){
							
							int c=srccol+1;
							for(;c<destcol; c++){
								if((!(newboard[srcrow][c] instanceof BoardNull))){ //there is a piece b/w king and rook or king will pass through a space that is in check
									return "Invalid Move. Try Again.\n";
								}
							}
							newboard[destrow][destcol]=newboard[srcrow][srccol];
							newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
							newboard[destrow][destcol-1]=newboard[srcrow][destcol+1];
							newboard[srcrow][destcol+1]=new BoardNull(srcrow,srccol+1);
							chess.Board.castlingw=true;
							moved=true; //do i have to mark moved true for rook too?
							return null;
							
						}
					}
					
					else if(srccol-2==destcol){//moving left
						if(
								(newboard[destrow][destcol-2].ID.contains("R")) &&
								(newboard[destrow][destcol-2].color==color) &&
								(newboard[destrow][destcol-2].moved==false)
									){
								
								int c=srccol-1;
								for(;c>destcol; c--){
									if((!(newboard[srcrow][c] instanceof BoardNull))){ //there is a piece b/w king and rook or king will pass through a space that is in check
										return "Invalid Move. Try Again.\n";
									}
								}
								newboard[destrow][destcol]=newboard[srcrow][srccol];
								newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
								newboard[destrow][destcol+1]=newboard[srcrow][destcol-2];
								newboard[srcrow][destcol-2]=new BoardNull(srcrow,srccol-2);
								chess.Board.castlingw=true;
								moved=true; //do i have to mark moved true for rook too?
								return null;
								
							} //end if
					}//end moving left
		} //end if boolean false
	} //end if black
	} //end castling
		return "Invalid Move. Try Again\n";
	}//end move method

	@Override
	public Set<Move> getAllMoves(Piece[][] newboard) {
		for(int i=0;i<8;i++){
			int[] kingloc=location;
			int[] temploc = {0,0};
			switch (i){
				case 0:
					//Up
					if(kingloc[0]-1>=0){
						temploc[0]=kingloc[0]-1;
						temploc[1]=kingloc[1];
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nUp");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 1:
					//Down
					if(kingloc[0]+1<=7){
						temploc[0]=kingloc[0]+1;
						temploc[1]=kingloc[1];
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nDown");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 2:
					//Left
					if(kingloc[1]-1>=0){
						temploc[0]=kingloc[0];
						temploc[1]=kingloc[1]-1;
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nLeft");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 3:
					//Right
					if(kingloc[1]+1<=7){
						temploc[0]=kingloc[0];
						temploc[1]=kingloc[1]+1;
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nRight");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 4:
					//Up Left
					if(kingloc[0]-1>=0 && kingloc[1]-1>=0){
						temploc[0]=kingloc[0]-1;
						temploc[1]=kingloc[1]-1;
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nUp Left");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 5:
					//Up Right
					if(kingloc[0]-1>=0 && kingloc[1]+1<=7){
						temploc[0]=kingloc[0]-1;
						temploc[1]=kingloc[1]+1;
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nUp Right");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 6:
					//Down Left
					if(kingloc[0]+1<=7 && kingloc[1]-1>=0){
						temploc[0]=kingloc[0]+1;
						temploc[1]=kingloc[1]-1;
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nDown Left");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
				case 7:
					//Down Right
					if(kingloc[0]+1<=7 && kingloc[1]+1<=7){
						temploc[0]=kingloc[0]+1;
						temploc[1]=kingloc[1]+1;
						//System.out.println(temploc[0]+" "+temploc[1]);
						if(newboard[temploc[0]][temploc[1]] instanceof BoardNull){
							//System.out.println("\nDown Right");
							allmoves.add(new Move(location, ID, temploc[0],temploc[1]));
						}
					}
					break;
			}
		}
		//System.out.println("All King Moves: "+allmoves);
		return allmoves;
	}
}
