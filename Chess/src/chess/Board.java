package chess;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import pieces.Bishop;
import pieces.BoardNull;
import pieces.CheckPiece;
import pieces.King;
import pieces.Knight;
import pieces.Move;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {

	static Piece[][] newboard=new Piece[9][9];
	public static boolean checkmate=false;
	boolean stalemate=false;
	public static boolean castlingb=false;
	public static boolean castlingw=false;
	public static char promotion;
	public static boolean nextMoveEP=false;
	char turnBorW='w';
	public static int[] bKing={0,4};
	public static boolean bKingCheck=false;
	public static int[] wKing={7,4};
	public static boolean wKingCheck=false;
	
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
					newboard[i][j].location[0]=i;
					newboard[i][j].location[1]=j;
				}
				else if(i==1 && j<8){
					newboard[i][j]=new Pawn('b');
					newboard[i][j].location[0]=i;
					newboard[i][j].location[1]=j;
				}
				else if(i==6 && j<8){
					newboard[i][j]=new Pawn('w');
					newboard[i][j].location[0]=i;
					newboard[i][j].location[1]=j;
				}
				else{
					newboard[i][j]=new BoardNull(i,j);
					newboard[i][j].location[0]=i;
					newboard[i][j].location[1]=j;
				}
			}
		}
		for(int j=0;j<8;j++){
			switch(j){
			case 0:
			case 7:
				newboard[0][j]=new Rook('b');
				newboard[0][j].location[0]=0;
				newboard[0][j].location[1]=j;
				break;
			case 1:
			case 6:
				newboard[0][j]=new Knight('b');
				newboard[0][j].location[0]=0;
				newboard[0][j].location[1]=j;
				break;
			case 2:
			case 5:
				newboard[0][j]=new Bishop('b');
				newboard[0][j].location[0]=0;
				newboard[0][j].location[1]=j;
				break;
			case 3:
				newboard[0][j]=new Queen('b');
				newboard[0][j].location[0]=0;
				newboard[0][j].location[1]=j;
				break;
			case 4:
				newboard[0][j]=new King('b');
				newboard[0][j].location[0]=0;
				newboard[0][j].location[1]=j;
				break;
			}
		}
		for(int j=0;j<8;j++){
			switch(j){
			case 0:
			case 7:
				newboard[7][j]=new Rook('w');
				newboard[7][j].location[0]=7;
				newboard[7][j].location[1]=j;
				break;
			case 1:
			case 6:
				newboard[7][j]=new Knight('w');
				newboard[7][j].location[0]=7;
				newboard[7][j].location[1]=j;
				break;
			case 2:
			case 5:
				newboard[7][j]=new Bishop('w');
				newboard[7][j].location[0]=7;
				newboard[7][j].location[1]=j;
				break;
			case 4:
				newboard[7][j]=new King('w');
				newboard[7][j].location[0]=7;
				newboard[7][j].location[1]=j;
				break;
			case 3:
				newboard[7][j]=new Queen('w');
				newboard[7][j].location[0]=7;
				newboard[7][j].location[1]=j;
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
		if((srcrow>=0 && srcrow<=7) && (destrow>=0 && destrow<=7) && (srccol>=0 && srccol<=7) && (destcol>=0 && destcol<=7)){
			Piece source=newboard[srcrow][srccol];
			if(source.color==turnBorW && source.color!='X'){
				source.location[0]=srcrow;
				source.location[1]=srccol;

				String s="";

				Piece temp=newboard[destrow][destcol];
				Piece temp2=newboard[srcrow][srccol];
				s=source.move(newboard, srcrow, srccol, destrow, destcol);
				//System.out.println("S: "+s);
				if(s==null){
					newboard[destrow][destcol].location[0]=destrow;
					newboard[destrow][destcol].location[1]=destcol;
					if(turnBorW=='w' && isKingInCheck('w') != null){
						wKingCheck=true;
						newboard[srcrow][srccol]=temp2;
						newboard[destrow][destcol]=temp;
						success=-1;
						printBoard();
						System.out.println("In check. Try Again.\n");
						return success;
					}
					else if(turnBorW=='b' && isKingInCheck('b') != null){
						bKingCheck=true;
						newboard[srcrow][srccol]=temp2;
						newboard[destrow][destcol]=temp;
						success=-1;
						printBoard();
						System.out.println("In check. Try Again.\n");
						return success;
					}
					else{
						success=0;
						if(bKingCheck)
						bKingCheck=false;
						if(wKingCheck)
						wKingCheck=false;
						printBoard();
						return success;
					}
				}
				else{
					success=-1;
					printBoard();
					System.out.println(s);
					return success;
				}
			}
			else{
				success=-2;
				printBoard();
				if(source.color=='X'){
					System.out.println("You selected an empty space. Try again.\n");
					return success;
				}
				else{
					System.out.println("You selected the opponent's piece. Try again.\n");
					return success;
				}
			}
		}
		else{
			success=-3;
			printBoard();
			System.out.println("Selection is off the board. Try again.");
			return success;
		}
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
	
	public String isKingInCheck(char color){
		Set<CheckPiece> result;
		String c;
		if(color=='b'){
			result=isInCheck(bKing,color);
			if (!result.isEmpty()){
				c=isInCheckMate(bKing);
				if(c!=null){ 
					System.out.println("White wins.");
					checkmate=true;
					return c;
				}
				bKingCheck=true;
				StringBuilder s=new StringBuilder();
				for(int i=0;i<result.size();i++){
					s.append("Check");
				}
				return s.toString(); 
			}
			return null;
		}
		else if(color=='w'){
			result=isInCheck(wKing,color);
			if (!result.isEmpty()){
				c=isInCheckMate(wKing);
				if(c!=null){ 
					System.out.println("Black wins.");
					checkmate=true;
					return c;
				}
				wKingCheck=true;
				StringBuilder s=new StringBuilder();
				for(int i=0;i<result.size();i++){
					s.append("Check");
				}
				return s.toString();
			}
			return null;
		}
		return null;
	}
	
	public static String isInCheckMate(int[] kingloc){
		boolean ischeckmate=false;
		char color=newboard[kingloc[0]][kingloc[1]].color;
		Set<CheckPiece> result=isInCheck(kingloc,color);
		if(!(result.isEmpty())){
			Set<Move> allmoves=getAllMoves(color);
			checkloop:
			for(CheckPiece cp:result){
				for(Move m:allmoves){
					for(Move cpm:cp.getM()){
						//System.out.println("Path of Check: "+cpm);
						//System.out.println("Same Color Moves: "+m);
						if(cpm.getI()!=m.getI() || cpm.getJ()!=m.getJ()){
							ischeckmate=true;
						}
						else if(cpm.getI()==m.getI() && cpm.getJ()==m.getJ()){
							if((cp.getM().size()==1) && !(m.getID().contains("K"))){
								String s=newboard[m.getOrigin()[0]][m.getOrigin()[1]].move(newboard, m.getOrigin()[0], m.getOrigin()[1], m.getI(), m.getJ());
								//System.out.println("S: "+s);
								if(s!=null){
									ischeckmate=true;
								}
								else{
									ischeckmate=false;
									break checkloop;
								}
							}
							else if((m.getID().contains("K"))){
								String s=newboard[kingloc[0]][kingloc[1]].move(newboard, kingloc[0],kingloc[1], m.getI(), m.getJ());
								//System.out.println("S: "+s);
								if(s!=null){
									ischeckmate=true;
								}
								else{
									ischeckmate=false;
									break checkloop;
								}
							}
							else{
								//System.out.println("Else M: " + m + " CPM: " + cpm + " CPM Size: "+ cp.getM().size());
								ischeckmate=false;
								break checkloop;
							}
						}
						//System.out.println("IsCheckmate: "+ischeckmate);
					}
				}
			}
		}
		//System.out.println(ischeckmate);
		if(ischeckmate){
			return "Checkmate";
		}
		return null;
	}
		
	
	public static Set<CheckPiece> isInCheck(int[] kingloc,char color){
		Set<CheckPiece> locofCheck=new LinkedHashSet<CheckPiece>();
		int d=1;
		int e=1;
		int srcrow=kingloc[0];
		int srccol=kingloc[1];
		Piece king= newboard[kingloc[0]][kingloc[1]];
		Set<Move> pathtocheck=new LinkedHashSet<Move>();
		
		//isInCheck by Bishop or Queen?
		for(int r=srcrow+1; r<8; r++){
			int c=srccol;
        	if(c+d<=7){
        		//System.out.println("Bottom Right: "+newboard[r][c+d]);
        		if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color!=king.color) && ((king.color=='b' && newboard[r][c+d].ID.contains("p") && d==1 && r==srcrow+1) || (newboard[r][c+d].ID.contains("B") || (newboard[r][c+d].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			newboard[r][c+d].location[0]=r;
        			newboard[r][c+d].location[1]=c+d;
        			pathtocheck.add(new Move(r,c+d));
        			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c+d],new ArrayList<Move>(pathtocheck)));
        		}
        		else if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color==king.color)){
        			break;
        		}
        		else if(newboard[r][c+d] instanceof BoardNull){
        			pathtocheck.add(new Move(r,c+d));
        		}
        		d++;
        	}
		}
		pathtocheck.clear();
		for(int r=srcrow+1; r<8; r++){ 
			int c=srccol;
        	if(c-e>=0){
        		//System.out.println("Bottom Left: "+newboard[r][c-e]);
        		if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color!=king.color) && ((king.color=='b' && newboard[r][c-e].ID.contains("p") && e==1 && r==srcrow+1) || (newboard[r][c-e].ID.contains("B") || (newboard[r][c-e].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			newboard[r][c-e].location[0]=r;
        			newboard[r][c-e].location[1]=c-e;
        			pathtocheck.add(new Move(r,c-e));
        			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c-e],new ArrayList<Move>(pathtocheck)));
        		}
        		else if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color==king.color)){
        			break;
        		}
        		else if(newboard[r][c-e] instanceof BoardNull){
        			pathtocheck.add(new Move(r,c-e));
        		}
        		e++;
        	}
        }
		d=1;
		pathtocheck.clear();
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
			if(c+d<=7){
        		//System.out.println("Top Right: "+newboard[r][c+d]);
        		if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color!=king.color) && ((king.color=='w' && newboard[r][c+d].ID.contains("p") && d==1 && r==srcrow-1) || (newboard[r][c+d].ID.contains("B") || (newboard[r][c+d].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			newboard[r][c+d].location[0]=r;
        			newboard[r][c+d].location[1]=c+d;
        			pathtocheck.add(new Move(r,c+d));
        			//System.out.println("Moves: "+pathtocheck);
        			//System.out.println("Moves Size: "+pathtocheck.size());
        			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c+d],new ArrayList<Move>(pathtocheck)));
        			//System.out.println(locofCheck.toString());
        		}
        		else if((!(newboard[r][c+d] instanceof BoardNull)) && (newboard[r][c+d].color==king.color)){
        			break;
        		}
        		else if(newboard[r][c+d] instanceof BoardNull){
        			pathtocheck.add(new Move(r,c+d));
        			//System.out.println("Moves: "+pathtocheck);
        		}
        		d++;
        	}
		}
		e=1;
		pathtocheck.clear();
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
        	if(c-e>=0){
        		//System.out.println("Top Left: "+newboard[r][c-e]);
        		if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color!=king.color) && ((king.color=='w' && newboard[r][c-e].ID.contains("p") && e==1 && r==srcrow-1) || (newboard[r][c-e].ID.contains("B") || (newboard[r][c-e].ID.contains("Q"))))){ //there is nothing between a bishop of another color and the king
        			newboard[r][c-e].location[0]=r;
        			newboard[r][c-e].location[1]=c-e;
        			pathtocheck.add(new Move(r,c-e));
        			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c-e],new ArrayList<Move>(pathtocheck)));
        		}
        		else if((!(newboard[r][c-e] instanceof BoardNull)) && (newboard[r][c-e].color==king.color)){
        			break;
        		}
        		else if(newboard[r][c-e] instanceof BoardNull){
        			pathtocheck.add(new Move(r,c-e));
        		}
        		e++;
        	}
        }
		pathtocheck.clear();
		for(int c=srccol+1; c<8; c++){ 
			int r=srcrow;
			//System.out.println("Right: "+newboard[r][c]);
    		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=king.color) && ((newboard[r][c].ID.contains("R") || (newboard[r][c].ID.contains("Q"))))){ //there is nothing between a rook or queen of another color and the king
    			newboard[r][c].location[0]=r;
    			newboard[r][c].location[1]=c;
    			pathtocheck.add(new Move(r,c));
    			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c],new ArrayList<Move>(pathtocheck)));
    		}
    		else if(((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==king.color))||(((newboard[r][c] instanceof Pawn)) && (newboard[r][c].color!=king.color))){
    			break;
    		}
    		else if(newboard[r][c] instanceof BoardNull){
    			pathtocheck.add(new Move(r,c));
    		}
		}
		pathtocheck.clear();
		for(int c=srccol-1; c>=0; c--){ 
			int r=srcrow;
			//System.out.println("Left: "+newboard[r][c]);
    		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=king.color) && ((newboard[r][c].ID.contains("R") || (newboard[r][c].ID.contains("Q"))))){ //there is nothing between a rook or queen of another color and the king
    			newboard[r][c].location[0]=r;
    			newboard[r][c].location[1]=c;
    			pathtocheck.add(new Move(r,c));
    			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c],new ArrayList<Move>(pathtocheck)));
    		}
    		else if(((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==king.color))||(((newboard[r][c] instanceof Pawn)) && (newboard[r][c].color!=king.color))){
    			break;
    		}
    		else if(newboard[r][c] instanceof BoardNull){
    			pathtocheck.add(new Move(r,c));
    		}
		}
		pathtocheck.clear();
		for(int r=srcrow+1; r<8; r++){ 
			int c=srccol;
			//System.out.println("Down: "+newboard[r][c]);
    		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=king.color) && ((newboard[r][c].ID.contains("R") || (newboard[r][c].ID.contains("Q"))))){ //there is nothing between a rook or queen of another color and the king
    			newboard[r][c].location[0]=r;
    			newboard[r][c].location[1]=c;
    			pathtocheck.add(new Move(r,c));
    			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c],new ArrayList<Move>(pathtocheck)));
    			//System.out.print("Location of check:"+locofCheck);
    		}
    		else if(((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==king.color))||(((newboard[r][c] instanceof Pawn)) && (newboard[r][c].color!=king.color))){
    			break;
    		}
    		else if(newboard[r][c] instanceof BoardNull){
    			pathtocheck.add(new Move(r,c));
    		}
		}
		pathtocheck.clear();
		for(int r=srcrow-1; r>=0; r--){ 
			int c=srccol;
    		//System.out.println("Up: "+newboard[r][c]);
    		if((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color!=king.color) && ((newboard[r][c].ID.contains("R") || (newboard[r][c].ID.contains("Q"))))){ //there is nothing between a rook or queen of another color and the king
    			newboard[r][c].location[0]=r;
    			newboard[r][c].location[1]=c;
    			pathtocheck.add(new Move(r,c));
    			if(!pathtocheck.isEmpty()) locofCheck.add(new CheckPiece(newboard[r][c],new ArrayList<Move>(pathtocheck)));
    		}
    		else if(((!(newboard[r][c] instanceof BoardNull)) && (newboard[r][c].color==king.color))||(((newboard[r][c] instanceof Pawn)) && (newboard[r][c].color!=king.color))){
    			break;
    		}
    		else if(newboard[r][c] instanceof BoardNull){
    			pathtocheck.add(new Move(r,c));
    		}
		}
		pathtocheck.clear();
		for(int i=0;i<8;i++){
			int r=srcrow; int c=srccol;
			switch(i){
			case 0:
				if(r+2<=7 && c-1>=0){
					//System.out.println("Down Left: "+newboard[r+2][c-1]);
					if(!(newboard[r+2][c-1] instanceof BoardNull) && (newboard[r+2][c-1].color!=king.color) && (newboard[r+2][c-1].ID.contains("N"))){ //there is a knight there
						newboard[r+2][c-1].location[0]=r+2;
						newboard[r+2][c-1].location[1]=c-1;
						pathtocheck.add(new Move(r+2,c-1));
						locofCheck.add(new CheckPiece(newboard[r+2][c-1],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 1:
				if(r+2<=7 && c+1<=7){
					//System.out.println("Down Right: "+newboard[r+2][c+1]);
					if(!(newboard[r+2][c+1] instanceof BoardNull) && (newboard[r+2][c+1].color!=king.color) && (newboard[r+2][c+1].ID.contains("N"))){ //there is a knight there
						newboard[r+2][c+1].location[0]=r+2;
						newboard[r+2][c+1].location[1]=c+1;
						pathtocheck.add(new Move(r+2,c+1));
						locofCheck.add(new CheckPiece(newboard[r+2][c+1],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 2:
				if(r-2>=0 && c-1>=0){
					//System.out.println("Up Left: "+newboard[r-2][c-1]);
					if(!(newboard[r-2][c-1] instanceof BoardNull) && (newboard[r-2][c-1].color!=king.color) && (newboard[r-2][c-1].ID.contains("N"))){ //there is a knight there
						newboard[r-2][c-1].location[0]=r-2;
						newboard[r-2][c-1].location[1]=c-1;
						pathtocheck.add(new Move(r-2,c-1));
						locofCheck.add(new CheckPiece(newboard[r-2][c-1],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 3:
				if(r-2>=0 && c+1<=7){
					//System.out.println("Up Right: "+newboard[r-2][c+1]);
					if(!(newboard[r-2][c+1] instanceof BoardNull) && (newboard[r-2][c+1].color!=king.color) && (newboard[r-2][c+1].ID.contains("N"))){ //there is a knight there
						newboard[r-2][c+1].location[0]=r-2;
						newboard[r-2][c+1].location[1]=c+1;
						pathtocheck.add(new Move(r-2,c+1));
						locofCheck.add(new CheckPiece(newboard[r-2][c+1],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 4:
				if(r+1<=7 && c-2>=0){
					//System.out.println("Left Down: "+newboard[r+1][c-2]);
					if(!(newboard[r+1][c-2] instanceof BoardNull) && (newboard[r+1][c-2].color!=king.color) && (newboard[r+1][c-2].ID.contains("N"))){ //there is a knight there
						newboard[r+1][c-2].location[0]=r+1;
						newboard[r+1][c-2].location[1]=c-2;
						pathtocheck.add(new Move(r+1,c-2));
						locofCheck.add(new CheckPiece(newboard[r+1][c-2],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 5:
				if(r+1<=7 && c+2<=7){
					//System.out.println("Right Down: "+newboard[r+1][c+2]);
					if(!(newboard[r+1][c+2] instanceof BoardNull) && (newboard[r+1][c+2].color!=king.color) && (newboard[r+1][c+2].ID.contains("N"))){ //there is a knight there
						newboard[r+1][c+2].location[0]=r+1;
						newboard[r+1][c+2].location[1]=c+2;
						pathtocheck.add(new Move(r+1,c+2));
						locofCheck.add(new CheckPiece(newboard[r+1][c+2],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 6: 
				if(r-1>=0 && c-2>=0){
					//System.out.println("Left Up: "+newboard[r-1][c-2]);
					if(!(newboard[r-1][c-2] instanceof BoardNull) && (newboard[r-1][c-2].color!=king.color) && (newboard[r-1][c-2].ID.contains("N"))){ //there is a knight there
						newboard[r-1][c-2].location[0]=r-1;
						newboard[r-1][c-2].location[1]=c-2;
						pathtocheck.add(new Move(r-1,c-2));
						locofCheck.add(new CheckPiece(newboard[r-1][c-2],new ArrayList<Move>(pathtocheck)));
					}
				}
			case 7:
				if(r-1>=0 && c+2<=7){
					//System.out.println("Right Up: "+newboard[r-1][c+2]);
					if(!(newboard[r-1][c+2] instanceof BoardNull) && (newboard[r-1][c+2].color!=king.color) && (newboard[r-1][c+2].ID.contains("N"))){ //there is a knight there
						newboard[r-1][c+2].location[0]=r-1;
						newboard[r-1][c+2].location[1]=c+2;
						pathtocheck.add(new Move(r-1,c+2));
						locofCheck.add(new CheckPiece(newboard[r-1][c+2],new ArrayList<Move>(pathtocheck)));
					}
				}
			}
		}
		pathtocheck.clear();
		return locofCheck;
	}

	public static boolean getKingBoolean(char c){
		if(c=='w'){
			return wKingCheck;
		}
		else if(c=='b'){
			return bKingCheck;
		}
		return false;
	}
	
	public static Set<Move> getAllMoves(char color){
		Set<Move> allmoves=new LinkedHashSet<Move>();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(newboard[i][j].color==color){
					if(newboard[i][j].ID.contains("K")){
						//System.out.println(newboard[i][j].ID+" "+newboard[i][j].getAllMoves(newboard));
					}
					//System.out.println(newboard[i][j].ID+" "+newboard[i][j].getAllMoves(newboard));
					allmoves.addAll(newboard[i][j].getAllMoves(newboard));
				}
			}
		}
		return allmoves;
	}
}
