package chapter_1;

public class SY7 {
	public static void main(String[] args) {
		Triangle tr = new Triangle(3, 4, 5);
		Circular cr = new Circular(3);

		tr.perimeter();
		tr.graphicType();

		cr.perimeter();
		cr.graphicType();
	}

}

abstract class Shape {

	abstract void perimeter();

	abstract void graphicType();
}

class Triangle extends Shape {
	int r1, r2, r3;

	public Triangle(int r1, int r2, int r3) {
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
	}

	void graphicType() {
		System.out.println("三角形");
	}

	void perimeter() {
		System.out.println("周长；" + (r1 + r2 + r3));
	}
}

class Circular extends Shape {
	static double PI = 3.14;
	int r;

	public Circular(int r) {
		this.r = r;
	}

	void graphicType() {
		System.out.println("圆形");
	}

	void perimeter() {
		System.out.println("周长：" + 2 * PI * r);
	}
}