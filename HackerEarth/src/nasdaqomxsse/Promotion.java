package nasdaqomxsse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Promotion {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String tokens[] = line.split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);

		int[] boxes = new int[N];
		int[] trucks = new int[M];
		int[] jobsTruckHandles = new int[M];

		String[] sboxes = br.readLine().split(" ");
		String[] strucks = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			boxes[i] = Integer.parseInt(sboxes[i]);
		}

		for (int i = 0; i < M; i++) {
			trucks[i] = Integer.parseInt(strucks[i]);
		}

		Arrays.sort(boxes);
		Arrays.sort(trucks);

		for (int i = N - 1; i >= 0; i--) {
			int minIndex = -1;
			for (int j = M - 1; j >= 0; j--) {
				if (boxes[i] <= trucks[j]) {
					if (minIndex == -1) {
						minIndex = j;
					} else if (jobsTruckHandles[j] < jobsTruckHandles[minIndex]) {
						minIndex = j;
					}
				}
			}
			jobsTruckHandles[minIndex] = jobsTruckHandles[minIndex] + 1;
		}
		
		int maxJobs = -1;
		for (int i=0 ;i<M; i++) {
			if (jobsTruckHandles[i] > maxJobs) {
				maxJobs = jobsTruckHandles[i];
			}
		}
		
		System.out.println(2*maxJobs-1);
	}
}
