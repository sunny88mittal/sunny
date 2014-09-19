/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BranchAndBound;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author sunny
 */
public class TravellingSalesmanProblem {
     private static List<List<GraphNode>> graph = new ArrayList<List<GraphNode>>(5);
     private static Queue<TourNode> tourNodesQueue = new PriorityQueue<TourNode>();
     private static int globalLowerBound = -1;
     private static TourNode finalTour = null;
     
     public static void main(String args[]){
         createGraph(graph);
         printGraph(graph);
         TourNode initalNode = new TourNode();
         initalNode.lowerBound = computeLowerBound(graph, null);;
         initalNode.nodes.add(0);
         tourNodesQueue.add(initalNode);
         
         while(!tourNodesQueue.isEmpty()){
             generateNodes();
         }
         
         System.out.println(finalTour);
     }
     
     private static void generateNodes(){
         TourNode tourNode = tourNodesQueue.remove();
         int lastVertex = tourNode.nodes.get(tourNode.nodes.size()-1);
         List<GraphNode> graphNodes = graph.get(lastVertex);
         
         //Prune the nodes with bound greater than global lower bound
         if(globalLowerBound ==-1 || tourNode.lowerBound < globalLowerBound){
             //We just need to return to the original point now
             if(tourNode.nodes.size() == graph.size()){
                 tourNode.pathLength += graphNodes.get(0).value;
                 tourNode.nodes.add(0);
                 globalLowerBound = tourNode.pathLength;
                 finalTour = tourNode;
             }else{
                 for(GraphNode graphNode : graphNodes){
                     int vertex = graphNode.vertex;
                     if(!tourNode.nodes.contains(vertex)){
                         int pathLength = graphNode.value;
                         TourNode tempTourNode = new TourNode();
                         tempTourNode.pathLength = tourNode.pathLength + pathLength;
                         tempTourNode.nodes.addAll(tourNode.nodes);
                         tempTourNode.nodes.add(vertex);
                         tempTourNode.lowerBound = computeLowerBound(graph, tempTourNode);
                         tourNodesQueue.add(tempTourNode);
                     }
                 }  
             }
         }
     }
     
     private static void createGraph(List<List<GraphNode>> graph){
         /////////////////////////////////////
         List<GraphNode> list = new LinkedList<GraphNode>();
         GraphNode node1 = new GraphNode(1,14);
         GraphNode node2 = new GraphNode(2,4);
         GraphNode node3 = new GraphNode(3,10);
         GraphNode node4 = new GraphNode(4,20);
         list.add(node1);
         list.add(node2);
         list.add(node3);
         list.add(node4);
         graph.add(list);
         
         ////////////////////////////////////////
         list = new LinkedList<GraphNode>();
         node1 = new GraphNode(0,14);
         node2 = new GraphNode(2,7);
         node3 = new GraphNode(3,8);
         node4 = new GraphNode(4,7);
         list.add(node1);
         list.add(node2);
         list.add(node3);
         list.add(node4);
         graph.add(list);
         
         ////////////////////////////////////////
         list = new LinkedList<GraphNode>();
         node1 = new GraphNode(0,4);
         node2 = new GraphNode(1,5);
         node3 = new GraphNode(3,8);
         node4 = new GraphNode(4,7);
         list.add(node1);
         list.add(node2);
         list.add(node3);
         list.add(node4);
         graph.add(list);
         
         ////////////////////////////////////////
         list = new LinkedList<GraphNode>();
         node1 = new GraphNode(0,11);
         node2 = new GraphNode(1,7);
         node3 = new GraphNode(2,9);
         node4 = new GraphNode(4,2);
         list.add(node1);
         list.add(node2);
         list.add(node3);
         list.add(node4);
         graph.add(list);
         
         ////////////////////////////////////////
         list = new LinkedList<GraphNode>();
         node1 = new GraphNode(0,18);
         node2 = new GraphNode(1,7);
         node3 = new GraphNode(2,17);
         node4 = new GraphNode(3,4);
         list.add(node1);
         list.add(node2);
         list.add(node3);
         list.add(node4);
         graph.add(list);
     } 
     
     private static void printGraph(List<List<GraphNode>> graph){
         for(List<GraphNode> list : graph){
             String str = "";
             for(GraphNode node : list){
                 str = str + node + " ";
             }
             System.out.println(str);
         }
     }
     
     private static int computeLowerBound(List<List<GraphNode>> graph, TourNode tourNode){
         int lowerBound = 0;
         int length = graph.size();
         if(null != tourNode){
             lowerBound = tourNode.pathLength;
         }
         for(int i=0; i<length; i++){
             if(null == tourNode || !tourNode.nodes.contains(i) || i== tourNode.nodes.get(tourNode.nodes.size()-1)){
                 int minValue = 999999999;
                 List<GraphNode> list = graph.get(i);
                 for(GraphNode node : list){
                     if(node.value < minValue){
                         minValue = node.value;
                     }
                 }
                 lowerBound = lowerBound + minValue;
             }
         }
         return lowerBound;
     }
}
