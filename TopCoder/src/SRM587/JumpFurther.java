package SRM587;

public class JumpFurther {
	
	public int furthest(int N, int badStep) {
		int max = N*(N+1)/2;
		
		if (max >= badStep) {
			
			int discriminant = 1+8*badStep;
			
			int solution = (int) Math.sqrt(discriminant);
			
			if (solution * solution == discriminant) {
				max = max - 1;
			}
		}
		
		return max;
	}

}
