package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindingElement {

	//Collect names starting with N
	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		
		//Using lambda expressions
		final List<String> startsWitN = 
				friends.stream()
				       .filter(name -> name.startsWith("N"))
				       .collect(Collectors.toList());
		System.out.println(startsWitN);
	}
}
