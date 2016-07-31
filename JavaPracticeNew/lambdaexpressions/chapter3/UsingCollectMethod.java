package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsingCollectMethod {

	public static void main (String args[]) {
		final List<Person> people = Arrays.asList(new Person("John", 20), 
				new Person("Sara", 21),
				new Person("Jane", 21), 
				new Person("Greg", 35));
		
		List<Person> olderThan20 =
				people.stream()
				.filter(person -> person.getAge() > 20)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
				System.out.println("People older than 20: " + olderThan20);
				
		List<Person> olderThan201 =
				people.stream()
				.filter(person -> person.getAge() > 20)
				.collect(Collectors.toList());
				System.out.println("People older than 20: " + olderThan201);
				
		Map<Integer, List<Person>> peopleByAge =
				people.stream()
				.collect(Collectors.groupingBy(Person::getAge));
				System.out.println("People grouped by age: " + peopleByAge);
	}
}
