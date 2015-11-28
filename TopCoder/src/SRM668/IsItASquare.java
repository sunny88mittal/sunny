package SRM668;

public class IsItASquare {

	public String isSquare(int[] x, int[] y) {
		int d1 = (x[1] - x[0]) * (x[1] - x[0]) + (y[1] - y[0]) * (y[1] - y[0]);
		int d2 = (x[2] - x[0]) * (x[2] - x[0]) + (y[2] - y[0]) * (y[2] - y[0]);
		int d3 = (x[3] - x[0]) * (x[3] - x[0]) + (y[3] - y[0]) * (y[3] - y[0]);

		int sqSide = -1;
		int dia1 = -1;
		int dia2 = -1;
		if (d1 == d2) {
			sqSide = d1;
			dia1 = (x[1] - x[2]) * (x[1] - x[2]) + (y[1] - y[2]) * (y[1] - y[2]);
			dia2 = (x[0] - x[3]) * (x[0] - x[3]) + (y[0] - y[3]) * (y[0] - y[3]);
		} else if (d2 == d3) {
			sqSide = d2;
			dia1 = (x[3] - x[2]) * (x[3] - x[2]) + (y[3] - y[2]) * (y[3] - y[2]);
			dia2 = (x[0] - x[1]) * (x[0] - x[1]) + (y[0] - y[1]) * (y[0] - y[1]);
		} else if (d1 == d3) {
			sqSide = d3;
			dia1 = (x[3] - x[1]) * (x[3] - x[1]) + (y[3] - y[1]) * (y[3] - y[1]);
			dia2 = (x[0] - x[2]) * (x[0] - x[2]) + (y[0] - y[2]) * (y[0] - y[2]);
		} else {
			return "Not a square";
		}
		
		if (dia1 != dia2) {
			return "Not a square";
		}

		for (int i=0; i<4; i++) {
			int count = 0; 
			for (int j=0; j<4; j++) {
				if (i != j) {
					int d = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
					if (d == sqSide) {
						count++;
					}
				}
			}
			if (count != 2) {
				return "Not a square";
			}
		}
		
		return "It's a square";
	}
	
	public static void main (String args[]) {
		IsItASquare obj = new IsItASquare();
		System.out.println(obj.isSquare(new int []{0, 0, 2, 2}, new int[]{0, 2, 0, 2}));
		System.out.println(obj.isSquare(new int []{0, 1, 5, 6}, new int[]{1, 6, 0, 5}));
		System.out.println(obj.isSquare(new int []{0, 0, 7, 7}, new int[]{0, 3, 0, 3}));
		System.out.println(obj.isSquare(new int []{0, 5000, 5000, 10000}, new int[]{5000, 0, 10000, 5000}));
		System.out.println(obj.isSquare(new int []{1, 2, 3, 4}, new int[]{4, 3, 2, 1}));
		System.out.println(obj.isSquare(new int []{0, 5, 3, 8}, new int[]{0, 0, 4, 4}));
	}
}
