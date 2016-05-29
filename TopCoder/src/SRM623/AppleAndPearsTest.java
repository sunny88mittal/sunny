package SRM623;

import org.testng.annotations.Test;

public class AppleAndPearsTest {
  @Test
  public void appleAndPearsTest() {
	  ApplesAndPears appleAndPears = new ApplesAndPears();
	  
	  String[] board = new String[] {".A","P."};
	  assert appleAndPears.getArea(board, 0) == 1;
	  
	  board = new String[] {".A","P."};
	  assert appleAndPears.getArea(board, 1) == 2;
	  
	  board = new String[] 	{".PP", 
			                 "PPA", 
			                 "PAP"};
	  assert appleAndPears.getArea(board, 3) == 6;
	  
	  board = new String[] {"A.P.PAAPPA",
			  				"PPP..P.PPP",
			  				"AAP.A.PAPA",
			  				"P.PA.AAA.A",
			  				"...PA.P.PA",
			  				"P..A.A.P..",
			  				"PAAP..A.A.",
			  				"PAAPPA.APA",
			  				".P.AP.P.AA",
			  				"..APAPAA.."};
	  assert appleAndPears.getArea(board, 10) == 21;
  }
}
