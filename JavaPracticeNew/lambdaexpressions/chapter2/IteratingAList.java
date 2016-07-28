package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class IteratingAList {

	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

		//For each using Consumer function
		friends.forEach(new Consumer<String>() {
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		//For each using lambda expression
		//Marking the name as immutable to maintain the immutability principle
		friends.forEach((final String name) -> System.out.println(name));
		
		//For each using lambda expression
		//Type inferred by the compiler but name will be mutable
	    friends.forEach((name) -> System.out.println(name));
	    
	    //For each using lambda expression
  		//Braces can be omitted for single paramter case
  	    friends.forEach(name -> System.out.println(name));
  	    
  	    //Even more concise code
  	    friends.forEach(System.out :: println);
	}
}
