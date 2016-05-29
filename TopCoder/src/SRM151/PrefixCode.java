package SRM151;

public class PrefixCode {
	
	public String isOne(String[] words) {
	    String returnValue = "Yes";
		int length = words.length;
		for (int i=0; i<length; i++) {
		    boolean found = false;
			for (int j=i+1; j<length; j++) {
		    	if (words[j].startsWith(words[i])){
		    		returnValue = "No, " + i;
		    		found = true;
		    		break;
		    	} else if (words[i].startsWith(words[j])){
		    		returnValue = "No, " + j;
		    		found = true;
		    		break;
		    	}
		    }
			if (found){
				break;
			}
		}
		return returnValue;
	}
}
