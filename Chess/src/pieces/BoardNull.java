package pieces;

import java.util.Set;

public class BoardNull extends Piece{
	
	public BoardNull(int i,int j){
		this.color='X';
		if(j==8 && i<8){
			this.ID=(8-i+"");
		}
		else if(i==8 && j<8){
			this.ID=("  "+Character.toString((char) (97+j))+" ");
		}
		else if(j!=8 && i!=8 && (j+i)%2!=0){
			this.ID=(" ## ");
			this.location[0]=i;
			this.location[1]=j;
		}
		else{
			this.ID=("    ");
			this.location[0]=i;
			this.location[1]=j;
		}
	}
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol) {
		return "Error. Choose a spot with a piece.\n";
	}

	@Override
	public Set<Move> getAllMoves(Piece[][] newboard) {
		return null;
	}

}