package SRM623;

import org.testng.annotations.Test;

public class UniformBoardTest {
	@Test
	public void f() {
		UniformBoard obj = new UniformBoard();

		assert obj.getBoard(new String[] { "AP", ".A" }, 0) == 1;

		assert obj.getBoard(new String[] { "AP", ".A" }, 1) == 2;

		assert obj.getBoard(new String[] { "PPP", "APA", "A.P" }, 2) == 3;

		assert obj.getBoard(new String[] { "AAA", "PPP", "AAA" }, 10) == 3;

		assert obj.getBoard(new String[] { "." }, 1000) == 0;

		assert obj.getBoard(new String[] { "PPAAPA..AP", "PPA.APAP..",
				"..P.AA.PPP", "P.P..APAA.", "P.P..P.APA", "PPA..AP.AA",
				"APP..AAPAA", "P.P.AP...P", ".P.A.PAPPA", "..PAPAP..P" }, 10) == 15;
	}
}
