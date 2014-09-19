package SRM622;

import org.testng.annotations.Test;

public class BoxesDiv2Test {
  @Test
  public void testBoxesDiv2() {
	  BoxesDiv2 boxesDiv2 = new BoxesDiv2();
	  assert boxesDiv2.findSize(new int[] {8,8}) == 16;
	  assert boxesDiv2.findSize(new int[] {5,6}) == 16;
	  assert boxesDiv2.findSize(new int[] {1,7}) == 16;
	  assert boxesDiv2.findSize(new int[] {1,1,13,1,1}) == 32;
	  assert boxesDiv2.findSize(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32}) == 1024;
  }
}
