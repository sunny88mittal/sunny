package nasdaqomxsse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class BigPandTheRoadLessTravelled {
	
	public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int totalCheckPoints = Integer.parseInt(line);
        boolean paths[][] = new boolean[totalCheckPoints+1][totalCheckPoints+1];
        while (true) {
        	String[] tokens = br.readLine().split(" ");
        	int x = Integer.parseInt(tokens[0]);
        	int y = Integer.parseInt(tokens[1]);
        	
        	if (x == 0 && y == 0) {
        		break;
        	} else {
        		paths[x][y] = true;
        	}
        }
        
        System.out.println(getPaths(paths));
    }
	
	private static long getPaths (boolean paths[][]) {
		int length = paths.length;
		long pathsLength[] = new long[length];
		
		pathsLength[length - 1] = 1;
		for (int i = length -2; i>=1; i--) {
			for (int j = i+1; j<length; j++) {
				if (paths[i][j]) {
					pathsLength[i] += pathsLength[j];
				}
			}
		}
		return pathsLength[1];
	}
}
