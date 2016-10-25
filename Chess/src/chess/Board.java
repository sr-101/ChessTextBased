package chess;

import pieces.Bishop;
import pieces.BoardNull;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {

	Piece[][] newboard=new Piece[9][9];
	boolean checkmate=false;
	boolean stalemate=false;
	char turnBorW='w';

	public Board(){
		for(int i=0;i<=8;i++){
			for(int j=0;j<=8;j++){
				if(j==8 && i<8){
					newboard[i][j]=new BoardNull(i,j);
				}
				else if(i==8 && j<8){
					newboard[i][j]=new BoardNull(i,j);
				}
				else if(j!=8 && i!=8 && i>=2 && i<=5 && (j+i)%2!=0){
					newboard[i][j]=new BoardNull(i,j);
				}
				else if(i==1 && j<8){
					newboard[i][j]=new Pawn('b');
				}
				else if(i==6 && j<8){
					newboard[i][j]=new Pawn('w');
				}
				else{
					newboard[i][j]=new BoardNull(i,j);
				}
			}
		}
		for(int j=0;j<8;j++){
			switch(j){
			case 0:
			case 7:
				newboard[0][j]=new Rook('b');
				break;
			case 1:
			case 6:
				newboard[0][j]=new Knight('b');
				break;
			case 2:
			case 5:
				newboard[0][j]=new Bishop('b');
				break;
			case 3:
				newboard[0][j]=new Queen('b');
				break;
			case 4:
				newboard[0][j]=new King('b');
				break;
			}
		}
		for(int j=0;j<8;j++){
			switch(j){
			case 0:
			case 7:
				newboard[7][j]=new Rook('w');
				break;
			case 1:
			case 6:
				newboard[7][j]=new Knight('w');
				break;
			case 2:
			case 5:
				newboard[7][j]=new Bishop('w');
				break;
			case 3:
				newboard[7][j]=new King('w');
				break;
			case 4:
				newboard[7][j]=new Queen('w');
				break;
			}
		}
		printBoard();
	}
	
	public void printBoard(){
		System.out.print("\n");
		for(int i=0;i<=8;i++){
			for(int j=0;j<=8;j++){
				System.out.print(newboard[i][j].ID);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		
	}
	
	public int move(String src, String dest){
		int success=0;
		int srcrow=8-(src.charAt(1)-'0');
		int srccol=src.charAt(0)-97;
		int destrow=8-(dest.charAt(1)-'0');
		int destcol=dest.charAt(0)-97;
		if((srcrow>=0 && srcrow<=8) && (destrow>=0 && destrow<=8) && (srccol>=0 && srccol<=8) && (destcol>=0 && destcol<=8)){
			Piece source=newboard[srcrow][srccol];
			if(source.BW==turnBorW && source.BW!='X'){
				//System.out.println(source.ID);
				//Piece destination=newboard[destrow][destcol];
				//System.out.println(destination.ID);
				String s=source.move(newboard, srcrow, srccol, destrow, destcol);
			
				if(s==null){
					success=0;
					printBoard();
				}
				else{
					success=-1;
					printBoard();
					System.out.println(s);
				}
			}
			else{
				success=-2;
				printBoard();
				if(source.BW=='X'){
					System.out.println("You selected an empty space. Try again.\n");
				}
				else{
					System.out.println("You selected the opponent's piece. Try again.\n");
				}
			}
		}
		else{
			success=-3;
			printBoard();
			System.out.println("Selection is off the board. Try again.");
		}
		return success;
	}
}