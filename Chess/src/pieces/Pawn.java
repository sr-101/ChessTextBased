package pieces;

public class Pawn extends Piece{
	
	public Pawn(char BW){
		this.BW=BW;
		this.ID=" "+BW+"p"+" ";
	}
	
	//public void enPassant(Piece[][] newboard, int destrow, int destcol, int epr, int epc){
		
		
	//	if
	//}
	
	public String pawnPromotion(Piece[][] newboard, int destrow, int destcol){
		char pro=chess.Board.promotion;
		char col=newboard[destrow][destcol].BW;
		
		if(pro=='Q'){
			if(col=='b'){
			newboard[destrow][destcol]=new Queen('b');
				return null;	
			}else if(col=='w'){
			newboard[destrow][destcol]=new Queen('w');
			    return null;
		}
		}
		
		if(pro=='R'){
			if(col=='b'){
			newboard[destrow][destcol]=new Rook('b');
				return null;
					
			}else if(col=='w'){
			newboard[destrow][destcol]=new Rook('w');
				return null;
			}
		}	
		
		if(pro=='N'){
			if(col=='b'){
				newboard[destrow][destcol]=new Knight('b');
				return null;
			}else if(col=='w'){
				newboard[destrow][destcol]=new Knight('w');
				return null;
			}
		}
		
		if(pro=='B'){
			if(col=='b'){
			newboard[destrow][destcol]=new Bishop('b');
			return null;
			}else if(col=='w'){
			newboard[destrow][destcol]=new Bishop('w');
			return null;
			}
		}
		 return "Error - invalid promotion";
		
	}
	
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
	//	if(chess.Board.enPassant==false){
			if((BW=='w' && srcrow-1==destrow)||(BW=='b' && srcrow+1==destrow)){
				if(newboard[destrow][destcol] instanceof BoardNull && (srccol==destcol)){  
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					moved=true;
					if(destrow==8 || destrow==0){
						pawnPromotion(newboard,destrow,destcol);
					}
					return null;
				}
				else if(!(newboard[destrow][destcol] instanceof BoardNull) && (srccol+1==destcol||srccol-1==destcol)){  
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					moved=true;
					if(destrow==8 || destrow==0){
						pawnPromotion(newboard,destrow,destcol);
					}
					return null;
				}
			}
			else if(moved==false && ((srcrow-2==destrow && srccol==destcol) || (srcrow+2==destrow && srccol==destcol))){  
				if(BW=='w'){
					if(newboard[destrow][destcol] instanceof BoardNull && newboard[destrow+1][destcol] instanceof BoardNull){
						newboard[destrow][destcol]=newboard[srcrow][srccol];
						newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
						moved=true;
						return null;
					}
				}
				else if(BW=='b'){
					if(newboard[destrow][destcol] instanceof BoardNull && newboard[destrow-1][destcol] instanceof BoardNull){
						newboard[destrow][destcol]=newboard[srcrow][srccol];
						newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
						moved=true;
						return null;
					}
				}
			}
			
			return "Invalid Move. Try Again.\n";
		}
	
	
}