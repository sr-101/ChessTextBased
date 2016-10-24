package chess;

import pieces.*;

public class Chess {

	public static void main(String[] args) {
		Piece[][] newboard=new Piece[9][9];
		for(int i=0;i<=8;i++){
			for(int j=0;j<=8;j++){
				if(j==8 && i<8){
					newboard[i][j]=new BoardNull(8-i+"");
				}
				else if(i==8 && j<8){
					newboard[i][j]=new BoardNull("  "+Character.toString((char) (97+j))+" ");
				}
				else if(j!=8 && i!=8 && i>=2 && i<=5 && (j+i)%2!=0){
					newboard[i][j]=new BoardNull(" ## ");
				}
				else if(i==1 && j<8){
					newboard[i][j]=new Pawn('b');
				}
				else if(i==6 && j<8){
					newboard[i][j]=new Pawn('w');
				}
				else{
					newboard[i][j]=new BoardNull("    ");
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
		for(int i=0;i<=8;i++){
			for(int j=0;j<=8;j++){
				System.out.print(newboard[i][j].ID);
			}
			System.out.print("\n");
		}
	}

}
