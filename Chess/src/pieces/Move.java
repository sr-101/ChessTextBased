package pieces;

public class Move {
	int[] origin;
	String ID;
	int i;
	int j;
	
	public Move(int[] origin,String ID, int i, int j){
		this.ID=ID;
		this.i=i;
		this.j=j;
	}
	
	public Move(int i, int j){
		this.i=i;
		this.j=j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int[] getOrigin() {
		return origin;
	}

	public void setOrigin(int[] origin) {
		this.origin = origin;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Move [ID=" + ID + ", i=" + i + ", j=" + j + "]";
	}
	
	
}
