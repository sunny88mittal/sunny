package SRM535;

public class FoxAndIntegers{

   public int[] get(int AminusB, int BminusC, int AplusB, int BplusC){
      int[] returnArray = new int[3];
      int[] emptyArray = new int[0];
      int sum = AminusB + AplusB;
      if(sum % 2 != 0){
         return emptyArray;
      } 
      returnArray[0] = sum /2;
      
      sum = BminusC + BplusC;
      if(sum % 2 != 0){
         return emptyArray;
      } 
      returnArray[1] = sum /2;
      
      returnArray[2] = BplusC - returnArray[1];
      
      if(returnArray[0] + returnArray[1] != AplusB){
         return emptyArray;
      }
      
      return returnArray;
   }
}
