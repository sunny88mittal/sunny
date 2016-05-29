package week2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClusteringSmall {

	private String filePath;
	
	public ClusteringSmall (String filePath) {
		this.filePath = filePath;
	}
	
	public static void main (String args[]) throws IOException {
		ClusteringSmall clusteringSmall = new ClusteringSmall("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 2\\Assignment\\clustering1.txt");
		List<PointsPair> pointsPairList = clusteringSmall.getPointsPairList();
		Collections.sort(pointsPairList);
		ClusterPoint[] clusterPoints = new ClusterPoint[501];
		int clusters = 500;
		for (int i=1; i<=clusters; i++) {
			clusterPoints[i] = new ClusterPoint(i, i);
		}
		int i=0;
		for (PointsPair pointsPair : pointsPairList) {
			int pointa = pointsPair.pointa;
			int pointb = pointsPair.pointb;
			boolean merged = clusteringSmall.mergeClusters(pointa, pointb, clusterPoints); 
			if (merged) {
				clusters--;
			}
			i++;
			if (clusters == 4) {
				break;
			}	
		}
		int pointa = pointsPairList.get(i).pointa;
		int pointb = pointsPairList.get(i).pointb;
		int distance = pointsPairList.get(i).distance;
		ClusterPoint pointaParent = clusteringSmall.find (pointa, clusterPoints);
		ClusterPoint pointbParent = clusteringSmall.find (pointb, clusterPoints);
		while (pointaParent == pointbParent) {
			i++;
			pointa = pointsPairList.get(i).pointa;
			pointb = pointsPairList.get(i).pointb;
			distance = pointsPairList.get(i).distance;
			pointaParent = clusteringSmall.find (pointa, clusterPoints);
			pointbParent = clusteringSmall.find (pointb, clusterPoints);
		}
		
		System.out.println(distance);
	}
	
	private boolean mergeClusters (int a, int b, ClusterPoint[] clusterPoints) {
		ClusterPoint pointaParent = find (a, clusterPoints);
		ClusterPoint pointbParent = find (b, clusterPoints);
		if (pointaParent == pointbParent) {
			return false;
		}
		pointbParent.parent = pointaParent.point;
		return true;
	}
	
	private ClusterPoint find (int point, ClusterPoint[] clusterPoints) {
		ClusterPoint clusterPoint =  clusterPoints[point];
		ClusterPoint parentClusterPoint =  clusterPoints[clusterPoint.parent];
		if (clusterPoint.point == parentClusterPoint.point) {
			return parentClusterPoint;
		}
		ClusterPoint finalParentClusterPoint = find (parentClusterPoint.point, clusterPoints);
		clusterPoint.parent = finalParentClusterPoint.point;
		return finalParentClusterPoint;
	}
	
	private List<PointsPair> getPointsPairList() throws IOException {
		List<PointsPair> pointsListPair = new ArrayList<PointsPair>();
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(filePath));
		String line = in.readLine();
		while (line != null) {
			String[] tokens = line.split(" ");
			int pointa = Integer.parseInt(tokens[0]);
			int pointb = Integer.parseInt(tokens[1]);
			int distance = Integer.parseInt(tokens[2]);
			pointsListPair.add(new PointsPair(pointa, pointb, distance));
			line = in.readLine();
		}
		in.close();
		return pointsListPair;
	}
	
	
	private static class ClusterPoint {
		public int point;
		
		public int parent;
		
		public ClusterPoint (int point, int parent) {
			this.point = point;
			this.parent = parent;
		}
	}
	
	private class PointsPair implements Comparable<PointsPair> {
        public int pointa;
        
        public int pointb;
        
        public Integer distance;
        
        PointsPair (int pointa, int pointb, int distance) {
        	this.pointa = pointa;
        	this.pointb = pointb;
        	this.distance = distance;
        }
        
		@Override
		public int compareTo(PointsPair o) {
			return distance.compareTo(o.distance);
		}
	}
}

