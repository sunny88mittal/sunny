package SRM532;

import java.util.Arrays;

public class DengklekTryingToSleep{
    public int minDucks(int[] ducks){
        int length = ducks.length;
        if(ducks.length == 1){
           return 0;
        }
        Arrays.sort(ducks);
        int valueDiff = ducks[length -1] - ducks[0];
        int lengthDiff = length - 2;
        int missedDucks =  valueDiff - lengthDiff - 1;
        if(missedDucks < 0){
           return 0;
        }
        return missedDucks;
    }
}