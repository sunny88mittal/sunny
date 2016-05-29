package introduction;

import java.util.Scanner;

public class Hangover {

	public static void main(String args[]) throws Exception {
		Scanner cin = new Scanner(System.in);
		float sum = cin.nextFloat();
		while (sum != 0.00) {
		    System.out.println(getNumber(sum)-1 + " card(s)");
		    sum = cin.nextFloat();
		}
	}
	
	private static int getNumber(float sum){
		float tempSum = 0;
		int temp = 1;
		while (tempSum < sum) {
			temp++;
			tempSum += 1/(float)temp;
		}
		return temp;
	}
}