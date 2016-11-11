package pieces;

import java.util.Arrays;

public class Move {
	int[] origin;
	String ID;
	int i;
	int j;
	
	public Move(int[] origin,String ID, int i, int j){
		this.origin=origin;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + i;
		result = prime * result + j;
		result = prime * result + Arrays.hashCode(origin);
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
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		if (!Arrays.equals(origin, other.origin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Move [ID=" + ID + ", i=" + i + ", j=" + j + "]";
	}
	
	
}
