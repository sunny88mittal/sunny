package SRM145;

public class ImageDithering {
	
	public int count(String dithered, String[] screen) {
	   	int length = dithered.length();
		int count = 0;
	   	for (int i=0; i<length; i++) {
			char ch = dithered.charAt(i);
			for (int j=0; j<screen.length; j++) {
				String str = screen[i];
				for (int k=0; k<str.length(); k++) {
					char scrrenchar = str.charAt(k);
					if (scrrenchar == ch) {
						count++;
					}
				}
			}
		}
	   	return count;
	}
}
