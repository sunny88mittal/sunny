package SRM570;

//http://community.topcoder.com/stat?c=problem_statement&pm=12425&rd=15490
public class RobotHerbDiv2 {

	public int getdist(int T, int[] a){
	       int initialX = 0;
	       int initialY = 0;
	       int initialFace = 0; //0 + ve y axis, 1 + x axis, 2 -ve y axis, 3 -ve x axis
	       for (int i=0; i<T; i++) {
	           for (int j=0; j<a.length; j++){
	               int times = a[j];
	               switch (initialFace){
	                  case 0:
	                   initialY = initialY + times;
	                   break;
	                  case 1:
	                   initialX = initialX + times;     
	                   break;
	                  case 2:
	                   initialY = initialY - times;
	                   break;
	                  case 3:
	                   initialX = initialX - times;     
	                   break;
	               }
	               initialFace = (initialFace + times) % 4; 
	           }
	       }
	       int dist = 0;
	       
	       dist = Math.abs(initialX) + Math.abs(initialY);
	       return dist;
	} 
}
