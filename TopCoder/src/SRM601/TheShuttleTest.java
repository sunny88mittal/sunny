package SRM601;

import org.testng.annotations.Test;

public class TheShuttleTest {
  @Test
  public void f() {
	  TheShuttles theShuttles = new TheShuttles();
	  
	  assert theShuttles.getLeastCost(new int[] {9},30, 5) == 75;
	  
	  assert theShuttles.getLeastCost(new int[] {9,4},30, 5) == 150;
	  
	  assert theShuttles.getLeastCost(new int[] {9,4},10, 5) == 105;
	  
	  assert theShuttles.getLeastCost(new int[] {51, 1, 77, 14, 17, 10, 80},32,40) == 12096;
  }
}
