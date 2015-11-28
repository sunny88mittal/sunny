package SRM657;

public class EightRooks {

	public String isCorrect(String[] board) {
	   for (int i=0; i<8; i++) {
		   int count=0;
		   for (int j=0; j<8; j++) {
			   if (board[i].charAt(j) == 'R') {
				   count++;
			   }
		   }
		   if (count != 1){
			   return "Incorrect";
		   }
		   
		   count = 0;
		   for (int j=0; j<8; j++) {
			   if (board[j].charAt(i) == 'R') {
				   count++;
			   }
		   }
		   if (count != 1){
			   return "Incorrect";
		   }
	   }
	   
	   return "Correct";
	}
}
