package chess;

import java.io.*;

public class Chess {

	public static void main(String[] args) throws IOException {
		Board newBoard=new Board();
		while(newBoard.checkmate==false){
			if(newBoard.turnBorW=='w'){
				System.out.println("White's Move: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String srcdest = br.readLine();
				if(srcdest.length()==5){
					String src=srcdest.substring(0,2);
					String dest=srcdest.substring(3,5);
					if(newBoard.move(src, dest)==0){
						newBoard.turnBorW='b';
					}
					System.out.println(Board.isKingInCheck('w'));
					//System.out.println("White King: " + newBoard.checkforKing('w'));
				}
				else{
					System.out.println("Invalid Move. Try Again.\n");
					newBoard.printBoard();
				}
			}
			if(newBoard.turnBorW=='b'){
				System.out.println("Black's Move: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String srcdest = br.readLine();
				if(srcdest.length()==5){
					String src=srcdest.substring(0,2);
					String dest=srcdest.substring(3,5);
					if(newBoard.move(src, dest)==0){
						newBoard.turnBorW='w';
					}
					System.out.println(Board.isKingInCheck('b'));
					//System.out.println("Black King: " + newBoard.checkforKing('b'));
				}
				else{
					System.out.println("Invalid Move. Try Again.\n");
					newBoard.printBoard();
				}
			}
		}
	}
}
