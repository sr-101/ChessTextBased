package chess;

import java.io.*;

public class Chess {

	public static void main(String[] args) throws IOException {
		Board newBoard=new Board();
		while(Board.checkmate==false){
			if(newBoard.turnBorW=='w'){
				System.out.println("White's Move: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String srcdest = br.readLine();
				if(srcdest.split(" ").length==3||srcdest.split(" ").length==2){
					String src=srcdest.split(" ")[0];
					String dest=srcdest.split(" ")[1];
					if(srcdest.split(" ").length==3){
						Board.promotion=srcdest.split(" ")[2].charAt(0);
					}
					if(newBoard.move(src, dest)==0){
						newBoard.turnBorW='b';
						if(newBoard.isKingInCheck('b')!=null)
						System.out.println(newBoard.isKingInCheck('b'));
					}
				}
				else if(srcdest.equalsIgnoreCase("Resign")){
					System.out.println("Resign");
					Board.checkmate=true;
				}
				else if(srcdest.contains("draw?")){
					System.out.println("Do you accept the draw?");
					String next = br.readLine();
					if(next.equalsIgnoreCase("draw")){
						System.out.println("Draw");
						Board.checkmate=true;
					}
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
				if(srcdest.split(" ").length==3||srcdest.split(" ").length==2){
					String src=srcdest.split(" ")[0];
					String dest=srcdest.split(" ")[1];
					if(srcdest.split(" ").length==3){
						Board.promotion=srcdest.split(" ")[2].charAt(0);
					}
					if(newBoard.move(src, dest)==0){
						newBoard.turnBorW='w';
						if(newBoard.isKingInCheck('w')!=null)
						System.out.println(newBoard.isKingInCheck('w'));
					}
				}
				else if(srcdest.equalsIgnoreCase("Resign")){
					System.out.println("Resign");
					Board.checkmate=true;
				}
				else if(srcdest.contains("draw?")){
					System.out.println("Do you accept the draw?");
					String next = br.readLine();
					if(next.equalsIgnoreCase("draw")){
						System.out.println("Draw");
						Board.checkmate=true;
					}
				}
				else{
					System.out.println("Invalid Move. Try Again.\n");
					newBoard.printBoard();
				}
			}
		}
		newBoard.printBoard();
	}
}
