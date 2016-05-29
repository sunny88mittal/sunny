package SRM666;

public class DevuAndGame {

	public String canWin(int[] nextLevel) {
		int location = 0;
		int temp = location;
		while (true) {
			if (location == -1) {
				return "Win";
			}
			
			if (location == -2) {
				break;
			}
			
			temp = location;
			location = nextLevel[location];
			nextLevel[temp] = -2;
		}
		
		return "Lose";
	}
	
	public static void main (String args[]) {
		DevuAndGame obj = new DevuAndGame();
		System.out.println(obj.canWin(new int[]{1, -1}));
		System.out.println(obj.canWin(new int[]{1, 0, -1}));
		System.out.println(obj.canWin(new int[]{0, 1, 2}));
		System.out.println(obj.canWin(new int[]{29,33,28,16,-1,11,10,14,6,31,7,35,34,8,15,17,26,12,13,22,1,20,2,21,-1,5,19,9,18,4,25,32,3,30,23,10,27}));
		System.out.println(obj.canWin(new int[]	{17,43,20,41,42,15,18,35,-1,31,7,33,23,33,-1,-1,0,33,19,12,42,-1,-1,9,9,-1,39,-1,31,46,-1,20,44,41,-1,-1,12,-1,36,-1,-1,6,47,10,2,4,1,29}));
		System.out.println(obj.canWin(new int[] {3, 1, 1, 2, -1, 4}));
	}
}
