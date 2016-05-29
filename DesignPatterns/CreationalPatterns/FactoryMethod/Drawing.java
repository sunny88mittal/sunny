package FactoryMethod;

interface Shape {
	public void draw();
}

class Line implements Shape {
	int x, y;

	Line(int a, int b) {
		x = a;
		y = b;
	}

	public void draw() {
		System.out.println("Drawing a line");
	}
}

class Square implements Shape {
	int start;
	int width, height;

	Square(int s, int w, int h) {
		start = s;
		width = w;
		height = h;
	}

	public void draw() {
		System.out.println("Drawing a square");
	}
}

class Painting {

	public void draw (int a, int b) {
		new Line(a, b).draw();
	}

	public void draw (int a, int w, int h) {
		new Square(a, w, h).draw();
	}
}

public class Drawing {
     public static void main (String args[]) {
    	 Painting paintingFactory = new Painting();
    	 paintingFactory.draw(1, 2);
    	 paintingFactory.draw(1, 2, 3);
     }
}
