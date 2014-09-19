/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BranchAndBound;

/**
 *
 * @author sunny
 */
public class GraphNode {
    public int vertex;
    public int value;
    
    public GraphNode(int vertex, int value){
        this.vertex = vertex;
        this.value = value;
    }
    
    @Override
    public String toString(){
        return "[" + vertex + "," + value + "]";
    }
}
