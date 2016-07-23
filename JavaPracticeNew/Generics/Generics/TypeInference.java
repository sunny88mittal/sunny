package Generics;

public class TypeInference {

	static class Box<T> {

		private T t; // T stands for "Type"

		public void set(T t) {
			this.t = t;
		}

		public T get() {
			return t;
		}
	}

	public static <U> void addBox(U u, java.util.List<Box<U>> boxes) {
		Box<U> box = new Box<>();
		box.set(u);
		boxes.add(box);
	}

	public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
		int counter = 0;
		for (Box<U> box : boxes) {
			U boxContents = box.get();
			System.out.println("Box #" + counter + " contains ["
					+ boxContents.toString() + "]");
			counter++;
		}
	}

	public static void main(String[] args) {
		java.util.ArrayList<Box<Integer>> listOfIntegerBoxes = new java.util.ArrayList<>();
		TypeInference.<Integer> addBox(Integer.valueOf(10), listOfIntegerBoxes); //Type given explicitly
		TypeInference.addBox(Integer.valueOf(20), listOfIntegerBoxes);//Type inference by compiler
		TypeInference.addBox(Integer.valueOf(30), listOfIntegerBoxes);//Type inference by compiler
		TypeInference.outputBoxes(listOfIntegerBoxes);
	}
}
