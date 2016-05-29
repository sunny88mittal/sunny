package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchedulingAlgorithm {

    public static void main (String args[]) throws IOException {
    	List<Job> jobs =  readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 1\\Assignment\\jobs.txt");
    	
    	Collections.sort(jobs);
    	
    	long time=0;
    	long totalweight = 0;
    	for (Job job : jobs) {
    		long endTime = time +  job.length;
    		totalweight += job.weight * endTime;
    		time += job.length;  
    	}
    	
    	System.out.println("Total weight is:" + totalweight);
    }
    
	/*private static class Job implements Comparable<Job>{
		int weight;
		int length;
		
		Job (int weight, int length) {
			this.weight = weight;
			this.length = length;
		}

		@Override
		public int compareTo(Job o) {
			Float r1= (float)weight / (float)length;
			Float r2 = (float)o.weight/(float)o.length;
			return  r2.compareTo(r1);
		}
	}*/
    
	private static class Job implements Comparable<Job> {
		int weight;
		int length;

		Job(int weight, int length) {
			this.weight = weight;
			this.length = length;
		}

		@Override
		public int compareTo(Job o) {
			Integer d1 = weight - length;
			Integer d2 = o.weight - o.length;
			if (d1 == d2) {
				return ((Integer)o.weight).compareTo((Integer)weight);
			}
			return d2.compareTo(d1);
		}
	}
	
	public static List<Job> readFile (String fileLocation) throws IOException {
		List<Job> jobs = new ArrayList<Job>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			String line = in.readLine();
			while (line != null) {
				String[] tokens = line.split(" ");
				int weight = Integer.parseInt(tokens[0]);
				int length = Integer.parseInt(tokens[1]);
				jobs.add(new Job(weight, length));
				line = in.readLine();
			}
			return jobs;	
		} finally {
		    if (in != null) {
		    	in.close();
		    }
		}
	}
}
