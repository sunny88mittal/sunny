package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class to calculate the no. of inversions
 * There is an inversion in array 
 * if i < j but a[i] > a[j]
 * @author sumittal
 *
 */
public class AssnmntArrayInversion {

	public static void main (String args[]) throws IOException {
		int[] elements = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithm Courses\\Stanford\\Algorithm Analysis and Design 1\\Week 1\\ProgrammingAssinment\\IntegerArray.txt", 100000);
		//int[] elements = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithm Courses\\Stanford\\Algorithm Analysis and Design 1\\Week 1\\ProgrammingAssinment\\TestArray.txt", 11);
		long inversions = countInversions(elements);
		System.out.println("No of inversions are:" +  inversions);
	}
	
	
	private static long countInversions(int[] elements) {
		long inversions = 0;
		int length = elements.length;
		
		if (length > 1) {
			int [] l = Arrays.copyOfRange(elements, 0, length/2);
			int [] r = Arrays.copyOfRange(elements, length/2, length);
			
			long lin = countInversions(l);
			long rin = countInversions(r);
			long lrin = countInversionsLR(l, r, elements);
			
			inversions = lin + rin + lrin;	
		} else {
			return 0;
		}
		return inversions;
	}
	
	private static long countInversionsLR(int[] l, int[] r, int [] mergedArray) {
        int inversions = 0;
		int i=l.length,j=r.length, k=0, m=0,n=0;
		while (m<i && n<j) {
		    int le = l[m];
		    int re = r[n];
		    //Count inversions if le > re and copy the re element to final array
		    if (le > re) {
		       	inversions += i-m;
		       	mergedArray[k] = re;
		       	n++;
		    } else { //Copy le to final array
		    	mergedArray[k] = le;
		    	m++;
		    }
		    k++;
		}
		
		//Copy the remaining left array elements
		while (m < i) {
			mergedArray[k] = l[m];
			m++;
			k++;
		}
		
		//copy the remaining right array elements
		while (n < j) {
			mergedArray[k] = r[n];
			n++;
			k++;
		}
		
		return inversions;
	}
	
	
	public static int[] readFile (String fileLocation, int noOfElements) throws IOException {
		int[] elements =  new int[noOfElements];
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			String line = in.readLine();
			int i =0;
			while (line != null) {
				elements[i] = Integer.parseInt(line);
				line = in.readLine();
				i++;
			}
			return elements;	
		} finally {
		    if (in != null) {
		    	in.close();
		    }
		}
	}
}
