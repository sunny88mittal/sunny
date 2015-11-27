package SRM661;

public class FallingSand {

	public String[] simulate(String[] board) {
		int boardLength = board.length;
		int rowLength = board[0].length();
	    char[][] cboard = new char[boardLength][rowLength];
	    
	    for (int i=0; i<board.length; i++) {
	    	cboard[i] = board[i].toCharArray();
	    }
	    
	    
	    for (int i=0; i<rowLength; i++) {
	    	for (int j=boardLength-1; j>=0; j--) {
	    		char c = cboard[j][i];
	    		if (c =='o') {
	    			for (int k=j+1; k<boardLength; k++) {
	    				char d = cboard[k][i];
	    				if (d != '.') {
	    					break;
	    				}
	    				cboard[k][i] = 'o';
	    				cboard[j][i] = '.';
	    				++j;
	    			}
	    		}
	    	}
	    }
	    
	    for (int i=0; i<board.length; i++) {
	    	board[i] = new String(cboard[i]);
	    }
	    
	    return board;
	}
	
	public static void main (String args[]) {
		FallingSand obj = new FallingSand();
		String[] board = obj.simulate(new String[] {"o",
				 ".",
				 "o",
				 ".",
				 "o",
				 ".",
				 "."});
		for (String str : board) {
			System.out.println(str);
		}
	}
}
