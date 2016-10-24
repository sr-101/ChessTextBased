package chess;

public class Chess {

	public static void main(String[] args) {
		String[][] newboard=new String[9][9];
		for(int i=0;i<=8;i++){
			for(int j=0;j<=8;j++){
				if(j==8 && i<8){
					newboard[i][j]=8-i+"";
				}
				else if(i==8 && j<8){
					newboard[i][j]="  "+Character.toString((char) (97+j))+" ";
				}
				else if(j!=8 && i!=8 && i>=2 && i<=5 && (j+i)%2!=0){
					newboard[i][j]=" ## ";
				}
				else{
					newboard[i][j]="    ";
				}
			}
		}
		for(int i=0;i<=8;i++){
			for(int j=0;j<=8;j++){
				System.out.print(newboard[i][j]);
			}
			System.out.print("\n");
		}
	}

}
