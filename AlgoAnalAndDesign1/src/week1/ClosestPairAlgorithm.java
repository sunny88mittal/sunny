package week1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClosestPairAlgorithm {

	public static void main (String args[]) {
		List<Point> Px = getDataSet();
		Collections.sort(Px, new XComparator());
		
		List<Point> Py = getDataSet();
		Collections.sort(Py, new YComparator());
		
		Point[] closestPoints = closestPair(Px, Py);
		
		System.out.println("Closest Pair is:" + closestPoints[0] + "," + closestPoints[1]);
		
		System.out.println("Closest Pair distance is:" + calcEuclidDistance(closestPoints[0], closestPoints[1]));
	}
	
	private static Point[] closestPair (List<Point> Px, List<Point> Py) {
		//Base Case for Divide And Conquer
		int size = Px.size();
		if (size < 4) {
			Point [] points = new Point[2];
			points[0] = Px.get(0);
			points[1] = Px.get(1);
			float distance = calcEuclidDistance(points[0], points[1]);
			for (int i=0; i<size; i++) {
				for (int j=i+1; j<size; j++) {
					float newDistance = calcEuclidDistance(Px.get(i), Px.get(j));
					if (distance < newDistance) {
						distance = newDistance;
						points[0] = Px.get(i);
						points[1] = Px.get(j);	
					}
				}
			}
			return points;
		}
		
		//Split the points into two parts 
		Point[] finalResult;
		int mid = size/2;
		
		//Get the result for Points in left half
		List<Point> Qx = Px.subList(0, mid);
		List<Point> Qy = getPyforPx(Qx, Py);
		Point[] leftResult = closestPair(Qx, Qy);
		float dLeft = calcEuclidDistance(leftResult[0], leftResult[1]);
		
		//Get the result for Points in right half
		List<Point> Rx = Px.subList(mid, size);
		List<Point> Ry = getPyforPx(Rx, Py);
		Point[] rightResult = closestPair(Rx, Ry);
		float dRight = calcEuclidDistance(rightResult[0], rightResult[1]);
		
		//Merge Phase
		Point[] combinedResult;
		if (dLeft < dRight) {
			finalResult = leftResult;
			combinedResult = findClosestPairCombine (Px,Py,dLeft);
			if (combinedResult != null) {
				finalResult = combinedResult;
			}
		} else {
			finalResult = rightResult;
			combinedResult = findClosestPairCombine (Px,Py,dLeft);
			if (combinedResult != null) {
				finalResult = combinedResult;
			}
		}
		
		return finalResult;
	}
	
	private static Point[] findClosestPairCombine (List<Point> Px, List<Point> Py, float minDistance) {
		Point[] smallest = null;
		int size = Px.size();
		int delta = (int) Math.ceil(minDistance);
		
		//Calculate the boundary for x
		int xMid = Px.get(size/2 - 1).x;
		int leftMostX = -delta + xMid;
		int rightMostX = delta + xMid;
		
		//Get points in range -minDistance+x, x+midDistance
		List<Point> pointsInStrip = new ArrayList<Point>();
		for (Point point : Py) {
			if (leftMostX <= point.x && point.x <= rightMostX) {
				pointsInStrip.add(point);
			}
		}
		
		//Find if points across the border have distance smaller than minDsiatnce
		int stripSzie = pointsInStrip.size();
		for (int i=0; i<(stripSzie-1); i++) {
			for (int j=1; j<Math.min(7, stripSzie-1); j++) {
				Point p = pointsInStrip.get(i);
				Point q = pointsInStrip.get(i+j);
				float distance = calcEuclidDistance(p, q);
				if (distance < minDistance) {
					minDistance = distance;
					smallest = new Point[2];
					smallest[0] = p;
					smallest[1] = q;
				}
			}
		}
		
		return smallest;
	}
	
	private static List<Point> getPyforPx (List<Point> Px, List<Point> Py) {
		List<Point> Y = new ArrayList<Point>();
		int maxX = Px.get(Px.size() - 1).x;
		for (Point point : Py) {
			if (point.x <= maxX) {
				Y.add(point);
			}
		}
		
		return Y;
	}
	
	private static float calcEuclidDistance (Point a, Point b) {
		int xDistance = b.x - a.x;
		int yDistance = b.y - b.x;
		float distance = (float) Math.sqrt(xDistance*xDistance + yDistance*yDistance);
		return distance;
	}
	
	private static List<Point> getDataSet() {
		List<Point>points = new ArrayList<Point>();
		points.add(new Point(2,3));
		points.add(new Point(12,30));
		points.add(new Point(40,50));
		points.add(new Point(5,1));
		points.add(new Point(12,10));
		points.add(new Point(3,4));
		return points;
	}
}





