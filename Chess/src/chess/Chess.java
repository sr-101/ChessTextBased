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
				String src=srcdest.substring(0,2);
				String dest=srcdest.substring(3,5);
				if(newBoard.move(src, dest)==0){
					newBoard.turnBorW='b';
				}
			}
			if(newBoard.turnBorW=='b'){
				System.out.println("Black's Move: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String srcdest = br.readLine();
				String src=srcdest.substring(0,2);
				String dest=srcdest.substring(3,5);
				if(newBoard.move(src, dest)==0){
					newBoard.turnBorW='w';
				}
			}
		}
	}
}
