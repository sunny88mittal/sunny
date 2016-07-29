package chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImplementingComparator {

	public static void main(String args[]) {
		final List<Person> people = Arrays.asList(new Person("John", 20), 
				new Person("Sara", 21),
				new Person("Jane", 21), 
				new Person("Greg", 35));
		
		List<Person> ascendingAge =
				people.stream()
				.sorted((person1, person2) -> person1.ageDifference(person2)) //Lmabda Expression form
				.collect(Collectors.toList());
		
		
		ascendingAge =
				people.stream()
				.sorted(Person :: ageDifference) //Method reference
				.collect(Collectors.toList());
		
		System.out.println(ascendingAge);
	}
}
