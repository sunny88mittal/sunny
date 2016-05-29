package week1;

public class XOR {

	public static void main (String[] args) {
		char arr[] = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g' ,'h' ,'i' ,'j' ,'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r' ,'s', 't', 'u', 'v', 'w' ,'x' ,'y' ,'z'};
		
		for (int i=0; i<arr.length; i++) {
			String output = "";
			for (int j=0; j<arr.length; j++) {
				output = output + (arr[i] ^ arr[j]) + " "; 
			}
			System.out.println(output);
		}
	}
}
