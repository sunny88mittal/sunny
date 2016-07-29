package chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultipleComparisons {

	public static void main(String args[]) {
		final List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21),
				new Person("Jane", 21), new Person("Greg", 35));

		final Function<Person, Integer> byAge = person -> person.getAge();
		final Function<Person, String> byTheirName = person -> person.getName();
		List<Person> ageAndName = people.stream().
				sorted(Comparator.comparing(byAge).thenComparing(byTheirName)).collect(Collectors.toList());
        System.out.println(ageAndName);
	}
}
