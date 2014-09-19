/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BranchAndBound;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sunny
 */
public class TourNode implements Comparable<TourNode> {
    public Integer lowerBound = 0;
    public Integer pathLength = 0;
    public List<Integer> nodes = new ArrayList<Integer>();

    @Override
    public int compareTo(TourNode o) {
        return lowerBound.compareTo(o.lowerBound);
    }
    
    @Override
    public String toString(){
        String nodesString = "";
        for(Integer node: nodes){
            nodesString = nodesString + (node + 1) + ",";
        } 
        return nodesString + "[" + pathLength + "]";
    }
}
