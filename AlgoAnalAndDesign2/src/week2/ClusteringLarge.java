package week2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClusteringLarge {

	public String filePath;
	
	public Map<Integer, List<ClusterPoint>> pointsMap = new HashMap<Integer, List<ClusterPoint>>();
	
	public ClusteringLarge (String filePath) {
		this.filePath = filePath;
	}
	
	public static void main (String args[]) throws IOException {
		ClusteringLarge clusteringLarge = new ClusteringLarge("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 2\\Assignment\\clustering_big.txt");
		clusteringLarge.getPointsList();
		Collection<List<ClusterPoint>> clusterPointsList = clusteringLarge.pointsMap.values();
		
	}
	
	private void getPointsList() throws IOException {
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(filePath));
		String line = in.readLine();
		while (line != null) {
			ClusterPoint clusterPoint = new ClusterPoint(line);
			int bits = getBits(line);
			List<ClusterPoint> pointsList = pointsMap.get(bits);
			if (pointsList == null) {
				pointsList = new ArrayList<ClusterPoint>();
			}
			pointsList.add(clusterPoint);
			line = in.readLine();
		}
		in.close();
	}
	
	private int getBitsDifference (String s1, String s2) {
		int diff =0;
		char [] chara = s1.toCharArray();
		char [] charb = s2.toCharArray();
		int length = chara.length;
		for (int i=0; i<length; i++) {
			if (chara[i] != charb[i]) {
				diff++;
			}
		}
		return diff;	
	}
	
	private int getBits (String str) {
		int bits = 0;
		char[] chars = str.toCharArray();
		for (char ch : chars) {
			if (ch == '1') {
				bits++;
			}
		}
		return bits;
	}
	
	private static class ClusterPoint {
		public String point;
		
		public ClusterPoint parent;
		
		public ClusterPoint (String point) {
			this.point = point;
			this.parent = this;
		}
	}
	
}

