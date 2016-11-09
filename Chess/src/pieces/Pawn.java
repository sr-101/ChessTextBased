package pieces;

public class Pawn extends Piece{
	public boolean enpassant=false;
	public Pawn(char BW){
		this.color=BW;
		this.ID=" "+BW+"p"+" ";
	}
	
	
	
	public String pawnPromotion(Piece[][] newboard, int destrow, int destcol){
		char pro=chess.Board.promotion;
		char col=newboard[destrow][destcol].color;
		
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
		 return "Error - invalid promotion\n";
		
	}
	
	
	public String move(Piece[][] newboard, int srcrow, int srccol, int destrow, int destcol){
	
			if((color=='w' && srcrow-1==destrow)||(color=='b' && srcrow+1==destrow)){
				if(newboard[destrow][destcol] instanceof BoardNull && (srccol==destcol)){  
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					moved=true;
					if(destrow==7 || destrow==0){
						pawnPromotion(newboard,destrow,destcol);
					}
					return null;
				}
				if(newboard[destrow][destcol] instanceof BoardNull && (chess.Board.nextMoveEP==true)){  
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					newboard[destrow-1][destcol]=new BoardNull(destrow-1,destcol);
					chess.Board.nextMoveEP=false;
					enpassant=false;
					moved=true;
					if(destrow==7 || destrow==0){
						pawnPromotion(newboard,destrow,destcol);
					}
					return null;
				}
				else if(!(newboard[destrow][destcol] instanceof BoardNull) && (srccol+1==destcol||srccol-1==destcol)){  //kill
					newboard[destrow][destcol]=newboard[srcrow][srccol];
					newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
					moved=true;
					if(destrow==7 || destrow==0){
						pawnPromotion(newboard,destrow,destcol);
					}
					return null;
				}
			}
			else if(moved==false && ((srcrow-2==destrow && srccol==destcol) || (srcrow+2==destrow && srccol==destcol))){  
				
				if((!(newboard[destrow][destcol-1] instanceof Pawn) ) && //just added
						(newboard[destrow][destcol-1].color!=color) ){
					enpassant=true;
				}
				
				if((!(newboard[destrow][destcol+1] instanceof Pawn) ) && 
						(newboard[destrow][destcol+1].color!=color) ){
					enpassant=true;
				}
//just added end				
				if(color=='w'){
					if(newboard[destrow][destcol] instanceof BoardNull && newboard[destrow+1][destcol] instanceof BoardNull){
						newboard[destrow][destcol]=newboard[srcrow][srccol];
						newboard[srcrow][srccol]=new BoardNull(srcrow,srccol);
						moved=true;
						return null;
					}
				}
				else if(color=='b'){
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