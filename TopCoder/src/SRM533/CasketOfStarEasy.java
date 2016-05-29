package SRM533;

import java.util.ArrayList;
import java.util.List;

public class CasketOfStarEasy{
  public int maxEnergy(int[] weight){
     List<Integer> processed = new ArrayList<Integer>();
     int length = weight.length;
     for(int i=0; i<length; i++){
        processed.add(weight[i]);
     }
     int max = 0;
     int sum = 0;
     int toRemove = 0;
     length = length -1;
     
     while(true){
        for(int i=1; i<length; i++){
            int temp =  processed.get(i-1) * processed.get(i+1);
            if(temp > max){
               max = temp;
               toRemove = i;
            }
        }
        sum = sum + max;
        processed.remove(toRemove);
        max = 0;
        toRemove = 0;
        length = processed.size() - 1;
        if(length == 1){
           break;
        }
     }
     return sum;
  }
}

//Failed System test