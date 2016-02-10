package SRM468;

public class RoadOrFlightHard {

	private long[] roadTime;

	private long[] flightTime;

	private int N;

	public long minTime(int N, int roadFirst, int roadProd, int roadAdd, int roadMod, int flightFirst, int flightProd,
			int flightAdd, int flightMod, int K) {
		this.N = N;

		roadTime = new long[N];
		roadTime[0] = roadFirst % roadMod;
		for (int i = 1; i < N; i++) {
			roadTime[i] = (roadTime[i - 1] * roadProd + roadAdd) % roadMod;
		}

		flightTime = new long[N];
		flightTime[0] = flightFirst % flightMod;
		for (int i = 1; i < N; i++) {
			flightTime[i] = (flightTime[i - 1] * flightProd + flightAdd) % flightMod;
		}

		long minByRoad = solve(0, K, false);
		long minByFlight = solve(0, K - 1, true);

		return Math.min(minByRoad, minByFlight);
	}

	private long solve(int i, int k, boolean flying) {
		long flyingMin = Long.MAX_VALUE;
		long roadMin = Long.MAX_VALUE;
		if (i == N) {
			return 0;
		}

		if (k < 0) {
			return Long.MAX_VALUE;
		}

		if (flying) {
			flyingMin = flightTime[i] + Math.min(solve(i + 1, k, false), solve(i + 1, k, true));
		} else {
			roadMin = roadTime[i] + Math.min(solve(i + 1, k, false), solve(i + 1, k - 1, true));
		}

		return Math.min(flyingMin, roadMin);
	}
	
	public static void main(String args[]) {
		RoadOrFlightHard obj = new RoadOrFlightHard();
		obj.minTime(364022, 49002, 93182, 68992, 24256, 6455, 42349, 78059, 3457, 37);
	}
}