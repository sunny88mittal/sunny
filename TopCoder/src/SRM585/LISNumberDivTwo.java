package SRM585;

public class LISNumberDivTwo {
   public int calculate(int[] seq) {
      int count = 1;
      int element = seq[0];
      int length = seq.length;
      for (int i=1; i<length; i++) {
    	  if (seq[i] < element) {
    		  count++;
    	  }
    	  element = seq[i];
      }
      return count;
   }
}
