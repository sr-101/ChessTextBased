package pieces;

import java.util.ArrayList;

public class CheckPiece {
	
	Piece p;
	ArrayList<Move> m;

	public CheckPiece(Piece p, ArrayList<Move> m) {
		this.p = p;
		this.m = m;
	}

	@Override
	public String toString() {
		return "CheckPiece [p=" + p + ", m=" + m.toString() + "]";
	}

	public Piece getP() {
		return p;
	}

	public ArrayList<Move> getM() {
		return m;
	}
}
