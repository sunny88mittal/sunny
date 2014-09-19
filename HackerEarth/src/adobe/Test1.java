package adobe;

import java.util.List;

import java.util.ArrayList;

public class Test1 {

	public static void main(String[] arguments) {

		List<Integer> myList = new ArrayList<Integer>();

		myList.add(25);

		myList.add(35);

		List anotherList = myList;

		anotherList.add("Hello World");

		for (Object o : anotherList)

			System.out.println(o);

	}

}