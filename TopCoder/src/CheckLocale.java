import java.util.Locale;


public class CheckLocale {
	
	public static void main (String args[]) {
		System.out.println(System.getProperty("user.country")); 
		System.out.println(System.getProperty("user.language"));
		System.out.println(Locale.getDefault());
	}

}
