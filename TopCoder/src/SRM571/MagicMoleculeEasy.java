package SRM571;

import java.util.ArrayList;
import java.util.List;

public class MagicMoleculeEasy {

   public static void main (String args[]) {
       System.out.println( maxMagicPower(new int[]{100, 1, 100},new String[]{"NYN","YNY","NYN"},1));
   }
    
   public static int maxMagicPower(int[] magicPower, String[] magicBond, int k){
       int result = 0;
       List<Integer> disconnected = new ArrayList<Integer>();
       List<Integer> finalElements = new ArrayList<Integer>();
       int length = magicBond.length;
       
       //find disconnected atoms
       for (int i=0; i<length; i++) {
          if (magicBond[i].lastIndexOf('Y') == -1){
             disconnected.add(i);
          }
       }
       
       //Take the required connected atoms such that one is there from each connection 
       for (int i=0; i<length; i++) {
           if (disconnected.contains(i) ||  finalElements.contains(i)){
               continue;
           }
           String str =  magicBond[i];
           int maxIndex = i;
           boolean doAdd = true;
           for (int j=0; j<str.length(); j++){
               if (str.charAt(j) == 'Y') {
                   if (finalElements.contains(j)) {
                       doAdd = false;
                       break;
                   } else if (magicPower[j] > magicPower[maxIndex]) {
                       maxIndex = j;
                   }
               }
           }
           if (doAdd) {
              finalElements.add(maxIndex);     
           }
       }
       
       //If all the connections can't be occupied return -1
       if (finalElements.size() > k) {
           return -1;
       }
       
       //Take sum of the connected elements
       for(Integer element : finalElements) {
           result = result + magicPower[element];
       }
       
       //Take remianing from the new diconnected atoms
       int count =  finalElements.size();
       int maxIndex = 0;
       while (count < k) {
           for (int i=0; i<length; i++) {
               if (!finalElements.contains(i) && magicPower[i] > magicPower[maxIndex]) {
                   maxIndex = i;
               }
           }
           finalElements.add(maxIndex);
           result += magicPower[maxIndex];
           count++;
       }
      
       return result;
   }
}