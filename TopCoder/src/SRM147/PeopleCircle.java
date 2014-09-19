package SRM147;

import java.util.Arrays;

public class PeopleCircle {
	
	public String order(int numMales, int numFemales, int K) {
		char [] ret = new char[numFemales + numMales];
        Arrays.fill(ret, 'M');
        int pos = ret.length - 1;
		for (int i=0; i<numFemales; i++) {
			for (int j=0; j<K;) {
				pos = (pos+1)%ret.length;
				//Increase Count only for females
				if (ret[pos] == 'M') j++;
			}
			ret[pos] = 'F';
		}
		return new String(ret);
	}
}

//Solved by converting the problem into the problem of seating the females by replacing males 
//While taking Care of the conditions

//TODO: Can we do better