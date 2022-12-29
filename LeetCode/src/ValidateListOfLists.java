import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateListOfLists {

	public static void main (String args[]) {
		List<List<Character>> listOfLists =  new ArrayList<List<Character>>();
		
		List<Character> list1 = Arrays.asList('a');
		List<Character> list2 = Arrays.asList('b','c');
		List<Character> list3 = Arrays.asList('d','e', 'f');
		List<Character> list4 = Arrays.asList('g','h', 'i');
		List<Character> list5 = Arrays.asList('j','k');
		
		listOfLists.add(list1);
		listOfLists.add(list2);
		listOfLists.add(list3);
		listOfLists.add(list4);
		listOfLists.add(list5);
		
		System.out.println(listOfLists);
	}
}
