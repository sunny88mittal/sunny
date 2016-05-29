package SRM149;

public class FormatAmt {
	
	public String amount(int dollars, int cents) {
		String returnString = "$";
		
		String dollarS = "";
		char[] dollarChar = (dollars + "").toCharArray();
		int j=0;
		for (int i=dollarChar.length-1; i>=0; i--){
			if ( j>0 &&  j%3 == 0){
				dollarS = "," + dollarS;
			}
			dollarS = dollarChar[i] +  dollarS;
			j++;
		}
		
		String centsS = cents + "";
		if (cents < 10) {
			centsS = "0" + centsS;
		}
		
		returnString += dollarS + "." + centsS;
		
		return returnString;
	}

}
