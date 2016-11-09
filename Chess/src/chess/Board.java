package chess;

import java.util.ArrayList;
import pieces.Bishop;
import pieces.BoardNull;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {

	static Piece[][] newboard=new Piece[9][9];
	boolean checkmate=false;
	boolean stalemate=false;
	public static boolean castling=false;
	public static char promotion;
	public static boolean nextMoveEP=false;
	char turnBorW='w';
	public static int[] bKing={0,4};
	public static int[] wKing={7,3};

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
			if(source.color==turnBorW && source.color!='X'){
				//System.out.println(source.ID);
				//Piece destination=newboard[destrow][destcol];
				//System.out.println(destination.ID);
				String s="";
				if(Board.nextMoveEP==true && Board.allowEnpassant(newboard, srcrow, srccol, destrow, destcol)!=null){
					s="Enpassant Not Allowed";
				}
				else if(Board.nextMoveEP==true && Board.allowEnpassant(newboard, srcrow, srccol, destrow, destcol)==null){
					s=source.move(newboard, srcrow, srccol, destrow, destcol);
				}
				else if (Board.nextMoveEP==false){
					s=source.move(newboard, srcrow, srccol, destrow, destcol);
				}
			
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
				if(source.color=='X'){
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
	
	
	public static String allowEnpassant(Piece[][] p, int srcrow, int srccol, int destrow, int destcol){
		//boolean nextMoveEP=false;
		int epc=0;
		
		if((srccol>0) && (p[srcrow][srccol-1] instanceof Pawn) && (((Pawn)p[srcrow][srccol-1]).enpassant==true)){
			epc=srccol-1;
		}
			
		if((srccol<7) && (p[srcrow][srccol+1] instanceof Pawn) && (((Pawn)p[srcrow][srccol+1]).enpassant==true)){
			epc=srccol+1;
		}
		
		if((p[srcrow][srccol] instanceof Pawn) && (((destrow==srcrow+1) && p[srcrow][srccol].color=='b')||((destrow==srcrow-1) && p[srcrow][srccol].color=='w')) && (destcol==epc)){
			nextMoveEP=true;
			return null;
		}
		//nextMoveEP=false;
		return "in allowenpassant - Invalid Move. Try Again.\n";
	//return nextMoveEP;
	}
	
	
	public static String isKingInCheck(char color){
		ArrayList<Piece> result;
		if(color=='b'){
			result=isInCheck(bKing);
			if (!result.isEmpty()){
				StringBuilder s=new StringBuilder();
				for(int i=0;i<result.size();i++){
					s.append("\nYou are in check from: ").append(result.get(i));
				}
				return s.toString(); 
			}
			return "Not in Check";
		}
		else if(color=='w'){
			result=isInCheck(wKing);
			if (!result.isEmpty()){
				StringBuilder s=new StringBuilder();
				for(int i=0;i<result.size();i++){
					s.append("\nYou are in check from: ").append(result.get(i));
				}
				return s.toString();
			}
			return "Not in Check";
		}
		return null;
	}
	
	public static ArrayList<Piece> isInCheck(int[] kingloc){
		ArrayList<Piece> locofCheck=new ArrayList<Piece>();
		int d=0;
		int e=0;
		int srcrow=kingloc[0];
		int srccol=kingloc[1];
		King king=(King) newboard[kingloc[0]][kingloc[1]];
		
        //isInCheck by Bishop or Queen?
		for(int r=srcrow+1; r<8; r++){ 
			int c=srccol;
        	if(c+d<=7){
        		if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color!=king.color) && ((newboard[r][c+d].ID.contains("B") || (newboard[r][c+d].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			locofCheck.add(newboard[r][c+d]);
        		}
        		d++;
        	}
        	if(c-e>=0){
        		if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color!=king.color) && ((newboard[r][c-e].ID.contains("B") || (newboard[r][c-e].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			locofCheck.add(newboard[r][c-e]);
        		}
        		e++;
        	}
        	if(c-e==0 && c+d==7){
        		d=0;
        		e=0;
        	}
        }
		
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
        	if(c+d<=7){
        		if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color!=king.color) && ((newboard[r][c+d].ID.contains("B") || (newboard[r][c+d].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			locofCheck.add(newboard[r][c+d]);
        		}
        		d++;
        	}
        	if(c-e>=0){
        		if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color!=king.color) && ((newboard[r][c-e].ID.contains("B") || (newboard[r][c-e].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			locofCheck.add(newboard[r][c-e]);
        		}
        		e++;
        	}
        	if(c-e==0 && c+d==7){
        		d=0;
        		e=0;
        	}
        }
		return locofCheck;
	}
}
