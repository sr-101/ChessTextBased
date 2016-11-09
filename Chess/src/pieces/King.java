package pieces;

import java.util.ArrayList;

import chess.Board;

public class King extends Piece {

	public King(char color){
		this.color=color;
		this.ID=" "+color+"K"+" ";
	}
	
	
	
	//for checkmate - the places around the king are also in check, and the piece(s) that put the king in check are not in check
	//and no piece of same color can come between king and piece in check
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){ //is empty space, so move up, down, left or right one space?
		if(
			(srcrow-1==destrow && srccol==destcol) || (srcrow+1==destrow && srccol==destcol) ||
			(srcrow==destrow && srccol-1==destcol) || (srcrow==destrow && srccol+1==destcol) )
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
		}
		else if(srcrow==destrow && ((Math.abs(srccol-destcol)==2) || (Math.abs(srccol-destcol)==3))){ //castling move
			if(
				(chess.Board.castling==false) && //there has been no other instance of castling in the game - once per side or game?
				(moved==false) && //king has not moved
				(newboard[destrow][destcol].ID.contains("R")) && //there's a Rook
				(newboard[destrow][destcol].color==color) && //Rook is same color as King we want to move
				(newboard[destrow][destcol].moved==false) && //Rook has also not moved 
				(Board.getKingBoolean(color)) && //king is not in check
				(Board.getKingBoolean(newboard[destrow][destcol].color)) //destination is not in check
				) 
			{
				int c=srccol;
				for(;c<destcol; c++){
					if((!(newboard[srcrow][c] instanceof BoardNull)) || !(Board.getKingBoolean(newboard[srcrow][c].color))){ //there is a piece b/w king and rook or king will pass through a space that is in check
						return "Invalid Move. Try Again.\n";
					}
				}
				newboard[destrow][destcol]=newboard[srcrow][srccol];
				Piece temp=newboard[destrow][destcol];
				newboard[srcrow][srccol]=temp;
				
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
		}

		return "Invalid Move. Try Again.\n";
	}
	}
