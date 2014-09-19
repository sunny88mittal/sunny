package SRM623;

import org.testng.annotations.Test;

public class CatAndRatTest {
	@Test
	public void catAndRatTest() {
		CatAndRat catAndRatTest = new CatAndRat();
		assert catAndRatTest.getTime(10, 1, 1, 1) == -1.0;
		assert catAndRatTest.getTime(10, 1, 1, 2) == 1.0;
		assert catAndRatTest.getTime(10, 1, 2, 1) == -1.0;
		assert catAndRatTest.getTime(1000, 1000, 1, 1000) == 1.001001001001001;
		assert catAndRatTest.getTime(1, 1000, 1, 2) == 3.141592653589793;
		
		assert catAndRatTest.getTime(3, 3, 3, 4) == 9.0;
		assert catAndRatTest.getTime(1000, 1000, 3, 4) == 3000.0;
		assert catAndRatTest.getTime(100, 1, 314, 315) == 314.0;
	}
}
