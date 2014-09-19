package SRM622;

import org.testng.annotations.Test;

public class FibonacciDiv2Test {
  
  @Test
  public void testFibonacciDiv2() {
      FibonacciDiv2 fibonacciDiv2 = new FibonacciDiv2();
      
      assert fibonacciDiv2.find(1) == 0;
      assert fibonacciDiv2.find(13) == 0;
      assert fibonacciDiv2.find(15) == 2;
      assert fibonacciDiv2.find(19) == 2;
      assert fibonacciDiv2.find(1000000) == 167960;
  }

}
