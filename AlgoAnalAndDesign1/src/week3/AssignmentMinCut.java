package week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssignmentMinCut {

	public static void main (String args[]) throws IOException, InterruptedException {
		short length = 200;
		List<AdjListNode> adjList = readGraph("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 3\\ProgrammingAssignment\\kargerMinCut.txt", length);
		//List<AdjListNode> adjList = readGraph("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 3\\ProgrammingAssignment\\Testing.txt", length);
		System.out.println("Read the graph");
		short minCut = getMinCut(adjList, length);
		System.out.println("Final Min cut is:" +  minCut);
	}
	
	
	private static short getMinCut (List<AdjListNode> adjList, short length) {
		short noOfTimes = (short) (length * Math.log(length));
		short minCut = -1;
		for (short i=0; i<noOfTimes; i++) {
			//System.out.println("In iteration:" + i+1);
			List<AdjListNode> adjListCopy = new ArrayList<AdjListNode>();
			for (AdjListNode adjListNode : adjList) {
				AdjListNode newAdjListNode = new AdjListNode(adjListNode.start);
				List<EndPointWithCount> endPointsList = newAdjListNode.endPoints;
				for (EndPointWithCount edgeWithCount : adjListNode.endPoints) {
					endPointsList.add(new EndPointWithCount(edgeWithCount.endPoint));
				}
				adjListCopy.add(newAdjListNode);
			}
			
			short size = (short) adjListCopy.size();  
			Random randoma = new Random();
			Random randomb = new Random(System.currentTimeMillis()/2);
			while (size > 2) {
				//Select the u
				short randomNumA = (short) randoma.nextInt(size);
				AdjListNode adjListNodeu = adjListCopy.get(randomNumA);
				
				//select the v
				List<EndPointWithCount> endPointsListu = adjListNodeu.endPoints;
				short randomNumB = (short) randomb.nextInt(endPointsListu.size());
				EndPointWithCount endPointWithCount = endPointsListu.get(randomNumB);
				short v = endPointWithCount.endPoint;
				
				//System.out.println("Selected " + adjListNodeu.start + ":" + v);
				
				//Get the adjacency List Node for v
				AdjListNode adjListNodev = null;
				for (AdjListNode adjListNode : adjListCopy) {
					if (adjListNode.start == v) {
						adjListNodev =  adjListNode;
						break;
					}
				}
				
				//Remove the selected vertex from the graph
				adjListCopy.remove(adjListNodev);
				//Remove the link of selected vertex to u 
				endPointsListu.remove(endPointWithCount);
				
				/*Shift all the edges of v to u
				  Update the count for existing edges
				  Add the new ones
				*/
				List<EndPointWithCount> endPointsListv = adjListNodev.endPoints;
	            for (EndPointWithCount endPointOfv : endPointsListv) {
	            	if (endPointOfv.endPoint != adjListNodeu.start) {
	            		boolean updated = false;
	                	for (EndPointWithCount endPointOfu : endPointsListu) {
	                		if (endPointOfv.endPoint == endPointOfu.endPoint) {
	                			//Update count in A
	                			endPointOfu.count += endPointOfv.count;
	                			updated = true;
	                			break;
	                		}
	                	}
	                	//Add the new Edge
	                	if(!updated) {
	                		endPointsListu.add(new EndPointWithCount(endPointOfv.endPoint, endPointOfv.count));	
	                	}	
	            	}
	            }
			
	            //Update the vertices which were connected to v
	            for (EndPointWithCount iendPointWithCount : endPointsListv){
	            	short endPointValue = iendPointWithCount.endPoint;
	            	if (endPointValue != adjListNodeu.start) {
	            		for (AdjListNode adjListNode : adjListCopy) {
	            			if (adjListNode.start == endPointValue) {
	            				EndPointWithCount endPointConnectntou = null;
	            				EndPointWithCount endPointConnectntov = null;
	            				for (EndPointWithCount jendPoint : adjListNode.endPoints) {
	            					if (jendPoint.endPoint == adjListNodeu.start) {
	            						endPointConnectntou = jendPoint;
	            					}
	            					
	            					if (jendPoint.endPoint == adjListNodev.start) {
	            						endPointConnectntov = jendPoint;
	            					}
	            				}
	            				
	            				if (endPointConnectntou != null) {
	            					endPointConnectntou.count += endPointConnectntov.count;
	            				} else {
	            					adjListNode.endPoints.add(new EndPointWithCount(adjListNodeu.start, endPointConnectntov.count));
	            				}
	            				
	            				adjListNode.endPoints.remove(endPointConnectntov);
	            			}
	            		}
	            	}
	            }
	            		
				//Update no of vertices
				size = (short) adjListCopy.size(); 
				
				//System.out.println("Size is:" + size);            
	            //printGraph (adjListCopy);
			}
			
			short newMinCut = adjListCopy.get(0).endPoints.get(0).count;
			//System.out.println("Min Cut is:" +  newMinCut);
			if (minCut == -1 || newMinCut < minCut) {
				minCut = newMinCut;
			}	
		}
		return minCut;
	}
	
	private static void printGraph(List<AdjListNode> adjList) {
		for (AdjListNode adjListNode : adjList) {
			System.out.println(adjListNode.start);
			for (EndPointWithCount edgeWithCount : adjListNode.endPoints) {
				System.out.print("\t" + edgeWithCount.endPoint);
			}
			System.out.println();
		}
	}
	
	private static List<AdjListNode> readGraph(String file, short size) throws IOException {
		List<AdjListNode> adjList = new ArrayList<AdjListNode>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			short i = 1;
			while (line != null) {
				String[] numbers = line.split("\t");
				short length = (short) numbers.length;
				AdjListNode node = new AdjListNode(i);
				List<EndPointWithCount> vs = node.endPoints;
				for (short j=1; j<length; j++) {
					short number = Short.parseShort(numbers[j].trim());
					vs.add(new EndPointWithCount(number));
				}
				adjList.add(node);
				line = in.readLine();
				i++;
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return adjList;
	}
}
