package SRM624;

import org.testng.annotations.Test;

public class GameOfSegmentsTest {
  @Test
  public void f() {
	  GameOfSegments obj = new GameOfSegments();
	  assert obj.winner(3) == 1;
	  assert obj.winner(4) == 1;
	  assert obj.winner(15) == 2;
	  assert obj.winner(191) == 2;
  }
}
