package SRM654;

public class SquareScoresDiv2 {

	public int getscore(String s) {
		int result = 0;
		String temp = "" + s.charAt(0);
		for (int i=1; i<s.length(); i++) {
		   	if (s.charAt(i) != s.charAt(i-1)) {
		   		int n = temp.length();
		   		result += n*(n+1)/2;
		   		temp = "";
		   	}
		   	temp = temp + s.charAt(i);
		}
		int n = temp.length();
		result += n*(n+1)/2;
		return result;
	}

}
