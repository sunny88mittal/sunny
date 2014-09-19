package week1;

public class HeapVertex {
	
	public int vertex;
	
	public int minWeight;

	public HeapVertex (int vertex, int minweight) {
		this.vertex = vertex;
		this.minWeight = minweight;
	}
	
	@Override
	public String toString() {
		String str = "(" + vertex + "," +  minWeight + ")";
		return str;
	}
}
